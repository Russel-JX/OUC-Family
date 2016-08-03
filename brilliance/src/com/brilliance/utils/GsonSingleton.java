package com.brilliance.utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GsonSingleton {

	private static Gson gson;

	private GsonSingleton() {
	}

	public static Gson getGsonInstance() {
		if (gson == null) {
			gson = new Gson();
		}
		
		return gson;
	}
	
	public static JsonObject getJsonObject(String src){
		JsonObject json = (JsonObject) new JsonParser().parse(src);
		//return gson.toJsonTree(src).getAsJsonObject();
		return json;
	}
	
	public static void main(String[] args){
		String str = "{message:ok,nu:761389244630,ischeck:1,com:zhongtong,status:200,condition:F00,state:3,data:[{time:2014-07-25 19:49:16,context:苏州吴中八部 派件已签收 ,签收人是 拍照签收,ftime:2014-07-25 19:49:16},{time:2014-07-25 13:50:45,context:苏州吴中八部 科技城 正在派件,ftime:2014-07-25 13:50:45},{time:2014-07-25 13:44:11,context:快件到达 苏州吴中八部，正在分捡中 上一站是 苏州中转部,ftime:2014-07-25 13:44:11},{time:2014-07-25 09:43:43,context:快件离开 苏州中转部，已发往 苏州吴中八部,ftime:2014-07-25 09:43:43},{time:2014-07-25 02:38:01,context:在 无锡中转部 装包，并发往 苏州中转部,ftime:2014-07-25 02:38:01},{time:2014-07-25 02:36:43,context:所在包到达 无锡中转部,ftime:2014-07-25 02:36:43},{time:2014-07-24 20:25:08,context:在 义乌 装包，并发往 无锡中转部,ftime:2014-07-24 20:25:08},{time:2014-07-24 19:17:54,context:快件离开 义乌，已发往 苏州中转部,ftime:2014-07-24 19:17:54},{time:2014-07-24 19:17:54,context:在 义乌 装包，并发往 苏州中转部,ftime:2014-07-24 19:17:54},{time:2014-07-24 18:53:22,context:义乌 林作松 已收件，进入公司分捡,ftime:2014-07-24 18:53:22},{time:2014-07-24 18:06:17,context:在 义乌 装包，并发往 苏州中转部,ftime:2014-07-24 18:06:17}]}";
		JsonObject json = GsonSingleton.getJsonObject(str);
		JsonElement ele =  json.get("data");
		
	}
}
