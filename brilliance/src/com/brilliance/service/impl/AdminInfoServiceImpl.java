/**
 * ============================================================
 * File : AdminInfoServiceImpl.java
 * Description : 
 * 
 * Package : com.brilliance.service.impl
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
package com.brilliance.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.brilliance.base.BaseService;
import com.brilliance.base.BriException;
import com.brilliance.base.ServiceReturns;
import com.brilliance.dao.AdminInfoDao;
import com.brilliance.po.AdminInfo;
import com.brilliance.service.AdminInfoService;

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
@Service
@Transactional
public class AdminInfoServiceImpl extends BaseService<AdminInfo> implements AdminInfoService {

	@Resource
	private AdminInfoDao adminInfoDao;

	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public AdminInfo getAdminInfoByName(String name) throws BriException{
		return adminInfoDao.getAdminInfoByName(name);
	}

	public ServiceReturns saveAdminInfo(AdminInfo adminInfo) {
		serviceReturns = new ServiceReturns();
		adminInfoDao.save(adminInfo);
		return serviceReturns;
		
	}

	public ServiceReturns getAdminInfo(AdminInfo admin) throws BriException {
		serviceReturns = new ServiceReturns();
		serviceReturns.putData("adminList", adminInfoDao.getAdminInfo(admin));
		return serviceReturns;
	}

	public List<AdminInfo> getAdminLst() throws BriException{
		return adminInfoDao.getAdminLst();
	}
	
	public void updateLoginTime(AdminInfo admin) throws BriException{
		logger.debug("admin = "+admin);
		adminInfoDao.updateLoginTime(admin);
	}


	
}
