package exception;

public class MyException extends Exception{

	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	
	private static final long serialVersionUID = -1480438414388599006L;
	public MyException(){
		super();
	}
	public MyException(String msg){
		super(msg);
	}
	public MyException(Throwable cause){
		super(cause);
	}
	public MyException(String msg,Throwable cause){
		super(msg,cause);
	}
}
