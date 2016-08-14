package com.brilliance.dao;

import java.util.List;

import com.brilliance.base.BriException;
import com.brilliance.po.OrderProgress;

public interface OrderProgressDao {
	public Object merge(OrderProgress orderProgress);
	
	public List<OrderProgress> getAll(OrderProgress orderProgress) throws BriException;
	
	public int getCount(String expressNo);
	
	public int updateStatusInfo(OrderProgress orderProgress);
}
