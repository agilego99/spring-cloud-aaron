package com.aaron.mongodb.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aaron.mongodb.batchupdate.BathUpdateOptions;
import com.aaron.mongodb.batchupdate.MongoBaseDao;
import com.aaron.mongodb.po.Article;
import com.aaron.mongodb.repository.ArticleRepositor;
import com.mongodb.client.ListIndexesIterable;

@RestController
public class ArticleController {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private ArticleRepositor articleRepositor;
	
	// 單筆增加
	@GetMapping("/save")
	public String save() {
		// 循環添加
		for (int i = 0; i < 10; i++) {
			Article article = new Article();
			article.setTitle("MongoTemplate 的基本使用 ");
			article.setAuthor("Aaron");
			article.setUrl("https://agilego99.blogspot.com/" + i);
			article.setTags(Arrays.asList("java", "mongodb", "spring"));
			article.setVisitCount(0L);
			article.setAddTime(new Date());
			mongoTemplate.save(article);
		}
		return "success";
	}

	// 批次添加
	@GetMapping("/batchSave")
	public String batchSave() {
		// 批次添加
		List<Article> articles = new ArrayList<>(10);
		for (int i = 0; i < 10; i++) {
			Article article = new Article();
			article.setTitle("MongoTemplate 的基本使用 ");
			article.setAuthor("Aaron");
			article.setUrl("https://agilego99.blogspot.com/" + i);
			article.setTags(Arrays.asList("java", "mongodb", "spring"));
			article.setVisitCount(0L);
			article.setAddTime(new Date());
			articles.add(article);
		}
		mongoTemplate.insert(articles, Article.class);
		return "success";
	}
	
	@GetMapping("/indexList")
	public String indexList() {
		ListIndexesIterable<Document> list = mongoTemplate.getCollection("person").listIndexes();
		for (Document document : list) {
			System.out.println(document.toJson());
		}
		return "success";
	}
	
	// 修改資料
	@GetMapping("/update")
	public String update() {
		
		Query query = Query.query(Criteria.where("author").is("Aaron")); 
		Update update = Update.update("title", "MongoTemplate")
				.set("visitCount", 10); 
		// 根據條件修改
		mongoTemplate.updateFirst(query, update, Article.class);
		
		query = Query.query(Criteria.where("author").is("Aaron"));
		update = Update.update("title", "MongoTemplate").set("visitCount", 10); 
		// 修改所有符合條件的資料
		mongoTemplate.updateMulti(query, update, Article.class);
		

		query = Query.query(Criteria.where("author").is("jason"));
		update = Update.update("title", "MongoTemplate").set("visitCount", 10); 
		// 特殊更新，有則修改資料，無責添加一條資料
		mongoTemplate.upsert(query, update, Article.class);
		
		query = Query.query(Criteria.where("author").is("jason"));
		update = Update.update("title", "MongoTemplate").set("money", 100); 
		mongoTemplate.updateMulti(query, update, Article.class);
		
		query = Query.query(Criteria.where("author").is("jason"));
		// 數字界加操作
		update = Update.update("title", "MongoTemplate").inc("money", 100); 
		mongoTemplate.updateMulti(query, update, Article.class);
		
		query = Query.query(Criteria.where("author").is("jason")); 
		// 修改字串名稱
		update = Update.update("title", "MongoTemplate")
				.rename("visitCount", "vc"); 
		mongoTemplate.updateMulti(query, update, Article.class);
		
		
		query = Query.query(Criteria.where("author").is("jason"));
		// 刪除字串
		update = Update.update("title", "MongoTemplate").unset("vc"); 
		mongoTemplate.updateMulti(query, update, Article.class);
		
		query = Query.query(Criteria.where("author").is("Aaron"));
		// 刪除 Array 中的值
		update = Update.update("title", "MongoTemplate").pull("tags", "java"); 
		mongoTemplate.updateMulti(query, update, Article.class);
		return "success";
	}
	
