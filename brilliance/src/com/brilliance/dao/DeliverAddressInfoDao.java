/**
 * 
 */
package com.brilliance.dao;

import java.util.List;

import com.brilliance.base.BaseDao;
import com.brilliance.po.DeliverAddressInfo;
import com.brilliance.po.PostAddressInfo;

/**
 * @author Russel
 *
 */
public interface DeliverAddressInfoDao extends BaseDao<DeliverAddressInfo> {
	
	public List<DeliverAddressInfo> getDeliverAddressesByUserRoute(String province,String city,String area,String expressCode,String userId);

}
