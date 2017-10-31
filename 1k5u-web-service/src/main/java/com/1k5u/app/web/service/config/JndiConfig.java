package com.zhenyulaw.jf.web.service.config;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.jndi.JndiLocatorDelegate;

public class JndiConfig {

	@Bean
	public DataSource jfDataSource() throws NamingException {
		return JndiLocatorDelegate.createDefaultResourceRefLocator().lookup("jfDs01", DataSource.class);
	}

}
