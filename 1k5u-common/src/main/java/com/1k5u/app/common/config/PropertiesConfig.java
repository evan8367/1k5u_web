package com.zhenyulaw.jf.common.config;


import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

public class PropertiesConfig {
	
	@Bean
	public Properties propertiesConfig() {

		String environment = AppConfig.getEvnName();
//		System.out.println("classpath:config/database/data_source_" + environment + ".properties");
		PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

		Resource[] resources = {
				resolver.getResource("classpath:config/api/api_" + environment + ".properties"),
//				resolver.getResource("classpath:config/mongo/mongo_" + environment + ".properties"),
//				resolver.getResource("classpath:config/database/ip_data_source_" + environment + ".properties"),
				resolver.getResource("classpath:config/disk/disk_" + environment + ".properties"),
				resolver.getResource("classpath:config/sms/sms_" + environment + ".properties"),
				resolver.getResource("classpath:config/web/"+ environment + "-main-setting-web.properties"),
				resolver.getResource("classpath:config/img/img_"+ environment + ".properties")};
		
		propertiesFactoryBean.setLocations(resources);
		try {
			propertiesFactoryBean.afterPropertiesSet();
			return propertiesFactoryBean.getObject();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
