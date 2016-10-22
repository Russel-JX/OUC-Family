package com.igate.izone.controller.impl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * @author Yicheng Zhou
 */
@Controller
@RequestMapping(value="/main")
public class MainController {
	/**
	 * 跳转myZone的控制器
	 * @return 跳转页面的地址WEB-INF/views/iZone/myZone.html
	 */
	@RequestMapping(value="/goToMyZone",method=RequestMethod.GET)
	public String goToMyZone(){
		return "iZone/myZone";
	}
	/**
	 * 跳转oa界面
	 * @return 跳转页面的地址WEB-INF/views/iZone/oa.html
	 */
	@RequestMapping(value="/goToOA",method=RequestMethod.GET)
	public String goToOA(){
		return "oa/oa";
	}
	
}
