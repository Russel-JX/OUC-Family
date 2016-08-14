package com.brilliance.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.brilliance.base.BriException;
import com.brilliance.base.impl.BaseDaoImpl;
import com.brilliance.dao.ExpressInfoDao;
import com.brilliance.po.ExpressInfo;

@Repository
public class ExpressInfoDaoImpl extends BaseDaoImpl<ExpressInfo> implements
		ExpressInfoDao {

	public List<?> getExpressInfo(ExpressInfo info) throws Exception {
		// List<?> ll=
		// findBySql("Select ID,EXPRESSCODE,NAME,TELEPHONE,MOBILE,SERVICELINE,EVALUTION,CREATETIME from express_info");
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct a.expresscode,a.name,b.officenumber,a.mobile,a.serviceline,a.evaluation,d.charge,d.deliverdays,");
		sql.append(" b.OFFICENUMBER,b.MOBILE subexpressmobile,b.subexpressname,b.SUBEXPRESSADDRESS,b.deliverareas,b.nondeliverareas,b.provinceid,b.id");
		sql.append(" from express_info a,deliver_areas b, express_deliver_info c,express_deliver_detail d ");
		sql.append(" where a.expresscode=b.expresscode and c.delivercode=d.delivercode and a.expresscode=d.expresscode");
		sql.append(" and (b.provinceid=? or b.provinceid=?) and (b.cityid=? or b.cityid=?) and (b.areaid=? or b.areaid=?)");
		sql.append(" and ((c.f_provinceid=? and c.t_provinceid=?) or (c.f_provinceid=? and c.t_provinceid=?))");

		String[] parmas = new String[] { info.getF_provinceId(),
				info.getT_provinceId(), info.getF_cityId(), info.getT_cityId(),
				info.getF_areaId(), info.getT_areaId(), info.getF_provinceId(),
				info.getT_provinceId(), info.getT_provinceId(),
				info.getF_provinceId() };
		
		List<ExpressInfo> lstExpress = null;
		List<?> list = findBySql(sql.toString(), parmas);
		if(!CollectionUtils.isEmpty(list)){
			Iterator<?> itor = list.iterator();
			lstExpress = new ArrayList<ExpressInfo>(); 
			String provinceId = "";
			String officeNo = "";
			while (itor.hasNext()) {
				Object[] obj = (Object[]) itor.next();
				provinceId = String.valueOf(obj[14]);
				officeNo = String.valueOf(obj[8]).trim();
				//System.out.println("========== "+officeNo.length());
				//排除目的地的快递公司信息(只需要展示始发地相关的快递公司信息)
				if (provinceId.equals(info.getF_provinceId()) && !StringUtils.isEmpty(officeNo)) {
					ExpressInfo tmp = new ExpressInfo();
					//System.out.println("========== "+officeNo);
					tmp.setExpressCode(String.valueOf(obj[0]));
					tmp.setName(String.valueOf(obj[1]));
					tmp.setTelephone(String.valueOf(obj[2]));
					tmp.setMobile(String.valueOf(obj[3]));
					tmp.setServiceLine(String.valueOf(obj[4]));
					tmp.setEvaluation(BigDecimal.valueOf(Double.valueOf(obj[5]
							.toString())));
					tmp.setCharge(String.valueOf(obj[6]));
					tmp.setDeliverDays(String.valueOf(obj[7]));
					tmp.setOfficeNo(officeNo);
					tmp.setSubExpressMobile(String.valueOf(obj[9]));
					tmp.setSubExpressName(String.valueOf(obj[10]));
					tmp.setSubExpressAddress(String.valueOf(obj[11]));
					tmp.setDeliverAreas(String.valueOf(obj[12]));
					tmp.setNonDeliverAreas(String.valueOf(obj[13]));
					tmp.setId(Integer.parseInt(obj[15].toString()));

					lstExpress.add(tmp);
				}
			}
		}
		return lstExpress;
	}
	
	public List<ExpressInfo> getAllExpress() throws BriException{
		return findByHql("from ExpressInfo");
	}
	
	public void saveExpressInfo(ExpressInfo info) throws BriException{
		save(info);
	}
	
	public void updateExpressInfo(ExpressInfo info) throws BriException{
		update(info);
	}
	
	public List<?> getExpressDeliver(String province,String city,String area){
		return null;
	}
	
	public int getMaxExpressCode(){
		String hql = "select max(expressCode) from ExpressInfo";
		Query query = getReadSession().createQuery(hql);
		int code = 0;
		List<?> list = query.list();
		if(!CollectionUtils.isEmpty(list)){
			if(null != list.get(0)){
				String expressCode = list.get(0).toString();
				code = Integer.parseInt(expressCode);
			}
		}
		return code;
	}
	
	/*
	 * public List<ExpressInfo> getExpressInfo(ExpressInfo info) throws
	 * Exception { List<ExpressInfo> list = findByHql("from ExpressInfo ");
	 * return list; }
	 */

}
