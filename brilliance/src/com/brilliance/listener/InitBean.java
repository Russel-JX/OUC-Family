package com.brilliance.listener;

import javax.xml.ws.Endpoint;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.brilliance.webserviceFortest.CxfWebServiceImpl;
import com.brilliance.webserviceFortest.WebServiceImpl;

/**
* @ClassName: InitBean
* @Package com.brilliance.listener
* @Description: 启动Spring容器时，发布WebService服务
* @author Russell Xun Jiang
* @date 2016年12月18日 下午12:00:13
* JDK原生服务和CXF矿建的服务发布一样。
*/
@Component
public class InitBean implements InitializingBean{
	public void afterPropertiesSet() throws Exception {
		System.out.println("启动Spring容器时，发布服务......");
		//定义服务访问地址。地址可任意指定
		String urlStr = "http://127.0.0.1:8080/brilliance/JDKService";
		String urlStr2 = "http://127.0.0.1:8080/brilliance/CXFService";
		System.out.println("URL是："+urlStr);
		System.out.println("URL是："+urlStr2);
		//发布服务端服务
		Endpoint.publish(urlStr, new WebServiceImpl());//发布原生服务
		Endpoint.publish(urlStr2, new CxfWebServiceImpl());//发布CXF服务
	}
}
