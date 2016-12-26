package jihe.map;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MapTest {

	private static Log logger = LogFactory.getLog(MapTest.class);
	public static void main(String[] args) {
//		//put方法测试 
//		putTest();
		
//		//单线程resize扩容
//		singleThreadResize();
		
		//多线程resize扩容
		multiThreadResize();
	}
	
	/** 
	* @Title: putTest 
	* @Description: put方法测试 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/ 
	public static void putTest(){
		Map<String,String> map = new HashMap<String,String>();
		//理论见HashMap源码
		logger.info("第一次put新key，返回的值是null："+map.put("a", "123"));//null
		logger.info("put放入重复的key之后，返回的值原来的value："+map.put("a", "456"));//123
		logger.info("put放入重复的key之后，get的值是覆盖后的value："+map.get("a"));//456
		map.toString();
	}
	
	
	/** 
	* @Title: singleThreadResize 
	* @Description: 单线程resize扩容 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	* 断点查看table数组变量
	*/ 
	public static void singleThreadResize(){
		//设置HashMap的数组初始容量为2，负载因子为0.75.所以放入第二个对象后，就就会扩容。
		Map<Integer,String> map = new HashMap<Integer,String>(2,0.75f);
		map.put(3, "a"); 
		map.put(5, "b");
		map.put(7, "c");
		logger.info(map);
	}
	
	//设置HashMap的数组初始容量为2，负载因子为0.75.所以放入第二个对象后，就就会扩容。
	private	static Map<Integer,String> map = new HashMap<Integer,String>(2,0.75f);
	/** 
	* @Title: multiThreadResize 
	* @Description: 多线程resize扩容。（未完成）
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	* http://www.importnew.com/20386.html	线程安全性
	* 测试步骤：
	* 	1.断点在HashMap源码的transfer方法的Entry[] src = table;处执行完此句
	* 	2.第一次put5数组只有一个元素<阈值1.5不扩容
	* 	3.先执行t2,数组元素个数为2>1.5，数组扩容成4；数组元素为[null,5,null,{7->next为3}]
	* 	4.再执行t1，最终map中没有元素。
	*/ 
	public static void multiThreadResize(){
		map.put(5, "a");
		new Thread("thread1"){
			public void run(){
				map.put(7, "b");
				logger.info("thread1.map="+map);
				map.get(11);
			}
		}.start();
		new Thread("thread2"){
			public void run(){
				map.put(3, "b");
				logger.info("thread2.map="+map);
			}
		}.start();
		
		logger.info(map);
	}

}
