package com.zhenyulaw.jf.web.service.config;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {
	
//	private static final Logger LOGGER = LoggerFactory.getLogger(SessionListener.class);

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		// 8小时
		event.getSession().setMaxInactiveInterval(28800);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
//		LOGGER.info("session destory");
	}
}