/**
 * 
 */
package com.brilliance.dao;

import java.util.List;

import com.brilliance.base.BaseDao;
import com.brilliance.po.CitiesInfo;
import com.brilliance.po.PostAddressInfo;
import com.brilliance.po.UserAddressInfo;

/**
 * @author Russel
 *
 */
public interface PostAddressInfoDao extends BaseDao<PostAddressInfo> {
	
	public List<PostAddressInfo> getPostAddressesByUserRoute(String province,String city,String area,String expressCode,String userId);

}
