package com.brilliance.service;

import com.brilliance.base.BriException;
import com.brilliance.base.ServiceReturns;
import com.brilliance.po.FavouriteAddress;
import com.brilliance.po.SearchCriteria;

public interface FavouriteAddressService {
	public ServiceReturns save(FavouriteAddress favouriteAddress);
	
	public ServiceReturns getCommCusddrs(SearchCriteria criteria) throws BriException;
	
	public ServiceReturns getMyselfFavAddrInfo(FavouriteAddress favouriteAddress,int page) throws BriException;
	
	public ServiceReturns getCusAddrInfo(String addressId) throws BriException;
	
	
	public FavouriteAddress getFavAddr(FavouriteAddress fav);
	
	public void deleteFavAddr(FavouriteAddress fav) throws Exception;
	
}
