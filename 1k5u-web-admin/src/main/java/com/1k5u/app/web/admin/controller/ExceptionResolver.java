package com.zhenyulaw.jf.web.portal.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.zhenyulaw.jf.common.exception.BusinessException;
import com.zhenyulaw.jf.common.exception.NeedLoginException;

@Component
public class ExceptionResolver extends SimpleMappingExceptionResolver {	
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response,
			Object handler, Exception ex)  {
		
	    ModelAndView mv = new ModelAndView();
		if(ex instanceof BusinessException) {
			BusinessException businessException = (BusinessException)ex;
			mv.addObject("errorMessage", businessException.getErrorMessage());
			mv.setViewName("/errorPage.ftl");
		}else if(ex instanceof NeedLoginException){
			try {
				response.sendRedirect("/jf-web-admin/login.htm");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			ex.printStackTrace();
		}
		return mv;
	}
}
