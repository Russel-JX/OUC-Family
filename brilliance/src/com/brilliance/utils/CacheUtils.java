package com.brilliance.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.brilliance.po.AdminInfo;
import com.brilliance.po.UserInfo;

public class CacheUtils {

	public static HttpServletRequest getSpringRequest() {
		HttpServletRequest curRequest = ((ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes()).getRequest();
		return curRequest;
	}

	/**
	 * 获取电脑端的登录缓存
	 * 
	 * @param request
	 * @return
	 */
	public static UserInfo getUserInfo_PC(HttpServletRequest request) {
		HttpSession session = request.getSession();
		return (UserInfo) session.getAttribute("userInfo");
	}

	/**
	 * 获取电脑端的管理员登录缓存
	 * 
	 * @param request
	 * @return
	 */
	public static AdminInfo getAdmInfo(HttpServletRequest request) {
		HttpSession session = request.getSession();
		return (AdminInfo) session.getAttribute("adminInfo");
	}

	/**
	 * 获取手机端的登录缓存
	 * 
	 * @param request
	 * @return
	 */
	public static UserInfo getLUserInfo_MB(HttpServletRequest request,
			String key) {
		HttpSession session = request.getSession();
		return (UserInfo) session.getAttribute(key);
	}

	/**
	 * 获取手机端的登录缓存
	 * 
	 * @param request
	 * @return
	 */
	public static void setLUserInfo_MB(HttpServletRequest request, String key,
			UserInfo user) {
		HttpSession session = request.getSession();
		session.setAttribute(key, user);
	}

	/**
	 * 删除登录信息(logout时)
	 * 
	 * @param request
	 * @return
	 */
	public static void remLUserInfo_m(HttpServletRequest request, String key) {
		HttpSession session = request.getSession();
		session.removeAttribute(key);
	}

	/**
	 * 获取服务的url基本地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getAppServerPath(HttpServletRequest request) {
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/";
		return basePath;
	}
	
	
	/**
	 * 获取服务的url基本地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getAppImgPath(HttpServletRequest request) {
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort()+Constants.DOC_BASE_PATH;
		return basePath;
	}
}
