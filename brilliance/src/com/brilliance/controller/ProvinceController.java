package com.brilliance.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.brilliance.base.BaseController;
import com.brilliance.base.ControllerReturns;
import com.brilliance.service.ProvincesInfoService;
import com.brilliance.utils.Tools;

@Controller
@Scope("request")
public class ProvinceController extends BaseController {
	@Resource
	private ProvincesInfoService provincesInfoService;

	@RequestMapping(value = "/getProvinces", produces = "text/html;charset=utf-8")
	public @ResponseBody
	String getProvincesInfo() {
		try {
			// returns = new ControllerReturns(provincesInfoService.getAll());
			returns = provincesInfoService.getAll();
		} catch (Exception e) {
			returns = Tools.getExceptionControllerRetruns(e);
		}
		return returns.generateJsonData();
	}

	@RequestMapping(value = "/getProvincesFromCach", produces = "text/html;charset=utf-8")
	public @ResponseBody
	String getProvinces(HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();
			ControllerReturns ctls = (ControllerReturns) session
					.getAttribute("provinces");
			if (null != ctls) {
				returns = ctls;
			} else {
				returns = provincesInfoService.getAll();
			}
		} catch (Exception e) {
			returns = Tools.getExceptionControllerRetruns(e);
		}
		return returns.generateJsonData();
	}
}
/**
 * 1：解决返回json数据中文乱码的问题 在@RequestMapping里面加上produces属性
 * 
 * @RequestMapping(value = "", produces = "text/html;charset=UTF-8")
 * @RequestMapping(value = "", produces = "text/html;charset=utf-8")
 */
