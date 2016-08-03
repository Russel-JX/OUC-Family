/**
 * ============================================================
 * File : ZipCodeInfoServiceImpl.java
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

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.brilliance.base.BaseService;
import com.brilliance.dao.ZipCodeInfoDao;
import com.brilliance.po.ZipCodeInfo;
import com.brilliance.service.ZipCodeInfoService;

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
public class ZipCodeInfoServiceImpl extends BaseService<ZipCodeInfo> implements ZipCodeInfoService {
	@Resource
	private ZipCodeInfoDao zipCodeInfoDao;

//	public void save(ZipCodeInfo entity) {
//
//	}
//
//	public void delete(Integer id) {
//
//	}
//
//	public void update(ZipCodeInfo entity) {
//
//	}
//
//	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
//	public ZipCodeInfo getById(Integer id) {
//
//		return null;
//	}
//
//	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
//	public List<ZipCodeInfo> getAll() {
//
//		return null;
//	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public ZipCodeInfo getZipCodeInfoByAreaId(String areaId) {
		return zipCodeInfoDao.getZipCodeInfoByAreaId(areaId);
	}

}
