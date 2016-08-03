package com.brilliance.service;

import com.brilliance.base.BriException;
import com.brilliance.po.AreasInfo;
import com.brilliance.po.CitiesInfo;
import com.brilliance.po.ExpressInfo;
import com.brilliance.po.ProvincesInfo;

public interface CommonService {
	public String findExpressNameByCode(String expressCode);

	public String findExpressCodeByName(String expressName);

	public ExpressInfo findExpressInfo(ExpressInfo expressInfo);

	public String findProvinceById(String provinceId);

	public String findProvinceIdByName(String province);

	public ProvincesInfo findProvinceInfo(ProvincesInfo provinceInfo);

	public String findCityById(String cityId);

	public String findCityIdByName(String city);

	public CitiesInfo findCityInfo(CitiesInfo citiesInfo);

	public String findAreaById(String areaId);

	public String findAreaIdByName(String area);

	public AreasInfo findAreaInfo(AreasInfo areasInfo);
	
	/**
	 * 根据地域对应信息查找对应的编码
	 * @param from 始发地
	 * @param to 目的地
	 */
	public String findExpDeliverMap(String from,String to);
	
	
	public String findPwdByMobile(String mobileNum,String pwd);
	
	public String getMaxUserId() throws BriException;
	

}
