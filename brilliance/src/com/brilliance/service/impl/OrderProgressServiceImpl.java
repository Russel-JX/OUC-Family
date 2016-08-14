package com.brilliance.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brilliance.base.BaseService;
import com.brilliance.base.BriException;
import com.brilliance.base.ServiceReturns;
import com.brilliance.dao.OrderProgressDao;
import com.brilliance.po.OrderProgress;
import com.brilliance.service.OrderProgressService;

@Service
@Transactional
public class OrderProgressServiceImpl extends BaseService<OrderProgress>
		implements OrderProgressService {

	@Resource
	private OrderProgressDao orderProgressDao;
	
	public Object merge(OrderProgress orderProgress) {
		return orderProgressDao.merge(orderProgress);
	}

	public ServiceReturns getAll(OrderProgress orderProgress) throws BriException {
		serviceReturns = new ServiceReturns();
		List<OrderProgress> orders = orderProgressDao.getAll(orderProgress);
		
		serviceReturns.putData("orders",orders);
		
		return serviceReturns;
	}

	
	public boolean isExist(String expressNo) {
		return orderProgressDao.getCount(expressNo) > 0;
	}

	
	public int updateStatusInfo(OrderProgress orderProgress) {
		return orderProgressDao.updateStatusInfo(orderProgress);
	}
	
	public ServiceReturns deleteHistoryOrder(OrderProgress orderProgress){
		serviceReturns = new ServiceReturns();
		super.delete(orderProgress);
		
		return serviceReturns;
	}
}
