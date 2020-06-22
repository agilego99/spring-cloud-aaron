package com.aaron.zuul_demo.support;

import java.util.Map;

/**
 * The interface Ribbon filter context.
 */
public interface RibbonFilterContext {
    /**
     * Add ribbon filter context.
     *
     * @param key   the key
     * @param value the value
     * @return the ribbon filter context
     */
    RibbonFilterContext add(String key, String value);

    /**
     * Get string.
     *
     * @param key the key
     * @return the string
     */
    String get(String key);

    /**
     * Remove ribbon filter context.
     *
     * @param key the key
     * @return the ribbon filter context
     */
    RibbonFilterContext remove(String key);

    /**
     * Gets attributes.
     *
     * @return the attributes
     */
    Map<String, String> getAttributes();
}
