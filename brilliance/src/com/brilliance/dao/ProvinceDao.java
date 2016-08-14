package com.brilliance.dao;

import com.brilliance.base.BaseDao;
import com.brilliance.po.ProvincePO;

public interface ProvinceDao extends BaseDao<ProvincePO> {
	public ProvincePO findTest(Integer id);
}
