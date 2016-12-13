package corejava.Final;

import java.util.Calendar;

/**
* @ClassName: FinalChildClass
* @Package corejava.Final
* @Description: 实验子类继承父类，不能重写final方法。
* @author Russell Xun Jiang
* @date 2016年12月13日 下午7:48:29
* 
*/
//如果 FinalTest申明成final类，抛出检查异常：Cannot override the final method from FinalTest
public class FinalChildClass extends FinalTest{
	
	public FinalChildClass(String grade) {
		super(grade);
	}

	//子类重写非final定义方法
	public int getAge1(int birthday){
		return Calendar.getInstance().get(Calendar.YEAR)-birthday;
	}
	
//	//子类不能重写final方法，检查异常： Cannot override the final method from FinalTest
//	public final int getAge2(int birthday){//
//		return Calendar.getInstance().get(Calendar.YEAR)-birthday;
//	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
