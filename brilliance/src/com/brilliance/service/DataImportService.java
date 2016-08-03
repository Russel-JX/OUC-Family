package com.brilliance.service;

import java.io.File;
import java.util.Map;

import com.brilliance.base.BriException;

public interface DataImportService {
	/**
	 * 派送范围数据导入
	 * @param file
	 * @throws Exception
	 */
	public void deliverAreasImport(File file) throws BriException;
	
	
	/**
	 * 货运价格和配送天数
	 * @param file
	 * @throws Exception
	 */
	public void expressDeliverImport(File file) throws BriException;
	
	
	/**
	 * 从数据库导入
	 * @throws BriException
	 */
	public void dbimportToCustAddr() throws BriException;
	
	/**
	 * 从数据文件导入customization_address
	 * @param file
	 * @throws BriException
	 */
	
	public Map<String,Object> importToCustAddress(String filePath,File file) throws BriException;

}
