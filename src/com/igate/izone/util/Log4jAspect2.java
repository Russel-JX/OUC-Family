package com.igate.izone.util;


/**
 * @author Xun Jiang
 * @description Aop 切面（Aspect）。用于向Dao层的方法前后，织入日志记录的方法。
 */
public class Log4jAspect2 {
	
	public void method1(){};
	
	public void logging(){
		System.out.println("这是前置通知");
		
	};
}
