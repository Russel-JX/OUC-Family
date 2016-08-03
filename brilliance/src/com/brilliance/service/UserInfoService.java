/**
 * ============================================================
 * File : UserInfoService.java
 * Description : 
 * 
 * Package : com.brilliance.service
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

package com.brilliance.service;

import com.brilliance.base.BriException;
import com.brilliance.base.ServiceReturns;
import com.brilliance.po.UserInfo;


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
public interface UserInfoService{
	
	public void saveUserInfo(UserInfo userInfo);
	
	public ServiceReturns saveUserInfoForMobile(UserInfo userInfo);
	
	public UserInfo getUserInfoByMobile(String mobile) throws BriException;
	
	public ServiceReturns getInternalUserInfoByMobile(String mobile) throws BriException;
	
	public ServiceReturns getUserInfoById(String userId) throws BriException;
	
	public ServiceReturns getUserInfo(UserInfo user) throws BriException;
	
	public ServiceReturns getAllUsersInfo(UserInfo user) throws BriException;
	
	public void updateLoginTime(UserInfo user) throws BriException;
	
	public void updateCredits(String[] userIds,int credits) throws BriException;
}
