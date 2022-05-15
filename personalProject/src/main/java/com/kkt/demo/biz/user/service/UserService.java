package com.kkt.demo.biz.user.service;

import org.springframework.stereotype.Service;

import com.kkt.demo.biz.user.mapper.UserMapper;
import com.kkt.demo.biz.user.vo.User;
import com.kkt.demo.tools.Sha256Tools;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserMapper userMapper;

	public User login(User user) {
		return userMapper.login(user);
	}

	public int signUp(User user) {
		Sha256Tools sha256 = new Sha256Tools();
		user.setPassword(sha256.encryptSHA256(user.getPassword()));

		return userMapper.insert(user);
	}

	public int getUser(User user) {
		return userMapper.getUser(user);
	}
}
