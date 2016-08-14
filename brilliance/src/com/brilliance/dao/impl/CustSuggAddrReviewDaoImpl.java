package com.brilliance.dao.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.brilliance.base.BriException;
import com.brilliance.base.impl.BaseDaoImpl;
import com.brilliance.dao.CustSuggAddrReviewDao;
import com.brilliance.po.AddressSuggInfo;
import com.brilliance.po.AddressReviewInfo;
import com.brilliance.po.CustomizationAddressInfo;
import com.brilliance.utils.Constants;
import com.brilliance.utils.Tools;

@Repository
public class CustSuggAddrReviewDaoImpl extends BaseDaoImpl<AddressSuggInfo> implements CustSuggAddrReviewDao{

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	public List<AddressSuggInfo> getUnstartAddrList(AddressSuggInfo info)
			throws BriException, ParseException {
		
		StringBuilder hql = new StringBuilder();
		hql.append("select a.id,a.addressId,a.expressCode,b.name,a.hotName,a.contactName,a.mobile,a.officeNo, "
				+ " a.createdBy,c.name,c.mobileNO,a.memo, a.createTime,a.addressDetail,a.provinceId,a.cityId,a.areaId, "
				+ " a.status,a.source,a.dataType,a.tailAddress,a.longitude,a.latitude  from AddressSuggInfo a ,ExpressInfo b,UserInfo c "
				+ " where a.expressCode=b.expressCode and c.userId=a.createdBy "
				+ "and not exists (select d.suggestId from AddressReviewInfo d where a.id=d.suggestId )");
		
		
		if(!StringUtils.isEmpty(info.getStatus())){
			hql.append(" and a.status = :status");
		}
		if(!StringUtils.isEmpty(info.getExpressCode())){
			hql.append(" and b.expressCode=:expressCode");
		}
		if(!StringUtils.isEmpty(info.getHotName())){
			hql.append(" and a.hotName like :hotName");
		}
		if(!StringUtils.isEmpty(info.getProvinceId())){
			hql.append(" and a.provinceId=:provinceId");
		}
		if(!StringUtils.isEmpty(info.getCityId())){
			hql.append(" and a.cityId=:cityId");
		}
		if(!StringUtils.isEmpty(info.getAreaId())){
			hql.append(" and a.areaId=:areaId");
		}
		
		List<AddressSuggInfo> lstAddr = new ArrayList<AddressSuggInfo>();
		Query query = getReadSession().createQuery(hql.toString());
		
		if(!StringUtils.isEmpty(info.getExpressCode())){
			query.setParameter("expressCode", info.getExpressCode());
		}
		if(!StringUtils.isEmpty(info.getHotName())){
			query.setParameter("hotName", info.getHotName()+"%");
		}
		if(!StringUtils.isEmpty(info.getStatus())){
			query.setParameter("status", info.getStatus());
		}
		if(!StringUtils.isEmpty(info.getProvinceId())){
			query.setParameter("provinceId", info.getProvinceId());
		}
		if(!StringUtils.isEmpty(info.getCityId())){
			query.setParameter("cityId", info.getCityId());
		}
		if(!StringUtils.isEmpty(info.getAreaId())){
			query.setParameter("areaId", info.getAreaId());
		}
		
		List<?> list = query.list();

		if(!CollectionUtils.isEmpty(list)){
			Iterator<?> itor = list.iterator();
			while (itor.hasNext()){
				Object[] obj = (Object[]) itor.next();
				AddressSuggInfo addressReviewInfo = new AddressSuggInfo();
				addressReviewInfo.setId(Integer.parseInt(obj[0].toString()));
				addressReviewInfo.setAddressId(String.valueOf(obj[1]));
				addressReviewInfo.setExpressCode(String.valueOf(obj[2]));
				addressReviewInfo.setExpressName(String.valueOf(obj[3]));
				addressReviewInfo.setHotName(String.valueOf(obj[4]));
				addressReviewInfo.setContactName(String.valueOf(obj[5]));
				addressReviewInfo.setMobile(String.valueOf(obj[6]));
				addressReviewInfo.setOfficeNo(String.valueOf(obj[7]));
				addressReviewInfo.setCreatedBy(String.valueOf(obj[8]));
				addressReviewInfo.setCreatorName(String.valueOf(obj[9]));
				addressReviewInfo.setCreatorMobile(String.valueOf(obj[10]));
				addressReviewInfo.setMemo(String.valueOf(obj[11]));
				addressReviewInfo.setCreateTime(Tools.parseToDate(String.valueOf(obj[12]), Constants.DATE_PATTEN_SEC));
				addressReviewInfo.setAddressDetail(String.valueOf(obj[13]));
				addressReviewInfo.setProvinceId(String.valueOf(obj[14]));
				addressReviewInfo.setCityId(String.valueOf(obj[15]));
				addressReviewInfo.setAreaId(String.valueOf(obj[16]));
				addressReviewInfo.setStatus(String.valueOf(obj[17]));
				addressReviewInfo.setSource(String.valueOf(obj[18]));
				addressReviewInfo.setDataType(String.valueOf(obj[19]));
				addressReviewInfo.setTailAddress(String.valueOf(obj[20]));
				addressReviewInfo.setLongitude(String.valueOf(obj[21]));
				addressReviewInfo.setLatitude(String.valueOf(obj[22]));
				
				lstAddr.add(addressReviewInfo);
			}
		}
		return lstAddr;
	}
	
