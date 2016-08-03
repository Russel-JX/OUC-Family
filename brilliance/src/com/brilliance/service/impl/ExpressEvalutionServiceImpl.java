package com.brilliance.service.impl;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.brilliance.base.BaseService;
import com.brilliance.base.BriException;
import com.brilliance.base.ServiceReturns;
import com.brilliance.dao.ExpressEvalutionDao;
import com.brilliance.dao.OrderInfoDao;
import com.brilliance.po.ExpressEvalution;
import com.brilliance.po.OrderInfo;
import com.brilliance.service.ExpressEvalutionService;
import com.brilliance.utils.Constants;

@Service
@Transactional
public class ExpressEvalutionServiceImpl extends BaseService<ExpressEvalution> implements
		ExpressEvalutionService {
	@Resource
	private OrderInfoDao orderDao;
	@Resource
	private ExpressEvalutionDao evalutionDao;
	
	public ServiceReturns evalution(ExpressEvalution evalution)
			throws Exception {
		serviceReturns = new ServiceReturns();
		//先判断订单是否属于当前用户
		OrderInfo orderInfo = orderDao.findOneByHql("from OrderInfo where orderCode=? and userId=?",evalution.getOrderCode(),evalution.getUserId());
		if(orderInfo==null){//订单不属于当前用户，非法操作
			serviceReturns.setSuccess(false);
			serviceReturns.setMessage(Constants.ERR_ORDER_NOT_BELONGS,Constants.MESSAGE_TYPE_WARNING);
			return serviceReturns;
		}
		//查看订单是否已经评价过了
		ExpressEvalution result = evalutionDao.getEvaluationById(evalution.getOrderCode());
		if(result!=null){//已经有评价信息了
			serviceReturns.setSuccess(false);
			serviceReturns.setMessage(Constants.ERR_ORDER_NOT_BELONGS,Constants.MESSAGE_TYPE_ERROR);
			return serviceReturns;
		}
		evalutionDao.save(evalution);
		//update evaluation for express
		evalutionDao.updateEvalScore(evalution.getExpressCode());
		serviceReturns.setSuccess(true);
		serviceReturns.setMessage(Constants.ERR_EVALUTION_SUCCESS,Constants.MESSAGE_TYPE_INFO);
		serviceReturns.putData("evalution", evalution);
		return serviceReturns;
	}
	
	
	
	public ServiceReturns saveEvalution(ExpressEvalution evalution)
			throws BriException {
		serviceReturns = new ServiceReturns();
		evalutionDao.save(evalution);
		serviceReturns.setMessage(Constants.ERR_EVALUTION_SUCCESS,Constants.MESSAGE_TYPE_INFO);
		return serviceReturns;
	}


	public ServiceReturns getlstEvaluation(String addressId,int page)
			throws BriException, ParseException {
		serviceReturns = new ServiceReturns();
		
		List<ExpressEvalution> lstEvaluation = evalutionDao.getlstEvaluation(addressId,page);
		serviceReturns.putData("lstData", lstEvaluation);
		
		return serviceReturns;
	}



	public ServiceReturns getMoreCommentsInfo(String addressId,int page)
			throws BriException, ParseException {
		serviceReturns = new ServiceReturns();
		
		List<ExpressEvalution> lstEvaluation = evalutionDao.getlstEvaluation(addressId,page);
		serviceReturns.putData("lstData", lstEvaluation);
		
		Map<String,String> map = evalutionDao.getcntScoreEvaluation(addressId);
		if(!CollectionUtils.isEmpty(map)){
			Map<String,String> map1 = evalutionDao.getavgScoreEvaluation(addressId);
			serviceReturns.putData("mapScore", map);
			serviceReturns.putData("avgScore", map1);
		}
		
		return serviceReturns;
	}



	public Map<String, String> getAvgScoreEvaluation() throws BriException {
		return evalutionDao.getAvgScoreEvaluation();
	}
	
	

}
