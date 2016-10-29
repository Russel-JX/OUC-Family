package type.transfer;

/**
* @ClassName: Test
* @Package type.transfer
* @Description: 类型转换
* @author Russell Xun Jiang
* @date 2016年10月26日 下午3:04:48
* 
*/

public class Test {

	public static void main(String[] args) {
//		m1();
//		m2();
		m3();
		
	}
	
	/** 
	* @Title: m1 
	* @Description: 向上转型。无需任何额外操作
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/ 
	public static void m1(){
		String a = "abc";
		Object b = a;//向上转型
		System.out.println(b);
	} 
	
	/** 
	* @Title: m2
	* @Description: 向下转型。值如果超过存储空间大小可能转型失败
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/ 
	public static void m2(){
		byte a = 1;
		a = (byte)(a +1);//a是byte类型，1是int类型，向下转型必须强制转型，否则有检查异常。
		System.out.println(a);
	} 
	
	/** 
	* @Title: m3
	* @Description: 自动类型提升
	* 数字类型的变量运算，
	* 	如果存在double，float或long类型，则计算结果是double，float或long类型，取类型较大的一个；
	* 	其余的运算结果都是int类型。
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/ 
	public static void m3(){
		byte a0 = 1;
		byte a1 = 2;
		double a2 = 3.0;
		float a3 = 4f;
		long  a4 = 5L;
		
		//a1 = ao+a1;//不能这么写，检查异常。byte+byte=int
		int b1 = a0+a1;//byte+int = int
		int b2 = a0+1;//byte+int = int
		double c = a2+1;//double+x(x<double) = double
		float d = a3+1;//float+x(x<float) = float
		long e = a4+1;//long+x(x<long) = long
		System.out.println(b1);
		System.out.println(b2);
		System.out.println(c);
		System.out.println(d);
		System.out.println(e);
	} 
	
	

}
