package com.aaron.cache_data_redis;
import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
public class MyBloomFilter {
 	public static void main(String[] args) {
 		// 總數量
 		int total = 1000000;
 		
 		/** 
 		 * loomFilter 預設的錯誤率為 0.03
 		 */
// 		BloomFilter<CharSequence> bf = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), total);
// 		 指定錯誤率 0.0003
 		 BloomFilter<CharSequence> bf = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), total,0.0003);

 		/**
 		 * 初始化 1000000 資料條資料到過濾器中
 		 */
 		for (int i = 0; i < total; i++) {
 			bf.put("" + i);
 		}
 		/**
 		 * 在 1000000 的基礎上在增加 10000 條比對資料，確認是否在過濾器中
 		 */
 		int count = 0;
 		for (int i = 0; i < total + 10000; i++) {
 			if (bf.mightContain("" + i)) {
 				count++;
 			}
 		}
 		System.out.println("BloomFilter 匹配數量 " + count);
 	}
}
