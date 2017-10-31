package com.zhenyulaw.jf.common.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = { 
		"com.zhenyulaw.jf.common",
		"com.zhenyulaw.jf.entity",
		"com.zhenyulaw.jf.dao",
		"com.zhenyulaw.jf.service",
		"com.zhenyulaw.jf.serviceImpl"})
@Import({PropertiesConfig.class, DataAccessConfig.class, SmsConfig.class})
public class AppConfig {
	
	
	public static String getEvnName() {
		return System.getProperty("evnName", "DEV");
	}

	
}