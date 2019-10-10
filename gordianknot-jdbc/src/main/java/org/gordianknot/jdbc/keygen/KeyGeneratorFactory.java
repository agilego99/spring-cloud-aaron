package org.gordianknot.jdbc.keygen;
public class KeyGeneratorFactory {
 	
 	public KeyGeneratorFactory() {
 		super();
 	}
 	/**
     * 創建主鍵生成器.
     * @param keyGeneratorClass 主鍵生成器類
     * @return 主鍵生成器實例
     */
    public static KeyGenerator createKeyGenerator(final Class<? extends KeyGenerator> keyGeneratorClass) {
        try {
            return keyGeneratorClass.newInstance();
        } catch (final InstantiationException | IllegalAccessException ex) {
            throw new IllegalArgumentException(String.format("Class %s should have public privilege and no argument constructor", keyGeneratorClass.getName()));
        }
    }
}
