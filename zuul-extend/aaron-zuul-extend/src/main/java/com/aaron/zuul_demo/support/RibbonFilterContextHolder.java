package com.aaron.zuul_demo.support;

import com.alibaba.ttl.TransmittableThreadLocal;

/**
 * The type Ribbon filter context holder.
 */
public class RibbonFilterContextHolder {
    private static final TransmittableThreadLocal<RibbonFilterContext> contextHolder = new TransmittableThreadLocal<RibbonFilterContext>() {
        @Override
        protected RibbonFilterContext initialValue() {
            return new DefaultRibbonFilterContext();
        }
    };


    /**
     * Gets current context.
     *
     * @return the current context
     */
    public static RibbonFilterContext getCurrentContext() {
        return contextHolder.get();
    }


    /**
     * Clear current context.
     */
    public static void clearCurrentContext() {
        contextHolder.remove();
    }
}
