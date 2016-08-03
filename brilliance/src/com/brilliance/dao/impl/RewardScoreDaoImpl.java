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
import com.brilliance.dao.RewardScoreDao;
import com.brilliance.po.RewardScore;
import com.brilliance.utils.Tools;

@Repository
public class RewardScoreDaoImpl extends BaseDaoImpl<RewardScore> implements RewardScoreDao {

	@SuppressWarnings("unchecked")
	public List<RewardScore> getUserInfoById(String userId) throws BriException, ParseException {
		String sql = "select a.id,a.userId,a.rewardType,a.score,a.creationTime,b.mobileNO FROM rewardScore a,UserInfo b WHERE a.userId=b.userId and a.userId=:userId";
		
		Query query = getReadSession().createQuery(sql);
		query.setParameter("userId", userId);
		List<?> list = query.list();
		
		List<RewardScore> list1 = new ArrayList<RewardScore>();
		
		if(!CollectionUtils.isEmpty(list)){
			Iterator<?> itor = list.iterator();
			while (itor.hasNext()){
				Object[] obj = (Object[])itor.next();
				RewardScore reward = new RewardScore();
				reward.setId(Integer.parseInt(obj[0].toString()));
				reward.setRewardType(String.valueOf(obj[2]));
				reward.setUserId(String.valueOf(obj[1]));
				reward.setScore(String.valueOf(obj[3]));
				reward.setCreationTime(Tools.parseToDate(obj[4].toString()));
				reward.setMobileNo(String.valueOf(obj[5]));
				
				list1.add(reward);
			}
		}
		return list1;
	}

}
