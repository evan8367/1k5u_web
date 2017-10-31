package com.zhenyulaw.jf.web.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.zhenyulaw.jf.common.controller.ResultDTO;
import com.zhenyulaw.jf.common.util.PageParam;
import com.zhenyulaw.jf.entity.Activity;
import com.zhenyulaw.jf.service.ActivityService;

@RestController
@RequestMapping(value="activity", produces="application/json; charset=UTF-8")
public class ActivityController {

	@Autowired
	ActivityService activityService; 
	
	@RequestMapping("list")
	public String getActivityList(String title, PageParam page) {
		
		PageInfo<Activity> list = this.activityService.getActivityList(title,page);
		
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.addAttribute("list", list);
		
		return resultDTO.toString();
	}
	
	@RequestMapping("getOne")
	public String getActivity(Long id) {
		
		Activity activity = this.activityService.getActivity(id);
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.addAttribute("activity", activity);
		
		return resultDTO.toString();
	}
	
}
