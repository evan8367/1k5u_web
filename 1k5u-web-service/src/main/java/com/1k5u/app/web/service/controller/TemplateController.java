package com.zhenyulaw.jf.web.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.zhenyulaw.jf.common.controller.ResultDTO;
import com.zhenyulaw.jf.common.util.PageParam;
import com.zhenyulaw.jf.entity.Template;
import com.zhenyulaw.jf.service.NetworkDiskService;
import com.zhenyulaw.jf.service.TemplateService;

@RestController
@RequestMapping(value="template", produces="application/json; charset=UTF-8")
public class TemplateController {

	@Autowired
	TemplateService templateService; 
	
	@Autowired
	NetworkDiskService networkDiskService;
	
	@RequestMapping("list")
	public String getTemplateList(String title, PageParam page) {
		
		PageInfo<Template> list = this.templateService.getTemplateList(title,page);
		
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.addAttribute("list", list);
		
		return resultDTO.toString();
	}
	
	@RequestMapping("getOne")
	public String getTemplate(Long id) {
		
		Template template = this.templateService.getTemplate(id);
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.addAttribute("template", template);
		
		return resultDTO.toString();
	}
	
	@RequestMapping("purchase")
	public String purchase(Long userId, Long templateId) throws Exception {
		ResultDTO resultDTO = new ResultDTO();
		
		this.networkDiskService.copyFromTemplate(userId, templateId);
		
		return resultDTO.toString();
	}
}
