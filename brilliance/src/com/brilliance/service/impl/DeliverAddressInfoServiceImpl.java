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
import com.brilliance.dao.DeliverAddressInfoDao;
import com.brilliance.po.DeliverAddressInfo;
import com.brilliance.service.DeliverAddressInfoService;

/**
 * @author Russel
 * 
 */
@Service
@Transactional
public class DeliverAddressInfoServiceImpl extends BaseService<DeliverAddressInfo> implements DeliverAddressInfoService {
	@Resource
	private DeliverAddressInfoDao deliverAddressInfoDao;
	private static final Log logger = LogFactory.getLog(DeliverAddressInfoServiceImpl.class);

	public void saveAddressInfo(DeliverAddressInfo deliverAddressInfo) throws Exception{
		deliverAddressInfoDao.save(deliverAddressInfo);
	}
	public ServiceReturns updateAddressInfo(DeliverAddressInfo deliverAddressInfo) throws Exception{
		serviceReturns = new ServiceReturns();
		deliverAddressInfoDao.update(deliverAddressInfo);
		
		//update后的deliverAddressInfo，为修改后的新的deliverAddressInfo
		serviceReturns.putData("deliverAddressInfo",deliverAddressInfo);
		return serviceReturns;
	}
	
	public List<DeliverAddressInfo> getDeliverAddressesByUserRoute(String province,String city,String area,String expressCode,String userId) {
		List<DeliverAddressInfo> deliverAddressInfos = deliverAddressInfoDao.getDeliverAddressesByUserRoute(province,city,area,expressCode, userId);
		return deliverAddressInfos;
	}
	
	public ServiceReturns deleteAddressInfo(DeliverAddressInfo deliverAddressInfo) throws Exception{
		serviceReturns = new ServiceReturns();
		deliverAddressInfoDao.delete(deliverAddressInfo);
		
		serviceReturns.putData("deliverAddressInfo",deliverAddressInfo);
		return serviceReturns;
	}
	

//	public void delete(Integer id) {
//		deliverAddressInfoDao.delete(id);
//	}
//
//	public void update(UserAddressInfo deliverAddressInfo) {
//		deliverAddressInfoDao.update(deliverAddressInfo);
//	}

//	public UserAddressInfo getById(Integer id) {
//
//		return deliverAddressInfoDao.getById(id);
//	}
//
//	public List<UserAddressInfo> getAll() {
//
//		return deliverAddressInfoDao.getAll();
//	}

}
