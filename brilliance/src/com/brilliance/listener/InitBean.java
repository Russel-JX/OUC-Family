package com.brilliance.listener;

import javax.xml.ws.Endpoint;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.brilliance.webserviceFortest.WebServiceImpl;

/**
* @ClassName: InitBean
* @Package com.brilliance.listener
* @Description: 启动Spring容器时，发布WebService服务
* @author Russell Xun Jiang
* @date 2016年12月18日 下午12:00:13
*/
@Component
public class InitBean implements InitializingBean{
	public void afterPropertiesSet() throws Exception {
		System.out.println("启动Spring容器时，发布服务......");
		//定义服务访问地址。地址可任意指定
		String urlStr = "http://127.0.0.1:8080/xx/aa";
		System.out.println("URL是："+urlStr);
		//发布服务端服务
		Endpoint.publish(urlStr, new WebServiceImpl());
	}
}
