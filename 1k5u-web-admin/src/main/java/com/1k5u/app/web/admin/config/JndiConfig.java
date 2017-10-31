package com.zhenyulaw.jf.web.portal.config;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.jndi.JndiLocatorDelegate;

public class JndiConfig {

	@Bean
	public DataSource adDataSource() throws NamingException {
		return JndiLocatorDelegate.createDefaultResourceRefLocator().lookup("jfDs01", DataSource.class);
	}

}
