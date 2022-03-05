/**
 * ============================================================
 * File : ZipCodeInfoService.java
 * Description :
 *
 * Package : com.brilliance.service
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

package com.brilliance.service;

import com.brilliance.po.ZipCodeInfo;

/*******************************************************************************
 *
 * @Author 		: Michael
 * @Version 	: 1.0
 * @Date Created: 2014-3-15
 * @Date Modified :
 * @Modified By :
 * @Contact 	:
 * @Description :
 * @History		:
 *
 ******************************************************************************/
public interface ZipCodeInfoService{
    public ZipCodeInfo getZipCodeInfoByAreaId(String areaId);

}
