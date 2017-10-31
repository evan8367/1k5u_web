package com.zhenyulaw.jf.web.portal.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.zhenyulaw.jf.common.exception.NeedLoginException;
import com.zhenyulaw.jf.entity.Admin;
import com.zhenyulaw.jf.entity.User;
import com.zhenyulaw.jf.web.portal.util.SessionUtil;

public class AuthFilter implements Filter {

	public static final List<String> PASS = new ArrayList<String>() {
		private static final long serialVersionUID = 1L;
		{
			
		}
	};

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest requestSer, ServletResponse responseSer, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) requestSer;
		HttpServletResponse response = (HttpServletResponse) responseSer;

		// 不需要登录链接直接转发
		if (match(request)) {
			chain.doFilter(request, response);
		} else {
			try {
				Admin user = SessionUtil.getUserInfo(request);
				if (user != null) {
					// 已登录直接转发
					chain.doFilter(request, response);
				} 
				else {
					// 未登录跳转到登录页面
					response.sendRedirect("/jf-web-admin/login.htm");
					return;
				}
			} catch (Exception e) {
				if(!(e instanceof NeedLoginException)) {
					e.printStackTrace(); 
				}
				response.sendRedirect("/jf-web-admin/login.htm");
				return;
			}
		}
	}

	@Override
	public void destroy() {
		// do nothing
	}

	private boolean match(HttpServletRequest request) {
		boolean result = false;
		for (String mertUrl : PASS) {
			RequestMatcher urlMatcher = new AntPathRequestMatcher(mertUrl);
			if (urlMatcher.matches(request)) {
				result = true;
				break;
			}
		}
		return result;
	}

}
