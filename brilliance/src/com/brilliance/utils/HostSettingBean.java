package com.brilliance.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class HostSettingBean implements ApplicationContextAware {
	private ApplicationContext context;
	private String  locale;
	
	private void init(){
		Constants.LOCALE = locale;
	}
	public void setApplicationContext(ApplicationContext cc)
			throws BeansException {
		init();
		context = cc;
	}
	public ApplicationContext getContext() {
		return context;
	}
	public void setContext(ApplicationContext context) {
		this.context = context;
	}
}
