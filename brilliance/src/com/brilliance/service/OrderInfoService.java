/**
 * 
 */
package com.brilliance.service;

import java.text.ParseException;
import java.util.List;

import com.brilliance.base.BriException;
import com.brilliance.base.ServiceReturns;
import com.brilliance.po.OrderInfo;

/**
 * @author flash
 * 
 */
public interface OrderInfoService {
	public ServiceReturns getOrderInfo(OrderInfo orderInfo) throws Exception;

	public void saveOrderInfo(OrderInfo orderInfo) throws Exception;

	public ServiceReturns findOrders(OrderInfo order) throws BriException;

	public void updateOrder(OrderInfo orderInfo);

	public int updateStatus(OrderInfo orderInfo);

	public int updateExpressSerNo(OrderInfo orderInfo);

	public ServiceReturns findAllOrders(OrderInfo order) throws BriException,ParseException;

}
