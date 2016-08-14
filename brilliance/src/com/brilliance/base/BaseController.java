package com.brilliance.base;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

//所有controller的父类,提供默认的logger以及controllerReturns
public class BaseController {
	protected static Log logger;
	protected ControllerReturns returns;
	
	public BaseController(){
		logger = LogFactory.getLog(this.getClass());
	}
	
}	
