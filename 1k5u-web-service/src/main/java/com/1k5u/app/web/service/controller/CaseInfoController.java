package com.zhenyulaw.jf.web.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.zhenyulaw.jf.common.controller.ResultDTO;
import com.zhenyulaw.jf.common.util.PageParam;
import com.zhenyulaw.jf.entity.CaseInfo;
import com.zhenyulaw.jf.service.CaseInfoService;

@RestController
@RequestMapping(value="caseInfo", produces="application/json; charset=UTF-8")
public class CaseInfoController {

	@Autowired
	CaseInfoService caseInfoService; 
	
	@RequestMapping("list")
	public String getCaseInfoList(PageParam page) {
		
		PageInfo<CaseInfo> pageInfo = this.caseInfoService.searchCaseList(page, null);
		
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.addAttribute("pageInfo", pageInfo);
		
		return resultDTO.toString();
	}
	
	@RequestMapping("getOne")
	public String getCaseInfo(Long id) {
		
		CaseInfo caseInfo = this.caseInfoService.getCaseInfo(id);
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.addAttribute("caseInfo", caseInfo);
		
		return resultDTO.toString();
	}
	
}
