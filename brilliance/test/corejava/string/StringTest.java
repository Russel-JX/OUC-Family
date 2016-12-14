package corejava.string;

import java.util.Random;

/**
* @ClassName: StringTest
* @Package corejava.string
* @Description: 字符串串池/常量池		Integer常量池
* @author Russell Xun Jiang
* @date 2016年12月14日 下午3:27:39
* 
*/
public class StringTest {

	public static void main(String[] args) {
//		//String字符串池——字面值创建和new方式创建
//		stringPoolLiteralandNew();
		
//		// 字符串拼接
//		stringPoolConcat();
		
//		//intern方法
//		stringIntern();
		
		//测试intern节省内存
		stringInternUse();
	}
	
	/** 
	* @Title: stringPool 
	* @Description: String字符串池——字面值创建和new方式创建
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	* 字面值和new方式创建的字符串
	* 	1.字面值创建：
	* 		字符串一定在常量池中。首先去常量池中找，如果有则直接返回常量池中字符串；没有在在常量池中创建此字符串并返回。
	* 	2.new方式创建：
	* 		首先查找常量池中是否有次字符串值，有则在复制一份到堆中，返回堆中的引用；没有则在常量池中创建，并复制一份到堆中，返回堆中的引用。
	* 所以，new方式创建的一定是新的字符串对象，字面值方式一定是常量池的对象。2者==肯定不等。
	* 常量池中的字符串内容相同的最多只有一个。堆中可以有多个。
	*/ 
	public static void stringPoolLiteralandNew(){
		String s1 = "abc";
		String s2 = "abc";
		String s5 = new String("abc");
		String s6 = new String("abc");

		System.out.println("s1==s2?"+(s1==s2));//true
		System.out.println("s1==s5?"+(s1==s5));//false
		System.out.println("s5==s6?"+(s5==s6));//false
	}
	
	/** 
	* @Title: itegerPool 
	* @Description: 整型包装类常量池（未完成） 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/ 
	public static void itegerPool(){
		Integer i1 = 40;
		Integer i2 = 40;
		Integer i3 = 0;
		Integer i4 = new Integer(40);
		Integer i5 = new Integer(40);
		Integer i6 = new Integer(0);
		System.out.println("i1==i2?"+(i1==i2));//true
		System.out.println("i1==i4?"+(i1==i4));//false
		System.out.println("i4==i5?"+(i4==i5));//false
		System.out.println("i1=(i2+i3)?"+(i1==(i2+i3)));//true   
		System.out.println("i4==(i5+i6)?"+(i4==(i5+i6)));//true
	}
	
	/** 
	* @Title: stringPoolConcat 
	* @Description: 字符串拼接
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	* 1.纯字面值的字符串拼接，因为在编译期就已经知道字面值"abc"+"def"的值是"abcdef"，所以编译器优化后的结果直接就是"abcdef"（用jd-gui反编译class文件可见）
	* 所以这个结果就会在运行时放在常量池中。
	* 2.包含字符串引用（非字面值）的拼接，因为在编译期不知道引用的值在内存中是多少，即引用的值会变不可控，编译期无法优化，只能在运行时动态的给拼接结果新的内存空间。
	* 	反编译之后，会调用StringBuilder的append方法，将说有字符串拼接，并调用StringBuilder.toString()返回给最终结果。而这个toString方法内部会new方式创建新的字符串，
	* 	显然是新的地址了。
	* 	 public String toString() {
	        // Create a copy, don't share the array
			return new String(value, 0, count);
	    }
	* 判定条件：
	* 	参与拼接的字面值或引用或方法返回值，如果都能在编译期确定值是多少，则最终变量的结果就是拼接后的整个字面值；否则会在运行时确定拼接结果，并返回新的内存地址。
	* 	注：字面值、final变量能在编译期确定值是多少。引用和方法不能确定。
	*/ 
	public static void stringPoolConcat(){
		String s1 = "abc";
		String s2 = "def";
		String s3 = s1+"def";
		String s4 = "abc"+"def";//编译后，s4就是"abcdef";
		String s5 = "abcdef";
		String s6 = new String("abc");
		String s7 = new String("def");
		String s8 = new String("abc")+new String("def");
		String s9 = new String("abcdef");

		System.out.println("s4==s5?"+(s4==s5));//true。因为在编译期就已经知道字面值"abc"+"def"的值是"abcdef"，所以编译器优化后的结果直接就是"abcdef"
		System.out.println("s3==s5?"+(s3==s5));//false。编译期不知道引用的值在内存中是多少，编译期无法优化，只能在运行时动态的给拼接结果新的内存空间。
		System.out.println("s5=(s1+s2)?"+(s5==(s1+s2)));//false.原因同上
		System.out.println("s8==s9?"+(s8==s9));//false。原因同上
		
		
		String s10 = "pq";
		final String s11 = "q";//常量。s11申明成final，s11在编译期可以确定（因为引用永远不变（不能给s11赋值），s11的值永远是q）
		String s12 = "p"+s11;//用字面值和引用拼接.效果同纯字符串拼接。
		System.out.println("s10==s12?"+(s10==s12));//true。
		
		String a = "ab"; 
		final String bb = getBB();//编译期不能确定getBB方法返回什么，必须是运行时确定。
		String b = "a" + bb;//b的地址是动态的
		System.out.println((a == b)); //false 
	}
	private static String getBB() { 
		return "b"; 
	}
	
