package com.kkt.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.kkt.demo.interceptor.LoginInterCeptor;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Configuration
public class WebMvcConfig implements WebMvcConfigurer{


	/*
	 * addPathPatterns 어느 url 요청에 사용하고 싶을때 기입.
	 * excludePathPatterns 는 해당 url은 제외하고 싶을때 사용.
	 * 해당 url에 진입하려고 할때 preHandle 메소드가 URI를 가로채 사용하여
	 * 요청값이 true면 진행 false면 예외 동작이 작동한다.
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		log.debug("=================addInterceptors================");
		registry.addInterceptor(new LoginInterCeptor()).addPathPatterns( "/faq", "/faq/{faqSeq}", "/faq/detail/{faqSeq}");
	}


}
