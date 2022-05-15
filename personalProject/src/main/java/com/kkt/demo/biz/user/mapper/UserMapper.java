package com.kkt.demo.biz.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kkt.demo.biz.user.vo.User;


@Mapper
public interface UserMapper {

	User login(User user);

	int insert(User user);

	int getUser(User user);
}
