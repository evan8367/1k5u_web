package com.zhenyulaw.jf.web.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.zhenyulaw.jf.common.controller.ResultDTO;
import com.zhenyulaw.jf.common.util.DocumentConvertUtils;
import com.zhenyulaw.jf.common.util.PageParam;
import com.zhenyulaw.jf.entity.NetworkDisk;
import com.zhenyulaw.jf.service.NetworkDiskService;
import com.zhenyulaw.jf.web.service.util.SessionUtil;

@RestController
@RequestMapping(value = "networkDisk", produces = "application/json; charset=UTF-8")
public class NetworkDiskController {

	@Autowired
	NetworkDiskService networkDiskService;

	@RequestMapping("list")
	public String networkDiskList(PageParam page, Integer category, Long parentId) {

		Long userId = SessionUtil.getUserId();
		PageInfo<NetworkDisk> pageInfo = this.networkDiskService.getNetworkDiskList(page, userId, category, parentId);

		ResultDTO resultDTO = new ResultDTO();
		resultDTO.addAttribute("pageInfo", pageInfo);

		return resultDTO.toString();
	}
}
