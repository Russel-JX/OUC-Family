package com.brilliance.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.brilliance.base.BaseController;
import com.brilliance.base.ControllerReturns;
import com.brilliance.service.CitiesInfoService;
import com.brilliance.utils.Tools;

@Controller
@Scope("request")
public class CityController extends BaseController {
	@Resource
	private CitiesInfoService citiesInfoService;

	@RequestMapping(value = "/getCities", produces = "text/html;charset=utf-8")
	public @ResponseBody String getCitiesInfo(HttpServletRequest request) {
		try{
		String provinceId = request.getParameter("provinceId");
		//String provinceId = "110000";
	    returns = citiesInfoService.getCitiesByProvinceId(provinceId);
		}catch(Exception e){
			returns = Tools.getExceptionControllerRetruns(e);
		}
		logger.info(returns.generateJsonData());
		return returns.generateJsonData();
	}
}
