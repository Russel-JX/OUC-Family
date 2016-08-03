package com.brilliance.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.brilliance.base.BaseController;
import com.brilliance.base.BriException;
import com.brilliance.po.ExpressInfo;
import com.brilliance.po.UserInfo;
import com.brilliance.service.ExpressInfoService;
import com.brilliance.service.UserInfoService;
import com.brilliance.utils.Constants;
import com.brilliance.utils.Tools;

@Controller
@Scope("request")
public class UserInfoController  extends BaseController {

	@Resource
	private UserInfoService userInfoService;
	
	@Resource
	private ExpressInfoService expressInfoService;
	
	@RequestMapping(value = "/userInfo")
	public ModelAndView userInfo(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("/userinfo/userInfo");
		return view;
	}
	
	@RequestMapping(value = "/forwardToUserStatistics")
	public ModelAndView allUserInfo(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("/userinfo/allUserInfo");
		List<ExpressInfo> expresslst;
		try {
			expresslst = expressInfoService.getAllExpress();
			view.getModel().put("expresslst", expresslst);
		} catch (BriException e) {
			e.printStackTrace();
		}
		return view;
	}
	
	@RequestMapping(value = "/userinfo/showUsers", produces = "text/html;charset=utf-8")
	public @ResponseBody
	String showUsers(HttpServletRequest request) {
		try {
			String userName = request.getParameter("userName");
			String mobileNo = request.getParameter("mobileNo");

			UserInfo userInfo = new UserInfo();
			userInfo.setName(userName);
			userInfo.setMobileNO(mobileNo);
			returns = userInfoService.getUserInfo(userInfo);

			return returns.generateJsonData();
		} catch (Exception e) {
			e.printStackTrace();
			return Tools.getExceptionControllerRetruns(e).generateJsonData();
		}
	}
	
	@RequestMapping(value = "/userinfo/showAllUsers", produces = "text/html;charset=utf-8")
	public @ResponseBody
	String showAllUsers(HttpServletRequest request) {
		try {
			String gender = request.getParameter("genderSelect");
			String provinceId = request.getParameter("provinceId");
			String cityId = request.getParameter("cityId");
			String areaId = request.getParameter("areaId");

			UserInfo userInfo = new UserInfo();
			userInfo.setGender(gender);
			userInfo.setProvinceId(provinceId);
			userInfo.setCityId(cityId);
			userInfo.setAreaId(areaId);
			
			returns = userInfoService.getAllUsersInfo(userInfo);

			return returns.generateJsonData();
		} catch (Exception e) {
			return Tools.getExceptionControllerRetruns(e).generateJsonData();
		}
	}
	
	@RequestMapping(value = "/userinfo/getUserInfoById", produces = "text/html;charset=utf-8")
	public @ResponseBody
	String getUserInfoById(HttpServletRequest request) {
		try {
			String userId = request.getParameter("userId");

			returns = userInfoService.getUserInfoById(userId);

			return returns.generateJsonData();
		} catch (Exception e) {
			return Tools.getExceptionControllerRetruns(e).generateJsonData();
		}
	}
	
	//���û����
	public String giveCreditsToUsers(String[] userIds) {
		try {
			int credits = Constants.SHARE_ADDRESS_SUCCESS;
			userInfoService.updateCredits(userIds, credits);

			return returns.generateJsonData();
		} catch (Exception e) {
			return Tools.getExceptionControllerRetruns(e).generateJsonData();
		}
	}
}
