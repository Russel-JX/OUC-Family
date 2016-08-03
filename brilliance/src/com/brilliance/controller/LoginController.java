package com.brilliance.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.brilliance.base.BaseController;
import com.brilliance.base.BriException;
import com.brilliance.base.ControllerReturns;
import com.brilliance.base.MessageContent;
import com.brilliance.base.ServiceReturns;
import com.brilliance.po.AdminInfo;
import com.brilliance.po.UserInfo;
import com.brilliance.service.AdminInfoService;
import com.brilliance.service.CommonService;
import com.brilliance.service.ProvincesInfoService;
import com.brilliance.service.UserInfoService;
import com.brilliance.utils.CacheUtils;
import com.brilliance.utils.Constants;
import com.brilliance.utils.Tools;

@Controller
@Scope("request")
public class LoginController extends BaseController{

	@Resource
	private CommonService commonService;
	
	@Resource
	private UserInfoService userInfoService;
	
	@Resource
	private AdminInfoService adminInfoService;
	
	@Resource
	private ProvincesInfoService provincesInfoService;
	
	@RequestMapping(value = "/loginFromMobile.json", produces = "text/html;charset=utf-8")
	public @ResponseBody
	String loginFromMobile(HttpServletRequest request) {
		try {
			String mobile = request.getParameter("mobile");
			String pwd = request.getParameter("password");
			//String sessionId = request.getParameter("sessionId");
			
			returns = new ServiceReturns();
			
			UserInfo user = userInfoService.getUserInfoByMobile(mobile);
			if (null != user){
				String tmpPwd = user.getPassword();
				if (pwd.equals(tmpPwd)){
					returns.putData("userInfo", user);
					//CacheUtils.setLUserInfo_MB(request, sessionId, user);
					logger.info("login from mobile success... ");
					//cach(user,request);
				}else{
					returns.setSuccess(false);
					returns.setMessage("用户名或密码错误!");
				}
			}else{
				returns.setSuccess(false);
				returns.setMessage("用户不存在，请注册！");
				
			}
			
			returns.getData().put("userInfo", user);
			return returns.generateJsonData();
		} catch (Exception e) {
			return Tools.getExceptionControllerRetruns(e).generateJsonData();
		}
		//return callback+"("+returns.generateJsonData()+")";
		
	}
	
	@RequestMapping(value = "/isLogin_m.json", produces = "text/html;charset=utf-8")
	public @ResponseBody
	String isLogin_m(HttpServletRequest request) {
		try {
			
			returns = new ServiceReturns();
			String sessionId = request.getParameter("sessionId");
			
			UserInfo user = CacheUtils.getLUserInfo_MB(request,sessionId);
			if (null == user){
				returns.setSuccess(false);
				returns.setMessage("用户没登录！");
				
			}
			return returns.generateJsonData();
		} catch (Exception e) {
			return Tools.getExceptionControllerRetruns(e).generateJsonData();
		}
		//return callback+"("+returns.generateJsonData()+")";
		
	}
	