    // 刪除資料庫操作
	@GetMapping("/delete")
	public String delete() {

		Query query = Query.query(Criteria.where("author").is("Aaron")); 
        // 按條件刪除資料庫
		mongoTemplate.remove(query, Article.class);
		
		query = Query.query(Criteria.where("author").is("Aaron"));
        // 按條件刪除資料庫並制定集合名稱
		mongoTemplate.remove(query, "article_info");
		
		query = Query.query(Criteria.where("author").is("Aaron")); 
        // 按條件刪除符合條件的第一條資料
		Article article = mongoTemplate.findAndRemove(query, Article.class);
		
		query = Query.query(Criteria.where("author").is("Aaron")); 
		// 按條件刪除符合條件的全部資料
		List<Article> articles =
				mongoTemplate.findAllAndRemove(query, Article.class);
		
		// 刪除整個集合
		mongoTemplate.dropCollection(Article.class); 

		// 刪除資料庫
		mongoTemplate.dropCollection("article_info");
		
		mongoTemplate.getDb().drop();
		return "success";
	}
	
	// 查詢資料庫操作
	@GetMapping("/query")
	public String query() {
		
		
		Query query = Query.query(Criteria.where("author").is("Aaron")); 
		// 查詢所有符合條件的資料
		List<Article> articles = mongoTemplate.find(query, Article.class);
		
		query = Query.query(Criteria.where("author").is("Aaron")); 
		// 查詢符合條件的第一條資料
		Article article = mongoTemplate.findOne(query, Article.class);
		
		// 查詢集合中的所有資料
		articles = mongoTemplate.findAll(Article.class);
		
		query = Query.query(Criteria.where("author").is("Aaron"));
        // 查詢符合條件的數量
		long count = mongoTemplate.count(query, Article.class);
		
		// 根據主鍵 ID 查詢
		article = mongoTemplate.findById(new ObjectId("57c6e1601e4735b2c306cdb7"), Article.class);
		
		List<String> authors = Arrays.asList("Aaron", "jason"); 
		// in 查詢
		query = Query.query(Criteria.where("author").in(authors));
		articles = mongoTemplate.find(query, Article.class);
		
		// 不等於查詢
		query = Query.query(Criteria.where("author").ne("Aaron"));
		articles = mongoTemplate.find(query, Article.class);
		
		// 小於查詢
		query = Query.query(Criteria.where("visitCount").lt(10)); 
		articles = mongoTemplate.find(query, Article.class);
		
		// 範圍查詢
		query = Query.query(Criteria.where("visitCount").gt(5).lt(10)); 
		articles = mongoTemplate.find(query, Article.class);
		
        // 模糊查詢
		query = Query.query(Criteria.where("author").regex("a")); 
		articles = mongoTemplate.find(query, Article.class);
		
		// Array 查詢
		query = Query.query(Criteria.where("tags").size(3)); 
		articles = mongoTemplate.find(query, Article.class);
		
		// or 查詢
		query = Query.query(Criteria.where("").orOperator( Criteria.where("author").is("jason"), Criteria.where("visitCount").is(0)));
		articles = mongoTemplate.find(query, Article.class);
		return "success";
	}
	
	@GetMapping("/findAll")
	public Object findAll() {
		return articleRepositor.findByAuthor("Aaron");
	}
	
	@GetMapping("/batchUpdate")
	public Object batchUpdate() {
		List<BathUpdateOptions> list = new ArrayList<BathUpdateOptions>();
		list.add(new BathUpdateOptions(Query.query(Criteria.where("author").is("Aaron")),Update.update("title", "批量更新"), true, true));
		list.add(new BathUpdateOptions(Query.query(Criteria.where("author").is("jason")),Update.update("title", "批量更新"), true, true));
		int n = MongoBaseDao.bathUpdate(mongoTemplate, "article_info", list, true);
		System.out.println("受影響的行数："+n);
		return n;
	}
}
