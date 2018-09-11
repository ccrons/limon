package com.limon.base.init;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @author gqf
 * 
 * 登录验证过滤
 * 2015-2-10 上午10:32:56
 */
public class SessionFilter extends OncePerRequestFilter {
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, javax.servlet.FilterChain filterChain)
			throws ServletException, IOException {
		if(request.getRequestURI().equals(request.getContextPath()+"/")){
			//首页登陆
			response.sendRedirect("http://www.duriantimes.com");
		}else{
			// 不过滤的URI
			String[] notFilter = new String[] {"MP_verify_BwehJ3KPiWY2s44c.txt","/wx","/api","/tobbs","/down","/public","/cluck","/getnow","/storead/show","/wap","/site","/spinfo","/pinfo","/tpinfo","/zpinfo","/rec","/zero","/luck","/timelimit","/upload","/temp","/productinfo/uploadFile","/HttpService","/login","/error","/images","/font","/js","/lib",".css",".js",".ico",".png",".jpg",".gif",".html",".txt"};

			// 请求URI
			String uri = request.getRequestURI();
			// URI中包时才进行过滤
			if (uri.indexOf("/") != -1) {
				// 是否过滤
				boolean doFilter = true;
				for (String s : notFilter) {
					if (uri.indexOf(s) != -1) {
						// 如果URI中包含不过滤的URI，则不进行过
						doFilter = false;
						break;
					}
				}
				
				if (doFilter) {
					// 执行过滤
					// 从session中获取登录实体
					Object obj = request.getSession().getAttribute("loginUser");
					Object objstore = request.getSession().getAttribute("storeloginUser");
					if (null == obj&&null == objstore) {
						// 如果session中不存在登录者实体，跳转到登陆页
						// 设置request和response的字符集，防止乱
						request.setCharacterEncoding("UTF-8");
						response.setContentType("text/html;charset=UTF-8");
						response.getWriter().write("<script>top.location.href='"+request.getContextPath()+"/login?error=1"+"'</script>");
					} else {
						// 如果session中存在登录实体，则继续
						filterChain.doFilter(request, response);
					}
				} else {
					// 如果不执行过滤，则继
					filterChain.doFilter(request, response);
				}
			} else {
				// 如果URI中不包含/，则继续
				filterChain.doFilter(request, response);
			}
		}
	}

}