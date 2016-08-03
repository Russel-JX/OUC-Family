package com.brilliance.controller;

import java.util.Calendar;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.brilliance.base.BaseController;
import com.brilliance.base.ServiceReturns;
import com.brilliance.po.URLInfo;
import com.brilliance.po.UserInfo;
import com.brilliance.service.URLService;
import com.brilliance.utils.CacheUtils;
import com.brilliance.utils.Constants;
import com.brilliance.utils.Tools;

@Controller
@Scope("request")
public class LinkController extends BaseController{

	@Resource
	private URLService urlService;
	
	//增加推荐URL
	@RequestMapping(value = "/url/shareAdd", produces = "text/html;charset=utf-8")
	public @ResponseBody
	String shareAdd(HttpServletRequest request) {
		try {
			String userId = request.getParameter("userId");
			String url = request.getParameter("url");
			String type = request.getParameter("type");
			
			returns = new ServiceReturns();
			
			URLInfo urlInfo = new URLInfo();
			urlInfo.setUserId(userId);
			urlInfo.setUrl(url);
			urlInfo.setType(type);
			urlInfo.setStartTime(Tools.getTime());
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, Constants.URL_EXPIRE_TIME);
			urlInfo.setExpireTime(cal.getTime());
			
			System.out.println("URLController........ = "+userId+"|"+url+"|"+type+"|"+urlInfo.getStartTime()+"|"+urlInfo.getExpireTime());
			
			
			returns = urlService.saveURLInfo(urlInfo);
			
			return returns.generateJsonData();
		} catch (Exception e) {
			return Tools.getExceptionControllerRetruns(e).generateJsonData();
		}
	}
	
	//增加推荐成功纪录
	@RequestMapping(value = "/url/recommendAdd", produces = "text/html;charset=utf-8")
	public @ResponseBody
	String recommendAdd(HttpServletRequest request) {
		try {
			String userId = request.getParameter("userId");
			String url = request.getParameter("url");
			String type = request.getParameter("type");
			
			returns = new ServiceReturns();
			
			URLInfo urlInfo = new URLInfo();
			urlInfo.setUserId(userId);
			urlInfo.setUrl(url);
			urlInfo.setType(type);
			urlInfo.setStartTime(Tools.getTime());
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, Constants.URL_EXPIRE_TIME);
			urlInfo.setExpireTime(cal.getTime());
			
			System.out.println("URLController........ = "+userId+"|"+url+"|"+type+"|"+urlInfo.getStartTime()+"|"+urlInfo.getExpireTime());
			
			
			returns = urlService.saveURLInfo(urlInfo);
			
			return returns.generateJsonData();
		} catch (Exception e) {
			return Tools.getExceptionControllerRetruns(e).generateJsonData();
		}
	}
	
	
}
