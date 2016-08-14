/**
 * 
 */
package com.brilliance.dao;

import java.text.ParseException;
import java.util.List;

import com.brilliance.base.BaseDao;
import com.brilliance.base.BriException;
import com.brilliance.po.OrderInfo;

/**
 * @author mx801343
 *
 */
public interface OrderInfoDao extends BaseDao<OrderInfo> {
	public OrderInfo findOrderBy(Integer ID, String orderID, String userID)throws Exception;
	
	public void saveOrderInfo(OrderInfo orderInfo) throws Exception;
	
	public List<OrderInfo> findOrders(OrderInfo order) throws BriException;
	
	public void updateOrder(OrderInfo orderInfo);
	
	public int updateStatus(OrderInfo orderInfo);
	
	public int updateExpressSerNo(OrderInfo orderInfo);
	
	public List<OrderInfo> findAllOrders(OrderInfo order) throws BriException, ParseException;
	
}
