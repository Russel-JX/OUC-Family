/**
 * ============================================================
 * File : ZipCodeInfoDaoImpl.java
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

import org.springframework.stereotype.Repository;

import com.brilliance.base.impl.BaseDaoImpl;
import com.brilliance.dao.ZipCodeInfoDao;
import com.brilliance.po.ZipCodeInfo;

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
public class ZipCodeInfoDaoImpl extends BaseDaoImpl<ZipCodeInfo> implements
		ZipCodeInfoDao {

	public ZipCodeInfo getZipCodeInfoByAreaId(String areaId) {
		return (ZipCodeInfo) getSession().createQuery(//
				"FROM ZipCodeInfo WHERE areaId=:areaId")//
				.setParameter("areaId", areaId).uniqueResult();
	}

}
