package com.zhenyulaw.jf.web.portal.config;

import org.springframework.stereotype.Component;

/**
 * 
 * 自动任务
 *
 * @author Evan
 */
@Component
public class AutoJobConfig {

//	private volatile Boolean isRunning = false;
//	private volatile Integer retryTimes = 0;

//	@Autowired
//	AppManagementService appManagementService;

//	@Scheduled(cron = "0 0/10 * * * ?")
////	@Scheduled(cron = "0 0 1 * * ?")
//	public void settle() {
//		// 如果在运行中，退出
//		if (this.isRunning)
//			return;
//
//		this.isRunning = true;
//		this.retryTimes = 0;
//		try {
//			this.appManagementService.settle();
//		} catch (Exception e) {
//			if (this.retryTimes >= 3) {
//				e.printStackTrace();
//			} else {
//				this.isRunning = false;
//				this.retryTimes += 1;
//				this.settle();
//			}
//		} finally {
//			this.isRunning = false;
//		}
//	}
}
