package com.zhenyulaw.jf.web.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.zhenyulaw.jf.common.controller.ResultDTO;
import com.zhenyulaw.jf.common.util.PageParam;
import com.zhenyulaw.jf.entity.Video;
import com.zhenyulaw.jf.service.VideoService;

@RestController
@RequestMapping(value="video", produces="application/json; charset=UTF-8")
public class VideoController {

	@Autowired
	VideoService videoService; 
	
	@RequestMapping("list")
	public String getVideoList(String title, PageParam page) {
		
		PageInfo<Video> list = this.videoService.getVideoList(title,page);
		
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.addAttribute("list", list);
		
		return resultDTO.toString();
	}
	
	@RequestMapping("getOne")
	public String getVideo(Long id) {
		
		Video video = this.videoService.getVideo(id);
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.addAttribute("video", video);
		
		return resultDTO.toString();
	}
	
}
