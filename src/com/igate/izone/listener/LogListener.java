package com.igate.izone.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.PropertyConfigurator;

public class LogListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	public void contextInitialized(ServletContextEvent arg0) {
		/***设置系统变量*/
 		 System.setProperty("izoneLog.dir", arg0.getServletContext().getRealPath("/"));
		/****加载配置文件**/
		PropertyConfigurator.configure(System.getProperty("izoneLog.dir")+"WEB-INF/log4j.properties") ;
	}

}
