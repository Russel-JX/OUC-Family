package com.brilliance.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brilliance.base.BaseService;
import com.brilliance.base.BriException;
import com.brilliance.base.ServiceReturns;
import com.brilliance.dao.FavouriteAddressDao;
import com.brilliance.po.CustomizationAddressInfo;
import com.brilliance.po.FavouriteAddress;
import com.brilliance.po.SearchCriteria;
import com.brilliance.service.CustomizationAddressService;
import com.brilliance.service.FavouriteAddressService;


@Service
@Transactional
public class FavouriteAddressServiceImpl extends BaseService<FavouriteAddress> implements
		FavouriteAddressService {
	@Resource
	private FavouriteAddressDao favouriteAddressInfoDao;
	
	@Resource
	private CustomizationAddressService customizationAddressService;
	
	
	public ServiceReturns save(FavouriteAddress favouriteAddress){
		return super.save(favouriteAddress);
	}

	public ServiceReturns getCommCusddrs(SearchCriteria criteria) throws BriException{
		serviceReturns = new ServiceReturns();
		
		List<CustomizationAddressInfo> list = customizationAddressService.getCommCusAddressInfo(criteria);
		
		serviceReturns.putData("lstAddr",list);
		return serviceReturns;
	}

	public ServiceReturns getMyselfFavAddrInfo(FavouriteAddress favouriteAddress,int page) throws BriException {
		serviceReturns = new ServiceReturns();
		
		List<FavouriteAddress> list = favouriteAddressInfoDao.getFavouriteAddresses(favouriteAddress,page);
		
		serviceReturns.putData("selfAddr",list);
		return serviceReturns;
	}
	
	public ServiceReturns getCusAddrInfo(String addressId) throws BriException {
		serviceReturns = new ServiceReturns();
		CustomizationAddressInfo cus = customizationAddressService.getCusAddressInfo(addressId);
		
		serviceReturns.putData("lstAddr",cus);
		return serviceReturns;
	}
	

	public FavouriteAddress getFavAddr(FavouriteAddress fav){
		return favouriteAddressInfoDao.getFavAddr(fav);
	}
	
	public void deleteFavAddr(FavouriteAddress favouriteAddr) throws Exception{
		favouriteAddressInfoDao.delete(favouriteAddr);
	}
}
