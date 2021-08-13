package com.luv2code.springdemo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {

	//setup logger
	private Logger logger = Logger.getLogger(getClass().getName());
	
	//set up pointcut declarations
	@Pointcut("execution(* com.luv2code.springdemo.controller.*.*(..))")
	private void forController() {}
	
	@Pointcut("execution(* com.luv2code.springdemo.service.*.*(..))")
	private void forService() {}
	
	@Pointcut("execution(* com.luv2code.springdemo.dao.*.*(..))")
	private void forDao() {}
	
	@Pointcut("forController() || forService() || forDao()")
	private void forAppflow() {}
	
	//add @before advice
	@Before("forAppflow()")
	public void beforeControllerAdvice(JoinPoint joinPoint)
	{
		logger.info("-----@before advice-----");
		
		//method signature
		String method = joinPoint.getSignature().toString();
		logger.info("Method: " + method);
		
		//display args
		Object[] args = joinPoint.getArgs();
		
		logger.info("---arguments---");
		for(Object temp:args)
		{
			logger.info("arg: "+temp.toString());
		}
	}
	
	//add @AfterReturning advice
	@AfterReturning(pointcut="forAppflow()",
			returning = "result")
	public void afterReturning(JoinPoint joinPoint, Object result)
	{
		logger.info("-----@AfterReturning Advice-----");
		
		//method signature
		String method = joinPoint.getSignature().toString();
		logger.info("Method: " + method);
		
		//display data returned
		logger.info("----result : " + result);
	}
}
