package com.brilliance.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.brilliance.base.BaseService;
import com.brilliance.base.ServiceReturns;
import com.brilliance.dao.CustomizationTempAddressDao;
import com.brilliance.po.CustomizationTempAddressInfo;
import com.brilliance.service.CustomizationTempAddressService;

@Service
public class CustomizationTempAddressServiceImpl extends BaseService<CustomizationTempAddressInfo> implements
CustomizationTempAddressService {
	@Resource
	private CustomizationTempAddressDao customizationTempAddressDao;
	private static final Log logger = LogFactory.getLog(CustomizationTempAddressServiceImpl.class);
	
	public ServiceReturns save(List<CustomizationTempAddressInfo> custTempAddrInfoList){
		for(int i=0;i<custTempAddrInfoList.size();i++){
			customizationTempAddressDao.save(custTempAddrInfoList.get(i));
		}
		return null;
	}
}
