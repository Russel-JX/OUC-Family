/**
 * 
 */
package com.brilliance.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.brilliance.base.impl.BaseDaoImpl;
import com.brilliance.dao.DeliverAddressInfoDao;
import com.brilliance.po.DeliverAddressInfo;

/**
 * @author Russel
 * 
 */
@Repository
public class DeliverAddressInfoDaoImpl extends BaseDaoImpl<DeliverAddressInfo>
		implements DeliverAddressInfoDao {
	
	@SuppressWarnings("unchecked")
	public List<DeliverAddressInfo> getDeliverAddressesByUserRoute(String province,String city,String area,String expressCode,String userId) {
		logger.info("根据省市区、用户和快递公司查询收货地址");
		return getSession().createQuery(//
				"FROM DeliverAddressInfo WHERE area=:area and city=:city and province=:province and expressCode=:expressCode and userId=:userId")//
				.setParameter("expressCode", expressCode).setParameter("userId", userId).
				setParameter("province", province).setParameter("city", city).setParameter("area", area).list();
	}

}
