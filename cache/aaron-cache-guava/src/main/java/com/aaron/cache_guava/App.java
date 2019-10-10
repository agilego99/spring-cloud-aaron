package com.aaron.cache_guava;

import java.util.concurrent.TimeUnit;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class App {
	public static void main(String[] args) {
		final PersonDao dao = new PersonDao();
		/**
		 * 建構緩存物件
		 * 設置寫入資料1秒後過期
		 */
		LoadingCache<String, Person> cahceBuilder = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.SECONDS)
				.build(new CacheLoader<String, Person>() {
					@Override
					/**
					 * 加載資料庫的資料
					 */
					public Person load(String key) throws Exception {
						return dao.findById(key);
					}
				});
		
		try {
			for(;;) {
				/**
				 * 通過 CacheBuilder 物件的 get 方法獲取資料。
				 * 如果緩存存在資料則以緩存的資料返回，如果緩存不存在對應的資料則執行 load 中的邏輯，從資料庫中查詢資料並緩存
				 */
				Person person = cahceBuilder.get("1");
				System.out.println("From Cache\t"+ person.getName());
				Thread.sleep(200);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
