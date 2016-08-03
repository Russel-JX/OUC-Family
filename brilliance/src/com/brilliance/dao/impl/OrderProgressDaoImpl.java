package com.brilliance.dao.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.brilliance.base.BriException;
import com.brilliance.base.impl.BaseDaoImpl;
import com.brilliance.dao.OrderProgressDao;
import com.brilliance.po.OrderProgress;
import com.brilliance.utils.Constants;
import com.brilliance.utils.Tools;

@Repository
public class OrderProgressDaoImpl extends BaseDaoImpl<OrderProgress> implements OrderProgressDao {

	public Object merge(OrderProgress orderProgress){
		return getWriteSession().save(orderProgress);
	}
	
	public List<OrderProgress> getAll(OrderProgress orderProgress) throws BriException{
		String hql = "select a.id,a.userId,a.expressNo,a.expressCode,b.name as expressName,a.source,a.creationDate,a.latestStatusInfo,b.logoUrl,b.postId from orderProgress a, ExpressInfo b where a.expressCode=b.expressCode and userId=:userId order by creationDate desc";
		Query query = getReadSession().createQuery(hql);
		query.setParameter("userId", orderProgress.getUserId());

		List<?> list = query.list();
		List<OrderProgress> lstOrder= new ArrayList<OrderProgress>();
		try {
			if (!CollectionUtils.isEmpty(list)) {
				Iterator<?> itor = list.iterator();
				while (itor.hasNext()) {
					Object[] obj = (Object[]) itor.next();
					OrderProgress order = new OrderProgress();
					order.setExpressCode(String.valueOf(obj[3]));

					order.setCreationDate(Tools.parseToDate(
							String.valueOf(obj[6]), Constants.DATE_PATTEN_SEC));

					order.setExpressName(String.valueOf(obj[4]));
					order.setExpressNo(String.valueOf(obj[2]));
					order.setId(Integer.parseInt(obj[0].toString()));
					order.setUserId(String.valueOf(obj[1]));
					order.setSource(String.valueOf(obj[5]));
					order.setLatestStatusInfo(String.valueOf(obj[7]));
					order.setImageUrl(String.valueOf(obj[8]));
					order.setPostId(String.valueOf(obj[9]));

					lstOrder.add(order);
				}
			}
		} catch (ParseException e) {
			logger.info("fetch scan orders error", e);
			throw new BriException(e, "fetch scan orders error");
		}
		return lstOrder;
	}
	
	public int getCount(String expressNo){
		String hql = "select count(*) from orderProgress where expressNo=:expressNo";
		
		Query query = getReadSession().createQuery(hql);
		query.setParameter("expressNo", expressNo);
		 
		return ((Long)query.iterate().next()).intValue();
	}
	
	
	public int updateStatusInfo(OrderProgress orderProgress){
		String hql = "update orderProgress set latestStatusInfo=:status where expressNo=:expressNo";
		
		Query query = getReadSession().createQuery(hql);
		query.setParameter("status", orderProgress.getLatestStatusInfo());
		query.setParameter("expressNo", orderProgress.getExpressNo());
		return query.executeUpdate();
	}
	
}
