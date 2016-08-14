/**
 * 
 */
package com.brilliance.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.brilliance.base.impl.BaseDaoImpl;
import com.brilliance.dao.PostAddressInfoDao;
import com.brilliance.po.CitiesInfo;
import com.brilliance.po.PostAddressInfo;

/**
 * @author Russel
 * 
 */
@Repository
public class PostAddressInfoDaoImpl extends BaseDaoImpl<PostAddressInfo>
		implements PostAddressInfoDao {
	
	@SuppressWarnings("unchecked")
	public List<PostAddressInfo> getPostAddressesByUserRoute(String province,String city,String area,String expressCode,String userId) {
		logger.info("根据省市区、用户和快递公司查询发货地址:province="+province+"city="+city+"area="+area+"expressCode="+expressCode+"userId="+userId);
		return getSession().createQuery(//
				"FROM PostAddressInfo WHERE area=:area and city=:city and province=:province and expressCode=:expressCode and userId=:userId")//
				.setParameter("expressCode", expressCode).setParameter("userId", userId).
				setParameter("province", province).setParameter("city", city).setParameter("area", area).list();
	}

}
