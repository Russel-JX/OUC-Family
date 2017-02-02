package corejava.generics.framework;

/**
* @ClassName: GenericsInterface
* @Package corejava.generics
* @Description: 活动操作接口
* 
* 定义可操作活动的所有方法。
* 
* 
* @author Russell Xun Jiang
* @date 2016年12月27日 下午12:13:01
* 
* @param <G>
* 
* 
*/
public interface Operation<A> {
	
	/** 
	* @Title: getActivity 
	* @Description: 根据活动好号获得活动对象
	*  
	* @param @param aid
	* @param @return    设定文件 
	* @return A    返回类型 
	* @throws 
	*/ 
	public A getActivity(String aid);
	
	/** 
	* @Title: addActivity 
	* @Description: 新增活动
	* 保证新增的活动和已有的活动不能冲突。
	* 以下情况是冲突的：
	* 	a.活动地点相同，并且活动的活动开始时间和 其他活动的活动开始时间相差在2小时之内的。
	* 	b.活动参与者重复参与活动的。即在活动开始时间1小时内，有相同参与者的。
	* @param @param a	活动对象
	* @param @return    设定文件 
	* @return boolean    返回类型  新增活动成功返回true;新增活动失败返回false. 
	* @throws 
	*/ 
	public boolean addActivity(A a);
	
	/** 
	* @Title: exists 
	* @Description: 判断活动是否已经存在
	* 以下情况为活动已存在的
	* 	a.活动地点相同，并且活动的活动开始时间和 其他活动的活动开始时间相差在2小时之内的。
	* 	b.活动参与者重复参与活动的。即在活动开始时间1小时内，有相同参与者的。
	* @param @param a
	* @param @return    设定文件 
	* @return boolean    返回类型  已存在相似活动，返回true;已存在相似活动，返回false. 
	* @throws 
	*/ 
	public boolean exists(A a);
	
	
	/** 
	* @Title: size 
	* @Description: 获得总活动数量
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws 
	*/ 
	public int size();
	
	/** 
	* @Title: clear 
	* @Description: 清空所有活动 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/ 
	public void clear();
	
	
	
	
	
	
	

}
