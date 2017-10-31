package com.zhenyulaw.jf.web.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhenyulaw.jf.common.controller.ResultDTO;
import com.zhenyulaw.jf.entity.Company;
import com.zhenyulaw.jf.service.CompanyService;

@RestController
@RequestMapping(value="company", produces="application/json; charset=UTF-8")
public class CompanyController {
	
	@Autowired
	CompanyService companyService;

	@RequestMapping("introduce")
	public String login(Long companyId) {
		Company company = this.companyService.getCompany(companyId);
		ResultDTO resultDTO = new ResultDTO();
		
		resultDTO.addAttribute("companyInfo", company);
		
		return resultDTO.toString();
	}
	
	
	
}
