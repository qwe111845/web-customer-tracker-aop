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
		
		// display the method signature
		MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature();
		myLogger.info("======>>> Executing @Before advice on method: " + methodSig + "\n");
		
		Object[] args = theJoinPoint.getArgs();
		
		for (Object tempArg: args) {
			myLogger.info("=======> argument: " + tempArg);
		}
				
		
	}
	// add @AfterReturning advice
	// add a new advice for @AfterReturning on the findAccounts method
	
	@AfterReturning(
			pointcut="forAppFlow()", 
			returning="result")
	public void afterAppFlowAdvice(JoinPoint theJoinPoint) {
		
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("========> Excuting @AfterReturning on method: " + method + "\n");
		
	} 
}
