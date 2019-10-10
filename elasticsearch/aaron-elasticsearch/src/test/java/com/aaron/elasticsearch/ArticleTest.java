package com.aaron.elasticsearch;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.aaron.elasticsearch.App;
import com.aaron.elasticsearch.po.Article;
import com.aaron.elasticsearch.repository.ArticleRepository;
import com.aaron.elasticsearch.repository.ArticleTemplate;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class ArticleTest {
 	
    // 基於 Repository
 	@Autowired
 	ArticleRepository articleRepository;
 	
 	// 基於 Elasticsearc Template
 	@Autowired
 	ArticleTemplate articleTemplate;
 	
 	 // 基於 Repository 用法
 	@Test
 	public void testAdd() {
 		Article article = new Article();
 		article.setId(1);
 		article.setSid("dak219dksd");
 		article.setTitle("java如何突破重圍");
 		article.setUrl("http://baidu.com");
 		article.setContent("java 及的垃圾的 的垃圾大家導入大大大");
 		articleRepository.save(article);
 	}
 	
 	 // 基於 Repository 用法
 	@Test
 	public void testList() {
 		Iterable<Article> list = articleRepository.findAll();
 		for (Article article : list) {
 			System.out.println(article.getTitle());
 		}
 	}
 	
 	 // 基於 Repository 用法
 	@Test
 	public void testQuery() {
 		Iterable<Article> list = articleRepository.findByTitleContaining("java");
 		for (Article article : list) {
 			System.out.println(article.getTitle());
 		}
 	}
 	
    // 基於 Elasticsearc Template 用法
 	@Test
 	public void testQueryByPage() {
 		Iterable<Article> list = articleTemplate.queryByPage("java", 0, 10);
 		for (Article article : list) {
 			System.out.println(article.getTitle());
 		}
 	}
 	
    // 基於 Elasticsearc Template 用法
 	@Test
 	public void testQueryByTitle() {
 		List<Article> list = articleTemplate.query("java");
 		for (Article article : list) {
 			System.out.println(article.getTitle());
 		}
 	}
 	
    // 基於 Elasticsearc Template 用法
 	@Test
 	public void testQueryTitleCount() {
 		System.out.println(articleTemplate.queryTitleCount("java"));
 	}
 	
    // 基於 Elasticsearc Template 用法
 	@Test
 	public void testQueryBySid() {
 		List<Article> list = articleTemplate.query("java", "dak219dksd");
 		for (Article article : list) {
 			System.out.println(article.getTitle());
 		}
 	}
 	
    // 基於 Elasticsearc Template 用法
 	@Test
 	public void testQueryByOr() {
 		List<Article> list = articleTemplate.queryByOr("java", "dad");
 		for (Article article : list) {
 			System.out.println(article.getTitle());
 		}
 	}
}
