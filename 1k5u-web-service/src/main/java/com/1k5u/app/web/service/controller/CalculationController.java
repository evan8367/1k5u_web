package com.zhenyulaw.jf.web.service.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhenyulaw.jf.common.controller.ResultDTO;
import com.zhenyulaw.jf.entity.CityInfo;
import com.zhenyulaw.jf.service.CalculationService;

@RestController
@RequestMapping(value="calculation", produces="application/json; charset=UTF-8")
public class CalculationController {

	@Autowired
	CalculationService calculationService;
	
	@RequestMapping("litigation")
	public String litigationFee(int caseType, int involveType, int input, int halve) {
		
		String fee = this.calculationService.litigationFee(caseType, involveType, input, halve);
		
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.addAttribute("fee", fee);
		
		return resultDTO.toString();
	}
	
	@RequestMapping("lawyer")
	public String lawyerFee(int caseType, int involveType, int input) {
		
		String fee = this.calculationService.lawyerFee(caseType, involveType, input);
		
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.addAttribute("fee", fee);
		
		return resultDTO.toString();
	}
	
	
	@RequestMapping("loan")
	public String loanFee(int year,int type,int way,BigDecimal basemoney1,BigDecimal basemoney2) {
		
		Map<String, Object> fee = this.calculationService.loan(year, type, way, basemoney1, basemoney2);
		
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.addAttribute("fee", fee);
		
		return resultDTO.toString();
	}
	
	@RequestMapping("getAllCity")
	public String getAllCity() {
		
		List<CityInfo> list = this.calculationService.getAllCity();
		
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.addAttribute("list", list);
		
		return resultDTO.toString();
	}
	
	@RequestMapping("personalInjury")
	public String personalInjury(int age,int injuryLevel,int city,int residence,int fee1,int fee2,int fee3,int fee4,int fee5,int fee6,int fee7) {
		
		String fee = this.calculationService.personalInjury(age, injuryLevel, city, residence, fee1, fee2, fee3, fee4, fee5, fee6, fee7);
		
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.addAttribute("fee", fee);
		
		return resultDTO.toString();
	}
	
	@RequestMapping("occupationalInjury")
	public String occupationalInjury(int injuryLevel,int salary){
		
		Map<String, Object> fee = this.calculationService.occupationalInjury(injuryLevel, salary);
		
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.addAttribute("fee", fee);
		
		return resultDTO.toString();
	}
	
	@RequestMapping("penalSumFee")
	public String penalSumFee(int money, String startDate, String endDate, int type, int option, int rate) throws ParseException{
		
		String fee = this.calculationService.penalSumFee(money, startDate, endDate, type, option, rate);
		
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.addAttribute("fee", fee);
		
		return resultDTO.toString();
	}
	
}
