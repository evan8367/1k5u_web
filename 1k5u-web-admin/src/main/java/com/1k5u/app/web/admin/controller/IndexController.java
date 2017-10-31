package com.zhenyulaw.jf.web.portal.controller;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhenyulaw.jf.common.util.PageParam;
import com.zhenyulaw.jf.entity.Admin;
import com.zhenyulaw.jf.service.AdminService;
import com.zhenyulaw.jf.web.portal.util.SessionUtil;

@Controller
@RequestMapping
public class IndexController {

	@Autowired
	AdminService adminService;
	

	@RequestMapping("/main")
	public String index(Model model,HttpServletResponse response) throws IOException {
		Long userId = SessionUtil.getUserId();
		if (userId==null){
			response.sendRedirect("login.htm");
			return null;
		}
		return "main.ftl";
	}
	
	@RequestMapping("/welcome")
	public String welcome(Model model,PageParam page) {
		return "welcome.ftl";
	}

	@RequestMapping("/leftMenu")
	public String leftMenu() {
		return "leftMenu.ftl";
	}
	//登录页面
	@RequestMapping("/login")
	public String login(String errorMessage, Model model,HttpServletRequest request) {
		SessionUtil.setUserInfo(request, new Admin());
		model.addAttribute("errorMessage", errorMessage);
		return "login.ftl";
	}
	
	//登录提交
	@RequestMapping("/loginSubmit")
	public String loginSubmit(Admin admin,HttpServletResponse response,HttpServletRequest request) throws IOException {
		admin= adminService.login(admin.getAdminName(), admin.getPassword());
	    SessionUtil.setUserInfo(request, admin);
		response.sendRedirect("main.htm");//跳转首页
		return null;
	}
}