	public List<AddressSuggInfo> getReviewedAddrList(AddressSuggInfo info) throws BriException, ParseException{
		StringBuilder hql = new StringBuilder();
		hql.append("select a.id,a.addressId,a.expressCode,b.name,a.hotName,a.contactName,a.mobile,a.officeNo, "
				+ " a.createdBy,c.name,c.mobileNO,a.memo, a.createTime,a.addressDetail,a.provinceId,a.cityId,a.areaId,"
				+ " d.status,a.source,a.dataType,a.tailAddress,a.longitude,a.latitude,  "
				+ " d.reviewer,e.name,d.reviewMemo "
				+ " from AddressSuggInfo a ,ExpressInfo b,UserInfo c,AddressReviewInfo d,adminInfo e"
				+ " where a.expressCode=b.expressCode and c.userId=a.createdBy and a.id=d.suggestId and d.reviewer=e.accountId ");
		
		if(!StringUtils.isEmpty(info.getStatus())){
			hql.append(" and d.status = :status");
		}
		if(!StringUtils.isEmpty(info.getExpressCode())){
			hql.append(" and b.expressCode=:expressCode");
		}
		if(!StringUtils.isEmpty(info.getHotName())){
			hql.append(" and a.hotName like :hotName");
		}
		if(!StringUtils.isEmpty(info.getProvinceId())){
			hql.append(" and a.provinceId=:provinceId");
		}
		if(!StringUtils.isEmpty(info.getCityId())){
			hql.append(" and a.cityId=:cityId");
		}
		if(!StringUtils.isEmpty(info.getAreaId())){
			hql.append(" and a.areaId=:areaId");
		}
		
		List<AddressSuggInfo> lstAddr = new ArrayList<AddressSuggInfo>();
		Query query = getReadSession().createQuery(hql.toString());
		
		if(!StringUtils.isEmpty(info.getExpressCode())){
			query.setParameter("expressCode", info.getExpressCode());
		}
		if(!StringUtils.isEmpty(info.getHotName())){
			query.setParameter("hotName", info.getHotName()+"%");
		}
		if(!StringUtils.isEmpty(info.getStatus())){
			query.setParameter("status", info.getStatus());
		}
		if(!StringUtils.isEmpty(info.getProvinceId())){
			query.setParameter("provinceId", info.getProvinceId());
		}
		if(!StringUtils.isEmpty(info.getCityId())){
			query.setParameter("cityId", info.getCityId());
		}
		if(!StringUtils.isEmpty(info.getAreaId())){
			query.setParameter("areaId", info.getAreaId());
		}
		
		List<?> list = query.list();

		if(!CollectionUtils.isEmpty(list)){
			Iterator<?> itor = list.iterator();
			while (itor.hasNext()){
				Object[] obj = (Object[]) itor.next();
				AddressSuggInfo addressReviewInfo = new AddressSuggInfo();
				addressReviewInfo.setId(Integer.parseInt(obj[0].toString()));
				addressReviewInfo.setAddressId(String.valueOf(obj[1]));
				addressReviewInfo.setExpressCode(String.valueOf(obj[2]));
				addressReviewInfo.setExpressName(String.valueOf(obj[3]));
				addressReviewInfo.setHotName(String.valueOf(obj[4]));
				addressReviewInfo.setContactName(String.valueOf(obj[5]));
				addressReviewInfo.setMobile(String.valueOf(obj[6]));
				addressReviewInfo.setOfficeNo(String.valueOf(obj[7]));
				addressReviewInfo.setCreatedBy(String.valueOf(obj[8]));
				addressReviewInfo.setCreatorName(String.valueOf(obj[9]));
				addressReviewInfo.setCreatorMobile(String.valueOf(obj[10]));
				addressReviewInfo.setMemo(String.valueOf(obj[11]));
				addressReviewInfo.setCreateTime(Tools.parseToDate(String.valueOf(obj[12]), Constants.DATE_PATTEN_SEC));
				addressReviewInfo.setAddressDetail(String.valueOf(obj[13]));
				addressReviewInfo.setProvinceId(String.valueOf(obj[14]));
				addressReviewInfo.setCityId(String.valueOf(obj[15]));
				addressReviewInfo.setAreaId(String.valueOf(obj[16]));
				addressReviewInfo.setStatus(String.valueOf(obj[17]));
				addressReviewInfo.setSource(String.valueOf(obj[18]));
				addressReviewInfo.setDataType(String.valueOf(obj[19]));
				addressReviewInfo.setTailAddress(String.valueOf(obj[20]));
				addressReviewInfo.setLongitude(String.valueOf(obj[21]));
				addressReviewInfo.setLatitude(String.valueOf(obj[22]));
				addressReviewInfo.setReviewer(String.valueOf(obj[23]));
				addressReviewInfo.setReviewerName(String.valueOf(obj[24]));
				addressReviewInfo.setReviewMemo(String.valueOf(obj[25]));
				
				lstAddr.add(addressReviewInfo);
			}
		}
		return lstAddr;
	}
	
	public void saveCustSuggAddr(AddressSuggInfo addressSuggInfo) {
		hibernateTemplate.save(addressSuggInfo);
	}
	
	public void saveCustSuggReviewAddr(AddressReviewInfo custSuggAddrReviewInfo){
		hibernateTemplate.save(custSuggAddrReviewInfo);
	}
	
	public void reviewCustSuggAddr(List<AddressReviewInfo> list) throws BriException, ParseException{
		hibernateTemplate.saveOrUpdateAll(list);
	}

	public void deleteCustSuggByAddrId(String addressId) throws BriException, ParseException{
		String hql = "delete AddressSuggInfo where addressId=:addressId";
		Query query = getReadSession().createQuery(hql);
		query.setParameter("addressId", addressId);
	}
	
	
	public void updateCustSuggStatus(String status,Object[] ids) throws BriException, ParseException{
		String hql = "update AddressSuggInfo a set a.status=:status where id in:ids";
		for(int i=0;i<ids.length;i++){
			Query query = getReadSession().createQuery(hql);
			query.setParameter("status", status);
			query.setParameterList("ids", ids);
			query.executeUpdate();
		}
	}
}
