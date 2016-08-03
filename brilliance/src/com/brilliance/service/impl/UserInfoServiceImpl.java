/**
 * ============================================================
 * File : UserInfoServiceImpl.java
 * Description : 
 * 
 * Package : com.brilliance.service.impl
 * Author : Michael
 * Last Edited By :
 * Version : 1.0
 * Created on : 2014-3-11
 * History
 * Modified By : Initial Release
 * Classification : Personality
 * Copyright (C) 2014 Michael. All rights reserved
 *
 * ============================================================
 */
package com.brilliance.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.brilliance.base.BaseService;
import com.brilliance.base.BriException;
import com.brilliance.base.ServiceReturns;
import com.brilliance.dao.UserInfoDao;
import com.brilliance.po.OrderInfo;
import com.brilliance.po.UserInfo;
import com.brilliance.service.UserInfoService;

/*******************************************************************************
 * 
 * @Author : Michael
 * @Version : 1.0
 * @Date Created: 2014-3-11
 * @Date Modified :
 * @Modified By :
 * @Contact :
 * @Description :
 * @History :
 * 
 ******************************************************************************/
@Service
@Transactional
public class UserInfoServiceImpl extends BaseService<UserInfo> implements UserInfoService {

	@Resource
	private UserInfoDao userInfoDao;
	/*private static final Log logger = LogFactory
			.getLog(UserInfoServiceImpl.class);*/

	public void saveUserInfo(UserInfo userInfo) {
		logger.debug("userInfo = "+userInfo);
		userInfoDao.addUserInfo(userInfo);
	}

//	public void delete(Integer id) {
//		userInfoDao.delete(id);
//	}
//
//	public void update(UserInfo userInfo) {
//		userInfoDao.update(userInfo);
//
//	}
//
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public UserInfo getUserInfoByMobile(String mobile) throws BriException{
		return userInfoDao.getUserInfoByMobile(mobile);
	}
	
	public ServiceReturns getInternalUserInfoByMobile(String mobile) throws BriException{
		serviceReturns = new ServiceReturns();
		UserInfo userInfo = userInfoDao.getUserInfoByMobile(mobile);
		
		serviceReturns.putData("userInfo",userInfo);
		
		return serviceReturns;
	}

	public ServiceReturns saveUserInfoForMobile(UserInfo userInfo) {
		serviceReturns = new ServiceReturns();
		userInfoDao.save(userInfo);
		return serviceReturns;
		
	}

	public ServiceReturns getUserInfo(UserInfo user) throws BriException {
		serviceReturns = new ServiceReturns();
		serviceReturns.putData("userLst", userInfoDao.getUserInfo(user));
		return serviceReturns;
	}
	
	public ServiceReturns getAllUsersInfo(UserInfo user) throws BriException{
		serviceReturns = new ServiceReturns();
		serviceReturns.putData("userLst", userInfoDao.getAllUsersInfo(user));
		return serviceReturns;
	}


//	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
//	public List<UserInfo> getAll() {
//		return userInfoDao.getAll();
//	}
	
	public void updateLoginTime(UserInfo user) throws BriException{
		logger.debug("user = "+user);
		userInfoDao.updateLoginTime(user);
	}
	
	public void updateCredits(String[] userIds,int credits) throws BriException{
		userInfoDao.updateCredits(userIds, credits);
	}

	public ServiceReturns getUserInfoById(String userId) throws BriException {
		serviceReturns = new ServiceReturns();
		serviceReturns.putData("userInfo", userInfoDao.getUserInfoById(userId));
		return serviceReturns;
	}
	
}
