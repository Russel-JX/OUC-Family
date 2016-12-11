package exception;

/**
* @ClassName: TryCatchOrder
* @Package exception
* @Description: try catch执行顺序
* @author Russell Xun Jiang
* @date 2016年12月10日 下午4:47:26
* 0.有try，没有catch时，发生异常也会被捕捉，程序将默认抛出真正发生的异常。
* 1.try或者catch中直接返回时，先返回给调用方，再执行finally中语句，然后再执行调用方的其他语句。所以finally中逻辑不影响调用方获取的结果，finally之后的代码不执行。
* 2.finally中返回时（程序正常无异常抛出），按正常顺序执行。
*/
public class TryCatchOrder {

	public static void main(String[] args) {
		//只要try,没有catch
		int i = tryNoCatch();
		System.out.println("i="+i);
		
		/**
		 * try-catch-finally执行顺序
		 */
		//try中返回
		int a1 = tryReturn();//(0.1)
		System.out.println("a1="+a1);//(0.2)
		//catch中返回
		int a2 = catchReturn();//(0.1)
		System.out.println("a2="+a2);//(0.2)
		//finally中返回
		int a3 = finallyReturn();//(0.1)
		System.out.println("a3="+a3);//(0.2)
		

	}
	
	/** 
	* @Title: getInt 
	* @Description: 只写try，没有catch也可以。try中的异常会被真正的异常类捕捉到，并且抛出。
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws 
	*/ 
	public static int tryNoCatch(){
		int i=0;
		try{
			i = 9;
			i = i/0;
		}finally{
			
		}
		return i;
	}
	
	/** 
	* @Title: tryReturn 
	* @Description: try中return 
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws 
	* 在try中返回时，先结果返回给调用方，再执行finally中的代码，然后再执行调用方的其他语句。所以finally中的逻辑不会影响调用方获取的结果。
	* 执行顺序：
	* 	(0.1)->(1)->(2)返回给调用方->(0.1)，被调用方->(4)->(0.2)
	*/ 
	public static int tryReturn(){
		System.out.println("tryReturn begin............");
		int a = 0;
		try{
			a = 9;//(1)
			return a;//(2)
		}catch(Exception e){
			a = 10;//(3)
		}finally{
			a = 11;//(4)
		}
		System.out.println("tryReturn begin............");
		return a;//(5)
	}
	
	/** 
	* @Title: catchReturn 
	* @Description: catch中return 
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws 
	* 在catch中返回时，和try中返回一样，先将catch结果返回给调用方，再执行finally中的代码，然后再执行调用方的其他语句。所以finally中的逻辑不会影响调用方获取的结果。
	* 执行顺序：
	* 	(0.1)->(1)->(2)->(3)->(4)返回给调用方->(0.1)，被调用方->(5)->(0.2)
	*/ 
	public static int catchReturn(){
		System.out.println("catchReturn begin............");
		int a = 0;
		try{
			a = 9;//(1)
			int b = 5/0;//(2)
		}catch(Exception e){
			a = 10;//(3)
			return a;//(4)
		}finally{
			a = 11;//(5)
		}
		a = 12;//(6)
		System.out.println("catchReturn end............");
		return a;
	}
	
	/** 
	* @Title: finallyReturn 
	* @Description: finally中return
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws 
	* finally中返回，则严格按照顺序依次执行。
	* 执行顺序：
	* 	(0.1)->(1)->(3)->(4)返回给调用方->(0.1)->(0.2)
	*/ 
	@SuppressWarnings("finally")
	public static int finallyReturn(){
		System.out.println("finallyReturn begin............");
		int a = 0;
		try{
			a = 9;//(1)
		}catch(Exception e){
			a = 10;//(2)
		}finally{
			a = 11;//(3)
			System.out.println("finallyReturn end............");
			return a;//(4)
		}
	}

}
