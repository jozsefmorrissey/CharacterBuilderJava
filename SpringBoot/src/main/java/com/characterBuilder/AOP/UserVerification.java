package com.characterBuilder.AOP;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class UserVerification {

	@Before("allControllers()")
	public Object verifyUser(){
		System.out.println("hello world... you shall pass... no user verification");
		return null;
	}
	
	@Pointcut("execution(* com.characterBuilder.controllers..*(..))")
	public void allControllers(){}
}
