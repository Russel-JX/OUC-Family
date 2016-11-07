package scope.pkg;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
* @ClassName: Father1
* @Package scope.pkg
* @Description: 变量作用域研究——本类访问
* @author Russell Xun Jiang
* @date 2016年11月7日 下午6:13:08
* * public：所有都能访问 
* protected:同一包下或者子类可访问
* private：私有的，本类对象可访问
* 不写：同一包下可访问
*/

public class Father1 {
	
	private static Log logger = LogFactory.getLog(Father1.class);
	
	public static String name = "Tom";
	protected static String nickname = "HooHoo";
	private static String address = "NewYork";
	static String school = "Middle";
	
	
	public static void main(String[] args){
		
		logger.info(name);//本类访问公有属性
		logger.info(nickname);//本类访问保护属性
		logger.info(address);//本类访问私有属性
		logger.info(school);//本类访问默认属性
		
		//本类中4个作用域的方法，都能访问
		f_m1();
		f_m2();
		f_m3();
		f_m4();
	}
	
	
	public static void f_m1(){
		logger.info("公开方法...");
	}
	protected static void f_m2(){
		logger.info("公开方法...");
	}
	private static void f_m3(){
		logger.info("公开方法...");
	}
	static void f_m4(){
		logger.info("公开方法...");
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	
}
