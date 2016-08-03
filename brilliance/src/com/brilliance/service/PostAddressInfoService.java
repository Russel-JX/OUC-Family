/**
 * 
 */
package com.brilliance.service;

import java.util.List;

import com.brilliance.base.ServiceReturns;
import com.brilliance.po.PostAddressInfo;


/**
 * @author Russel
 * 
 */
public interface PostAddressInfoService{
	
	public ServiceReturns saveAddressInfo(PostAddressInfo postAddressInfo) throws Exception;
	
	public ServiceReturns updateAddressInfo(PostAddressInfo postAddressInfo) throws Exception;
	
	public List<PostAddressInfo> getPostAddressesByUserRoute(String province,String city,String area,String expressCode,String userId) throws Exception;
	
	public ServiceReturns deleteAddressInfo(PostAddressInfo postAddressInfo) throws Exception;

}
