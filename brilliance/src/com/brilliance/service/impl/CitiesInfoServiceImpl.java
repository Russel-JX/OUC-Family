/**
 * ============================================================
 * File : CitiesInfoServiceImpl.java
 * Description : 
 * 
 * Package : com.brilliance.service.impl
 * Author : Michael
 * Last Edited By :
 * Version : 1.0
 * Created on : 2014-3-15
 * History
 * Modified By : Initial Release
 * Classification : Brilliance Confidential
 * Copyright (C) 2014 Brilliance Team. All rights reserved
 *
 * ============================================================
 */

package com.brilliance.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.brilliance.base.BaseService;
import com.brilliance.base.ServiceReturns;
import com.brilliance.dao.CitiesInfoDao;
import com.brilliance.po.CitiesInfo;
import com.brilliance.service.CitiesInfoService;

/*******************************************************************************
 * 
 * @Author : Michael
 * @Version : 1.0
 * @Date Created: 2014-3-15
 * @Date Modified :
 * @Modified By :
 * @Contact :
 * @Description :
 * @History :
 * 
 ******************************************************************************/
@Service
@Transactional
public class CitiesInfoServiceImpl extends BaseService<CitiesInfo> implements CitiesInfoService {
	@Resource
	private CitiesInfoDao citiesInfoDao;
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public ServiceReturns getCitiesByProvinceId(String provinceId) {
		serviceReturns = new ServiceReturns();
		serviceReturns.putData("cities", citiesInfoDao.getCitiesByProvinceId(provinceId));
		return serviceReturns;
	}

}
