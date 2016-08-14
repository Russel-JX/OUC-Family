package com.brilliance.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.brilliance.base.BaseController;
import com.brilliance.base.ControllerReturns;
import com.brilliance.service.AreasInfoService;
import com.brilliance.utils.Tools;

@Controller
@Scope("request")
public class AreaController extends BaseController{
	@Resource
	private AreasInfoService areasInfoService;

	@RequestMapping(value = "/getAreas", produces = "text/html;charset=utf-8")
	public @ResponseBody String getAreasInfo(HttpServletRequest request) {
		try{
		String cityId = request.getParameter("cityId");
		//String cityId = "110100";
	    returns = new ControllerReturns(areasInfoService.getAreasByCityId(cityId));
		}catch(Exception e){
			returns = Tools.getExceptionControllerRetruns(e);
		}
		logger.info(returns.generateJsonData());
		return returns.generateJsonData();
	}
}
