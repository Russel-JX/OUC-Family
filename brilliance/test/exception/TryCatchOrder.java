package exception;

/**
* @ClassName: TryCatchOrder
* @Package exception
* @Description: try catch执行顺序
* @author Russell Xun Jiang
* @date 2016年12月10日 下午4:47:26
*/
public class TryCatchOrder {

	public static void main(String[] args) {
//		//只要try,没有catch
//		int i = tryNoCatch();
//		System.out.println("i="+i);
		
		//try catch执行顺序
		int a = getInt2();
		System.out.println("a="+a);
		

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
			i = 10;
			i = i/0;
		}finally{
			
		}
		return i;
	}
	
	/** 
	* @Title: getInt1 
	* @Description: try中return 
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws 
	*/ 
	public static int getInt1(){
		int a = 0;
		try{
			a = 10;
			return a;
		}catch(Exception e){
			
		}finally{
			a = 11;
		}
		return a;
	}
	
	/** 
	* @Title: getInt2 
	* @Description: finally中return
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws 
	*/ 
	@SuppressWarnings("finally")
	public static int getInt2(){
		int a = 0;
		try{
			a = 10;
			
		}catch(Exception e){
			
		}finally{
			a = 11;
			return a;
		}
	}

}
