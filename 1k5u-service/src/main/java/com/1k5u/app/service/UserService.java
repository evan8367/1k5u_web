package com.zhenyulaw.jf.service;

import java.math.BigDecimal;

import com.github.pagehelper.PageInfo;
import com.zhenyulaw.jf.common.util.PageParam;
import com.zhenyulaw.jf.entity.User;

public interface UserService {
	
	User loginUser(String phone, String password);
	
	Long addUser(User user);
	
	int resetPassword(String phone, String password, String code);
	
	//获取积分
	int getCardPoints(Long userId);
	
	//增加积分
	void addCardPoints(Long userId, int points);
	
	//扣除积分
	void reduceCardPoints(Long userId, int points);
	
	//所有用户列表
	public PageInfo<User> searchUserList(PageParam page, String phone);
	
	User getUser(Long id);
	
	//扣钱
	void reduceAccount(Long userId, BigDecimal acount);
	
	void addAccountRecord(Integer type, BigDecimal amount, Long userId, Long relatedId);

		
}