	/** 
	* @Title: stringIntern 
	* @Description: String的intern方法 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	* String.intern()方法：拿堆中的字符串和比较常量池中比较是否有一样的内容的。有则返回常量池中字符串；没有则将堆中的字符串赋值到常量池中，并返回常量池字符串的引用。
总之，调用intern方法后，返回的都是常量池中的唯一字符串。所以任何2个内容相等的字符串，不管什么方式创建的（字面值方式或者new方法），intern返回值都相同，都是常量池中的字符串。
优点：节省内存。让字符串引用不再指向堆中的对象，GC的时候堆中的对象失去了引用，会被早点GC掉。
	*/ 
	public static void stringIntern(){
		String s1 = new String("hello");
		s1=s1.intern();//s1指向常量池中的字符串。堆中的hello对象会在GC计数器达到指定数额时GC掉。节省内存空间。
		String s2 = "hello";//s2的hello正好在常量池中
		
		String s3 = new String("1")+new String("1");
		s3=s3.intern();//同理。s3的内容是“11”。此时s3指向常量池的11.
		String s4 = "11";
		
		System.out.println("s1==s2?"+(s1==s2));//true.s1和s2都是常量池中的字符串
		System.out.println("s3==s4?"+(s3==s4));//true.s3和s4都是常量池中的字符串
	}
	
	/** 
	* @Title: stringInternDiffJDK 
	* @Description: 不同JDK对intern的影响 （未完成）
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	* http://blog.csdn.net/baidu_31657889/article/details/52315902
	* JDK1.6及以下
	* JDK1.7及以上
	*/ 
	public static void stringInternDiffJDK(){
		String s1 = new String("hello");
		s1.intern();
		String s2 = "hello";
		
		String s3 = new String("1")+new String("1");
		s3.intern();
		String s4 = "11";
		
		System.out.println("s1==s2?"+(s1==s2));
		System.out.println("s3==s4?"+(s3==s4));
	}
	
	/** 
	* @Title: stringInternUse 
	* @Description: 测试intern节省内存
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	* 使用MAT测试堆内存使用情况：
	* http://blog.csdn.net/baidu_31657889/article/details/52315902
	* 步骤
	* 1.设置测java类的运行参数：run as->run configurations->arguments->VM arguments:-agentlib:hprof=heap=dump,format=b
	* 	(1)不使用intern方法：
	* 	(2)使用intern方法：
	*/ 
	static final int MAX = 100000;  
	static final String[] arr = new String[MAX]; 
	public static void stringInternUse(){
        //为长度为10的Integer数组随机赋值  
        Integer[] sample = new Integer[10];  
        Random random = new Random(1000);  
        for (int i = 0; i < sample.length; i++) {  
            sample[i] = random.nextInt();  
        }  
        //记录程序开始时间  
        long t = System.currentTimeMillis();  
        //使用/不使用intern方法为10万个String赋值，值来自于Integer数组的10个数  
            for (int i = 0; i < MAX; i++) {  
//                arr[i] = new String(String.valueOf(sample[i % sample.length]));  
                arr[i] = new String(String.valueOf(sample[i % sample.length])).intern();  
            }  
            System.out.println((System.currentTimeMillis() - t) + "ms");  
            System.gc();  
	}

}
