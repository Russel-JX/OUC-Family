/**
 * ============================================================
 * File : AdminInfoDao.java
 * Description : 
 * 
 * Package : com.brilliance.dao
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

package com.brilliance.dao;

import java.util.List;

import com.brilliance.base.BaseDao;
import com.brilliance.base.BriException;
import com.brilliance.po.AdminInfo;

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
public interface AdminInfoDao extends BaseDao<AdminInfo> {
	
	public AdminInfo getAdminInfoByName(String name) throws BriException;
	
	public void addAdminInfo(AdminInfo admin);
	
	public List<AdminInfo> getAdminInfo(AdminInfo admin) throws BriException;
	
	public List<AdminInfo> getAdminLst() throws BriException;
	
	public void updateLoginTime(AdminInfo admin) throws BriException;
	
}
