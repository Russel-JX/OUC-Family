package exception;

import java.io.FileNotFoundException;
import java.io.IOException;


/**
* @ClassName: ExceptionTest2
* @Package exception
* @Description: 异常栈的顺序——捕获一个异常后，再抛出另一个异常
* 异常的冒泡上传机制：当一个异常对象产生了以后，其会按照调用层次（一般是方法的调用层次）进行冒泡，直到被try-catch处理，或上报至main()方法，有编译器进行提示。
* 调用者的catch异常，必须是被调用者抛出或发生异常的父类或同类，才能捕捉到被调用者发生的异常，并对其处理；否则不能捕捉到被调用者发生的异常。
* @author Russell Xun Jiang
* @date 2016年4月8日 上午9:11:38
* 
*/
public class ExceptionTest2 {
	
	public static void f() throws MyException{
		System.out.println("-------------f---------------");
		//f抛出或发生异常，在调用者g中的catch(MyException e)中，必须是发生异常的父类或同类，才能捕捉到此异常。
		//即如果此处抛出的是Exception父类异常，则g中的子类MyException是捕捉不到，并处理的。
		throw new MyException("f发生异常了");
	}
	public static void g() throws Exception{
		try{
			f();
		}catch(MyException e){
			System.out.println("-------------g---------------"+e.getMessage());
			e.printStackTrace();
			throw new Exception("g异常",e);
		}
	}
	public static void h() throws Exception    {
		try{
			g();
		}catch(Exception e){
			System.out.println("-------------h---------------"+e.getMessage());
			e.printStackTrace();
			throw new Exception("h异常",e);
		}
	}

	public static void main(String[] args) throws Exception {
		try{
			h();
		}catch(Exception e){
			System.out.println("-------------main---------------"+e.getMessage());
			throw new Exception("main异常");
		}
	}
}
