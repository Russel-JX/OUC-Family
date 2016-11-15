package com.brilliance.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.brilliance.base.BaseController;
import com.brilliance.service.impl.TestServiceImpl;


@Controller
@Scope("request")
public class TestController extends BaseController{
	//日志
	Logger log = Logger.getLogger(BaseController.class);

	@Resource
	private TestServiceImpl testServiceImpl;
	
	@RequestMapping(value = "/test/saveDiary",method=RequestMethod.GET, produces = "text/html;charset=utf-8")
//	@Transactional
	public void saveDiary(HttpServletRequest request) throws Exception{
		log.info("\n------新增日记 开始-----");
		testServiceImpl.A();
		log.info("\n------新增日记 结束-----");
	}
	
	
}
