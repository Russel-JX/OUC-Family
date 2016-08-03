package com.brilliance.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.brilliance.base.BaseService;
import com.brilliance.base.BriException;
import com.brilliance.base.ServiceReturns;
import com.brilliance.dao.CustSuggAddrReviewDao;
import com.brilliance.dao.CustomizationAddressDao;
import com.brilliance.po.AdminInfo;
import com.brilliance.po.AddressSuggInfo;
import com.brilliance.po.AddressReviewInfo;
import com.brilliance.po.CustomizationAddressInfo;
import com.brilliance.service.CustSuggAddrReviewService;
import com.brilliance.service.CustomizationAddressService;
import com.brilliance.utils.Constants;

@Service
@Transactional
public class CustSuggAddrReviewServiceImpl extends BaseService<CustomizationAddressInfo> implements CustSuggAddrReviewService {
	
	@Resource
	private CustSuggAddrReviewDao custSuggAddrReviewDao;
	
	public ServiceReturns getCustSuggAddrList(AddressSuggInfo info) throws BriException, ParseException{
		serviceReturns = new ServiceReturns();
//		List<AddressSuggInfo> list = new ArrayList<AddressSuggInfo>();
		
		List<AddressSuggInfo> list = null;
		List<AddressSuggInfo> unstart_list;
		List<AddressSuggInfo> reviewed_list;
		if(StringUtils.isEmpty(info.getStatus())){
			//Get unstart address
			unstart_list = custSuggAddrReviewDao.getUnstartAddrList(info);
			//Get reviewed address
			reviewed_list = custSuggAddrReviewDao.getReviewedAddrList(info);
			unstart_list.addAll(reviewed_list);
			list = unstart_list;
		}else{
			if(info.getStatus().equals(Constants.REVIEW_PASS)||info.getStatus().equals(Constants.REVIEW_REJECT)){
				reviewed_list = custSuggAddrReviewDao.getReviewedAddrList(info);
				list = reviewed_list;
			}
			if(info.getStatus().equals(Constants.REVIEW_UNSTART)){
				unstart_list = custSuggAddrReviewDao.getUnstartAddrList(info);
				list = unstart_list;
			}
		}
		
		serviceReturns.putData("lstAddr", list);
		return serviceReturns;
	}
	
	public ServiceReturns reviewCustSuggAddr(List<AddressReviewInfo> list) throws BriException, ParseException{
		serviceReturns = new ServiceReturns();
		//insert review info
		custSuggAddrReviewDao.reviewCustSuggAddr(list);
		
		Object[] ids = new Object[list.size()];
		for(int i=0;i<list.size();i++){
			ids[i] = Integer.parseInt(list.get(i).getSuggestId());
		}
		//update status in suggestion table
		custSuggAddrReviewDao.updateCustSuggStatus(list.get(0).getStatus(),ids);
		
		return serviceReturns;
	}

	public void saveCustSuggAddr(AddressSuggInfo addressSuggInfo) {
		custSuggAddrReviewDao.saveCustSuggAddr(addressSuggInfo);
	}
	
	public void saveCustSuggReviewAddr(AddressReviewInfo custSuggAddrReviewInfo){
		custSuggAddrReviewDao.saveCustSuggReviewAddr(custSuggAddrReviewInfo);
	}
	
}
