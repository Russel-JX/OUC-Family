package com.brilliance.dao.impl;

import org.springframework.stereotype.Repository;
import com.brilliance.base.impl.BaseDaoImpl;
import com.brilliance.dao.ProvinceDao;
import com.brilliance.po.ProvincePO;

@Repository
public class ProvinceDaoImpl extends BaseDaoImpl<ProvincePO> implements
		ProvinceDao {

	public ProvincePO findTest(Integer id) {
		logger.info("findTest");
		return (ProvincePO) getReadSession().get(ProvincePO.class, id);
	}

}
