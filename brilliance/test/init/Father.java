package init;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * java类初始化顺序——无继承关系
* @ClassName: Father
* @Package init
* @Description: 
* 
* 初始化顺序：静态变量赋值>静态代码块>自由代码块/构造代码块>构造方法
* 
* @author Russell Xun Jiang
* @date 2016年10月29日 下午7:56:26
* 
*/
public class Father {
	static Log logger = LogFactory.getLog(Father.class);

	//静态变量
	private static String staticField = "父类静态变量/属性";
	//非静态变量
	private String field = "父类变量/属性";
	
	//静态代码块
	static{
		logger.info("父类静态代码块中,使用静态变量"+staticField);//这边不能引用非静态变量field，因为非静态变量后与静态代码块
		logger.info("父类静态代码块");
	}
	
	//自由代码快/构造代码块
	{
		logger.info("父类自由代码块,使用静态变量"+staticField+",普通变量field"+field);
	}
	
	//构造方法
	public Father(){
		logger.info("父类构造方法");
	}
	
	//程序主入口
	public static void main(String[] args){
		new Father();
		
		
		
		/**
		 * 输出
		 * [init.Father] 父类静态代码块中,使用静态变量父类静态变量/属性
		 * [init.Father] 父类静态代码块
		   [init.Father] 父类自由代码块,使用静态变量父类静态变量/属性,普通变量field父类变量/属性
		   [init.Father] 父类构造方法
		 */
	}

}
