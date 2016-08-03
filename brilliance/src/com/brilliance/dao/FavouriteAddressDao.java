package com.brilliance.dao;

import java.util.List;

import com.brilliance.base.BriException;
import com.brilliance.po.FavouriteAddress;

public interface FavouriteAddressDao {
	public void save(FavouriteAddress favouriteAddr);

	public void delete(FavouriteAddress favouriteAddr) throws Exception;

	public List<FavouriteAddress> getFavouriteAddresses(
			FavouriteAddress favouriteAddress,int page) throws BriException;

	public FavouriteAddress getFavAddr(FavouriteAddress fav);

}
