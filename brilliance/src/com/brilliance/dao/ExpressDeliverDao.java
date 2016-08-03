package com.brilliance.dao;

import java.util.List;

import com.brilliance.po.ExpressDeliverDetail;

public interface ExpressDeliverDao {
	/**
	 * 保存价格和配送天数信息
	 * @param list
	 */
	public void saveExpDeliverData(List<ExpressDeliverDetail> list);
}
