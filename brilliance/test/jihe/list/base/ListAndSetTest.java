package jihe.list.base;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import thisInClass.Employee;

/**
* @ClassName: ListAndSetTest
* @Package jihe.list.base
* @Description: List和Set集合的区别
* @author Russell Xun Jiang
* @date 2016年11月7日 上午11:21:07
* 
* List集合有序，可重复
* Set集合无序，不可重复（重复的会合并成一个）
* 
*/

public class ListAndSetTest {
	
	private static Log logger = LogFactory.getLog(ListAndSetTest.class);

	public static void main(String[] args) {
		m1();
//		m2();
	}
	
	/** 
	* @Title: m1 
	* @Description: ist有序可重复，Set无序不可重复，Map集合的key不可重复。
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	* Set集合的元素重复取一个；Map集合的key重复，后者的value覆盖前者的value。
	*/ 
	
	public static void m1(){
		List<String> list = new ArrayList<String>();
		Set<String> set = new HashSet<String>();
		Map<String,String> map = new HashMap<String,String>();
		list.add("a");
		list.add("a");
		list.add("b");
		set.add("1");
		set.add("1");
		map.put("jiangsu", "good");
		map.put("jiangsu", "yes");
		
		//输出list集合元素是：[a, a, b]，集合大小是：3
		logger.info("list集合元素是："+list+"，集合大小是："+list.size());
		//set集合元素是：[1]，集合大小是：1
		logger.info("set集合元素是："+set+"，集合大小是："+set.size());
		//map集合元素是：{jiangsu=yes}，集合大小是：1
		logger.info("map集合元素是："+map+"，集合大小是："+map.size());
	}
	
	/** 
	* @Title: m2 
	* @Description:  自定义的类，集合去重的实现。如Set的元素和Map的key。
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	* 集合去重的原则：
		如Set集合的元素和Map集合的key，在java中是不能有重复的。这些在基本J2EE中已经实现。
		但自定义的类，在集合中是可能有重复的，因为集合中判断去重是的原则是：首先判断hashCode值是否相等，不相等则元素一定不等，则不是同一元素；hashCode相等，则再比较对象的equals方法是否相等，相等则是同一元素，不等则不是同一元素。
		所以，对于自定义对象，要保证Set元素不重复，Map的key不重复，必须同时重写元素的hashCode和equals方法，缺一不可。
		总结，hashCode是判断元素应该在哪个小范围的子集中；equals用来在子集和中判断内容是否相等。

	   集合去重的实现（或者说重写equals和hashcode的方法）：
		equals重写：只要比较自定义类的各个属性是否相同即可，根据具体的业务来。
		hashCode重写：一般（简单点的），对象的属性是引用类型的，去其引用类型的hashCode值；属性是数字类型的，取常量*属性值；最后相加，作为对象的hashCode。这种方式比较简单，可能出现这种情况：不同的对象，hashCode值一样，不必担心，因为hashCode相同之后，集合还会继续比较equals方法是否相同。
		所以通过同时重写equals和简单的hashCode方法，一定能给集合去重。
		总结:要想成功的去重,只要同时保证2点
		1.equals方法：内容相等返回true
		2.hashCode方法：内容相等的返回值相同。
	* 
	*/ 
	public static void m2(){
		//Set集合元素去重
		Set<Employee> set = new HashSet<Employee>();
		Map<Employee,String> map = new HashMap<Employee,String>();
		Employee e1 = new Employee("Tom",23,168);
		Employee e2 = new Employee("Tom",23,168);
		map.put(e1, "abc");
		map.put(e2, "def");//因为重写了对象的equals和hashCode方法，第二个map值覆盖了第一个map值。
		set.add(e1);
		set.add(e2);//因为重写了对象的equals和hashCode方法，Set集合只有一个相同的对象。
		logger.info("set集合元素是："+set+"，集合大小是："+set.size());
		//输出，自定义对象是否相等false.set集合元素是：[员工的姓名是：Tom,年龄是：23,身高是：168.0, 员工的姓名是：Tom,年龄是：23,身高是：168.0]，集合大小是：1
		logger.info("set集合自定义对象是否相等"+e1.equals(e2)+"e1的hashCode值是："+e1.hashCode()+"e2的hashCode值是："+e2.hashCode());
		//输出，Map集合元素是：{员工的姓名是：Tom,年龄是：23,身高是：168.0=def}，集合大小是：1
		logger.info("Map集合元素是："+map+"，集合大小是："+map.size());
		

	}

}
