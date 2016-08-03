package com.brilliance.controller;

import javax.annotation.Resource;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.brilliance.base.BaseController;
import com.brilliance.po.UserInfo;
import com.brilliance.service.UserInfoService;
import com.brilliance.utils.Tools;

@Controller
@Scope("request")
public class AdminInfoController  extends BaseController {

	@Resource
	private UserInfoService userInfoService;
	
	
	
	
}
