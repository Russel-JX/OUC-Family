package com.brilliance.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.brilliance.base.BaseController;

@Controller
@Scope("request")
public class CommonController extends BaseController{
	
	@RequestMapping(value = "/trackAddress", produces = "text/html;charset=utf-8")
	public ModelAndView foward(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("common/trackAddress");
		return view;// 直接跳转到jsp/order/index.jsp页面
	}
}
