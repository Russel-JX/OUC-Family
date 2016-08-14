/**
 * 
 */
package com.brilliance.service;

import java.util.List;

import com.brilliance.base.ServiceReturns;
import com.brilliance.po.DeliverAddressInfo;


/**
 * @author Russel
 * 
 */
public interface DeliverAddressInfoService{
	
	public void saveAddressInfo(DeliverAddressInfo deliverAddressInfo) throws Exception;
	
	public ServiceReturns updateAddressInfo(DeliverAddressInfo deliverAddressInfo) throws Exception;
	
	public List<DeliverAddressInfo> getDeliverAddressesByUserRoute(String province,String city,String area,String expressCode,String userId) throws Exception;
	
	public ServiceReturns deleteAddressInfo(DeliverAddressInfo deliverAddressInfo) throws Exception;

}
