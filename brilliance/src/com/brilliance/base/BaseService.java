/**
 * 
 */
package com.brilliance.base;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.brilliance.utils.Tools;

/**
 * @author mx801343
 */
@Service
public class BaseService<T> {
	protected static Log logger;
	protected ServiceReturns serviceReturns;
//	如果有必须要记录日志后期可以注入LogDao，方便每个子service方便的记录操作日志，流水号等等
//	暂时不需要*
//	@Resource
//	protected LogDao LogDao;
	
	@Autowired
	protected BaseDao<T> baseDao;
	
	public BaseService(){
		logger = LogFactory.getLog(this.getClass());
	}
	@Transactional
	public ServiceReturns save(T t){
		serviceReturns = new ServiceReturns();
		try {
			baseDao.save(t);
			serviceReturns.success = true;
			serviceReturns.data.put("details", t);
		} catch (Exception e) {
			serviceReturns = Tools.getExceptionServiceReturns(e);
		}
		return serviceReturns;
	}
	
	@Transactional
	public void saveAll(List<T> list) throws BriException{
		try {
			baseDao.saveAll(list);
		} catch (Exception e) {
			throw new BriException(e, "批量保存异常");
		}
	}
	
	@Transactional
	public ServiceReturns saveBulks(List<T> list) throws BriException{
		serviceReturns = new ServiceReturns();
		try {
			baseDao.saveAll(list);
			
			serviceReturns.success = true;
			serviceReturns.data.put("details",list);
		} catch (Exception e) {
			throw new BriException(e, "批量保存异常");
		}
		return serviceReturns;
	}
	
	@Transactional
	public ServiceReturns update(T t){
		serviceReturns = new ServiceReturns();
		try {
			baseDao.update(t);
			serviceReturns.success = true;
			serviceReturns.data.put("details", t);
		} catch (Exception e) {
			serviceReturns = Tools.getExceptionServiceReturns(e);
		}
		return serviceReturns;
	}
	
	@Transactional
	public ServiceReturns updateBulk(List<T> list){
		serviceReturns = new ServiceReturns();
		try {
			baseDao.updateBulk(list);
			serviceReturns.success = true;
			serviceReturns.data.put("details", list);
		} catch (Exception e) {
			serviceReturns = Tools.getExceptionServiceReturns(e);
		}
		return serviceReturns;
	}
	
	@Transactional
	public ServiceReturns delete(T t){
		serviceReturns = new ServiceReturns();
		try {
			baseDao.delete(t);
			serviceReturns.success = true;
			serviceReturns.data.put("details", t);
		} catch (Exception e) {
			serviceReturns = Tools.getExceptionServiceReturns(e);
		}
		return serviceReturns;
	}
	
	@Transactional
	public ServiceReturns deleteAll(List<T> list){
		serviceReturns = new ServiceReturns();
		try {
			baseDao.deleteAll(list);
			serviceReturns.success = true;
			serviceReturns.data.put("details", list);
		} catch (Exception e) {
			serviceReturns = Tools.getExceptionServiceReturns(e);
		}
		return serviceReturns;
	}
	
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public ServiceReturns getAll(Class<T> clazz){
		serviceReturns = new ServiceReturns();
		try {
			serviceReturns.success = true;
			serviceReturns.data.put("details", baseDao.getAll(clazz));
		} catch (Exception e) {
			serviceReturns = Tools.getExceptionServiceReturns(e);
		}
		return serviceReturns;
	}
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public ServiceReturns get(Class<T> clazz,Integer id){
		serviceReturns = new ServiceReturns();
		try {
			serviceReturns.success = true;
			serviceReturns.data.put("details", baseDao.getById(clazz,id));
		} catch (Exception e) {
			serviceReturns = Tools.getExceptionServiceReturns(e);
		}
		return serviceReturns;
	}
	
}
