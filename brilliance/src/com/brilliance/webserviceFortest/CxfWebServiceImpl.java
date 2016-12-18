package com.brilliance.webserviceFortest;

import java.util.Calendar;

import javax.jws.WebService;

/**
* @ClassName: WebServiceImpl
* @Package com.brilliance.webserviceFortest
* @Description:Cxf的webservice服务端
* @author Russell Xun Jiang
* @date 2016年12月18日 上午16:08:06
* 定义服务端服务。为了简便，这里直接写实现类，没有定义interface
*/
@WebService
public class CxfWebServiceImpl {
	
	//根据出生年份，计算当前年龄
	public int getAge2(int birthYear){
		System.out.println("访问WebService服务，getAge2()......");
		return Calendar.getInstance().get(Calendar.YEAR)-birthYear;
	}
	//根据姓名，返回欢迎信息
	public String getWelcome2(String name){
		System.out.println("访问WebService服务，getWelcome2()......");
		return "Hello,"+name;
	}

}
