package com.brilliance.dao.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.brilliance.base.BriException;
import com.brilliance.base.impl.BaseDaoImpl;
import com.brilliance.dao.CustomizationAddressDao;
import com.brilliance.po.AdminInfo;
import com.brilliance.po.CustomizationAddressInfo;
import com.brilliance.po.SearchCriteria;
import com.brilliance.utils.Constants;
import com.brilliance.utils.DataTransferUtils;
import com.brilliance.utils.Tools;

@Repository
public class CustomizationAddressDaoImpl extends BaseDaoImpl<CustomizationAddressInfo> implements
		CustomizationAddressDao {

	public List<CustomizationAddressInfo> getCommCusAddressInfo(SearchCriteria criteria) {
		StringBuilder hql = new StringBuilder(128);
		hql.append("select a.id,a.addressId,a.expressCode,a.hotName,a.contactName,a.mobile,a.officeNo,a.addressDetail,a.memo,b.name ");
		hql.append(" ,a.longitude,a.latitude,b.logoUrl ");
		hql.append(" from customizationAddressInfo a,ExpressInfo b ");
		hql.append(" where a.expressCode=b.expressCode  and a.createdBy != :userId and archiveFlag=").append(Constants.ARCHIVED);
		hql.append(" and not exists (select c.addressId from favouriteAddress c where a.addressId=c.addressId and c.userId=:userId)");
		
		String[] tmp = null;
		
		if(!StringUtils.isEmpty(criteria.getAddressInfo())){
			tmp = StringUtils.split(criteria.getAddressInfo(),Constants.SEPARATOR_BLANK);
			if(!ArrayUtils.isEmpty(tmp)){
				hql.append(" and a.addressDetail like :addressDetail");
			}
		}
		
		if(!StringUtils.isEmpty(criteria.getHotName())){
			hql.append(" and a.hotName like :hotName");
		}
		
		if(!StringUtils.isEmpty(criteria.getProvinceId())){
			hql.append(" and  a.provinceId = :privinceId");
		}
		
		if(!StringUtils.isEmpty(criteria.getCityId())){
			hql.append(" and  a.cityId = :cityId");
		}
		
		if(!StringUtils.isEmpty(criteria.getAreaId())){
			hql.append(" and  a.areaId = :areaId");
		}
		
		Query query = getReadSession().createQuery(hql.toString());
		query.setParameter("userId", criteria.getUserId());
		
		if(!StringUtils.isEmpty(criteria.getAddressInfo())){
			if(!ArrayUtils.isEmpty(tmp)){
				if(tmp.length >= 2){
				    query.setParameter("addressDetail", "%"+tmp[1]+"%");
				}
			}
		}
		
		if(!StringUtils.isEmpty(criteria.getHotName())){
			query.setParameter("hotName", "%"+criteria.getHotName()+"%");
		}
		
		if(!StringUtils.isEmpty(criteria.getProvinceId())){
			query.setParameter("privinceId", criteria.getProvinceId());
		}
		
		if(!StringUtils.isEmpty(criteria.getCityId())){
			query.setParameter("cityId", criteria.getCityId());
		}
		
		if(!StringUtils.isEmpty(criteria.getAreaId())){
			query.setParameter("areaId", criteria.getAreaId());
		}
		
		if (criteria.isLimitaion()) {
			int startCnt = (criteria.getCurrentPage() - 1) * Constants.PAGE_COUNT;

			query.setFirstResult(startCnt);
			query.setMaxResults(Constants.PAGE_COUNT);
		}
		
		List<?> list = query.list();
		CustomizationAddressInfo cus = null;
		List<CustomizationAddressInfo> lstCus = new ArrayList<CustomizationAddressInfo>();
		if(!CollectionUtils.isEmpty(list)){
			Iterator<?> itor = list.iterator();
			while (itor.hasNext()){
				Object[] obj = (Object[])itor.next();
				cus = new CustomizationAddressInfo();
				
				cus.setId(Integer.parseInt(obj[0].toString()));
				cus.setAddressId(String.valueOf(obj[1]));
				cus.setExpressCode(String.valueOf(obj[2]));
				cus.setHotName(String.valueOf(obj[3]));
				cus.setContactName(String.valueOf(obj[4]));
				cus.setMobile(String.valueOf(obj[5]));
				cus.setOfficeNo(String.valueOf(obj[6]));
				//cus.setProvinceId(String.valueOf(obj[7]));
				//cus.setCityId(String.valueOf(obj[8]));
				//cus.setAreaId(String.valueOf(obj[9]));
				//cus.setTailAddress(String.valueOf(obj[10]));
				cus.setAddressDetail(String.valueOf(obj[7]));
				//cus.setCreatedBy(String.valueOf(obj[12]));
				cus.setMemo(String.valueOf(obj[8]));
				cus.setExpressName(String.valueOf(obj[9]));
				cus.setLongitude(String.valueOf(obj[10]));
				cus.setLatitude(String.valueOf(obj[11]));
				cus.setLogoUrl(String.valueOf(obj[12]));

				lstCus.add(cus);
			}
		}
		return lstCus;
	}
	
	public CustomizationAddressInfo getCusAddressInfo(String addressId) {
		String hql = " from customizationAddressInfo where addressId=:addressId";
		Query query = getReadSession().createQuery(hql);
		query.setParameter("addressId", addressId);
		
		CustomizationAddressInfo cus = null;
		List<?> list = query.list();
		
		if(!CollectionUtils.isEmpty(list)){
			//Object[] obj = (Object[])list.get(0);
			cus = (CustomizationAddressInfo)list.get(0);
			
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
		}
		
		return cus;
	}
	
	public int updateArchiveFlag(String[] addressIds,String archiveFlag) {
		String hql = " update customizationAddressInfo set archiveFlag=:archiveFlag where addressId in (:addressIds)";
		Query query = getReadSession().createQuery(hql);
		query.setParameterList("addressIds", addressIds);
		query.setParameter("archiveFlag", archiveFlag);
		return query.executeUpdate();
	}
	
	public List<CustomizationAddressInfo> getCustomAddrList(
			CustomizationAddressInfo info,AdminInfo adminInfo) throws BriException, ParseException {
		StringBuilder hql = new StringBuilder();
		hql.append("select a.id,a.parentId,a.dataType,a.archiveFlag,a.expressCode,b.name,a.hotName,a.contactName,a.mobile,a.officeNo,a.addressId,a.memo,");
		hql.append(" a.createTime,a.addressDetail,a.source,a.createdBy,a.provinceId,a.cityId,a.areaId,a.tailAddress,a.longitude,a.latitude ");
		hql.append(" from customizationAddressInfo a");
		//hql.append(" ,ExpressInfo b where a.expressCode=b.expressCode and createdBy=:userId");
		hql.append(" ,ExpressInfo b where a.expressCode=b.expressCode and 1=1");

		if(!Constants.ADMIN_ID.equals(adminInfo.getAccountId())){
			hql.append(" and createdBy=:createdBy");
		}else{
			if(!StringUtils.isEmpty(info.getCreatedBy())){
				hql.append(" and a.createdBy=:createdBy");
			}
		}
		if(!StringUtils.isEmpty(info.getArchiveFlag())){
			hql.append(" and archiveFlag=:archiveFlag");
		}
		if(!StringUtils.isEmpty(info.getSource())){
			hql.append(" and source=:source");
		}
		if(!StringUtils.isEmpty(info.getExpressCode())){
			hql.append(" and b.expressCode=:expressCode");
		}
		if(!StringUtils.isEmpty(info.getHotName())){
			hql.append(" and a.hotName like :hotName");
		}
		if(!StringUtils.isEmpty(info.getTailAddress())){
			hql.append(" and a.tailAddress like :tailAddress");
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
		if(!StringUtils.isEmpty(info.getDataType())){
			hql.append(" and a.dataType=:dataType");
		}
		
		List<CustomizationAddressInfo> lstAddr = new ArrayList<CustomizationAddressInfo>();
		Query query = getReadSession().createQuery(hql.toString());
		
		if(!Constants.ADMIN_ID.equals(adminInfo.getAccountId())){
			query.setParameter("createdBy", adminInfo.getAccountId());
		}else{
			if(!StringUtils.isEmpty(info.getCreatedBy())){
				query.setParameter("createdBy", info.getCreatedBy());
			}
		}
		if(!StringUtils.isEmpty(info.getArchiveFlag())){
			query.setParameter("archiveFlag", info.getArchiveFlag());
		}
		if(!StringUtils.isEmpty(info.getSource())){
			query.setParameter("source", info.getSource());
		}
		if(!StringUtils.isEmpty(info.getExpressCode())){
			query.setParameter("expressCode", info.getExpressCode());
		}
		if(!StringUtils.isEmpty(info.getHotName())){
			query.setParameter("hotName", info.getHotName()+"%");
		}
		if(!StringUtils.isEmpty(info.getTailAddress())){
			query.setParameter("tailAddress", info.getTailAddress()+"%");
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
		if(!StringUtils.isEmpty(info.getDataType())){
			query.setParameter("dataType", info.getDataType());
		}
		
		
		List<?> list = query.list();

		if(!CollectionUtils.isEmpty(list)){
			Iterator<?> itor = list.iterator();
			while (itor.hasNext()){
				Object[] obj = (Object[]) itor.next();
				CustomizationAddressInfo addrInfo = new CustomizationAddressInfo();
				addrInfo.setId(Integer.parseInt(obj[0].toString()));
				
				addrInfo.setParentId(String.valueOf(obj[1]));
				addrInfo.setDataType(obj[2].toString());
				addrInfo.setArchiveFlag(obj[3].toString());
				
				addrInfo.setExpressCode(String.valueOf(obj[4]));
				addrInfo.setExpressName(String.valueOf(obj[5]));
				addrInfo.setHotName(String.valueOf(obj[6]));
				addrInfo.setContactName(String.valueOf(obj[7]));
				addrInfo.setMobile(String.valueOf(obj[8]));
				addrInfo.setOfficeNo(String.valueOf(obj[9]));
				addrInfo.setAddressId(String.valueOf(obj[10]));
				addrInfo.setMemo(String.valueOf(obj[11]));
				addrInfo.setCreateTime(Tools.parseToDate(String.valueOf(obj[12]), Constants.DATE_PATTEN_SEC));
				addrInfo.setAddressDetail(String.valueOf(obj[13]));
				addrInfo.setSource(obj[14].toString());
				addrInfo.setCreatedBy(String.valueOf(obj[15]));
				addrInfo.setProvinceId(String.valueOf(obj[16]));
				addrInfo.setCityId(String.valueOf(obj[17]));
				addrInfo.setAreaId(String.valueOf(obj[18]));
				addrInfo.setTailAddress(String.valueOf(obj[19]));
				
				addrInfo.setLongitude(String.valueOf(obj[20]));
				addrInfo.setLatitude(String.valueOf(obj[21]));
				
				lstAddr.add(addrInfo);
				
			}
		}
		return lstAddr;
	}
	
	public int deleteCustAddr(String addressId) {
		String hql = "delete from customizationAddressInfo where addressId=:addressId and not exists (select 1 from favouriteAddress b where b.addressId=:addressId)";
		Query query = getReadSession().createQuery(hql.toString());
		query.setParameter("addressId", addressId);
		return query.executeUpdate();
	}
	
	public int[] ForceDeleteCustAddr(String addressId) {
		Transaction tx = getReadSession().beginTransaction();
		int[] tmp = new int[2];
		String hql1 = "delete from favouriteAddress where addressId=:addressId";
		
		String hql = "delete from customizationAddressInfo where addressId=:addressId and not exists (select 1 from favouriteAddress b where b.addressId=:addressId)";
		Query query = getReadSession().createQuery(hql1.toString());
		query.setParameter("addressId", addressId);
		tmp[0]=query.executeUpdate();
		
		query = getReadSession().createQuery(hql.toString());
		query.setParameter("addressId", addressId);
		tmp[1] = query.executeUpdate();
		
		tx.commit();
		return tmp;
	}
	
	public int countAddrNumber(String expressCode, String hotName){
		String hql = "select count(*) from customizationAddressInfo where expressCode=:expressCode and hotName=:hotName";
		Query query = getReadSession().createQuery(hql.toString());
		query.setParameter("expressCode", expressCode);
		query.setParameter("hotName", hotName);
		Object rs = query.uniqueResult();
		int addrNumber = Integer.parseInt(rs.toString());
		return addrNumber;
	}
	
	
	public List<CustomizationAddressInfo> lastCustAddr( String addressId,String nextOperation,int dataType,String adminId) throws ParseException{
		String sql = "SELECT id,parentId,dataType,archiveFlag,expressCode,hotName,CONTACT,mobile,officeNo,addressId,memo,"+
				"createTime,addressDetail,source,createdBy,PROVINCEID,CITYID,AREAID,TAILADDRESS,LONGITUDE,LATITUDE"+""
				+ "  FROM customization_addressinfo WHERE addressId >? and dataType=? and CREATEDBY=? ORDER BY id asc limit 1";
		Query query = getReadSession().createSQLQuery(sql).setParameter(0, addressId).setParameter(1, dataType).setParameter(2, adminId);
		
		List<CustomizationAddressInfo> lstAddr = new ArrayList<CustomizationAddressInfo>();
		List<?> list = query.list();
		lstAddr = DataTransferUtils.convertData(list);
		return lstAddr;
	}
	
	public List<CustomizationAddressInfo> nextCustAddr( String addressId,String nextOperation,int dataType,String adminId) throws ParseException{
		String sql = "SELECT id,parentId,dataType,archiveFlag,expressCode,hotName,CONTACT,mobile,officeNo,addressId,memo,"+
				"createTime,addressDetail,source,createdBy,PROVINCEID,CITYID,AREAID,TAILADDRESS,LONGITUDE,LATITUDE"+""
				+ "  FROM customization_addressinfo WHERE addressId <? and dataType=? and CREATEDBY=? ORDER BY id desc limit 1";
		Query query = getReadSession().createSQLQuery(sql).setParameter(0, addressId).setParameter(1, dataType).setParameter(2, adminId);
		
		List<CustomizationAddressInfo> lstAddr = new ArrayList<CustomizationAddressInfo>();
		List<?> list = query.list();
		lstAddr = DataTransferUtils.convertData(list);
		return lstAddr;
	}
	
	public List<CustomizationAddressInfo> getPackedCustAddr( String addressId) throws ParseException{
		String sql = "(SELECT id,parentId,dataType,archiveFlag,expressCode,hotName,CONTACT,mobile,officeNo,addressId,memo,"+
				"createTime,addressDetail,source,createdBy,PROVINCEID,CITYID,AREAID,TAILADDRESS,LONGITUDE,LATITUDE"+""
				+ "  FROM customization_addressinfo WHERE addressId =?) union (SELECT id,parentId,dataType,archiveFlag,expressCode,hotName,CONTACT,mobile,officeNo,addressId,memo,"+
				"createTime,addressDetail,source,createdBy,PROVINCEID,CITYID,AREAID,TAILADDRESS,LONGITUDE,LATITUDE"+""
				+ "  FROM customization_addressinfo WHERE parentId =?) ";
		Query query = getReadSession().createSQLQuery(sql).setParameter(0, addressId).setParameter(1, addressId);
		
		List<CustomizationAddressInfo> lstAddr = new ArrayList<CustomizationAddressInfo>();
		List<?> list = query.list();
		lstAddr = DataTransferUtils.convertData(list);
		return lstAddr;
	}
	
	
}
