/**
 * ============================================================
 * File : AreasInfoDao.java
 * Description : 
 * 
 * Package : com.brilliance.dao
 * Author : Michael
 * Last Edited By :
 * Version : 1.0
 * Created on : 2014-3-15
 * History
 * Modified By : Initial Release
 * Classification : Brilliance Confidential
 * Copyright (C) 2014 Brilliance Team. All rights reserved
 *
 * ============================================================
 */

package com.brilliance.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.brilliance.base.BaseDao;
import com.brilliance.base.BriException;
import com.brilliance.po.ExpressEvalution;

/*******************************************************************************
 * 
 * @Author : Flash
 * @Version : 1.0
 * @Date Created: 2014-3-15
 * @Date Modified :
 * @Modified By :
 * @Contact :
 * @Description :
 * @History :
 * 
 ******************************************************************************/
public interface ExpressEvalutionDao extends BaseDao<ExpressEvalution> {
	
	public int updateEvalScore(String expressCode) throws BriException;
	
	public List<ExpressEvalution> getlstEvaluation(String addressId,int page) throws BriException, ParseException;
	
	public Map<String,String> getcntScoreEvaluation(String addressId) throws BriException;
	
	public Map<String,String> getavgScoreEvaluation(String addressId) throws BriException;
	
	public ExpressEvalution getEvaluationById(String orderCode) throws BriException;
	
	public Map<String,String> getAvgScoreEvaluation() throws BriException;

}
