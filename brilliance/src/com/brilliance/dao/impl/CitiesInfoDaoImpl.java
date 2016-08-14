/**
 * ============================================================
 * File : CitiesInfoDaoImpl.java
 * Description : 
 * 
 * Package : com.brilliance.dao.impl
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

package com.brilliance.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.brilliance.base.impl.BaseDaoImpl;
import com.brilliance.dao.CitiesInfoDao;
import com.brilliance.po.CitiesInfo;

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
@Repository
public class CitiesInfoDaoImpl extends BaseDaoImpl<CitiesInfo> implements CitiesInfoDao {

	@SuppressWarnings("unchecked")
	public List<CitiesInfo> getCitiesByProvinceId(String provinceId) {
		logger.info("根据provinceId查询城市cities");
		return getSession().createQuery(//
				"FROM CitiesInfo WHERE provinceId=:provinceId")//
				.setParameter("provinceId", provinceId).list();
	}

}
