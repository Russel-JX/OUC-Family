package com.igate.izone.util;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author Xun Jiang
 * @description Aop 切面（Aspect）。用于向Dao层的方法前后，织入日志记录的方法。Spring AOP 方式二：注解方式。
 */
@Component
@Aspect
public class Log4jAspect {
	
	private static Logger log;
	
	/**
	 * @description 对com.igate.izone.dao.impl包下的，任何返回值类型的任何方法，作为切入点。
	 */
	//@Pointcut("execution(* * * (..))")
	//定义切入点。拦截所有dao层实现类中的所有方法。
	@Pointcut("execution(* com.igate.izone.dao.impl.*.*(..))")
	public void method1(){};
	
	//某个切入点将要执行的前置方法
	@Before("com.igate.izone.util.Log4jAspect.method1()")//com.igate.izone.util.Log4jAspect.method1()&&args(user_ID)
	public void logging(JoinPoint jp){
		//创建Log4j日志对象
		log = getLog(jp.getTarget().getClass().getName());
		//System.out.println("............................正在织入..................");
		log.log(Level.INFO,"............................手动创建日志对象，正在织入..................");
		
	};
	
	//某个切入点将要执行的后置方法（（不论是正常返回还是异常退出））
	@After("com.igate.izone.util.Log4jAspect.method1()")
	public void loggingAfter(JoinPoint jp){
//		System.out.println("............................正在织入.................."+"类名："+jp.getTarget().getClass().getName()+"方法名："+jp.getSignature().getName()+".."+jp.getClass()+",,,"+jp.getThis()+",,,"+jp.getThis().getClass()+",,,"+jp.getKind()+",,"+jp.getTarget());
//		for(Object o:jp.getArgs()){
//			System.out.println(".............................................."+o);
//		}
		//Logger myLog = Logger.getLogger(jp.getThis().getClass());
		//log = getLog(jp.getTarget().getClass().getName());
		//日志记录：访问的类、方法、传递的参数值、数据库查询的结果。
		log.log(Level.INFO,"......method......:"+jp.getSignature().getName()+"......Params:"+Arrays.asList(jp.getArgs()));
	}
	
	//记录返回值
	@AfterReturning(pointcut="com.igate.izone.util.Log4jAspect.method1()",returning = "result")
	public void loggingReturnValue(Object result){
		//System.out.println(result+"......xxxxxxxxxx");
		log.log(Level.INFO,"......result......:"+result);
	}	

	//某个切入点发生异常时，执行的通知方法
	@AfterThrowing(pointcut="com.igate.izone.util.Log4jAspect.method1()",throwing="e")
	public void loggingException(JoinPoint jp,Exception e){
		//System.out.println("......exception......:"+e);
		log.log(Level.INFO,"......exception......:"+e);
	}
	//环绕...
	
	
	
	public Logger getLog(String className) {
		return Logger.getLogger(className);
	}

	public void setLog(Logger log) {
		this.log = log;
	}
	
	
	
	
	
}
