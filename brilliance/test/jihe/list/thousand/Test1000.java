package jihe.list.thousand;

import java.util.ArrayList;
import java.util.List;

import thisInClass.Employee;

/**
* @ClassName: Test1000
* @Package thousand
* @Description: 循环集合，每5个作为一个整体
* ArrayList.subList()方法注意点：
* 1.对于原集合list的非结构性改变，都将反应到原集合上。
* 
* @author Russell Xun Jiang
* @date 2016年10月20日 上午10:40:19
* 
*/

public class Test1000 {

	public static void main(String[] args) {
		
//		method1();
		method2();
//		method3();
		
//		List<String> updateList = new ArrayList<String>();
//		updateList.add("1");
//		updateList.add("1");
//		updateList.add("1");
//		updateList.add("1");
//		updateList.add("1");
//		//付款单号超过1000时,分批更新付款单
//		int leftNum = updateList.size();
//		int span = 2;//每次的更新数量
//		while(leftNum>0){
//			span = (leftNum<span)?leftNum:span;
//			List<String> subList = new ArrayList<String>(updateList.subList(0, span));//更新的付款号
//			System.out.println("循环中...每次循环subList大小是："+subList.size());
//			//取总集合用完的子集
//			updateList = (List<String>) CollectionUtils.subtract(updateList, subList);
//			leftNum = updateList.size();
//		}
		
//		//下面代码死循环
//		List<String> updateList = new ArrayList<String>();
//		updateList.add("1");
//		updateList.add("1");
//		updateList.add("1");
//		updateList.add("1");
//		updateList.add("1");
//		//付款单号超过1000时,分批更新付款单
//		int leftNum = updateList.size();
//		int span = 2;//每次的更新数量
//		while(leftNum>0){
//			span = (leftNum<span)?leftNum:span;
////			List<String> subList = new ArrayList<String>(updateList.subList(0, span));//更新的付款号
//			List<String> subList = updateList.subList(0, span);
//			System.out.println("循环中...每次循环subList大小是："+subList.size());
//			subList.clear();
//			leftNum = updateList.size();
//		}

	}
	
	public static void method1(){
		List<String> updateList = new ArrayList<String>();
		updateList.add("1");
		updateList.add("2");
		updateList.add("3");
		updateList.add("4");
		updateList.add("5");
		
		int span = 2;//每次的更新数量
		List<String> subList = updateList.subList(0, span);
		System.out.println("subList="+subList.toString()+",updateList="+updateList.toString());
		//非结构性修改，子集和元集合都将影响彼此
		subList.add("a");
		//updateList.add("a");//给子集subList添加/删除元素时，不能给原集合添加/删除元素。java.util.ConcurrentModificationException
		//*********  http://danstart.iteye.com/blog/2325360
		//http://www.cnblogs.com/dolphin0520/p/3933551.html
		//http://www.cnblogs.com/gaojing/archive/2012/06/17/java-list-sublist-caution.html
		System.out.println("subList="+subList.toString()+",updateList="+updateList.toString());
		subList.remove("1");
		System.out.println("subList="+subList.toString()+",updateList="+updateList.toString());
		subList.clear();
		System.out.println("subList="+subList.toString()+",updateList="+updateList.toString());
	}
	
	//效果同method1
	public static void method2(){
		TestDto testDto = new TestDto();
		List<String> updateList = new ArrayList<String>();
		updateList.add("1");
		updateList.add("2");
		updateList.add("3");
		updateList.add("4");
		updateList.add("5");
		
		testDto.setEmpList(updateList);
		updateList = null;
		System.out.println("---"+testDto.getEmpList().size());
		
		//付款单号超过1000时,分批更新付款单
		int leftNum = testDto.getEmpList().size();
		int span = 2;//每次的更新数量
		List<String> subList;//更新的付款单号
		while(leftNum>0){
			span = (leftNum<span)?leftNum:span;
			subList = testDto.getEmpList().subList(0, span);
			System.out.println("subList="+subList.toString()+",testDto.getEmpList()="+testDto.getEmpList().toString());
			subList.clear();
			leftNum = testDto.getEmpList().size();
		}
	}
	
	//效果同method1
	public static void method3(){
		TestDto testDto = new TestDto();
		List<String> updateList = new ArrayList<String>();
		updateList.add("1");
		updateList.add("2");
		updateList.add("3");
		updateList.add("4");
		updateList.add("5");
		
		List<String> subList = updateList.subList(0, 2);
		subList.add("a");
		testDto.setEmpList(updateList);
		System.out.println("subList="+subList.toString()+",updateList="+updateList.toString());
		updateList.set(0, "z");
		System.out.println("subList="+subList.toString()+",updateList="+updateList.toString());
		updateList.add("b");
		System.out.println("subList="+subList.toString()+",updateList="+updateList.toString());
		
	}

}
