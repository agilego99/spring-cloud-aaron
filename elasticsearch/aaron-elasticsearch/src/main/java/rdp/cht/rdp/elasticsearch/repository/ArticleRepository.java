package com.cht.rdp.elasticsearch.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cht.rdp.elasticsearch.po.Article;

/**
 * extends CrudRepository 基礎功能
 * @author Aaron
 */
@Repository
public interface ArticleRepository extends CrudRepository<Article, Long> {
	List<Article> findByTitleContaining(String title);
	// ... 還有其它多種用法
} 