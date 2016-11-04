package jvm.yichang;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * @ClassName: OutofMemoryErrorPermGen
 * @Package jvm.yichang
 * @Description: 内存溢出——方法区/永久区异常  Caused by: java.lang.OutOfMemoryError: PermGen space
 * @author Russell Xun Jiang
 * @date 2016年11月4日 下午3:54:19
 */
public class OutofMemoryErrorPermGen {

	private static Log logger = LogFactory.getLog(OutofMemoryErrorPermGen.class);
	private static int count = 0;

	public static void main(String[] args) {
		int count = 0;
		//对于这种情况的测试，基本的思路是运行时产生大量的类去填满方法区，直到溢出。
		//这里需要借助CGLib直接操作字节码运行时，生成了大量的动态类。
		while (true) {
			Enhancer enhancer = new Enhancer();
			enhancer.setSuperclass(OutofMemoryErrorPermGen.class);
			enhancer.setUseCache(false);
			enhancer.setCallback(new MethodInterceptor() {
				public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
					return proxy.invoke(obj, args);
				}
			});
			enhancer.create();
			System.out.println(++count);
		}
	}

}
