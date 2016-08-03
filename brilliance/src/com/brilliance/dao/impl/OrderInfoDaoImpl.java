package com.brilliance.dao.impl;

import java.text.ParseException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.brilliance.base.BriException;
import com.brilliance.base.impl.BaseDaoImpl;
import com.brilliance.dao.OrderInfoDao;
import com.brilliance.po.OrderInfo;
import com.brilliance.utils.Tools;

@Repository
public class OrderInfoDaoImpl extends BaseDaoImpl<OrderInfo> implements OrderInfoDao {
	
	
	public OrderInfo findOrderBy(Integer ID, String orderID, String userID)
			throws Exception {
		Query query = getReadSession().createQuery("FROM OrderInfo where userId=:userID and orderCode=:orderID");
		//query.setParameter("ID", ID);
		query.setParameter("userID", userID);
		query.setParameter("orderID", orderID);
		List list = query.list();
		if(list==null || list.size()==0){
			return null;
		}
		return (OrderInfo)(list.get(0));
	}
	
	public List<OrderInfo> findOrders(OrderInfo order) throws BriException {
		/*ExpressInfo expressInfo = new ExpressInfo();
		Criteria criteria = getReadSession().createCriteria(OrderInfo.class,"order").add(Example.create(expressInfo)).createCriteria("expressInfo").add(Example.create(expressInfo.getExpressCode()));*/
		
		Criteria criteria = getReadSession().createCriteria(OrderInfo.class,"order");
		criteria.createCriteria("expressInfo", "express");
		//criteria.createAlias("expressInfo", "express"); //same use with above one
		
		//criteria.add(Restrictions.eq("order.expressInfo"))
		//criteria.setComment(arg0)
		//Query query = getReadSession().createQuery("select a,orderCode,a.expressCode,b.name FROM OrderInfo a ,ExpressInfo b where a.expressCode=b.expressCode");
		//criteria.add(Restrictions.eq("expressCode", order.getExpressCode()));
		//criteria.createCriteria("expressInfo");
		if (!Tools.isEmpty(order.getExpressCode())) {
			criteria.add(Restrictions.eq("order.expressCode", order.getExpressCode()));
		}
		if (!Tools.isEmpty(order.getT_contact())) {
			criteria.add(Restrictions.eq("order.t_contact", order.getT_contact()));
		}
		if (!Tools.isEmpty(order.getCargoName())) {
			criteria.add(Restrictions.like("order.cargoName", order.getCargoName()
					+ "%"));
		}
		if (!Tools.isEmpty(order.getUserId())) {
			criteria.add(Restrictions.eq("order.userId", order.getUserId()));
		}
		if(!Tools.isEmpty(order.getStatus())){
			if (0 != order.getStatus()) {
				criteria.add(Restrictions.eq("order.status", order.getStatus()));
			}
		}
		
		if(null != order.getStartDt()){
			criteria.add(Restrictions.ge("order.createTime", order.getStartDate()));
		}
		if(null != order.getEndDt()){
			criteria.add(Restrictions.le("order.createTime", order.getEndDt()));
		}

		return criteria.list();
	}
	
	public List<OrderInfo> findAllOrders(OrderInfo order) throws BriException, ParseException {
		Criteria criteria = getReadSession().createCriteria(OrderInfo.class);
		if (0 != order.getStatus()) {
			criteria.add(Restrictions.eq("status", order.getStatus()));
		}
		if (!Tools.isEmpty(order.getStartDate())) {
			criteria.add(Restrictions.gt("createTime", Tools.parseToDate(order.getStartDate())));
		}
		if (!Tools.isEmpty(order.getEndDate())) {
			criteria.add(Restrictions.lt("createTime", Tools.parseToDate(order.getEndDate())));
		}

		return criteria.list();
	}
	
	public void updateOrder(OrderInfo orderInfo) {
		update(orderInfo);
	}

	public int updateStatus(OrderInfo orderInfo){
		Query query = getWriteSession().createQuery("update OrderInfo set status=:status where ordercode=:orderCode ");
		query.setParameter("status", orderInfo.getStatus());
		query.setParameter("orderCode", orderInfo.getOrderCode());
		return query.executeUpdate();
	}
	
	public int updateExpressSerNo(OrderInfo orderInfo){
		Query query = getWriteSession().createQuery("update OrderInfo set expressSerialNO=:serNo where ordercode=:orderCode ");
		query.setParameter("serNo", orderInfo.getExpressSerialNO());
		query.setParameter("orderCode", orderInfo.getOrderCode());
		return query.executeUpdate();
	}
	
	public void saveOrderInfo(OrderInfo orderInfo) throws Exception{
		this.save(orderInfo);
	}
}
