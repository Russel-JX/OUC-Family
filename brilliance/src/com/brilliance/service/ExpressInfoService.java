package com.brilliance.service;

import java.util.List;

import com.brilliance.base.BriException;
import com.brilliance.base.ServiceReturns;
import com.brilliance.po.ExpressInfo;

public interface ExpressInfoService {

	public ServiceReturns getExpressInfo(ExpressInfo info) throws Exception;
	
	public List<ExpressInfo> getAllExpress() throws BriException;
	
	public ServiceReturns getAllExpList() throws BriException;
	
	public ServiceReturns saveExpress(ExpressInfo info) throws BriException;
	
	public ServiceReturns editExpress(ExpressInfo info) throws Exception;
	
	public ServiceReturns removeExpress(ExpressInfo info) throws Exception;
	
	public ServiceReturns saveExpressLogo(ExpressInfo info) throws Exception;
	
	public int getMaxExpressCode();
	
}
