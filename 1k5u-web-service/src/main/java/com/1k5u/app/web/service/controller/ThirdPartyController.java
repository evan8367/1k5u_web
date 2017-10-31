package com.zhenyulaw.jf.web.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.zhenyulaw.jf.common.controller.ResultDTO;
import com.zhenyulaw.jf.common.util.PageParam;
import com.zhenyulaw.jf.entity.ThirdParty;
import com.zhenyulaw.jf.service.ThirdPartyService;

@RestController
@RequestMapping(value="thirdParty", produces="application/json; charset=UTF-8")
public class ThirdPartyController {

	@Autowired
	ThirdPartyService thirdPartyService; 
	
	@RequestMapping("list")
	public String getThirdPartyList(String name, PageParam page) {
		
		PageInfo<ThirdParty> list = this.thirdPartyService.getThirdPartyList(name, page);
		
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.addAttribute("list", list);
		
		return resultDTO.toString();
	}
	
	@RequestMapping("getOne")
	public String getThirdParty(Long id) {
		
		ThirdParty thirdParty = this.thirdPartyService.getThirdParty(id);
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.addAttribute("thirdParty", thirdParty);
		
		return resultDTO.toString();
	}
	
}
