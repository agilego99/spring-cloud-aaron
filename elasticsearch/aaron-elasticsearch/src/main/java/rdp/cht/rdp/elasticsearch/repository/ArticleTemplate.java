package com.cht.rdp.elasticsearch.repository;
import java.util.ArrayList;
import java.util.List;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.ResultsExtractor;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Repository;
import com.cht.rdp.elasticsearch.po.Article;
@Repository
public class ArticleTemplate {
 	
 	@Autowired
 	private ElasticsearchTemplate elasticsearchTemplate;
 	/**
 	* 簡單的模糊查詢
 	* @param title
 	* @return
 	*/
 	public List<Article> queryByTitle(String title){
 		return elasticsearchTemplate.queryForList(
 				new CriteriaQuery(Criteria.where("title").contains(title)), Article.class);
 	}
 	
 	/**
 	* 全文檢索
 	* 根據標題全文檢索，高亮顯示分詞結果
 	* @param keyword
 	* @param pageble
 	* @return
 	*/
 	public List<Article> query(String keyword) {
 		NativeSearchQueryBuilder query = buildQuery(keyword);
 		return buildResult(query);
 	}
 	/**
 	* 分頁全文查詢
 	* 根據標題全文檢索，高亮顯示分詞結果,分頁查詢
 	* @param keyword
 	* @param pageble
 	* @return
 	*/
 	public List<Article> queryByPage(String keyword, int page, int limit) {
 		NativeSearchQueryBuilder query = buildQuery(keyword);
 		query.withPageable(PageRequest.of(page, limit));
 		return buildResult(query);
 	}
 	
 	/** 
 	 * 構造查詢條件
 	 */
 	private NativeSearchQueryBuilder buildQuery(String keyword) {
 		NativeSearchQueryBuilder query = new NativeSearchQueryBuilder();
 		query.withIndices("gordianknot");
 		query.withTypes("article");
 		query.withHighlightFields(new HighlightBuilder.Field("title").preTags("<font style='color:red;'>").postTags("</font>"));
 		query.withQuery(QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("title",keyword)));
 		return query;
 	}
 	
 	/**
 	 * 建構查詢結果
 	 * @param query
 	 * @return
 	 */
 	private List<Article> buildResult(NativeSearchQueryBuilder query) {
 		return elasticsearchTemplate.query(query.build(), new ResultsExtractor<List<Article>>() {
 			@Override
 			public List<Article> extract(SearchResponse response) {
 				List<Article> list = new ArrayList<Article>();
 				for (SearchHit hit : response.getHits()) {
 					Article r = new Article();
 					r.setId(Integer.parseInt(hit.getId()));
 					r.setTitle(hit.getHighlightFields().get("title").fragments()[0].toString());
 					r.setUrl(hit.getSource().get("url").toString());
 					r.setContent(hit.getSource().get("content").toString());
 					list.add(r);
 				}
 				return list;
 			}
 		});
 	}
 	
 	
 	/**
 	* 標題檢索結果總數量
 	* @param keyword
 	* @return
 	*/
 	public Long queryTitleCount(String keyword) {
 		NativeSearchQueryBuilder query = buildQuery(keyword);
 		return elasticsearchTemplate.count(query.build());
 	}
 	
 	/**
 	* And 查詢
 	* 查詢 sid 下的標題信息，相當於 SQL 中的 select * from article where title like '%keyword%' and sid = sid
 	* @param keyword
 	* @param sid
 	* @return
 	*/
 	public List<Article> query(String keyword, String sid) {
 		NativeSearchQueryBuilder query = new NativeSearchQueryBuilder();
 		query.withIndices("gordianknot");
 		query.withTypes("article");
 		query.withHighlightFields(new HighlightBuilder.Field("title").preTags("<font style='color:red;'>").postTags("</font>"));
 		query.withQuery(
 				QueryBuilders.boolQuery()
 					.must(QueryBuilders.matchQuery("title",keyword))
 					.must(QueryBuilders.matchQuery("sid", sid))
 		);
 		return buildResult(query);
 	}
 	
 	/**
 	* OR 查詢 
 	* 查詢 sid 下的標題信息，相當於 SQL 中的 select * from article where title like '%keyword%' or sid = sid
 	* @param keyword
 	* @param sid
 	* @return
 	*/
 	public List<Article> queryByOr(String keyword, String sid) {
 		NativeSearchQueryBuilder query = new NativeSearchQueryBuilder();
 		query.withIndices("gordianknot");
 		query.withTypes("article");
 		query.withHighlightFields(new HighlightBuilder.Field("title").preTags("<font style='color:red;'>").postTags("</font>"));
 		query.withQuery(
 				QueryBuilders.boolQuery()
 					.must(QueryBuilders.matchQuery("title",keyword))
 					.should(QueryBuilders.matchQuery("sid", sid))
 		);
 		return buildResult(query);
 	}
 	
}
