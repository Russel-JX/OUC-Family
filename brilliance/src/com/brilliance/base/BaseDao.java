/**
 * 
 */
package com.brilliance.base;

import java.util.List;

/**
 * @author mx801343
 * 
 */
public interface BaseDao<T> {
	/**
	 * 保存实体
	 * 
	 * @param entity
	 */
	public void save(T entity);
	
	/**
	 * 保存实体
	 * 
	 * @param entity
	 */
	public void saveAll(List<T> list);

	/**
	 * 删除实体
	 * 
	 * @param id
	 */

	public void delete(Class<T> clazz,Integer id);

	/**
	 * 更新实体
	 * 
	 * @param entity
	 */
	public void update(T entity);
	
	
	/**
	 * 更新实体
	 * 
	 * @param entity
	 */
	public void updateBulk(List<T> list);

	/**
	 * 按id查询
	 * 
	 * @param id
	 * @return
	 */

	public T getById(Class<T> clazz,Integer id);
	public T getById2(Class<T> clazz,Integer id);

	/**
	 * 按ids查询
	 * 
	 * @param ids
	 * @return
	 */

	public List<T> getByIds(Class<T> clazz,Integer[] ids);

	/**
	 * 查询所有实体
	 * 
	 * @return
	 */
	public List<T> getAll(Class<T> clazz);
	
	/**
	 * 删除实体
	 * 
	 * @param id
	 */

	public void delete(T t) throws Exception;
	
	/**
	 * 批量删除实体
	 * @param list
	 * @throws Exception
	 */
	public void deleteAll(List<T> list) throws Exception;
	
	
	/**
	 * 根据模板去查找
	 * 
	 * @param id
	 */

	public List<T> findByExample(T t) throws Exception;
	
	/**
	 * 根据模板去查找一条
	 * 
	 * @param id
	 */
	
	public T findOneByExample(T t) throws Exception;
	
	public List findBySql(String sql) throws Exception;
	public List findBySql(String sql,Object ...params) throws Exception;
	public List findByHql(String hql) throws Exception;
	public List findByHql(String hql,Object ...params) throws Exception;
	public int executeBySql(String sql) throws Exception;
	public int executeBySql(String sql,Object ...params) throws Exception;
	public T findOneBySql(String sql) throws Exception;
	public T findOneBySql(String sql,Object ...params) throws Exception;
	public T findOneByHql(String hql) throws Exception;
	public T findOneByHql(String hql,Object ...params) throws Exception;

}
