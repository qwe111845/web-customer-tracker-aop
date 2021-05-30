package com.lin.springdemo.aspect;

import java.util.List;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class CRMLoggingAspect {
	
	// setup logger 
	private Logger myLogger = Logger.getLogger(CRMLoggingAspect.class.getName());
	
	// setup pointcut declarations
	@Pointcut("execution(* com.lin.springdemo.controller.*.*(..))")
	private void forControllerPackage() {
		
	}
	
	// do the same for service and dao
	@Pointcut("execution(* com.lin.springdemo.service.*.*(..))")
	private void forServicePackage() {
		
	}
	
	@Pointcut("execution(* com.lin.springdemo.dao.*.*(..))")
	private void forDaoPackage() {
		
	}
	
	@Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
	private void forAppFlow() {
		
	}
	
	// add @Before advice
	@Before("forAppFlow()")
	public void beforeAppFlow(JoinPoint theJoinPoint) {
		
		myLogger.info("\n=====>>> Executing @Before advice on method");
		
		// display the method signature
		MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature();
		
		myLogger.info("Method: " + methodSig);
				
		
	}
	// add @AfterReturning advice
	// add a new advice for @AfterReturning on the findAccounts method
	/*
	@AfterReturning(
			pointcut="execution(* com.lin.aopdemo.dao.AccountDAO.findAccounts(..))", 
			returning="result")
	public void afterFindAccountsAdvice(JoinPoint theJoinPoint) {
		
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("\n========> Excuting @AfterReturning on method: " + method);
		
	} */
}
