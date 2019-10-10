package org.gordianknot.common.support;

import java.util.Map;

/**
 * @author aaron
 **/
public interface RibbonFilterContext {
    RibbonFilterContext add(String key, String value);

    String get(String key);

    RibbonFilterContext remove(String key);

    Map<String, String> getAttributes();
}
