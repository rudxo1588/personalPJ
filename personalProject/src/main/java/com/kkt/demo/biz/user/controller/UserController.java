package com.kkt.demo.biz.user.controller;

import org.springframework.stereotype.Controller;

import com.kkt.demo.biz.user.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

}
