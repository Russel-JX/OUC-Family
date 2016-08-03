package com.brilliance.service;

import java.text.ParseException;
import java.util.Map;

import com.brilliance.base.BriException;
import com.brilliance.base.ServiceReturns;
import com.brilliance.po.ExpressEvalution;


public interface ExpressEvalutionService {
	public ServiceReturns evalution(ExpressEvalution evalution)throws Exception;
	
	public ServiceReturns saveEvalution(ExpressEvalution evalution) throws BriException;
	
	public ServiceReturns getlstEvaluation(String addressId,int page) throws BriException, ParseException;
	
	public ServiceReturns getMoreCommentsInfo(String addressId,int page) throws BriException, ParseException;
	
	public Map<String,String> getAvgScoreEvaluation() throws BriException;
	
}
