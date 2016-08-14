package com.brilliance.utils;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessagesTool {
	private static ResourceBundle resource;

	private MessagesTool() {}

	public static ResourceBundle getInstance() {
		String locale = (String)(CacheUtils.getSpringRequest().getSession().getAttribute("locale"));
		if(Tools.isBlank(locale)){
			locale = "zh_CN";
		}
		String language = locale.split("_")[0];
		String country = locale.split("_")[1];
		if (resource == null) {
			resource = ResourceBundle.getBundle("message", new Locale(language,country));
		}
//		String localeTemp = resource.getLocale().getLanguage()+"_"+resource.getLocale().getCountry();
//		if(!localeTemp.equals(locale)){
//			resource = ResourceBundle.getBundle("message", new Locale(language,country));
//		}
		return resource;
	}

	public static String getMessage(String key) {
		String val = getInstance().getString(key);
		if(Tools.isBlank(val)){
			return "";
		}
		return val;
	}
	
	public static void main(String args[]){
		System.out.print(MessagesTool.getMessage("10001"));
	}
	
}
