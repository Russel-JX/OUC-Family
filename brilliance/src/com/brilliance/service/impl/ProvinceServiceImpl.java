package com.brilliance.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brilliance.base.BaseService;
import com.brilliance.dao.ProvinceDao;
import com.brilliance.po.AreasInfo;
import com.brilliance.po.ProvincePO;
import com.brilliance.service.ProvinceService;

@Service
@Transactional
public class ProvinceServiceImpl extends BaseService<AreasInfo> implements ProvinceService {

	@Resource
	private ProvinceDao provinceDao;
	private static final Log logger = LogFactory.getLog(ProvinceServiceImpl.class);

	public void save(ProvincePO provincePO) {
		provinceDao.save(provincePO);
	}

	public void delete(Integer id) {
//		provinceDao.delete(id);
	}

	public void update(ProvincePO provincePO) {
		provinceDao.update(provincePO);
	}

	public ProvincePO getById(Integer id) {

//		return provinceDao.getById(id);
		return null;
	}

//	public List<ProvincePO> getAll() {
//		return provinceDao.getAll();
//	}

	public ProvincePO findTest(Integer id) {
		return provinceDao.findTest(id);
	}

	@Override
	public List<ProvincePO> getByIds(Integer[] ids) {
		
//		return provinceDao.getByIds(ids);
		return null;
	}

}
