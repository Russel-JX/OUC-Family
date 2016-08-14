package com.brilliance.utils;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
* @ClassName: LogAspect
* @Package com.brilliance.utils
* @Description: 日志记录切面
* @author Russell Xun Jiang
* @date 2016年4月11日 下午2:46:51
*/
@Aspect
public class LogAspect {
	
	Logger log = Logger.getLogger(LogAspect.class);
	//拦截controller类下的所有方法
	public static final String COMMON_LOG_URL="execution(* com.brilliance.controller..*.*(..))";
	
	//方法执行之前
	@Before(value=COMMON_LOG_URL)
	private void logBefore(JoinPoint point) {
		log.info("------------"+point.getSignature().getName()+" 开始--------------");

		System.out.println("@Before：目标方法为：" + point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName());
//		System.out.println("@Before：参数为：" + Arrays.toString(point.getArgs()));
//		System.out.println("@Before：被织入的目标对象为：" + point.getTarget());
	}
	
	//方法执行之后
	@After(value=COMMON_LOG_URL)
	private void logAfter(JoinPoint point){
		log.info("------------"+point.getSignature().getName()+" 结束--------------");
	}
	
	//获取方法返回值
	@AfterReturning(value=COMMON_LOG_URL,returning="result")
	private void logAfterReturn(JoinPoint point,Object result){
		log.info("---------------方法："+point.getSignature().getName()+" 的返回结果是:--------------"+result);
	}
	
	//获取发生异常时的异常对象
	@AfterThrowing(value=COMMON_LOG_URL,throwing="ex")
	private void logException(JoinPoint point,Exception ex){
		log.info("---------------方法："+point.getSignature().getName()+" 发生异常，异常消息是:--------------"+ex.getMessage());
	}

}
