package com.brilliance.service;

import com.brilliance.base.BriException;
import com.brilliance.base.ServiceReturns;
import com.brilliance.po.OrderProgress;

public interface OrderProgressService {

	public Object merge(OrderProgress orderProgress);
	
	public ServiceReturns getAll(OrderProgress orderProgress) throws BriException;
	
	public boolean isExist(String expressNo);
	
	public int updateStatusInfo(OrderProgress orderProgress);
	
	public ServiceReturns deleteHistoryOrder(OrderProgress orderProgress);
}
