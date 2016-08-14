/**
 * ============================================================
 * File : AreasInfoDao.java
 * Description : 
 * 
 * Package : com.brilliance.dao
 * Author : Michael
 * Last Edited By :
 * Version : 1.0
 * Created on : 2014-3-15
 * History
 * Modified By : Initial Release
 * Classification : Brilliance Confidential
 * Copyright (C) 2014 Brilliance Team. All rights reserved
 *
 * ============================================================
 */

package com.brilliance.dao.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.brilliance.base.BriException;
import com.brilliance.base.impl.BaseDaoImpl;
import com.brilliance.dao.ExpressEvalutionDao;
import com.brilliance.po.ExpressEvalution;
import com.brilliance.utils.Constants;
import com.brilliance.utils.Tools;

/*******************************************************************************
 * 
 * @Author : Flash
 * @Version : 1.0
 * @Date Created: 2014-4-15
 * @Date Modified :
 * @Modified By :
 * @Contact :
 * @Description :
 * @History :
 * 
 ******************************************************************************/
@Repository
public class ExpressEvalutionDaoImpl extends BaseDaoImpl<ExpressEvalution>
		implements ExpressEvalutionDao {

	public int updateEvalScore(String expressCode) throws BriException {
		String sql = "update express_info a set a.evaluation=(select round(avg(score),2) score from express_evaluation b where b.expresscode=?) where a.expresscode=?";
		String[] parmas = new String[] { expressCode, expressCode };

		return executeBySql(sql, parmas);
	}
	
	
	public List<ExpressEvalution> getlstEvaluation(String addressId,int page) throws BriException, ParseException {
		StringBuilder hql = new StringBuilder();
		hql.append("select b.id,b.addressId,b.imageUrl,b.expressCode,b.userId,b.score,b.extraInfo,b.createTime,a.name,a.logoUrl,c.name,c.mobileNO ");
		hql.append(" from ExpressInfo a,expressEvalution b,UserInfo c where a.expressCode=b.expressCode and b.userId=c.userId and b.addressId=:addressId order by b.id desc");
		
		Query query = getReadSession().createQuery(hql.toString());
		query.setParameter("addressId", addressId);
		int startCnt = (page-1) * Constants.PAGE_COUNT;
		
		query.setFirstResult(startCnt);
		query.setMaxResults(Constants.PAGE_COUNT);

		List<?> list = query.list();
		
		List<ExpressEvalution> lstEvalution = null;
		if(!CollectionUtils.isEmpty(list)){
			lstEvalution = new ArrayList<ExpressEvalution>();
			Iterator<?> itor = list.iterator();
			while (itor.hasNext()){
				Object[] obj = (Object[]) itor.next();
				
				ExpressEvalution evalution = new ExpressEvalution();
				evalution.setId(Integer.parseInt(String.valueOf(obj[0])));
				evalution.setAddressId(String.valueOf(obj[1]));
				evalution.setImageUrl(String.valueOf(obj[2]));
				evalution.setExpressCode(String.valueOf(obj[3]));
				evalution.setUserId(String.valueOf(obj[4]));
				evalution.setScore(String.valueOf(obj[5]));
				evalution.setExtraInfo(String.valueOf(obj[6]));
				evalution.setCreateTime(Tools.parseToDate(String.valueOf(obj[7]),Constants.DATE_PATTEN_SEC));
				evalution.setExpressName(String.valueOf(obj[8]));
				evalution.setLogoPath(String.valueOf(obj[9]));
				evalution.setUserName(String.valueOf(obj[10]));
				evalution.setMobileNo(String.valueOf(obj[11]));
				
				lstEvalution.add(evalution);
			}
		}
		return lstEvalution;
	}
	
	/**
	 * 获取每个星级的被评定的次数
	 * @param addressId
	 * @return map<score,count(score)>
	 * @throws BriException
	 */
	public Map<String,String> getcntScoreEvaluation(String addressId) throws BriException{
		String hql = "select count(score),score from expressEvalution where addressId=:addressId group by addressId,score";
		Query query = getReadSession().createQuery(hql.toString());
		query.setParameter("addressId", addressId);
		
		List<?> list = query.list();
		Map<String,String> map = null;
		if(!CollectionUtils.isEmpty(list)){
			map = new HashMap<String,String>();
			Iterator<?> itor = list.iterator();
			while(itor.hasNext()){
				Object[] obj = (Object[])itor.next();
				map.put(String.valueOf(obj[1]), String.valueOf(obj[0]));
			}
		}
		return map;
	}
	
	
	/**
	 * 获取平均值和总记录数
	 * @param addressId
	 * @return map<avgScore,count(score)>
	 * @throws BriException
	 */
	public Map<String,String> getavgScoreEvaluation(String addressId) throws BriException{
		String hql = "select round(avg(score),1),count(score) from expressEvalution where addressId=:addressId group by addressId";
		Query query = getReadSession().createQuery(hql.toString());
		query.setParameter("addressId", addressId);
		
		List<?> list = query.list();
		Map<String,String> map = null;
		if(!CollectionUtils.isEmpty(list)){
			map = new HashMap<String,String>();
			Iterator<?> itor = list.iterator();
			while(itor.hasNext()){
				Object[] obj = (Object[])itor.next();
				map.put("avgScore", String.valueOf(obj[0]));
				map.put("cnt", String.valueOf(obj[1]));
			}
		}
		return map;
	}
	
	public ExpressEvalution getEvaluationById(String orderCode) throws BriException{
		String hql = "select extraInfo,score,orderCode,addressId,userId from expressEvalution where orderCode=:orderCode";
		Query query = getReadSession().createQuery(hql.toString());
		query.setParameter("orderCode", orderCode);
		
		List<?> list = query.list();
		ExpressEvalution evaluation = null;
		if(!CollectionUtils.isEmpty(list)){
			evaluation = new ExpressEvalution();
			Object[] obj = (Object[])list.get(0);
			evaluation.setExtraInfo(String.valueOf(obj[0]));
			evaluation.setScore(String.valueOf(obj[1]));
			evaluation.setOrderCode(String.valueOf(obj[2]));
			evaluation.setAddressId(String.valueOf(obj[3]));
			evaluation.setUserId(String.valueOf(obj[4]));
		}
		return evaluation;
	}
	
	public Map<String,String> getAvgScoreEvaluation() throws BriException{
		String hql = "select avg(score),expressCode from expressEvalution group by expressCode";
		Query query = getReadSession().createQuery(hql.toString());
		
		List<?> list = query.list();
		Map<String,String> map = null;
		if(!CollectionUtils.isEmpty(list)){
			map = new HashMap<String,String>();
			Iterator<?> itor = list.iterator();
			while(itor.hasNext()){
				Object[] obj = (Object[])itor.next();
				map.put(String.valueOf(obj[1]), String.valueOf(obj[0]));
			}
		}
		return map;
	}

}
