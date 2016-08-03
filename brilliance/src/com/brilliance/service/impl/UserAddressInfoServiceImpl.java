/**
 * 
 */
package com.brilliance.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brilliance.base.BaseService;
import com.brilliance.dao.UserAddressInfoDao;
import com.brilliance.po.UserAddressInfo;
import com.brilliance.service.UserAddressInfoService;

/**
 * @author mx801343
 * 
 */
@Service
@Transactional
public class UserAddressInfoServiceImpl extends BaseService<UserAddressInfo> implements UserAddressInfoService {
	@Resource
	private UserAddressInfoDao userAddressInfoDao;
	private static final Log logger = LogFactory.getLog(UserAddressInfoServiceImpl.class);

//	public void save(UserAddressInfo userAddressInfo) {
//		userAddressInfoDao.save(userAddressInfo);
//	}

//	public void delete(Integer id) {
//		userAddressInfoDao.delete(id);
//	}
//
//	public void update(UserAddressInfo userAddressInfo) {
//		userAddressInfoDao.update(userAddressInfo);
//	}

//	public UserAddressInfo getById(Integer id) {
//
//		return userAddressInfoDao.getById(id);
//	}
//
//	public List<UserAddressInfo> getAll() {
//
//		return userAddressInfoDao.getAll();
//	}

}
