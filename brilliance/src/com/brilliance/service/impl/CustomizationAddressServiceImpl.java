package com.brilliance.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.brilliance.base.BaseService;
import com.brilliance.base.BriException;
import com.brilliance.base.ServiceReturns;
import com.brilliance.dao.CustomizationAddressDao;
import com.brilliance.po.AdminInfo;
import com.brilliance.po.CustomizationAddressInfo;
import com.brilliance.po.SearchCriteria;
import com.brilliance.service.CustomizationAddressService;
import com.brilliance.utils.Constants;

@Service
@Transactional
public class CustomizationAddressServiceImpl extends BaseService<CustomizationAddressInfo> implements
		CustomizationAddressService {
	
	@Resource
	private CustomizationAddressDao customizationAddressDao;
	
	public ServiceReturns save(CustomizationAddressInfo customizationAddressInfo){
		return super.save(customizationAddressInfo);
	}
	
	public void saveAll(List<CustomizationAddressInfo> list) throws BriException{
		super.saveAll(list);
	}
	
	
	public ServiceReturns saveBulk(List<CustomizationAddressInfo> customizationAddressInfos) throws BriException{
		return super.saveBulks(customizationAddressInfos);
	}
	
	public ServiceReturns delete(String[] ids) {
		serviceReturns = new ServiceReturns();
		List<String> result = new ArrayList<String>();
		int tmp = 0;
		if (!ArrayUtils.isEmpty(ids)) {
			for (String addrssId : ids) {
				//CustomizationAddressInfo info = new CustomizationAddressInfo();
				//info.setId(Integer.parseInt(addrssId));
				tmp = customizationAddressDao.deleteCustAddr(addrssId);
				if (0 == tmp) {
					result.add(addrssId);
				}
			}
			serviceReturns.putData("undelete", result);
		}

		return serviceReturns;
	}
	
	
	public ServiceReturns deleteAll(List<CustomizationAddressInfo> list){
		return super.deleteAll(list);
	}
	
	public ServiceReturns ForceDeleteCustAddr(String[] ids) {
		serviceReturns = new ServiceReturns();
		if (!ArrayUtils.isEmpty(ids)) {
			for (String addrssId : ids) {
				customizationAddressDao.ForceDeleteCustAddr(addrssId);
			}
		}
		return serviceReturns;
	}

	public ServiceReturns publishAddrData(String[] addressIds) {
		serviceReturns = new ServiceReturns();
		if(!ArrayUtils.isEmpty(addressIds)){
			customizationAddressDao.updateArchiveFlag(addressIds, Constants.ARCHIVED);
		}
		return serviceReturns;
	}

	public ServiceReturns updateBulk(List<CustomizationAddressInfo> list){
		serviceReturns = new ServiceReturns();
		if(!CollectionUtils.isEmpty(list)){
			Iterator<CustomizationAddressInfo> itor = list.iterator();
			while (itor.hasNext()){
				CustomizationAddressInfo cust = itor.next();
				super.update(cust);
			}
		}
		return serviceReturns;
	}
	
	public ServiceReturns update(CustomizationAddressInfo cust){
		serviceReturns = super.update(cust);
		return serviceReturns;
	}
	
	public ServiceReturns update(CustomizationAddressInfo customizationAddressInfo, String nextOperation,int dataType,String adminId) throws ParseException{
		//���µ�ǰ��ַ
		CustomizationAddressInfo originalCustAddr = customizationAddressDao.getById(CustomizationAddressInfo.class, customizationAddressInfo.getId());
		BeanUtils.copyProperties(originalCustAddr, customizationAddressInfo, new String[]{"hotName","contactName","mobile","officeNo","provinceId","cityId","areaId","tailAddress","addressDetail","longitude","latitude","expressName"});
		customizationAddressDao.update(customizationAddressInfo);
		
		//������/��һ����ַ��ֻ�ܳ�һ����ݣ�
		List<CustomizationAddressInfo> lstAddr = new ArrayList<CustomizationAddressInfo>();
		if(nextOperation.equalsIgnoreCase(Constants.LASTRECORD)){
			lstAddr = customizationAddressDao.lastCustAddr(customizationAddressInfo.getAddressId(),nextOperation,dataType,adminId);
		}else if(nextOperation.equalsIgnoreCase(Constants.NEXTRECORD)){
			lstAddr = customizationAddressDao.nextCustAddr(customizationAddressInfo.getAddressId(),nextOperation,dataType,adminId);
		}else{
		}
		
		if(dataType==1){//��Χ
			serviceReturns.putData("lstAddr",lstAddr);
		}else if(dataType==0){//�ŵ�
			lstAddr = customizationAddressDao.getPackedCustAddr(customizationAddressInfo.getAddressId());
			serviceReturns.putData("lstAddr",lstAddr);
		}
		
		return serviceReturns;
	}
	
	public CustomizationAddressInfo getCusAddressInfo(String addressId) {
		return customizationAddressDao.getCusAddressInfo(addressId);
	}
	
	public ServiceReturns getPackedCustAddrInfo(String addressId) throws Exception {
		serviceReturns = new ServiceReturns();
		List<CustomizationAddressInfo> list = customizationAddressDao.getPackedCustAddr(addressId);
		
		serviceReturns.putData("lstAddr", list);
		return serviceReturns;
	}


	public List<CustomizationAddressInfo> getCommCusAddressInfo(SearchCriteria criteria) {
		return customizationAddressDao.getCommCusAddressInfo(criteria);
	}

	public ServiceReturns getCustomAddrList(CustomizationAddressInfo info,AdminInfo adminInfo)
			throws BriException, ParseException {
		serviceReturns = new ServiceReturns();
		List<CustomizationAddressInfo> list = customizationAddressDao.getCustomAddrList(info,adminInfo);
		
		serviceReturns.putData("lstAddr", list);
		return serviceReturns;
	}
	
	public ServiceReturns validateHotName(String expressCode, String[] hotNameArray, String[] hotNameIdentifyArray){
		serviceReturns = new ServiceReturns();
		int hotNameNumber = 0;
		String validHotNames="";
		String invalidHotNames="";
		
		for(int i=0;i<hotNameArray.length;i++){
			List<String> validList = new ArrayList<String> ();
			List<String> invalidList = new ArrayList<String> ();
			hotNameNumber = customizationAddressDao.countAddrNumber(expressCode, hotNameArray[i]);
			if(hotNameNumber==0){
				validHotNames += hotNameArray[i]+" ";
				validList.add(hotNameIdentifyArray[i]);
			}else{
				invalidHotNames += hotNameArray[i]+" ";
				invalidList.add(hotNameIdentifyArray[i]);
			}
			serviceReturns.putData("validHotNames", validHotNames);
			serviceReturns.putData("invalidHotNames", invalidHotNames);
			serviceReturns.putData("validList", validList);
			serviceReturns.putData("invalidList", invalidList);
		}
		return serviceReturns;
	}
}
