package com.brilliance.service.impl;

import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brilliance.base.BaseService;
import com.brilliance.base.BriException;
import com.brilliance.base.ServiceReturns;
import com.brilliance.dao.RewardScoreDao;
import com.brilliance.po.RewardScore;
import com.brilliance.service.RewardScoreService;

@Service
@Transactional
public class RewardScoreServiceImpl extends BaseService<RewardScore> implements RewardScoreService {
	
	@Resource
	private RewardScoreDao rewardScoreDao;
	
	public ServiceReturns save(RewardScore rewardScore){
		return super.save(rewardScore);
	}
	
	public ServiceReturns saveBulk(List<RewardScore> list) throws BriException{
		return super.saveBulks(list);
	}

	
	public ServiceReturns getUserRewardInfo(String userId) throws BriException, ParseException {
		serviceReturns = new ServiceReturns();
		List<RewardScore> list = rewardScoreDao.getUserInfoById(userId);
		serviceReturns.putData("lstReward", list);
		return serviceReturns;
	}
	
	
}
