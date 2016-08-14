package com.brilliance.dao.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.brilliance.base.BriException;
import com.brilliance.base.impl.BaseDaoImpl;
import com.brilliance.dao.FavouriteAddressDao;
import com.brilliance.po.CustomizationAddressInfo;
import com.brilliance.po.FavouriteAddress;
import com.brilliance.utils.Constants;
import com.brilliance.utils.Tools;

@Repository
public class FavouriteAddressDaoImpl extends BaseDaoImpl<FavouriteAddress> implements FavouriteAddressDao{

	public void save(FavouriteAddress favouriteAddr){
		super.save(favouriteAddr);
	}
	
	public void delete(FavouriteAddress favouriteAddr) throws Exception{
		super.delete(favouriteAddr);
	}
	
	public FavouriteAddress getFavAddr(FavouriteAddress fav){
		String hql ="from favouriteAddress where addressId=:addressId and userId=:userId";
		Query query = getReadSession().createQuery(hql);
		query.setParameter("addressId", fav.getAddressId());
		query.setParameter("userId", fav.getUserId());
		List<?> list = query.list();
		FavouriteAddress favAddr = null;
		if(!CollectionUtils.isEmpty(list)){
			favAddr = (FavouriteAddress)list.get(0);
		}
		return favAddr;
		
	}
	
	public List<FavouriteAddress> getFavouriteAddresses(
			FavouriteAddress favouriteAddress,int page) throws BriException {
		String hql = "select a.id,a.userId,c.expressCode,c.hotName,c.contact,c.mobile,c.officeNo,c.addressId,c.memo,c.createTime,b.name,c.addressDetail,a.source,b.logoUrl,c.longitude,c.latitude from favourite_address a"
				+ " ,express_info b,customization_addressinfo c where c.expressCode=b.expressCode and a.addressId=c.addressId and a.userId=:userId and c.archiveflag="+Constants.ARCHIVED;
		String hql1 = "select a.id,a.userId,c.expressCode,c.hotName,c.contact,c.mobile,c.officeNo,c.addressId,c.memo,c.createTime,b.name,c.addressDetail,a.source,b.logoUrl,c.longitude,c.latitude from favourite_address a"
				+ " ,express_info b,address_sugg_info c where c.expressCode=b.expressCode and a.addressId=c.addressId and a.userId=:userId and c.status<>"+Constants.REVIEW_PASS+" and c.deleteflag="+Constants.ADDRESS_ACTIVE;
		
		Query query = null;
		/*if(null != favouriteAddress && flag == Constants.ONLE_SELF){
			hql +=" and a.userId=:userId";
			query = getReadSession().createQuery(hql);
			query.setParameter("userId", favouriteAddress.getUserId());
		}else if(flag == Constants.ALL){
			query = getReadSession().createQuery(hql);
		}else if(flag == Constants.NON_SELF){
			hql +=" and a.userId != :userId";
			query = getReadSession().createQuery(hql);
			query.setParameter("userId", favouriteAddress.getUserId());
		}*/
		
		//query = getReadSession().createQuery("("+hql + ") union all ("+ hql1+")");
		query = getReadSession().createSQLQuery("("+hql + ") union ("+ hql1+")");
		query.setParameter("userId", favouriteAddress.getUserId());
		
		int startCnt = (page-1) * Constants.PAGE_COUNT;
		
		
		query.setFirstResult(startCnt);
		query.setMaxResults(Constants.PAGE_COUNT);
		
		List<?> list = query.list();
		List<FavouriteAddress> lstAddr = new ArrayList<FavouriteAddress>();
		try {
			if (!CollectionUtils.isEmpty(list)) {
				Iterator<?> itor = list.iterator();
				while (itor.hasNext()) {
					Object[] obj = (Object[]) itor.next();
					FavouriteAddress fav = new FavouriteAddress();
					CustomizationAddressInfo cus = new CustomizationAddressInfo();
					fav.setCreateTime(Tools.parseToDate(obj[9].toString(),
							Constants.DATE_PATTEN_SEC));
					fav.setExpressName(String.valueOf(obj[10]));
					fav.setId(Integer.parseInt(obj[0].toString()));
					fav.setUserId(String.valueOf(obj[1]));
					fav.setSource(String.valueOf(obj[12]));
					fav.setLogoUrl(String.valueOf(obj[13]));
					
					cus.setExpressCode(String.valueOf(obj[2]));
					cus.setHotName(String.valueOf(obj[3]));
					cus.setContactName(String.valueOf(obj[4]));
					cus.setMobile(String.valueOf(obj[5]));
					cus.setOfficeNo(String.valueOf(obj[6]));
					cus.setAddressId(String.valueOf(obj[7]));
					cus.setMemo(String.valueOf(obj[8]));
					cus.setAddressDetail(String.valueOf(obj[11]));
					cus.setLongitude(String.valueOf(obj[14]));
					cus.setLatitude(String.valueOf(obj[15]));
					
					
					fav.setCustomizationAddressInfo(cus);
					//fav.setFullAddress(String.valueOf(obj[11]));

					lstAddr.add(fav);
				}
			}
		} catch (ParseException e) {
			logger.error("fetch favourite address error", e);
			throw new BriException(e, "fetch favourite address error");
		}
		return lstAddr;
	}
	
}
