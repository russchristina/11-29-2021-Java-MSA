package com.revature.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Recall that Spring AOP can (and should) be integrated with AspectJ. AspectJ
 * allows us to easily engage in aspect-oriented programming by allowing us to
 * use annotations to turn Java classes into "aspects". An aspect represents a
 * module which handles a particular cross-cutting concern in your application.
 * 
 * Our module is going to handle logging.
 * 
 * Note that we still want Spring to manage the life cycle of this LoggingAspect,
 * so we'll make it a component.
 *
 */

@Component("loggingAspect")
@Aspect //marks this class as an aspect
public class LoggingAspect {
	
	/*
	 * This is a logging aspect, so of course we need a logger.
	 */
	private Logger logger = LoggerFactory.getLogger("aLogger");

	/**
	 * The first step is to determine the areas we'd like to target for the injection
	 * of code. We can do this by creating a Pointcut. A Pointcut defines a target
	 * for the injection of our advice. Note that you have to use a very particular
	 * Pointcut expression to define where you'd like to do this.
	 * 
	 * For this particular pointcut, we'll target our entire controller package.
	 */
	
	@Pointcut(value = "within(com.revature.controller..*)")
	public void targetControllerMethods() {}
	
	/**
	 * We must then decide when to inject our advice. AspectJ gives us a couple of
	 * options. Note that we specify the method that our Pointcut is attached to
	 * in order to let the framework know which methods to log before.
	 * 
	 * All of your different types of advice have access to a JoinPoint object.
	 * A JoinPoint represents a method that has been invoked.
	 */
	@Before(value = "targetControllerMethods()")
	public void logBeforeControllerMethods(JoinPoint jp) {
		/*
		 * Now we just determine what we're doing before those methods are invoked.
		 */
		this.logger.debug("logging before " 
		 + jp.getSignature().getName() + " in the " 
				+ jp.getSignature().getDeclaringType() + " is invoked.");
	}
	
	/**
	 * After advice will be injected after a method has been invoked.
	 */
	@After(value = "targetControllerMethods()")
	public void logAfterControllerMethods(JoinPoint jp) {
		this.logger.debug("logging after " 
				 + jp.getSignature().getName() + " in the " 
						+ jp.getSignature().getDeclaringType() + " is invoked.");
	}
	
	/**
	 * Note that AfterReturning allows us to capture the return value of a method.
	 * We just have to ensure that our "returning" attribute name matches our paramter
	 * name. Also note that we have chosen to use the "Object" type because we're
	 * not sure which type will be returned by this specific method represented by
	 * this JoinPoint.
	 */
	
	@AfterReturning(value = "targetControllerMethods()", returning = "returnedValue")
	public void logAfterReturningControllerMethods(JoinPoint jp, Object returnedValue) {
		this.logger.debug("logging after return for " 
				 + jp.getSignature().getName() + " in the " 
						+ jp.getSignature().getDeclaringType() + " and it has returned "
						+ returnedValue);
	}
	
	@AfterThrowing(value = "targetControllerMethods()", throwing = "thrownException")
	public void logAfterThrowingControllerMethods(JoinPoint jp, Exception thrownException) {
		this.logger.debug("logging after exception thrown for " 
				 + jp.getSignature().getName() + " in the " 
						+ jp.getSignature().getDeclaringType() + " and exception is: "
						+ thrownException);
	}
	
	/**
	 * Around advice has access to a special, more powerful type of JoinPoint
	 * called a ProceedingJoinPoint. Around advice is so powerful that it can
	 * stop a method invocation entirely. In fact, you have to explicitly
	 * invoke the method using the ProceedingJoinPoint object.
	 * 
	 * As a result, it should be used sparingly.
	 */
	@Around(value = "targetControllerMethods()")
	public Object logAroundControllerMethods(ProceedingJoinPoint pjp) {
		this.logger.debug("logging around (before)" 
				 + pjp.getSignature().getName() + " in the " 
						+ pjp.getSignature().getDeclaringType());
		
		Object returnedValue = null;
		
		try {
			/*
			 * If you do not call proceed(), the target method invocation does
			 * not occur.
			 */
			returnedValue = pjp.proceed();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.logger.debug("logging around (after)" 
				 + pjp.getSignature().getName() + " in the " 
						+ pjp.getSignature().getDeclaringType());
		
		return returnedValue;
	}
}
