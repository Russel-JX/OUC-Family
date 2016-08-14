package com.brilliance.dao;

import java.text.ParseException;
import java.util.List;

import com.brilliance.base.BriException;
import com.brilliance.base.ServiceReturns;
import com.brilliance.po.AdminInfo;
import com.brilliance.po.AddressSuggInfo;
import com.brilliance.po.AddressReviewInfo;
import com.brilliance.po.CustomizationAddressInfo;

public interface CustSuggAddrReviewDao {
	
	public void saveCustSuggAddr(AddressSuggInfo addressSuggInfo);
	
	public void saveCustSuggReviewAddr(AddressReviewInfo custSuggAddrReviewInfo);
	
	public List<AddressSuggInfo> getUnstartAddrList(AddressSuggInfo info) throws BriException, ParseException;
	
	public List<AddressSuggInfo> getReviewedAddrList(AddressSuggInfo info) throws BriException, ParseException;
	
	
	public void reviewCustSuggAddr(List<AddressReviewInfo> list) throws BriException, ParseException;
	
	public void updateCustSuggStatus(String status,Object[] ids) throws BriException, ParseException;
	

}
