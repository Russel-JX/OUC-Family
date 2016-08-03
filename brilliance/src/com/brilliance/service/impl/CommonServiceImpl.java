package com.brilliance.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brilliance.base.BriException;
import com.brilliance.dao.CommonDao;
import com.brilliance.po.AreasInfo;
import com.brilliance.po.CitiesInfo;
import com.brilliance.po.ExpressInfo;
import com.brilliance.po.ProvincesInfo;
import com.brilliance.service.CommonService;

@Service
@Transactional
public class CommonServiceImpl implements CommonService {

	@Resource
	private CommonDao commonDao;
	
	public String findExpressNameByCode(String expressCode) {
		return commonDao.findExpressNameByCode(expressCode);
	}

	public String findExpressCodeByName(String expressName) {
		return commonDao.findExpressCodeByName(expressName);
	}

	@Override
	public ExpressInfo findExpressInfo(ExpressInfo expressInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	public String findProvinceById(String provinceId) {
		return commonDao.findProvinceById(provinceId);
	}

	public String findProvinceIdByName(String province) {
		return commonDao.findProvinceIdByName(province);
	}

	@Override
	public ProvincesInfo findProvinceInfo(ProvincesInfo provinceInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	public String findCityById(String cityId) {
		return commonDao.findCityById(cityId);
	}

	public String findCityIdByName(String city) {
		return commonDao.findCityIdByName(city);
	}

	@Override
	public CitiesInfo findCityInfo(CitiesInfo citiesInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	public String findAreaById(String areaId) {
		return commonDao.findAreaById(areaId);
	}

	public String findAreaIdByName(String area) {
		return commonDao.findAreaIdByName(area);
	}

	@Override
	public AreasInfo findAreaInfo(AreasInfo areasInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 根据地域对应信息查找对应的编码
	 * @param from 始发地
	 * @param to 目的地
	 */
	public String findExpDeliverMap(String from, String to) {
		return commonDao.findExpDeliverMap(from, to);
	}

	public String findPwdByMobile(String mobileNum, String pwd) {
		return commonDao.findPwdByMobile(mobileNum, pwd);
	}

	public String getMaxUserId() throws BriException {
		return commonDao.getMaxUserId();
	}



}
