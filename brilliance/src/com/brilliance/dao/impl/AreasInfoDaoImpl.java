/**
 * ============================================================
 * File : AreasInfoDaoImpl.java
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
import com.brilliance.dao.AreasInfoDao;
import com.brilliance.po.AreasInfo;

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
public class AreasInfoDaoImpl extends BaseDaoImpl<AreasInfo> implements AreasInfoDao {

	@SuppressWarnings("unchecked")
	public List<AreasInfo> getAreasByCityId(String cityId) {
		return getReadSession().createQuery(//
				"FROM AreasInfo WHERE cityId=:cityId")
				.setParameter("cityId", cityId).list();
	}

}
