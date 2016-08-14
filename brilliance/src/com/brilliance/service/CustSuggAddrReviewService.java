package com.brilliance.service;

import java.text.ParseException;
import java.util.List;

import com.brilliance.base.BriException;
import com.brilliance.base.ServiceReturns;
import com.brilliance.po.AddressReviewInfo;
import com.brilliance.po.AddressSuggInfo;

public interface CustSuggAddrReviewService {
	
	public void saveCustSuggAddr(AddressSuggInfo addressSuggInfo);
	
	public void saveCustSuggReviewAddr(AddressReviewInfo custSuggAddrReviewInfo);
	
	public ServiceReturns getCustSuggAddrList(AddressSuggInfo info) throws BriException, ParseException;
	
	public ServiceReturns reviewCustSuggAddr(List<AddressReviewInfo> list) throws BriException, ParseException;
	
}
