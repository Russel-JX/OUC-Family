package jihe.list.modify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import thisInClass.Employee;

/**
* @ClassName: ArrayListTest
* @Package jihe.list.modify
* @Description: 修改集合中元素，有的改变原集合，有的不变。
* @author Russell Xun Jiang
* @date 2016年10月26日 下午5:05:20
* 
*/

public class ArrayListTest {

	public static void main(String[] args) {
		/**测试一
		 * 子类的数组存放的元素，多态之后（向上转型），内存中存放的还是子类类型的数据（可通过类对象得知），因此不能改变数组中元素类型（即不能向下转型）。
		 * 抛出异常java.lang.ArrayStoreException:，此处数组存放的数据类型不正确。
		 */
		Double[] arr = {1.0,2.0,3.0};
//		Object[] arr2 = arr;//多态。父类的引用指向子类对象。
//		arr2[0] = new Object();//抛出异常java.lang.ArrayStoreException:
		
		/**
		 * 测试二。原因同测试一
		 */
		List<String> jihe = Arrays.asList("abc","def");
		jihe.add("ghi");
//		System.out.println("jihe的类对象是："+jihe.getClass()+"jihe="+jihe);
		Object[] objArr = jihe.toArray();//toArray()方法把集合转数组，objArr数组的元素类型还是String
//		System.out.println("objArr的类对象是："+objArr.getClass());//objArr数组的元素类型还是String
//		objArr[0] = new Object();//抛出异常java.lang.ArrayStoreException:
		
		/**
		 * 测试三。
		 */
		List<String> listx = new ArrayList<String>();
		listx.add("a");
		listx.add("b");
		listx.add("c");
		System.out.println("listx的类对象是："+listx.getClass());
		Object[] arrX = listx.toArray();
		arrX[0] = 1;
		arrX[1] = "c";
		arrX[2] = 3.0;
		System.out.println("arrX的类对象是："+arrX.getClass());
		
		Employee[] empArr = {new Employee("a",1,1.0),new Employee("b",2,2.0)};
		System.out.println("arr的类对象是："+arr.getClass());
		System.out.println("empArr的类对象是："+empArr.getClass());
		
		List<Integer> list = new ArrayList<Integer>();
		list.add(2);
		list.add(3);
		list.add(4);
		System.out.println("原list集合是："+list+"集合的类对象是："+list.getClass());
		for(int item:list){
			item = 1;
		}
		System.out.println("后list集合是："+list+"\n");//元素值都是1
		
		List<Employee> list2 = new ArrayList<Employee>();
		list2.add(new Employee("Tom",23,168.0));
		list2.add(new Employee("Jack",23,168.0));
		list2.add(new Employee("Rose",23,168.0));
		System.out.println("原list2集合是："+list2);
		for(Employee item:list2){
			item.setName("A");
		}
		System.out.println("后list2集合是："+list2+"\n");//姓名都是A

	}

}
