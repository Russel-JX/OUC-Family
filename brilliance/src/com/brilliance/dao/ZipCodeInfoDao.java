/**
 * ============================================================
 * File : ZipCodeInfoDao.java
 * Description : 
 * 
 * Package : com.brilliance.dao
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

import com.brilliance.base.BaseDao;
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
public interface ZipCodeInfoDao extends BaseDao<ZipCodeInfo> {
	public ZipCodeInfo getZipCodeInfoByAreaId(String areaId);
}