	@RequestMapping(value = "/logout_m.json", produces = "text/html;charset=utf-8")
	public @ResponseBody
	String logout_m(HttpServletRequest request) {
		try {
			
			returns = new ServiceReturns();
			String sessionId = request.getParameter("sessionId");
			
			UserInfo user = CacheUtils.getLUserInfo_MB(request,sessionId);
			if (null == user){
				returns.setSuccess(false);
				returns.setMessage("用户没登录！");
				
			}
			return returns.generateJsonData();
		} catch (Exception e) {
			return Tools.getExceptionControllerRetruns(e).generateJsonData();
		}
		//return callback+"("+returns.generateJsonData()+")";
		
	}
	
	
	@RequestMapping(value = "index")
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("home");
		return view;
	}
	
	@RequestMapping(value = "loginIndex")
	public ModelAndView loginIndex(HttpServletRequest request) {
		logger.info("=================================");
		ModelAndView view = new ModelAndView("/common/login");
		return view;
	}
	
	@RequestMapping(value = "login")
	public ModelAndView login(HttpServletRequest request) {
		String mobile = request.getParameter("mobile");
		String pwd = request.getParameter("password");
		ModelAndView  view = new ModelAndView();
		try {
			UserInfo user = userInfoService.getUserInfoByMobile(mobile);
			if (null != user){
				String tmpPwd = user.getPassword();
				if (pwd.equals(tmpPwd)){
					view.setViewName("home");
					cach(user,request);
					//登录次数加1
					user.setLoginTimes(user.getLoginTimes()+1);
					userInfoService.updateLoginTime(user);
				}else{
					view.getModel().put("msg", "用户名或密码错误!");
					view.setViewName("common/login");
				}
			}else{
				view.setViewName("common/register");
				
			}
		} catch (BriException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return view;
	}
	
	//从内部登录(异步)
	@RequestMapping(value = "/internalLogin", produces = "text/html;charset=utf-8")
	public @ResponseBody String internalLogin(HttpServletRequest request) {
		String mobile = request.getParameter("mobile");
		String pwd = request.getParameter("password");
		UserInfo user = new UserInfo();
		ControllerReturns returns = new ControllerReturns();
		try {
			user = userInfoService.getUserInfoByMobile(mobile);
			if (null != user){
				String tmpPwd = user.getPassword();
				if (pwd.equals(tmpPwd)){//用户名秘密正确
					cach(user,request);
					//登录次数加1
					user.setLoginTimes(user.getLoginTimes()+1);
					userInfoService.updateLoginTime(user);
					returns.putData("userInfo",user);
				}else{//密码不正确
					returns.setMessageType(Constants.MESSAGE_TYPE_WARNING);
					returns.setMessage(new MessageContent(Constants.ERR_COMMON_BAD_ACCESS_PASSWORD));
				}
			}else{//用户名错误
				returns.setMessageType(Constants.MESSAGE_TYPE_WARNING);
				returns.setMessage(new MessageContent(Constants.ERR_COMMON_BAD_ACCESS_USERNAME));
			}
		} catch (BriException e) {
			returns = Tools.getExceptionControllerRetruns(e);
		}
		
		return returns.generateJsonData();
	}
	
	
	private void cach(UserInfo user,HttpServletRequest request){
		HttpSession session = request.getSession();
		//缓存用户登录
		session.setAttribute("userInfo", user);
		//登陆成功后需要缓存相关信息
		returns = provincesInfoService.getAll();
		session.setAttribute("provinces", returns);
		
	}
	
	private void cach_admin(AdminInfo admin,HttpServletRequest request){
		HttpSession session = request.getSession();
		session.setAttribute("adminInfo", admin);
		//登陆成功后需要缓存相关信息
		returns = provincesInfoService.getAll();
		session.setAttribute("provinces", returns);
		
	}
	
	@RequestMapping(value = "admin")
	public ModelAndView adminIndex(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("/common/admin_login");
		return view;
	}
	
	/*管理员登录*/
	@RequestMapping(value = "admin_login")
	public ModelAndView adminLogin(HttpServletRequest request) {
		String name = request.getParameter("name");
		String pwd = request.getParameter("password");
		ModelAndView  view = new ModelAndView();
		try {
			AdminInfo admin = adminInfoService.getAdminInfoByName(name);
			if (null != admin){
				String tmpPwd = admin.getPassword();
				if (pwd.equals(tmpPwd)){
					view.setViewName("userinfo/userInfo");
					cach_admin(admin,request);
//					if(name.equals(Constants.ADMIN)){
						request.getSession().setAttribute("isAdmin", "1");
//					}
				}else{
					view.getModel().put("msg", "用户名或密码错误!");
					view.setViewName("common/admin_login");
				}
			}else{
				view.setViewName("common/admin_login");//admin_register
				
			}
		} catch (BriException e) {
			e.printStackTrace();
		}
		return view;
	}
	
	//注销用户
	@RequestMapping(value = "signoff",method=RequestMethod.POST)
	public ModelAndView signoff(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("userInfo");
		ModelAndView  view = new ModelAndView();
		view.setViewName("home");
		return view;
	}
	
	//管理员注销
	@RequestMapping(value = "signoffAdmin",method=RequestMethod.GET)
	public ModelAndView signoffAdmin(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		ModelAndView  view = new ModelAndView();
		view.setViewName("common/admin_login");
		return view;
	}
	
}
