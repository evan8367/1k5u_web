package com.zhenyulaw.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhenyulaw.jf.entity.User;

import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User> {
	List<User> userList(@Param("phone")String phone);
}
