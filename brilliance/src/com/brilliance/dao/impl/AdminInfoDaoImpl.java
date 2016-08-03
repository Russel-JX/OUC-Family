/**
 * ============================================================
 * File : AdminInfoDaoImpl.java
 * Description : 
 * 
 * Package : com.brilliance.dao.impl
 * Author : Russel
 * Last Edited By :
 * Version : 1.0
 * Created on : 2014-7-2
 * History
 * Modified By : Initial Release
 * Classification : Personality
 * Copyright (C) 2014 Russel. All rights reserved
 *
 * ============================================================
 */
package com.brilliance.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.brilliance.base.BriException;
import com.brilliance.base.impl.BaseDaoImpl;
import com.brilliance.dao.AdminInfoDao;
import com.brilliance.po.AdminInfo;
import com.brilliance.po.CustomizationAddressInfo;
import com.brilliance.utils.Constants;
import com.brilliance.utils.Tools;

/*******************************************************************************
 * 
 * @Author : Russel
 * @Version : 1.0
 * @Date Created: 2014-7-2
 * @Date Modified :
 * @Modified By :
 * @Contact :
 * @Description :
 * @History :
 * 
 ******************************************************************************/
@Repository
public class AdminInfoDaoImpl extends BaseDaoImpl<AdminInfo> implements
		AdminInfoDao {
	public AdminInfo getAdminInfoByName(String name) throws BriException {
		return findOneByHql("FROM adminInfo WHERE name=? and status=1",new Object[] {name});
	}

	public void addAdminInfo(AdminInfo admin) {
		save(admin);
	}

	public List<AdminInfo> getAdminInfo(AdminInfo admin) throws BriException {

		return null;
	}
	
	public List<AdminInfo> getAdminLst() throws BriException{
		String hql = "SELECT distinct admin.id,admin.accountId,admin.name FROM adminInfo admin";

		List<AdminInfo> adminLst = new ArrayList<AdminInfo>();
		Query query = getReadSession().createQuery(hql.toString());
		List<?> list = query.list();

		if(!CollectionUtils.isEmpty(list)){
			Iterator<?> itor = list.iterator();
			while (itor.hasNext()){
				Object[] obj = (Object[]) itor.next();
				AdminInfo adminInfo = new AdminInfo();
				adminInfo.setId(Integer.parseInt(obj[0].toString()));
				adminInfo.setAccountId(obj[1].toString());
				adminInfo.setName(obj[2].toString());
				
				adminLst.add(adminInfo);
				
			}
		}
		return adminLst;
		
//		return findByHql("SELECT new adminInfo(distinct admin.id,admin.accountId,admin.name)FROM adminInfo admin,customizationAddressInfo custAddr where admin.accountId = custAddr.createdBy");
//		return findByHql("SELECT new AdminInfo(admin.id,admin.accountId,admin.name)FROM AdminInfo admin INNER JOIN admin.   CustomizationAddressInfo custAddr  ON admin.accountId = custAddr.createdBy");
	}
	
	public void updateLoginTime(AdminInfo admin) throws BriException{
		update(admin);
	}

	



}
