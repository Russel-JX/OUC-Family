package com.brilliance.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.brilliance.base.impl.BaseDaoImpl;
import com.brilliance.dao.DeliverAreasImportDao;
import com.brilliance.po.DeliverAreas;

@Repository
public class DeliverAreasImportDaoImpl extends BaseDaoImpl<DeliverAreas> implements DeliverAreasImportDao {

	public void saveDeliverAreaData(List<DeliverAreas> list) {
		saveAll(list);
	}

	
	public List<DeliverAreas> getAllAddressInfo() {
		String hql = "from DeliverAreas";
		
		Query query = getSession().createQuery(hql);
		query.setMaxResults(1000);
		return query.list();
		
		/*if(!CollectionUtils.isEmpty(list)){
			//Object[] obj = (Object[])list.get(0);
			cus = (CustomizationAddressInfo)list.get(0);*/
			
			/*cus.setId(Integer.parseInt(obj[0].toString()));
			cus.setAddressId(String.valueOf(obj[1]));
			cus.setExpressCode(String.valueOf(obj[2]));
			cus.setHotName(String.valueOf(obj[3]));
			cus.setContactName(String.valueOf(obj[4]));
			cus.setMobile(String.valueOf(obj[5]));
			cus.setOfficeNo(String.valueOf(obj[6]));
			cus.setProvinceId(String.valueOf(obj[7]));
			cus.setCityId(String.valueOf(obj[8]));
			cus.setAreaId(String.valueOf(obj[9]));
			cus.setTailAddress(String.valueOf(obj[10]));
			cus.setAddressDetail(String.valueOf(obj[11]));
			cus.setCreatedBy(String.valueOf(obj[12]));
			cus.setMemo(String.valueOf(obj[13]));*/
		//}
		
	}
}
