package com.igate.izone.test;

import org.apache.naming.resources.Resource;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.context.support.XmlWebApplicationContext;

import com.igate.izone.dao.impl.XXXDaoTest;


public class AopLogTest {

	public static void main(String[] args) {
//		@SuppressWarnings("resource")
//		XmlWebApplicationContext xmlctx = new XmlWebApplicationContext();   
//		xmlctx.setConfigLocations(new String[] {"/WEB-INF/ applicationContext.xml"});   
////		xmlctx.setServletContext(pageContext.getServletContext());   
//		xmlctx.refresh();  
//		XXXDaoTest xxx= (XXXDaoTest) xmlctx.getBean("xxxBean"); 
		
//		FileSystemResource resource= new FileSystemResource("C:/Users/xj817306/Russel/dev_tools/IDE/russel_workplace/iZone/WebContent/WEB-INF/applicationContext.xml");   
//		@SuppressWarnings("deprecation")
//		BeanFactory factory= new XmlBeanFactory(resource );  
//		XXXDaoTest xxx= (XXXDaoTest )factory.getBean("xxxBean ");  
		
		//***测试AOP时，将applicationContext.xml复制到src目录下。
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		XXXDaoTest xxx = (XXXDaoTest)context.getBean("xxxBean");
		
		xxx.saveMoney();
	}

}
