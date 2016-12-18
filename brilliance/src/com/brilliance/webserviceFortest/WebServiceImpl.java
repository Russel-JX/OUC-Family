package com.brilliance.webserviceFortest;

import java.util.Calendar;

import javax.jws.WebService;

/**
* @ClassName: WebServiceImpl
* @Package com.brilliance.webserviceFortest
* @Description:纯jdk的webservice服务端
* @author Russell Xun Jiang
* @date 2016年12月18日 上午11:02:06
* 定义服务端服务
*/
@WebService
public class WebServiceImpl {
	
	//根据出生年份，计算当前年龄
	public int getAge(int birthYear){
		System.out.println("访问WebService服务，getAge()......");
		return Calendar.getInstance().get(Calendar.YEAR)-birthYear;
		
	}
	//根据姓名，返回欢迎信息
	public String getWelcome(String name){
		System.out.println("访问WebService服务，getWelcome()......");
		return "Hello,"+name;
	}

}
