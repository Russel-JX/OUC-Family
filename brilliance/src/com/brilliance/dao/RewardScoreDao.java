package com.brilliance.dao;

import java.text.ParseException;
import java.util.List;

import com.brilliance.base.BaseDao;
import com.brilliance.base.BriException;
import com.brilliance.po.RewardScore;

public interface RewardScoreDao extends BaseDao<RewardScore>{
	
	List<RewardScore> getUserInfoById(String userId) throws BriException, ParseException;

}
