package com.brilliance.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.brilliance.base.impl.BaseDaoImpl;
import com.brilliance.dao.ExpressDeliverDao;
import com.brilliance.po.ExpressDeliverDetail;

@Repository
public class ExpressDeliverDaoImpl extends BaseDaoImpl<ExpressDeliverDetail> implements ExpressDeliverDao {

	public void saveExpDeliverData(List<ExpressDeliverDetail> list) {
		saveAll(list);
	}

}
