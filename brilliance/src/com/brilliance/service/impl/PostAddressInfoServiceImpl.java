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
import com.brilliance.base.ServiceReturns;
import com.brilliance.dao.PostAddressInfoDao;
import com.brilliance.po.PostAddressInfo;
import com.brilliance.service.PostAddressInfoService;

/**
 * @author Russel
 * 
 */
@Service
@Transactional
public class PostAddressInfoServiceImpl extends BaseService<PostAddressInfo> implements PostAddressInfoService {
	@Resource
	private PostAddressInfoDao postAddressInfoDao;
	private static final Log logger = LogFactory.getLog(PostAddressInfoServiceImpl.class);

	public ServiceReturns saveAddressInfo(PostAddressInfo postAddressInfo) throws Exception{
		serviceReturns = new ServiceReturns();
		postAddressInfoDao.save(postAddressInfo);
		
		//save后的postAddressInfo中id为刚插入的主键id
		serviceReturns.putData("postAddressInfo",postAddressInfo);
		return serviceReturns;
	}
	public ServiceReturns updateAddressInfo(PostAddressInfo postAddressInfo) throws Exception{
		serviceReturns = new ServiceReturns();
		postAddressInfoDao.update(postAddressInfo);
		
		//update后的postAddressInfo，为修改后的新的postAddressInfo
		serviceReturns.putData("postAddressInfo",postAddressInfo);
		return serviceReturns;
	}
	
	
	public List<PostAddressInfo> getPostAddressesByUserRoute(String province,String city,String area,String expressCode,String userId) throws Exception{
		List<PostAddressInfo> postAddressInfos = postAddressInfoDao.getPostAddressesByUserRoute(province,city,area,expressCode, userId);
		return postAddressInfos;
	}
	
	public ServiceReturns deleteAddressInfo(PostAddressInfo postAddressInfo) throws Exception{
		serviceReturns = new ServiceReturns();
		postAddressInfoDao.delete(postAddressInfo);
		
		serviceReturns.putData("postAddressInfo",postAddressInfo);
		return serviceReturns;
	}
	

//	public void delete(Integer id) {
//		postAddressInfoDao.delete(id);
//	}
//
//	public void update(UserAddressInfo postAddressInfo) {
//		postAddressInfoDao.update(postAddressInfo);
//	}

//	public UserAddressInfo getById(Integer id) {
//
//		return postAddressInfoDao.getById(id);
//	}
//
//	public List<UserAddressInfo> getAll() {
//
//		return postAddressInfoDao.getAll();
//	}

}
