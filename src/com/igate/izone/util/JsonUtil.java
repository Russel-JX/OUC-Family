package com.igate.izone.util;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUtil {
	public static String formatList2Json(List<?> list,Class classType){
		//获得所有要转化为json的对象的属性域
		//遍历list
		JSONArray jsonArray = JSONArray.fromObject(list);
		String jsonData = jsonArray.toString();
		return jsonData;
	}
	public static String formatObject2Json(Object obj){
		JSONObject jsonObject = JSONObject.fromObject(obj);
		return jsonObject.toString();
	}
	
}
