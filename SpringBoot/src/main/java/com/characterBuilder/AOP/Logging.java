package com.characterBuilder.AOP;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Logging {
	private Logger log = Logger.getLogger("characterBuilder");
	
	/**
	 * All fatal errors should be in the nonRecoverableErrors 
	 * package that will allow their creation to be detected.
	 * 
	 * @param joinPoint
	 * @return
	 */
	@Before("beforeFatal()")
	public Object nonRecoverableErrors(JoinPoint joinPoint){
		log.fatal("Fatal error detected");
		return null;
	}
	
	@Pointcut("execution(*com.characterBuilder.throwable.nonRecoverableErrors.**.new(..))")
	public void beforeFatal(){}
	
	/**
	 * All ignored errors should be in the ignoredErrors package
	 * allowing this function to catch thier creation.
	 * 
	 * @return
	 */
	@Before("beforeError()")
	public Object ignoredErrors(){
		log.error("Error detected but ignored");
		return null;
	}
	@Pointcut("execution(*com.characterBuilder.throwable.ignoredErrors.**.new(..))")
	public void beforeError(){}
	
	/**
	 * All caught exceptions should be in the exception folder and
	 * this function will detect when the constructor is called.
	 * 
	 * @return
	 */
	@Before("beforeException()")
	public Object exception(){
		log.warn("Exception detected");
		return null;
	}
	@Pointcut("execution(*com.characterBuilder.throwable.exceptions.**.new(..))")
	public void beforeException(){}
	
	/**
	 * Surrounds controllers intended to display data corresponding
	 * to internet traffic.
	 * 
	 * @param pjp
	 * @return
	 * @throws Throwable 
	 */
	@Around("allControllers()")
	public Object traffic(ProceedingJoinPoint pjp) throws Throwable{
		Object obj = null;
		log.info("Incoming request: " + pjp.getSignature() + 
					" - With Arguments: " + pjp.getArgs());
		try{
			obj = pjp.proceed();
		}finally{}
		log.info(pjp.getSignature() + " returned: " + obj);
		return obj;
	}
	@Pointcut("execution(* com.characterBuilder.controllers..*(..))")
	public void allControllers(){}
	
	/**
	 * Surrounds alll methods existing in this project and logs
	 * the arguments and the return values.
	 * 
	 * @param pjp
	 * @return
	 * @throws Throwable 
	 */
	@Around("allMethods()")
	public Object verifyUser(ProceedingJoinPoint pjp) throws Throwable{
		Object obj = null;
		log.debug("Incoming request: " + pjp.getSignature() + 
					" - With Arguments: " + pjp.getArgs());
		try{
			obj = pjp.proceed();
		}finally{}
		log.debug(pjp.getSignature() + " returned: " + obj);
		return obj;
	}
	@Pointcut("execution(* com.characterBuilder..*(..))")
	public void allMethods(){}
}
