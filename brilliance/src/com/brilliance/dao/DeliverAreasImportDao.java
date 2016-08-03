package com.brilliance.dao;

import java.util.List;

import com.brilliance.po.DeliverAreas;

public interface DeliverAreasImportDao {
	
	/**
	 * 保存派送范围信息
	 * @param list
	 */
	public void saveDeliverAreaData(List<DeliverAreas> list);
	
	public List<DeliverAreas> getAllAddressInfo();
	
}
