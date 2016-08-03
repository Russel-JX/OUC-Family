package com.brilliance.service;

import java.text.ParseException;
import java.util.List;

import com.brilliance.base.BriException;
import com.brilliance.base.ServiceReturns;
import com.brilliance.po.AdminInfo;
import com.brilliance.po.CustomizationAddressInfo;
import com.brilliance.po.SearchCriteria;

public interface CustomizationAddressService {
	public ServiceReturns save(CustomizationAddressInfo customizationAddressInfo);
	
	public void saveAll(List<CustomizationAddressInfo> list) throws BriException;
	
	public ServiceReturns saveBulk(List<CustomizationAddressInfo> customizationAddressInfos) throws BriException;
	
	public ServiceReturns delete(String[] ids);
	
	public ServiceReturns deleteAll(List<CustomizationAddressInfo> list);
	
	public ServiceReturns ForceDeleteCustAddr(String[] ids);
	
	public ServiceReturns update(CustomizationAddressInfo customizationAddressInfo);
	
	public ServiceReturns getCustomAddrList(CustomizationAddressInfo info,AdminInfo adminInfo) throws BriException, ParseException;
	
	public ServiceReturns updateBulk(List<CustomizationAddressInfo> list);
	
	public ServiceReturns update(CustomizationAddressInfo customizationAddressInfo,String nextOperation, int dataType,String adminId) throws ParseException;
	
	public ServiceReturns publishAddrData(String[] addressIds);
	
	public CustomizationAddressInfo getCusAddressInfo(String addressId);
	
	public ServiceReturns getPackedCustAddrInfo(String addressId) throws Exception;
	
	public List<CustomizationAddressInfo> getCommCusAddressInfo(SearchCriteria criteria);
	
	public ServiceReturns validateHotName(String expressCode, String[] hotNameArray, String[] hotNameIdentifyArray);
	
}
