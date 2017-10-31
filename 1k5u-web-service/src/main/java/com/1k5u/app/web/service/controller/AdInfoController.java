package com.zhenyulaw.jf.web.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhenyulaw.jf.common.controller.ResultDTO;
import com.zhenyulaw.jf.entity.AdInfo;
import com.zhenyulaw.jf.service.AdInfoService;

@RestController
@RequestMapping(value="adInfo", produces="application/json; charset=UTF-8")
public class AdInfoController {

	@Autowired
	AdInfoService adInfoService; 
	
	@RequestMapping("list")
	public String getAdInfoList() {
		AdInfo adInfo = new AdInfo();
		adInfo.setStatus(1);
		List<AdInfo> list = this.adInfoService.getAdInfoList(adInfo);
		
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.addAttribute("list", list);
		
		return resultDTO.toString();
	}
	
	@RequestMapping("getOne")
	public String getAdInfo(Long adId) {
		
		AdInfo adInfo = this.adInfoService.getAdInfo(adId);
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.addAttribute("adInfo", adInfo);
		
		return resultDTO.toString();
	}
	
}
