package com.zhenyulaw.jf.service.impl;


import java.math.BigDecimal;

import java.util.Date;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhenyulaw.jf.common.exception.BusinessException;
import com.zhenyulaw.jf.common.util.Md5Util;
import com.zhenyulaw.jf.common.util.PageParam;
import com.zhenyulaw.jf.dao.AccountRecordMapper;
import com.zhenyulaw.jf.dao.UserMapper;
import com.zhenyulaw.jf.entity.AccountRecord;
import com.zhenyulaw.jf.entity.Company;
import com.zhenyulaw.jf.entity.User;
import com.zhenyulaw.jf.service.CompanyService;
import com.zhenyulaw.jf.service.NetworkDiskService;
import com.zhenyulaw.jf.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserMapper userMapper;
	@Autowired
	AccountRecordMapper accountRecordMapper;
	@Autowired
	CompanyService companyService;
	@Autowired
	NetworkDiskService networkDiskService;

	@Override
	public User loginUser(String phone, String password) {
		if(phone == null) {
			throw new BusinessException("手机号不能为空");
		}
		if(password == null) {
			throw new BusinessException("密码不能为空");
		}
		User condition = new User();
		condition.setPhone(phone);
		List<User> list = this.userMapper.select(condition);
		if(list.size() == 0){
			throw new BusinessException("该用户不存在");
		}
		password = Md5Util.MD5(password);
		User user = list.get(0);
		if(!password.equals(user.getPassword())){
			throw new BusinessException("密码错误");
		}
		return user;
	}


	@Override
	public int resetPassword(String phone, String password, String code) {
		if(phone == null) {
			throw new BusinessException("手机号不能为空");
		}
		if(password == null) {
			throw new BusinessException("密码不能为空");
		}
		if(code == null) {
			throw new BusinessException("验证码不能为空");
		}
		password = Md5Util.MD5(password);
		User condition = new User();
		condition.setPhone(phone);
		List<User> list = this.userMapper.select(condition);
		User user = new User();
		if(list.size()>0){
			user = list.get(0);
			user.setPassword(password);
		}else{
			throw new BusinessException("用户不存在");
		}
		int result = this.userMapper.updateByPrimaryKeySelective(user);
		return result;
	}

	@Override
	public int getCardPoints(Long userId) {
		Company company =companyService.getCompany(getUser(userId).getCompanyId());
		return company.getCardPoints();
	}
	@Override
	public User getUser(Long userId) {
		User user = new User();
		user.setId(userId);
		user = userMapper.selectOne(user);
		user.setCompanyName(companyService.getCompany(user.getCompanyId()).getName());
		return user;
	}

	@Override
	public void addCardPoints(Long userId, int points) {
		companyService.addCardPoints(getUser(userId).getCompanyId(), points);
	}

	@Override
	public void reduceCardPoints(Long userId, int points) {
		companyService.reduceCardPoints(getUser(userId).getCompanyId(), points);
	}


	@Override
	public PageInfo<User> searchUserList(PageParam page, String phone){
		PageHelper.offsetPage(page.getStartIndex(), page.getPageSize());
		List<User> list = userMapper.userList(phone);
		
		return new PageInfo<User>(list);
	}


	@Override
	@Transactional
	public Long addUser(User user) {
		if (StringUtils.isEmpty(user.getName())) {
			throw new BusinessException("用户名不能为空");
		}
		if (StringUtils.isEmpty(user.getPhone())) {
			throw new BusinessException("手机号不能为空");
		}
		if (user.getCompanyId() ==null) {
			throw new BusinessException("公司不能为空");
		}
		User condition = new User();
		condition.setPhone(user.getPhone());
		List<User> list = this.userMapper.select(condition);
		if(list.size() != 0){
			throw new BusinessException("该手机号已注册");
		}
		if (user.getId() != null) {
			userMapper.updateByPrimaryKeySelective(user);
		}else{
			user.setPassword(Md5Util.MD5(user.getPhone()));
			userMapper.insertSelective(user);
			
			this.networkDiskService.initUserFolder(user.getId());
		}
		
		return userMapper.selectOne(user).getId();
	}

	@Override
	public
	void reduceAccount(Long userId, BigDecimal account){
		companyService.reduceAccount(getUser(userId).getCompanyId(), account);	
	}


	@Override
	public void addAccountRecord(Integer type, BigDecimal amount, Long userId, Long relatedId) {
		
		AccountRecord accountRecord = new AccountRecord();
		accountRecord.setType(type);
		accountRecord.setAmount(amount);
		accountRecord.setUserId(userId);
		accountRecord.setRelatedId(relatedId);
		accountRecord.setCreateTime(new Date());
		
		this.accountRecordMapper.insert(accountRecord);
	}

	
}
