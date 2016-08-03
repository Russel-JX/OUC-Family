package com.brilliance.service.impl;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.brilliance.base.BaseService;
import com.brilliance.base.BriException;
import com.brilliance.base.ServiceReturns;
import com.brilliance.dao.ExpressInfoDao;
import com.brilliance.po.ExpressInfo;
import com.brilliance.service.ExpressEvalutionService;
import com.brilliance.service.ExpressInfoService;
import com.brilliance.utils.Constants;

@Service
@Transactional
public class ExpressInfoServiceImpl extends BaseService<ExpressInfo> implements
		ExpressInfoService {

	@Resource
	private ExpressInfoDao expressInfoDao;
	
	@Resource
	private ExpressEvalutionService expressEvalutionService;
	
	public ServiceReturns getExpressInfo(ExpressInfo info) throws Exception {
		ServiceReturns serviceReturns = new ServiceReturns();
		
		List<?> list = expressInfoDao.getExpressInfo(info);
		//合法，则返回该订单信息
		serviceReturns.putData("dataList",list);
		return serviceReturns;
	}

	public List<ExpressInfo> getAllExpress()
			throws BriException {
		return expressInfoDao.getAllExpress();
	}
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public ServiceReturns getAllExpList() throws BriException {
		serviceReturns = new ServiceReturns();
		List<ExpressInfo> list = expressInfoDao.getAllExpress();
		if(!CollectionUtils.isEmpty(list)){
			Map<String,String> map = expressEvalutionService.getAvgScoreEvaluation();
			Iterator<ExpressInfo> itor = list.iterator();
			String expressCode = "";
			BigDecimal avgScore = null;
			while (itor.hasNext()){
				ExpressInfo info = itor.next();
				expressCode = info.getExpressCode();
				if (map.containsKey(expressCode)) {
					avgScore = new BigDecimal(map.get(expressCode)).setScale(
							Constants.DECIMAL_PLACE_ONE,
							BigDecimal.ROUND_HALF_UP);
					info.setEvaluation(avgScore);
				}else{
					info.setEvaluation(new BigDecimal("0"));
				}
				
			}
		}
		serviceReturns.putData("lstExpress",list);
		
		return serviceReturns;
	}
	
	public ServiceReturns saveExpress(ExpressInfo info) throws BriException {
		serviceReturns = new ServiceReturns();
		expressInfoDao.save(info);
		serviceReturns.putData("newExpress",info);
		
		return serviceReturns;
	}
	public ServiceReturns editExpress(ExpressInfo info) throws Exception{
		serviceReturns = new ServiceReturns();
		
		ExpressInfo originalExpress = expressInfoDao.getById(ExpressInfo.class, info.getId());
		originalExpress.setName(info.getName());
		originalExpress.setPostId(info.getPostId());
		originalExpress.setTelephone(info.getTelephone());
		originalExpress.setMobile(info.getMobile());
		originalExpress.setServiceLine(info.getServiceLine());
		
		//将旧记录的未改变的属性copy到新记录中
		//BeanUtils.copyProperties(originalExpress, info, new String[]{"name","expressCode","postId","logoUrl","telephone","mobile","serviceLine","evaluation"});
		BeanUtils.copyProperties(originalExpress, info, new String[]{"name","postId","telephone","mobile","serviceLine",});
		
		expressInfoDao.update(info);//??2条update语句??
		serviceReturns.putData("editedExpress",info);
		
		return serviceReturns;
	}
	
	public ServiceReturns removeExpress(ExpressInfo info) throws Exception{
		serviceReturns = new ServiceReturns();
		expressInfoDao.delete(info);
		serviceReturns.putData("deletedExpress",info);
		
		return serviceReturns;
	}
	
	public ServiceReturns saveExpressLogo(ExpressInfo info) throws Exception{
		serviceReturns = new ServiceReturns();
		
		ExpressInfo originalExpress = expressInfoDao.getById(ExpressInfo.class, info.getId());
		originalExpress.setLogoUrl(info.getLogoUrl());
//		BeanUtils.copyProperties(originalExpress, info, new String[]{"name","postId","telephone","mobile","serviceLine","","","","","",""});
		BeanUtils.copyProperties(originalExpress, info, new String[]{"logoUrl"});
		
		expressInfoDao.update(info);//??2条update语句??
		serviceReturns.putData("editedExpress",info);
		
		return serviceReturns;
	}

	
	public int getMaxExpressCode() {
		return expressInfoDao.getMaxExpressCode();
	}
	
}
