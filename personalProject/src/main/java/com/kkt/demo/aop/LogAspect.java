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
		log.debug("aop 진입 :::::::::::::" + pjp.getSignature().getModifiers());
		log.debug("aop 진입 :::::::::::::" + pjp.toLongString());
		log.debug("aop 진입 :::::::::::::" + pjp.toShortString());
		log.debug("aop 진입 :::::::::::::" + pjp.getSignature());
		log.debug("aop 진입 :::::::::::::" + pjp.getSourceLocation());
		log.debug("aop 진입 :::::::::::::" + pjp.getSourceLocation());
		log.debug("aop 진입 :::::::::::::" + ob[0]);

		Faq faq = (Faq)ob[0];

		log.debug("faq::::::::::::::::: " + faq);

		return faq;
	}


	@Before("execution(* com.kkt.demo.biz.faq..*.*Controller.getDetail(..))")
	public void before(JoinPoint jp) throws Throwable {
		Object[] ob = jp.getArgs();
		log.debug("Before ::::::::::::::::::::::::::::::::::::::::::::::::::::::: ");
		log.debug(jp.getSignature().getName());			// 단순 메소드 명을 나타냄 ex) save
		log.debug(jp.getSignature().toLongString());	// 생성자, 리턴타입, 해당 컨트롤러의 패키지 경로, 메소드명, 파라미터
		log.debug(jp.getSignature().toShortString());	// 컨트롤러명과 메소드명
		log.debug(jp.getKind());
		log.debug(jp.getStaticPart().toLongString());	// getSignature().toLongString()와 같음 앞에 execution이걸로 묶여있는 부분이 다름
		log.debug(jp.getSourceLocation().getWithinType().toString()); // 해당 소스의 패키지 상세 위치와 파일 종류를 나타냄 ex) class

		log.debug("aop 진입 ::::::::::::::::::::::::::::::: " + ob[0].toString());	// 컨트롤러를 타기 전에 들어와 a태그에 걸려있던 faqSeq만 값이 존재


    }

	@After("execution(* com.kkt.demo.biz.faq..*.*Mapper.insert(..))")
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
		log.debug("afterReturning:::::::::: " + result.toString());
		log.debug("afterReturning:::::::::: " + jp.getSignature().toShortString());
	}

	// 에러가 났을경우 해당 메소드를 타며 에러의 종류, 에러가 난 메소드명을 반환한다.
	@AfterThrowing(pointcut = "execution(* com.kkt.demo.biz.faq..*.*Service.test(..))", throwing = "exception")
	public void AfterThrowing(JoinPoint jp, Exception exception) throws Throwable {
		log.debug("exception ::::::::::::::: " + exception);
		log.debug("exception ::::::::::::::: " + jp.getSignature().toShortString());
		log.debug("exception ::::::::::::::: " + jp.getSignature().toLongString());
		log.debug("exception ::::::::::::::: " + jp.getSignature().getDeclaringTypeName());
	}



}
