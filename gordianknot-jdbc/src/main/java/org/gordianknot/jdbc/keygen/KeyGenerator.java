package org.gordianknot.jdbc.keygen;
/**
 * 主鍵生成器
 *
 */
public interface KeyGenerator {
 	/**
     * 生成主鍵.
     * @return 自動生成的主鍵
     */
    Number generateKey();
}
