/**
 * ============================================================
 * File : UserInfoDao.java
 * Description : 
 * 
 * Package : com.brilliance.dao
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

package com.brilliance.dao;

import java.util.List;

import com.brilliance.base.BaseDao;
import com.brilliance.base.BriException;
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
public interface UserInfoDao extends BaseDao<UserInfo> {
	
	public UserInfo getUserInfoByMobile(String mobile) throws BriException;
	
	public void addUserInfo(UserInfo user);
	
	public List<UserInfo> getUserInfo(UserInfo user) throws BriException;
	
	public List<UserInfo> getAllUsersInfo(UserInfo user) throws BriException;
	
	public void updateLoginTime(UserInfo user) throws BriException;
	
	public void updateCredits(String[] userIds,int credits) throws BriException;
	
	public UserInfo getUserInfoById(String userId) throws BriException;
	
}
