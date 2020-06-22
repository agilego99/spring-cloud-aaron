package com.aaron.zuul_demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.aaron.zuul_demo.filter.AuthFilter;
import com.aaron.zuul_demo.filter.DownGradeFilter;
import com.aaron.zuul_demo.filter.GrayPushFilter;
import com.aaron.zuul_demo.filter.LimitFilter;

/**
 * The type Filter config.
 */
@Configuration
public class FilterConfig {

    /**
     * Auth filter auth filter.
     *
     * @return the auth filter
     */
// 啟用認證過濾器
	@Bean
	public AuthFilter authFilter() {
		return new AuthFilter();
	}

    /**
     * Limit filter limit filter.
     *
     * @return the limit filter
     */
// 啟用限流過濾器
	@Bean
	public LimitFilter limitFilter() {
		return new LimitFilter();
	}

    /**
     * Down grade filter down grade filter.
     *
     * @return the down grade filter
     */
// 啟用服務降級
	@Bean
	public DownGradeFilter downGradeFilter() {
		return new DownGradeFilter();
	}

    /**
     * Gray push filter gray push filter.
     *
     * @return the gray push filter
     */
    @Bean
	public GrayPushFilter grayPushFilter() {
		return new GrayPushFilter();
	}
}
