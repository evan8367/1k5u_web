package com.zhenyulaw.jf.web.portal.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ObjectUtils.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.zhenyulaw.jf.common.util.PageParam;
import com.zhenyulaw.jf.entity.Company;
import com.zhenyulaw.jf.entity.User;
import com.zhenyulaw.jf.service.CompanyService;
import com.zhenyulaw.jf.service.UserService;

@Controller
@RequestMapping("base")
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	CompanyService companyService;

	
	 //获取用户列表
	@RequestMapping("/userList")
	public String getUserList(PageParam page,Model model,String phone) {
		PageInfo<User> pageInfo = userService.searchUserList(page, phone);		
		model.addAttribute("phone", phone);
		model.addAttribute("pageInfo", pageInfo);
		return "user/userList.ftl";
	}
	//编辑个人信息
	@RequestMapping("/editUserInfo")
	public String editUserInfo(Model model,Long id) {
		User user = new User();
		if (id != null) {
			user = userService.getUser(id);
		}
		model.addAttribute("user", user);
		model.addAttribute("companyList", companyService.getCompanyList());
		
		return "user/editUserInfo.ftl";
	}
	//编辑个人信息
	@RequestMapping("/editUserInfoSubmit")
	public String editUserInfoSubmit(Model model,User user,HttpServletResponse response) throws IOException {
		Long id = userService.addUser(user);
		model.addAttribute("message", "保存成功");
//		response.sendRedirect("editUserInfo.htm?id="+id +"&message=保存成功");
		return editUserInfo(model, id);
	}
	 //获取公司列表
	@RequestMapping("/companyList")
	public String getCompanyList(PageParam page,Model model,String name) {
		PageInfo<Company> pageInfo = companyService.companyList(page, name);	
		model.addAttribute("name", name);
		model.addAttribute("pageInfo", pageInfo);
		return "user/companyList.ftl";
	}
	//编辑公司信息
	@RequestMapping("/editCompanyInfo")
	public String editCompanyInfo(Model model,Long id) {
		Company company = new Company();
		if (id != null) {
			company = companyService.getCompany(id);
		}
		model.addAttribute("company", company);
		return "user/editCompanyInfo.ftl";
	}
	//编辑公司信息提交
	@RequestMapping("/editCompanyInfoSubmit")
	public String editCompanyInfoSubmit(Model model,Company company,MultipartFile file,MultipartFile logofile, HttpServletResponse response) throws IOException {
		Long id = companyService.addCompany(company, file, logofile);
		model.addAttribute("message", "保存成功");
		return editCompanyInfo(model,id);
	}
}
