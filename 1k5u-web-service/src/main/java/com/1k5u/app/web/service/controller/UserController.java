package com.zhenyulaw.jf.web.service.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhenyulaw.jf.common.controller.ResultDTO;
import com.zhenyulaw.jf.entity.Company;
import com.zhenyulaw.jf.entity.User;
import com.zhenyulaw.jf.service.CompanyService;
import com.zhenyulaw.jf.service.UserService;
import com.zhenyulaw.jf.web.service.util.SessionUtil;

@RestController
@RequestMapping(value="user", produces="application/json; charset=UTF-8")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	CompanyService companyService;

	@RequestMapping("login")
	public String login(String phone, String password, HttpServletRequest request) {
//		String token = request.getHeader("token");
		User user = this.userService.loginUser(phone, password);
		SessionUtil.setUserInfo(request, user);
		
		Company company = this.companyService.getCompany(user.getCompanyId());
		user.setAccount(company.getAccount());
		user.setCompanyLogo(company.getLogo());
		
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.addAttribute("user", user);
		
		return resultDTO.toString();
	}
	
	@RequestMapping("resetPassword")
	public String resetPassword(String phone, String password, String code){
		
		userService.resetPassword(phone, password, code);
		
		ResultDTO resultDTO = new ResultDTO();
		
		return resultDTO.toString();
		
	}
	@RequestMapping("addPoints")
	public String addCardPoints(Long userId, int points){
		
		userService.addCardPoints(userId, points);
		
		ResultDTO resultDTO = new ResultDTO();
		
		return resultDTO.toString();
		
	}
	@RequestMapping("getPoints")
	public String getCardPoints(Long userId){

		ResultDTO resultDTO = new ResultDTO();
		resultDTO.addAttribute("points", userService.getCardPoints(userId));
		
		return resultDTO.toString();
		
	}
	@RequestMapping("reducePoints")
	public String reduceCardPoints(Long userId, int points){
		
		userService.reduceCardPoints(userId, points);
		
		ResultDTO resultDTO = new ResultDTO();
		
		return resultDTO.toString();
		
	}
	
	@RequestMapping("reduceAccount")
	public String reduceAccount(Long userId, BigDecimal acount){
		
		userService.reduceAccount(userId, acount);
		
		ResultDTO resultDTO = new ResultDTO();
		
		return resultDTO.toString();
		
	}
	
	
	
}
