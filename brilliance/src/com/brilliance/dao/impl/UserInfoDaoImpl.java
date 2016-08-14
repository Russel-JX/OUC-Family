/**
 * ============================================================
 * File : UserInfoDaoImpl.java
 * Description : 
 * 
 * Package : com.brilliance.dao.impl
 * Author : Michael
 * Last Edited By :
 * Version : 1.0
 * Created on : 2014-3-11
 * History
 * Modified By : Initial Release
 * Classification : Personality
 * Copyright (C) 2014 Michael. All rights reserved
 *
 * ============================================================
 */
package com.brilliance.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.brilliance.base.BriException;
import com.brilliance.base.impl.BaseDaoImpl;
import com.brilliance.dao.UserInfoDao;
import com.brilliance.po.UserInfo;
import com.brilliance.utils.Tools;

/*******************************************************************************
 * 
 * @Author : Michael
 * @Version : 1.0
 * @Date Created: 2014-3-11
 * @Date Modified :
 * @Modified By :
 * @Contact :
 * @Description :
 * @History :
 * 
 ******************************************************************************/
@Repository
public class UserInfoDaoImpl extends BaseDaoImpl<UserInfo> implements
		UserInfoDao {

	public UserInfo getUserInfoByMobile(String mobile) throws BriException {
		return findOneByHql("FROM UserInfo WHERE mobileNO=?",
				new Object[] { mobile });
	}
	
	public UserInfo getUserInfoById(String userId) throws BriException {
		return findOneByHql("FROM UserInfo WHERE userId=?",
				new Object[] { userId });
	}

	public void addUserInfo(UserInfo user) {
		save(user);
	}

	public List<UserInfo> getUserInfo(UserInfo user) throws BriException {

		Criteria criteria = getSession().createCriteria(UserInfo.class);
		if (!Tools.isEmpty(user.getName())) {
			criteria.add(Restrictions.eq("name", user.getName()));
		}
		if (!Tools.isEmpty(user.getMobileNO())) {
			criteria.add(Restrictions.eq("mobileNO", user.getMobileNO()));
		}
		return criteria.list();
	}
	
	public List<UserInfo> getAllUsersInfo(UserInfo user) throws BriException {
		Criteria criteria = getSession().createCriteria(UserInfo.class);
		if (!Tools.isEmpty(user.getGender())) {
			criteria.add(Restrictions.eq("gender", user.getGender()));
		}
		if (!Tools.isEmpty(user.getProvinceId())) {
			criteria.add(Restrictions.eq("provinceId", user.getProvinceId()));
		}
		if (!Tools.isEmpty(user.getCityId())) {
			criteria.add(Restrictions.eq("cityId", user.getCityId()));
		}
		if (!Tools.isEmpty(user.getAreaId())) {
			criteria.add(Restrictions.eq("areaId", user.getAreaId()));
		}
		return criteria.list();
	}
	
	public void updateLoginTime(UserInfo user) throws BriException{
		update(user);
	}
	
	//只看每次的用户和要加的分数，不是看用户是谁和次数。
	public void updateCredits(String[] userIds,int credits) throws BriException{
		String hql = "update UserInfo user set credits = credits+:credits where userId=:userId";
		for(int i=0;i<userIds.length;i++){
			Query query = getReadSession().createQuery(hql);
			query.setParameter("credits", credits);
			query.setParameter("userId", userIds[i]);
			query.executeUpdate();
		}
		
	}

}
