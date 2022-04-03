package com.kkt.demo.biz.user.service;

import org.springframework.stereotype.Service;

import com.kkt.demo.biz.user.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserMapper userMapper;
}
