/**
 * 
 */
package com.brilliance.base.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.brilliance.base.BaseDao;
import com.brilliance.base.BriException;

/**
 * @author mx801343
 * 
 */
@Repository("baseDao")
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

//	private Class<T> clazz;
	protected Logger logger = null;
	
	@Autowired
	private HibernateTemplate readHibernateTemplate;
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	

	public BaseDaoImpl() {
		// log日志的支持 
		logger = Logger.getLogger(this.getClass());
//		// 获取当前new的对象的泛型父类 类型
//		ParameterizedType pt = (ParameterizedType) this.getClass()
//				.getGenericSuperclass();
//		this.clazz = (Class<T>) pt.getActualTypeArguments()[0];
	}

	public void save(T entity) {
		hibernateTemplate.save(entity);
//		this.logger.info("保存"+this.clazz.getSimpleName());
	}
	
	public void saveAll(List<T> list) {
		hibernateTemplate.saveOrUpdateAll(list);
//		this.logger.info("保存"+this.clazz.getSimpleName());
	}

	public void delete(Class<T> clazz,Integer id) {
		T entity = readHibernateTemplate.load(clazz, id);
		if(entity!=null){
			hibernateTemplate.delete(entity);
		}
//		logger.info("删除"+this.clazz.getSimpleName());
	}

	public void updateBulk(List<T> list) {
		hibernateTemplate.update(list);
//		logger.info("批量更新"+this.clazz.getSimpleName());
	}
	
	public void update(T entity) {
		hibernateTemplate.update(entity);
//		logger.info("更新"+this.clazz.getSimpleName());
	}

	public T getById(Class<T> clazz,Integer id) {
//		logger.info("根据id查询 "+this.clazz.getSimpleName());
		return readHibernateTemplate.load(clazz, id);
	}
	
	public T getById2(Class<T> clazz,Integer id) {
		return readHibernateTemplate.get(clazz, id);
	}

	@SuppressWarnings("unchecked")
	public List<T> getByIds(Class<T> clazz,Integer[] ids) {
//		logger.info("根据ids查询 "+this.clazz.getSimpleName());
		return readHibernateTemplate.getSessionFactory().getCurrentSession().createQuery(//
				"FROM " + clazz.getSimpleName() + " WHERE id IN (:ids)")//
				.setParameterList("ids", ids).list();
	}

	@SuppressWarnings("unchecked")
	public List<T> getAll(Class<T> clazz) {
//		logger.info("查询所有  "+this.clazz.getSimpleName());
		return readHibernateTemplate.find("FROM "+clazz.getSimpleName());
	}

	public void delete(T t) throws Exception{
		hibernateTemplate.delete(t);
	}
	
	
	public void deleteAll(List<T> list) throws Exception{
		hibernateTemplate.deleteAll(list);
	}

	
	@SuppressWarnings("unchecked")
	public List<T> findByExample(T t) throws BriException {
		return readHibernateTemplate.findByExample(t);
	}

	public T findOneByExample(T t) throws BriException {
		List<T> list = findByExample(t);
		if(list==null ||list.size()==0){
			return null;
		}
		return list.get(0);
	}
	
	public List findBySql(String sql) throws BriException {
		return readHibernateTemplate.getSessionFactory().getCurrentSession().createSQLQuery(sql).list();
	}

	public List findBySql(String sql, Object... params) throws BriException {
		SQLQuery query = readHibernateTemplate.getSessionFactory().getCurrentSession().createSQLQuery(sql);
		int i = 0;
		for(Object param : params){
			query.setParameter(i, param);
			i++;
		}
		return query.list();
	}

	public List findByHql(String hql) throws BriException {
		return readHibernateTemplate.find(hql);
	}
	
	public List findByHql(String hql, Object... params) throws BriException {
		return readHibernateTemplate.find(hql,params);
	}

	public int executeBySql(String sql) throws Exception {
		return hibernateTemplate.getSessionFactory().getCurrentSession().createSQLQuery(sql).executeUpdate();
	}

	public int executeBySql(String sql, Object... params) throws BriException {
		SQLQuery query = hibernateTemplate.getSessionFactory().getCurrentSession().createSQLQuery(sql);
		int i = 0;
		for(Object param : params){
			query.setParameter(i, param);
			i++;
		}
		return query.executeUpdate();
	}

	public T findOneBySql(String sql) throws BriException {
		List<T> list = findBySql(sql);
		if(list==null || list.size()==0){
			return null;
		}
		return list.get(0);
	}

	public T findOneBySql(String sql, Object... params) throws BriException {
		List<T> list = findBySql(sql,params);
		if(list==null || list.size()==0){
			return null;
		}
		return list.get(0);
	}

	public T findOneByHql(String hql) throws BriException {
		List<T> list = findByHql(hql);
		if(list==null || list.size()==0){
			return null;
		}
		return list.get(0);
	}

	public T findOneByHql(String hql, Object... params) throws BriException {
		List<T> list = findByHql(hql,params);
		if(list==null || list.size()==0){
			return null;
		}
		return list.get(0);
	}
	
	
	public Session getReadSession(){
		return  readHibernateTemplate.getSessionFactory().getCurrentSession();
	}
	
	public Session getWriteSession(){
		return  hibernateTemplate.getSessionFactory().getCurrentSession();
	}
}
