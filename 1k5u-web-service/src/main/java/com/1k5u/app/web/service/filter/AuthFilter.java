package com.zhenyulaw.jf.web.service.filter;

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
import com.zhenyulaw.jf.entity.User;
import com.zhenyulaw.jf.web.service.util.SessionUtil;

public class AuthFilter implements Filter {

	public static final List<String> CHECK_LIST = new ArrayList<String>() {
		private static final long serialVersionUID = 1L;
		{
			add("/index.htm");
			
			add("/user/login.htm");
			add("/user/loginSubmit.htm");
			add("/user/register.htm");
			
			add("/user/registerSubmit.htm");
			
			add("/user/verifyMobile.htm");
			add("/user/verifyMobileSubmit.htm");
			
			add("/user/retrievePassword.htm");
			add("/user/retrievePasswordSubmit.htm");
			
			add("/user/loginSubmit.htm");
			add("/user/logout.htm");
			
			add("/api/acquire/v1.do");
			add("/api/track/imp/v1.do");
			add("/api/track/clicking/v1.do");
			add("/api/track/download/v1.do");
			add("/api/track/install/v1.do");
			add("/api/test.htm");
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
				User user = SessionUtil.getUserInfo(request);
				if (user != null) {
					// 已登录直接转发
					chain.doFilter(request, response);
				} 
				else {
					// 未登录跳转到登录页面
					response.sendRedirect("/ad-web-portal-provider/user/login.htm");
					return;
				}
			} catch (Exception e) {
				if(!(e instanceof NeedLoginException)) {
					e.printStackTrace(); 
				}
				response.sendRedirect("/ad-web-portal-provider/user/login.htm");
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
		for (String mertUrl : CHECK_LIST) {
			RequestMatcher urlMatcher = new AntPathRequestMatcher(mertUrl);
			if (urlMatcher.matches(request)) {
				result = true;
				break;
			}
		}
		return result;
	}

}
