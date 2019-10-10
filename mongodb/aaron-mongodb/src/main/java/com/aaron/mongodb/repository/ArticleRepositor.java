package com.aaron.mongodb.repository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.aaron.mongodb.po.Article;
@Repository("ArticleRepositor")
public interface ArticleRepositor extends PagingAndSortingRepository<Article, String> {
 	// 分頁查詢
 	public Page<Article> findAll(Pageable pageable);
 	// 根據 author 查詢
 	public List<Article> findByAuthor(String author);
 	// 根據作者和標題查詢
 	public List<Article> findByAuthorAndTitle(String author, String title);
 	// 忽略參數大小寫
 	public List<Article> findByAuthorIgnoreCase(String author);
 	// 忽略所有參數大小寫
 	public List<Article> findByAuthorAndTitleAllIgnoreCase(String author, String title);
 	// 排序
 	public List<Article> findByAuthorOrderByVisitCountDesc(String author);
 	public List<Article> findByAuthorOrderByVisitCountAsc(String author);
 	// 自帶排序條件
 	public List<Article> findByAuthor(String author, Sort sort);
}
