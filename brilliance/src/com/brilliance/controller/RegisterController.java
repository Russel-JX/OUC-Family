package com.brilliance.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.brilliance.base.BaseController;
import com.brilliance.base.BriException;
import com.brilliance.base.ControllerReturns;
import com.brilliance.po.AdminInfo;
import com.brilliance.po.RecommendInfo;
import com.brilliance.po.UserInfo;
import com.brilliance.service.AdminInfoService;
import com.brilliance.service.CommonService;
import com.brilliance.service.RecommendService;
import com.brilliance.service.UserInfoService;
import com.brilliance.utils.Constants;
import com.brilliance.utils.Tools;
import com.mysql.jdbc.StringUtils;

@Controller
@Scope("request")
public class RegisterController extends BaseController {
	
	@Resource
	private UserInfoService userInfoService;
	
	@Resource
	private AdminInfoService adminInfoService;
	
	@Resource
	private CommonService commonService;
	
	@Resource
	private RecommendService recommendService;

	@RequestMapping(value = "/register")
	public ModelAndView register(HttpServletRequest request) {
		String mobile = request.getParameter("mobile");
		String email = request.getParameter("email");
		String pwd = request.getParameter("pass");
		String address = request.getParameter("address");
		String userName = request.getParameter("userName");
		String provinceId = request.getParameter("province");
		String cityId = request.getParameter("city");
		String areaId = request.getParameter("area");
		
		//推荐人id
		String userId = request.getParameter("userId");
		String source = request.getParameter("source");
		
		
		try {
			String tmpId = commonService.getMaxUserId();
			if (StringUtils.isNullOrEmpty(tmpId)){
				tmpId = Constants.DEFAULT_ID;
			}else{
				tmpId = String.valueOf((Integer.parseInt(tmpId)+1));
			}
			UserInfo user = new UserInfo();
			user.setMobileNO(mobile);
			user.setUserId(tmpId);
			user.setEmail(email);
			user.setPassword(pwd);
			user.setProvinceId(provinceId);
			user.setCityId(cityId);
			user.setAreaId(areaId);
			user.setAddress(address);
			user.setName(userName);
			user.setStatus(Constants.ACTIVE);
			user.setCreateTime(Tools.getTime());
			userInfoService.saveUserInfo(user);
			
			if(!Tools.isEmpty(userId)){
				RecommendInfo recommendInfo = new RecommendInfo();
				recommendInfo.setUserId(userId);
				recommendInfo.setNomineeId(user.getId().toString());
				recommendInfo.setSource(Integer.parseInt(source));
				recommendInfo.setCompleteDate(Tools.getTime());
				
				System.out.println("RegisterController........ = "+recommendInfo.getUserId()+"|"+recommendInfo.getNomineeId()+"|"+recommendInfo.getSource()+"|"+recommendInfo.getCompleteDate());
				
				recommendService.saveRecommendInfo(recommendInfo);
			}
			
		} catch (BriException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ModelAndView view = new ModelAndView("redirect:/index.html");
		return view;
	}
	
	@RequestMapping(value = "/mobileExists")
	public ModelAndView mobileExists(HttpServletRequest request) {
		String mobile = request.getParameter("mobile");
		returns = new ControllerReturns();
		try {
			UserInfo user = userInfoService.getUserInfoByMobile(mobile);
			
			if (null != user){
				returns.setMessage("该号码已注册.");
				/*String tmpPwd = user.getPassword();
				if (pwd.equals(tmpPwd)){
					view.setViewName("home");
					cach(user,request);
				}else{
					view.getModel().put("msg", "用户名或密码错误!");
					view.setViewName("common/login");
				}*/
			}
			
			
		} catch (BriException e) {
			e.printStackTrace();
		}
		
		ModelAndView view = new ModelAndView("redirect:/index.html");
		return view;
	}
	
	
	@RequestMapping(value = "/registerForMobile")
	public @ResponseBody String registerForMobile(HttpServletRequest request) {
		String mobile = request.getParameter("mobile");
		String email = request.getParameter("email");
		String pwd = request.getParameter("pass");
		String address = request.getParameter("address");
		String userName = request.getParameter("userName");
		String longitude = request.getParameter("longitude");
		String latitude = request.getParameter("latitude");
		
		try {
			String tmpId = commonService.getMaxUserId();
			if (StringUtils.isNullOrEmpty(tmpId)){
				tmpId = Constants.DEFAULT_ID;
			}else{
				tmpId = String.valueOf((Integer.parseInt(tmpId)+1));
			}
			UserInfo user = new UserInfo();
			user.setMobileNO(mobile);
			user.setUserId(tmpId);
			user.setEmail(email);
			user.setPassword(pwd);
			user.setAddress(address);
			user.setName(userName);
			user.setStatus(Constants.ACTIVE);
			user.setLongitude(longitude);
			user.setLatitude(latitude);
			
			
			user.setCreateTime(Tools.getTime());
			returns = userInfoService.saveUserInfoForMobile(user);
		} catch (BriException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returns.generateJsonData();
	}
	
	@RequestMapping(value = "/backToLogin")
	public ModelAndView backToLogin(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("redirect:/index.html");
		return view;
	}
	
	@RequestMapping(value = "/backToAdminLogin")
	public ModelAndView backToAdminLogin(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("redirect:/admin_index.html");
		return view;
	}
	
	@RequestMapping(value = "/forwardToRegister")
	public ModelAndView forwardToRegister(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("/common/register");
		
		String userId = request.getParameter("userId");
		String source = request.getParameter("source");
		System.out.println(userId+"|"+source);
		//推荐的url
		if(!Tools.isEmpty(userId)){
			view.getModel().put("userId", userId);
			view.getModel().put("source", source);
		}
		return view;
	}
	
	//管理员注册
	@RequestMapping(value = "/forwardToAdminRegister")
	public ModelAndView forwardToAdminRegister(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("/common/admin_register");
		
		return view;
	}
	
	//管理员注册
	@RequestMapping(value = "/adminRegister")
	@Transactional
	public ModelAndView adminRegister(HttpServletRequest request) {
		String pwd = request.getParameter("pass");
		String name = request.getParameter("name");
		
		try {
		//wrong code, the tmpId can't from user_info table
			String tmpId = commonService.getMaxUserId();
			if (StringUtils.isNullOrEmpty(tmpId)){
				tmpId = Constants.DEFAULT_ID;
			}else{
				tmpId = String.valueOf((Integer.parseInt(tmpId)+1));
			}
			AdminInfo admin = new AdminInfo();
			admin.setName(name);
			admin.setPassword(pwd);
			admin.setAccountId(tmpId);
			//admin.setUpdateBy(1);
			admin.setCreateTime(Tools.getTime());
			admin.setStatus(Constants.ACTIVE);
			adminInfoService.saveAdminInfo(admin);
			
		} catch (BriException e) {
			e.printStackTrace();
		}
		
		ModelAndView view = new ModelAndView("redirect:/index.html");
		return view;
	}
}
