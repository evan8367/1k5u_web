package com.zhenyulaw.jf.common.config;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.github.pagehelper.PageInterceptor;

import tk.mybatis.spring.mapper.MapperScannerConfigurer;

@EnableTransactionManagement
public class DataAccessConfig {
	
	@Bean
	public DataSourceTransactionManager transactionManager(DataSource adDataSource) {
		
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
		transactionManager.setDataSource(adDataSource);
		transactionManager.setNestedTransactionAllowed(true);
		transactionManager.setDefaultTimeout(600);
		return transactionManager;
	}
	@Bean
	public Configuration configuration() {
		Configuration configuration = new Configuration();  
		configuration.setMapUnderscoreToCamelCase(true);
		return configuration;
	}
	
	@Bean
	public PageInterceptor pageInterceptor() {
		PageInterceptor pageInterceptor = new PageInterceptor();
		
		Properties properties = new Properties();
		properties.setProperty("helperDialect", "mysql");
		pageInterceptor.setProperties(properties);
		return pageInterceptor;
	}
	
	
	@Bean
	public SqlSessionFactoryBean sqlSessionFactory(DataSource adDataSource, Configuration configuration, PageInterceptor pageInterceptor) throws IOException {
		
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setTypeAliasesPackage("com.zhenyulaw.jf.entity");
		sqlSessionFactory.setDataSource(adDataSource);
		sqlSessionFactory.setPlugins(new PageInterceptor[]{pageInterceptor});
		
		sqlSessionFactory.setMapperLocations(resolver.getResources("classpath:sqlMap/*Mapper.xml"));
		sqlSessionFactory.setConfiguration(configuration);
		
		return sqlSessionFactory;
	}
	
	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer() {
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer.setBasePackage("com.zhenyulaw.jf.dao");
		mapperScannerConfigurer.setMarkerInterface(tk.mybatis.mapper.common.Mapper.class);
		return mapperScannerConfigurer;
	}
}
