package com.zhenyulaw.jf.web.service.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.zhenyulaw.jf.common.controller.ResultDTO;
import com.zhenyulaw.jf.common.exception.BusinessException;
import com.zhenyulaw.jf.common.exception.NeedLoginException;



@Component
public class ExceptionResolver extends SimpleMappingExceptionResolver {	
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response,
			Object handler, Exception ex)  {
		
	    ModelAndView mv = new ModelAndView();
		if(ex instanceof BusinessException) {
			BusinessException businessException = (BusinessException)ex;
			ResultDTO resultDTO = new ResultDTO(businessException.getErrorMessage());
			response.setContentType("application/json;charset=UTF-8");
			
			try {
				response.getWriter().write(resultDTO.toString());
				response.flushBuffer();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if(ex instanceof NeedLoginException){
			try {
				ResultDTO resultDTO = new ResultDTO("需要登录");
				response.getWriter().write(resultDTO.toString());
				response.flushBuffer();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			ResultDTO resultDTO = new ResultDTO("操作失败，请稍后再试");
			response.setContentType("application/json;charset=UTF-8");
			
			try {
				response.getWriter().write(resultDTO.toString());
				response.flushBuffer();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ex.printStackTrace();
		}
		return mv;
	}
}
