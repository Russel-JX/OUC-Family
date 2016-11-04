package jvm.yichang;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @ClassName: OutofMemoryErrorHeap
 * @Package jvm.yichang
 * @Description: 内存溢出——堆溢出 OutOfMemoryError: GC overhead limit exceeded
 * @author Russell Xun Jiang
 * @date 2016年11月4日 下午3:54:19
 * 
 * 测试步骤：
 * 设置：run as->run configurations->-Xmx30m -Xms30m -Xmn10m -Xss1m
 */
public class OutofMemoryErrorHeap {

	private static Log logger = LogFactory.getLog(OutofMemoryErrorHeap.class);
	private static int count = 0;

	public static void main(String[] args) {
		List<Object> list = new ArrayList<Object>();
		//反复创建对象，塞满JVM堆内存
		while(true){
			count++;
			list.add(new Object());
			logger.info("第"+count+"次创建对象");
		}
	}
	
	

}
