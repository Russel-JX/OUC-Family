/**
 * ============================================================
 * File : CitiesInfoDao.java
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

package com.brilliance.dao;

import java.util.List;

import com.brilliance.base.BaseDao;
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
public interface CitiesInfoDao extends BaseDao<CitiesInfo> {
	public List<CitiesInfo> getCitiesByProvinceId(String provinceId);

}
