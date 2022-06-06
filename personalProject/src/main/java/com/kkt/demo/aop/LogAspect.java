package com.kkt.demo.aop;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.kkt.demo.biz.faq.vo.Faq;
import com.kkt.demo.biz.user.vo.User;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LogAspect {

	@Around("execution(* com.kkt.demo.biz.faq..*.*Service.save(..))")
	public Object faqLog(ProceedingJoinPoint pjp) throws Throwable {

		Object[] ob = pjp.getArgs();
		log.debug("aop 진입 :::::::::::::" + pjp.getKind().toString());
		log.debug("aop 진입 :::::::::::::" + pjp.toLongString());
		log.debug("aop 진입 :::::::::::::" + pjp.toShortString());
		log.debug("aop 진입 :::::::::::::" + pjp.getSignature());
		log.debug("aop 진입 :::::::::::::" + pjp.getSourceLocation());

		Faq faq = (Faq)ob[0];
		faq.setModrId("rudxo");
		faq.setRgstrId("rudxo");

		return faq;
	}


	@Before("execution(* com.kkt.demo.biz.faq..*.*Controller.getDetail(..))")
	public void before(JoinPoint jp) throws Throwable {
		Object[] ob = jp.getArgs();
		log.debug("Before ::::::::::::::::::::::::::::::::::::::::::::::::::::::: ");
		log.debug(jp.getSignature().getName());			// 단순 메소드 명을 나타냄 ex) save
		log.debug(jp.getSignature().toLongString());	//
		log.debug(jp.getSignature().toShortString());
		log.debug(jp.getKind());
		log.debug(jp.getStaticPart().toLongString());
		log.debug(jp.getSourceLocation().getWithinType().toString());

		log.debug("aop 진입 ::::::::::::::::::::::::::::::: " + ob[0].toString());

		Faq faq = (Faq) ob[0];
		faq.setRgstrId("rudxo");
		faq.setModrId("rudxo");

    }

	@After("execution(* com.kkt.demo.biz.faq..*.*Service.getDetail(..))")
	public void after(JoinPoint jp) throws Throwable {
		Object[] ob = jp.getArgs();
		log.debug("After ::::::::::::::::::::::::::::::::::::::::::::::::::::::: ");
		log.debug(jp.getSignature().getName());			// 단순 메소드 명을 나타냄 ex) save
		log.debug(jp.getSignature().toLongString());	//
		log.debug(jp.getSignature().toShortString());
		log.debug(jp.getKind());
		log.debug(jp.getStaticPart().toLongString());
		log.debug(jp.getSourceLocation().getWithinType().toString());

		log.debug("aop 진입 ::::::::::::::::::::::::::::::: " + ob[0].toString());

	}

	@AfterReturning(pointcut = "execution(* com.kkt.demo.biz.faq..*.*Service.getList(..))", returning = "result")
	public void afterReturning(JoinPoint jp, List<Faq> result) throws Throwable {
		log.debug(result.toString());
	}

//	@AfterThrowing(pointcut = "execution(* com.kkt.demo.biz.faq..*.*Service.save(..))", throwing = "exception")
//	public void AfterThrowing(JoinPoint jp, Exception exception) throws Throwable {
//		log.debug(exception.getMessage());
//	}



}
