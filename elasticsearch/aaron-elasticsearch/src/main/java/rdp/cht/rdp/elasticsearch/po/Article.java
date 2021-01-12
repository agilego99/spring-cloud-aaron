package rdp.cht.rdp.elasticsearch.po;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.Data;

/**
 * Elasticsearch 實體類定義
 * @author Aaron
 */
@Data
// 使用的索引名稱及索引的類型 type
@Document(indexName = "gordianknot", type = "article")
public class Article {
	@Id    
    @Field(type = FieldType.Integer)    
	private Integer id;
	
	@Field(type = FieldType.Keyword)   
	private String sid;
	
	// 將文本做最細粒度的拆分，比如會將「中華人民共和國人民大會堂」拆分為「中華人民共和國、中華人民、中華、華人、人民共和國、人民、共和國、大會堂、大會、會堂等詞語。
	@Field(type = FieldType.Keyword,  analyzer = "ik_max_word", searchAnalyzer = "ik_max_word") 
	private String title;
	
	@Field(type = FieldType.Keyword) 
	private String url;
	
	@Field(type = FieldType.Keyword) 
	private String content;	
}
