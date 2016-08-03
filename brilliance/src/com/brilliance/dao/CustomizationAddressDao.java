package com.brilliance.dao;

import java.text.ParseException;
import java.util.List;

import com.brilliance.base.BaseDao;
import com.brilliance.base.BriException;
import com.brilliance.po.AdminInfo;
import com.brilliance.po.CustomizationAddressInfo;
import com.brilliance.po.SearchCriteria;

public interface CustomizationAddressDao  extends BaseDao<CustomizationAddressInfo>{
	public List<CustomizationAddressInfo> getCommCusAddressInfo(SearchCriteria criteria);
	
	public CustomizationAddressInfo getCusAddressInfo(String addressId);
	
	public List<CustomizationAddressInfo> getCustomAddrList(
			CustomizationAddressInfo info,AdminInfo adminInfo) throws BriException, ParseException;
	
	public int deleteCustAddr(String addressId);
	
	public int[] ForceDeleteCustAddr(String addressId);

	public int updateArchiveFlag(String[] addressIds,String archiveFlag);
	
	public int countAddrNumber(String expressCode, String hotName);
	
	public List<CustomizationAddressInfo> lastCustAddr( String addressId,String nextOperation,int dataType,String adminId) throws ParseException;
	
	public List<CustomizationAddressInfo> nextCustAddr( String addressId,String nextOperation,int dataType,String adminId) throws ParseException;
	
	public List<CustomizationAddressInfo> getPackedCustAddr( String addressId) throws ParseException;
	
}
