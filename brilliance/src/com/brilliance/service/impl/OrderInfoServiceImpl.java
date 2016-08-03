/**
 * 
 */
package com.brilliance.service.impl;

import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.brilliance.base.BaseService;
import com.brilliance.base.BriException;
import com.brilliance.base.ServiceReturns;
import com.brilliance.dao.ExpressEvalutionDao;
import com.brilliance.dao.OrderInfoDao;
import com.brilliance.po.ExpressEvalution;
import com.brilliance.po.OrderInfo;
import com.brilliance.service.CommonService;
import com.brilliance.service.OrderInfoService;
import com.brilliance.utils.Constants;
import com.brilliance.utils.Tools;

/**
 * @author mx801343
 * 
 */
@Service
@Transactional
public class OrderInfoServiceImpl extends BaseService<OrderInfo> implements OrderInfoService {
	@Resource
	private OrderInfoDao orderInfoDao;
	@Resource
	private ExpressEvalutionDao evalutionDao;
	@Resource
	private CommonService commonService;
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public ServiceReturns getOrderInfo(OrderInfo orderInfo) throws Exception {
		serviceReturns = new ServiceReturns();
		//先验证改订单的合法性
		OrderInfo order = orderInfoDao.findOrderBy(orderInfo.getId(), orderInfo.getOrderCode(), orderInfo.getUserId());
		if(order==null){
			//不合法
			serviceReturns.setSuccess(false);
			serviceReturns.setMessageType(Constants.MESSAGE_TYPE_WARNING);
			serviceReturns.setMessage(Constants.ERR_ORDER_NOT_EXISTS);
//			serviceReturns.setForwardUrl(Constants.FORWARD_500_PATH);
			return serviceReturns;
		}
		order.setCreateTimeStr(Tools.formatDate(order.getCreateTime()));
		//查找快递公司名称
		//ExpressInfo expressInfo = expressInfoDao.findOneByHql("from ExpressInfo where expressCode=?", order.getExpressCode());
		order.setExpressName(commonService.findExpressNameByCode(order.getExpressCode()));
		
		//合法，则返回该订单信息
		serviceReturns.putData("order",order);
		//查找订单的评价信息
		ExpressEvalution evalution = evalutionDao.getEvaluationById(orderInfo.getOrderCode());
		serviceReturns.putData("evalution",evalution);
//		serviceReturns.setForwardUrl("order/detail");
		return serviceReturns;
	}
	
	public void saveOrderInfo(OrderInfo orderInfo) throws Exception{
		orderInfoDao.save(orderInfo);
	}

	public ServiceReturns findOrders(OrderInfo order) throws BriException{
		serviceReturns = new ServiceReturns();
		List<OrderInfo> orders = orderInfoDao.findOrders(order);
		
		serviceReturns.putData("orders",orders);
		
		return serviceReturns;
	}

	public void updateOrder(OrderInfo orderInfo) {
		orderInfoDao.update(orderInfo);
		
	}

	public int updateStatus(OrderInfo orderInfo) {
		return orderInfoDao.updateStatus(orderInfo);
	}

	public int updateExpressSerNo(OrderInfo orderInfo) {
		return orderInfoDao.updateExpressSerNo(orderInfo);
	}

	public ServiceReturns findAllOrders(OrderInfo order) throws BriException,
			ParseException {
		serviceReturns = new ServiceReturns();
		List<OrderInfo> orders = orderInfoDao.findAllOrders(order);
		
		serviceReturns.putData("orders",orders);
		
		return serviceReturns;
	}
}
