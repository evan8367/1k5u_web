package com.zhenyulaw.jf.web.service.config;

import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import com.zhenyulaw.jf.common.config.AppConfig;

import freemarker.template.TemplateException;

@Configuration
@ComponentScan(basePackages = { "com.zhenyulaw.jf.web.service" })
@EnableScheduling
public class MvcConfig extends WebMvcConfigurationSupport {
	@Bean
	public ViewResolver viewResolver() throws IOException {
		PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
		propertiesFactoryBean.setLocation(new ClassPathResource("config/web/"
				+ AppConfig.getEvnName() + "-main-setting-web.properties"));
		propertiesFactoryBean.afterPropertiesSet();
		Properties props = propertiesFactoryBean.getObject();

		FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
		resolver.setViewNames(new String[] { "*.ftl" });
		resolver.setOrder(1);
		resolver.setViewClass(FreeMarkerView.class);
		resolver.setContentType("text/html;charset=utf-8");
		resolver.setAttributes(props);

		return resolver;
	}

	

	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setMaxUploadSize(1024 * 1024 * 1024 * 10);
		return resolver;
	}

}