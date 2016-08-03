package com.brilliance.service.impl;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brilliance.base.BaseService;
import com.brilliance.base.ServiceReturns;
import com.brilliance.dao.RecommendDao;
import com.brilliance.po.RecommendInfo;
import com.brilliance.service.RecommendService;

@Service
@Transactional
public class RecommendServiceImpl extends BaseService<RecommendInfo> implements RecommendService {

	@Resource
	private RecommendDao recommendDao;
	private static final Log logger = LogFactory.getLog(RecommendServiceImpl.class);

	public ServiceReturns saveRecommendInfo(RecommendInfo recommendInfo) {
		
		serviceReturns = new ServiceReturns();
		recommendDao.save(recommendInfo);
		
		//save后的postAddressInfo中id为刚插入的主键id
		serviceReturns.putData("recommendInfo",recommendInfo);
		return serviceReturns;
	}
	
	


}
