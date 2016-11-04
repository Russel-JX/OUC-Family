package jvm.yichang;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
* @ClassName: StackOverflowTest
* @Package jvm.yichang
* @Description: 栈溢出异常研究
* 见：有道->->云协作->面试学习->7.4栈溢出异常
* @author Russell Xun Jiang
* @date 2016年11月4日 上午11:01:21
*/

public class StackOverflowTest {

	private static Log logger = LogFactory.getLog(StackOverflowTest.class);
	private static int count = 0;
	public static void main(String[] args) {
		method1();
	}
	
	public static void method1(){
		long totalMemory = Runtime.getRuntime().totalMemory();//JVM总共内存
		long maxMemory = Runtime.getRuntime().maxMemory();//JVM最大内存
		long freeMemory = Runtime.getRuntime().freeMemory();//JVM可用内存
		int avaliableProcessors = Runtime.getRuntime().availableProcessors();//JVM可用进程数
		count++;
		logger.info("第"+count+"次调用method1方法。JVM总共内存是："+totalMemory+"，"
				+ "最大内存是："+freeMemory+"，可用内存是:"+freeMemory+"，可用进程数量："+avaliableProcessors);
		method1();
		//在调用第13571次调用method1方法之后，跑出栈溢出异常。
	}

}
