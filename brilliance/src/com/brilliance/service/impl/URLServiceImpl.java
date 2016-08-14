package com.brilliance.service.impl;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brilliance.base.BaseService;
import com.brilliance.base.ServiceReturns;
import com.brilliance.dao.URLDao;
import com.brilliance.po.RecommendInfo;
import com.brilliance.po.URLInfo;
import com.brilliance.service.URLService;

@Service
@Transactional
public class URLServiceImpl extends BaseService<URLInfo> implements URLService {

	@Resource
	private URLDao urlDao;
	private static final Log logger = LogFactory.getLog(URLServiceImpl.class);

	public ServiceReturns saveURLInfo(URLInfo urlInfo) {
		
		serviceReturns = new ServiceReturns();
		urlDao.save(urlInfo);
		
		//save后的urlInfo中id为刚插入的主键id
		serviceReturns.putData("urlInfo",urlInfo);
		return serviceReturns;
	}
	
	


}
