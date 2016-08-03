package com.brilliance.service;

import java.text.ParseException;
import java.util.List;

import com.brilliance.base.BriException;
import com.brilliance.base.ServiceReturns;
import com.brilliance.po.RewardScore;

public interface RewardScoreService {

	public ServiceReturns save(RewardScore rewardScore);
	
	public ServiceReturns saveBulk(List<RewardScore> list) throws BriException;
	
	public ServiceReturns getUserRewardInfo(String userId) throws BriException, ParseException;
}
