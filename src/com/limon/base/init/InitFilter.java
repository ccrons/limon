package com.limon.base.init;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;

import com.limon.util.CacheUtil;


public class InitFilter implements Filter {
	private static final Logger log = Logger.getLogger(InitFilter.class);

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		chain.doFilter(request, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		try {
			//初始化缓存工具
			CacheUtil.map = new HashMap<String, Object>();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}