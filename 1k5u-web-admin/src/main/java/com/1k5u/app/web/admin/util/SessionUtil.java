package com.zhenyulaw.jf.web.portal.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.zhenyulaw.jf.common.exception.NeedLoginException;
import com.zhenyulaw.jf.entity.Admin;

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
		return getUserInfo().getId();
	}

	/**
	 * 获取session中的User
	 * 
	 * @return
	 */
	public static Admin getUserInfo(HttpServletRequest request) {
		Object User = request.getSession(true).getAttribute(SESSION_USER);
		if (User == null) {
			throw new NeedLoginException();
		} else {
			if (isNeedThrowLoginException((Admin) User)) {
				throw new NeedLoginException();
			} else {
				return (Admin) User;
			}
		}
	}
	
	public static Admin getUserInfoWithoutLogin() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		Object User = request.getSession(true).getAttribute(SESSION_USER);
		return (Admin) User;
	}

	/**
	 * 获取session中的User
	 * 
	 * @return
	 */
	public static Admin getUserInfo() {
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
	public static void setUserInfo(HttpServletRequest request, Admin adminUser) {
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
	private static boolean isNeedThrowLoginException(Admin adminUser) {
		return adminUser == null || adminUser.getId() == null;
	}

}
