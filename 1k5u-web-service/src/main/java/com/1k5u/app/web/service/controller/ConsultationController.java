package com.zhenyulaw.jf.web.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhenyulaw.jf.common.controller.ResultDTO;
import com.zhenyulaw.jf.entity.Consultation;
import com.zhenyulaw.jf.service.ConsultationService;

@RestController
@RequestMapping(value="consultation", produces="application/json; charset=UTF-8")
public class ConsultationController {

	@Autowired
	ConsultationService consultationService;
	
	@RequestMapping("submit")
	public String consultationList(Consultation consultation) {
		
		consultationService.insert(consultation);
		
		ResultDTO resultDTO = new ResultDTO();
		
		return resultDTO.toString();
	}
}
