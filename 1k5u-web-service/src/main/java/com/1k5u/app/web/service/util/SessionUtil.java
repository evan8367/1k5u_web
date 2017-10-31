package com.zhenyulaw.jf.web.service.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.zhenyulaw.jf.common.exception.NeedLoginException;
import com.zhenyulaw.jf.entity.User;

public class SessionUtil {

	public static final String SESSION_USER = "userInfo";

	/**
	 * 获取session中的userId
	 * 
	 * @param request
	 * @return
	 */
	public static Long getUserId(HttpServletRequest request) {
		return getUserInfo(request).getId();
	}

	/**
	 * 获取session中的userId
	 * 
	 * @return
	 */
	public static Long getUserId() {
//		return 1L;
		return getUserInfo().getId();
	}

	/**
	 * 获取session中的User
	 * 
	 * @return
	 */
	public static User getUserInfo(HttpServletRequest request) {
		Object User = request.getSession(true).getAttribute(SESSION_USER);
		if (User == null) {
			throw new NeedLoginException();
		} else {
			if (isNeedThrowLoginException((User) User)) {
				throw new NeedLoginException();
			} else {
				return (User) User;
			}
		}
	}
	
	public static User getUserInfoWithoutLogin() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		Object User = request.getSession(true).getAttribute(SESSION_USER);
		return (User) User;
	}

	/**
	 * 获取session中的User
	 * 
	 * @return
	 */
	public static User getUserInfo() {
		return getUserInfo(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest());
	}

	/**
	 * 将User设置到session中
	 * 
	 * @param request
	 * @param userId
	 *            用户编号
	 * @return
	 */
	public static void setUserInfo(HttpServletRequest request, User adminUser) {
		// HttpSession session = request.getSession();
		// if (null != session) {
		// session.invalidate();
		// }
		request.getSession(true).setAttribute(SESSION_USER, adminUser);
	}

	/**
	 * 根据用户信息判断是否需要抛出登录异常
	 * 
	 * @param User
	 *            用户信息
	 * @return
	 */
	private static boolean isNeedThrowLoginException(User adminUser) {
		return adminUser == null || adminUser.getId() == null;
	}

}
