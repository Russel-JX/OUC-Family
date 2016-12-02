package exception;

import java.io.FileNotFoundException;
import java.io.PrintStream;



/**
* @ClassName: ExceptionTest
* @Package exception
* @Description: 异常栈的顺序
* @author Russell Xun Jiang
* @date 2016年4月8日 上午9:09:43
* 
*/

public class ExceptionTest1 {
	
	public static void f(){
		throw new RuntimeException("f发生异常了");
	}
	public static void g(){
		f();
	}
	public static void h(){
		g();
	}

	public static void main(String[] args) throws FileNotFoundException {
		try{
			h();
		}catch(Exception e){
			//输出异常信息
			e.printStackTrace();//按顺序从里到外发生异常:f,g,h,main.依次从栈顶到栈底输出异常栈路径。
			//将异常信息输出到文件中
			e.printStackTrace(new PrintStream("d:\\test.txt"));
			throw (RuntimeException)e.fillInStackTrace();
		}
		
	}

}
