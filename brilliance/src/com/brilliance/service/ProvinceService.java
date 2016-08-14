package com.brilliance.service;

import java.util.List;

import com.brilliance.po.ProvincePO;

public interface ProvinceService{

	public ProvincePO findTest(Integer id);
	
	public List<ProvincePO> getByIds(Integer[] ids);

}
