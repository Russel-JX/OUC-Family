package com.brilliance.dao;

import java.util.List;

import com.brilliance.base.BaseDao;
import com.brilliance.base.BriException;
import com.brilliance.po.ExpressInfo;

public interface ExpressInfoDao  extends BaseDao<ExpressInfo> {

	public List<?> getExpressInfo(ExpressInfo info) throws Exception;
	
	public List<ExpressInfo> getAllExpress() throws BriException;
	
	public int getMaxExpressCode();
}
