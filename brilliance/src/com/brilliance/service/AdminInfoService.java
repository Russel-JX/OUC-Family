/**
 * ============================================================
 * File : AdminInfoService.java
 * Description : 
 * 
 * Package : com.brilliance.service
 * Author : Russel
 * Last Edited By :
 * Version : 1.0
 * Created on : 2014-7-2
 * History
 * Modified By : Initial Release
 * Classification : Personality
 * Copyright (C) 2014 Russel. All rights reserved
 *
 * ============================================================
 */

package com.brilliance.service;

import java.util.List;

import com.brilliance.base.BriException;
import com.brilliance.base.ServiceReturns;
import com.brilliance.po.AdminInfo;
import com.brilliance.po.UserInfo;


/*******************************************************************************
 * 
 * @Author : Russel
 * @Version : 1.0
 * @Date Created: 2014-7-2
 * @Date Modified :
 * @Modified By :
 * @Contact :
 * @Description :
 * @History :
 * 
 ******************************************************************************/
public interface AdminInfoService{
	
	public ServiceReturns saveAdminInfo(AdminInfo dminInfo);
	
	public AdminInfo getAdminInfoByName(String name) throws BriException;
	
	public ServiceReturns getAdminInfo(AdminInfo admin) throws BriException;
	
	public List<AdminInfo> getAdminLst() throws BriException;
	
	public void updateLoginTime(AdminInfo admin) throws BriException;
}
