package com.brilliance.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.brilliance.base.BriException;
import com.brilliance.dao.CommonDao;
import com.brilliance.po.AreasInfo;
import com.brilliance.po.CitiesInfo;
import com.brilliance.po.ExpressInfo;
import com.brilliance.po.ProvincesInfo;


@Repository
public class CommonDaoImpl implements CommonDao {

	protected Logger logger = Logger.getLogger(CommonDaoImpl.class);

	@Autowired
	private HibernateTemplate readHibernateTemplate;


	public String findExpressNameByCode(String expressCode) {
		String sql = "select NAME from express_info where EXPRESSCODE=?";
		SQLQuery query = getHibernateSession().createSQLQuery(sql);
		query.setParameter(0, expressCode);
		List<?> list = query.list();
		String name = "";
		if (!CollectionUtils.isEmpty(list)) {
			if (null != list.get(0)){
				name = list.get(0).toString();
			}
		}
		return name;
	}

	public String findExpressCodeByName(String expressName) {
		String sql = "select EXPRESSCODE from express_info where NAME=?";
		SQLQuery query = getHibernateSession().createSQLQuery(sql);
		query.setParameter(0, expressName);
		List<?> list = query.list();
		String code = "";
		if (!CollectionUtils.isEmpty(list)) {
			if (null != list.get(0)){
				code = list.get(0).toString();
			}
		}
		return code;
	}

	public ExpressInfo findExpressInfo(ExpressInfo expressInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	public String findProvinceById(String provinceId) {
		String sql = "select PROVINCE from provinces_info where PROVINCEID=?";
		SQLQuery query = getHibernateSession().createSQLQuery(sql);
		query.setParameter(0, provinceId);
		List<?> list = query.list();
		String code = "";
		if (!CollectionUtils.isEmpty(list)) {
			if (null != list.get(0)){
				code = list.get(0).toString();
			}
		}
		return code;
	}

	public String findProvinceIdByName(String province) {
		String sql = "select PROVINCEID from provinces_info where PROVINCE=?";
		SQLQuery query = getHibernateSession().createSQLQuery(sql);
		query.setParameter(0, province);
		List<?> list = query.list();
		String code = "";
		if (!CollectionUtils.isEmpty(list)) {
			if (null != list.get(0)){
				code = list.get(0).toString();
			}
		}
		return code;
	}

	@Override
	public ProvincesInfo findProvinceInfo(ProvincesInfo provinceInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	public String findCityById(String cityId) {
		String sql = "select CITY from cities_info where CITYID=?";
		SQLQuery query = getHibernateSession().createSQLQuery(sql);
		query.setParameter(0, cityId);
		List<?> list = query.list();
		String code = "";
		if (!CollectionUtils.isEmpty(list)) {
			if (null != list.get(0)){
				code = list.get(0).toString();
			}
		}
		return code;
	}

	@Override
	public String findCityIdByName(String city) {
		String sql = "select CITYID from cities_info where CITY=?";
		SQLQuery query = getHibernateSession().createSQLQuery(sql);
		query.setParameter(0, city);
		List<?> list = query.list();
		String code = "";
		if (!CollectionUtils.isEmpty(list)) {
			if (null != list.get(0)){
				code = list.get(0).toString();
			}
		}
		return code;
	}

	@Override
	public CitiesInfo findCityInfo(CitiesInfo citiesInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	public String findAreaById(String areaId) {
		String sql = "select AREA from areas_info where AREAID=?";
		SQLQuery query = getHibernateSession().createSQLQuery(sql);
		query.setParameter(0, areaId);
		List<?> list = query.list();
		String code = "";
		if (!CollectionUtils.isEmpty(list)) {
			if (null != list.get(0)){
				code = list.get(0).toString();
			}
		}
		return code;
	}

	public String findAreaIdByName(String area) {
		String sql = "select AREAID from areas_info where AREA=?";
		SQLQuery query = getHibernateSession().createSQLQuery(sql);
		query.setParameter(0, area);
		List<?> list = query.list();
		String code = "";
		if (!CollectionUtils.isEmpty(list)) {
			if (null != list.get(0)){
				code = list.get(0).toString();
			}
		}
		return code;
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
	public String findExpDeliverMap(String from,String to) {
		String sql = "select DELIVERCODE from express_deliver_info where (FROMADDR=? and TOADDR=?) or (FROMADDR=? and TOADDR=?)";
		SQLQuery query = getHibernateSession().createSQLQuery(sql);
		query.setParameter(0, from);
		query.setParameter(1, to);
		query.setParameter(2, to);
		query.setParameter(3, from);
		List<?> list = query.list();
		String code = "";
		if (!CollectionUtils.isEmpty(list)) {
			if (null != list.get(0)){
				code = list.get(0).toString();
			}
		}
		return code;
	}
	
	public String findPwdByMobile(String mobileNum,String pwd){
		String sql = "select count(1) from USER_INFO where MOBILENO=? and PASSWORD=?";
		SQLQuery query = getHibernateSession().createSQLQuery(sql);
		query.setParameter(0, mobileNum);
		query.setParameter(1, pwd);
		List<?> list = query.list();
		String code = "";
		if (!CollectionUtils.isEmpty(list)) {
			if (null != list.get(0)){
				code = list.get(0).toString();
			}
		}
		return code;
	}
	
	public String getMaxUserId() throws BriException{
		String sql = "select max(userid+0) userid from user_info";
		SQLQuery query = getHibernateSession().createSQLQuery(sql);
		List<?> list = query.list();
		String value = "";
		if (!CollectionUtils.isEmpty(list)) {
			if (null != list.get(0)){
				value= list.get(0).toString();
				int pos = value.indexOf(".");
				if (pos != -1){
					value = value.substring(0,pos);
				}
			}
		}
		return value;
	}

	
	private Session getHibernateSession(){
		return  readHibernateTemplate.getSessionFactory().getCurrentSession();
	}

}
