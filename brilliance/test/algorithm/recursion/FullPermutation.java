package algorithm.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

/**
* @ClassName: FullPermutation
* @Package algorithm.recursion
* @Description: 递归算法——实现全排列
* @author Russell Xun Jiang
* @date 2016年11月25日 下午5:36:25
* 把大问题分解成一个个逐渐推进的类似小问题。
* 诀窍：
* 	1.分析相邻元素的关系。因为递归是所有元素都有类似的逻辑，所以可以分析相邻元素有哪些关系，这样根据相邻的关系，逐渐关联到问题中左右元素的关系。
* 		有的从一个元素开始，分析第一个元素，和第二个元素的关系，以及第二个元素和第三个元素的关系，以此类推。
* 		有的从最后一个元素开始，分析和倒数第二个元素的关系，一次类推。
* 	2.将相邻元素的关系具体化。**这个具体关系是递归方法的核心，就是代码要实现的东西**
* 		2.1递归方法。
* 		入参：当前递归的对象。
* 		返回值：当前递归的返回结果。
* 		递归方法：根据当前的递归对象，以及上一个或下一个结果与当前对象的关系，返回结果。返回结果用上一个或下一个结果和当前对象来表示。
* 	3.确定递归的结束条件，一般在第一个元素或最后一个元素的关系中，即第一或最后元素不再满足2的关系了。否则无限递归，不是死循环。栈溢出。
*/
public class FullPermutation {

	public static void main(String[] args) {
//		String a = "abc";
//		String c = "abc";
//		String[] b = {a};
//		char[] d = {'1','2','3'};
//		System.out.println(b[0]);
//		System.out.println(String.valueOf(d));
		
//		String x = "abc";
//		char[] y = x.toCharArray();
//		String[] z = new String[4];
//		for(int i=0;i<(y.length+1);i++){
//			z[i] = String.valueOf(ArrayUtils.add(y, i, 'd'));
//		}
//		for(int i=0;i<z.length;i++){
//			System.out.println(z[i]);
//		}
		
		
		String[] result = getArrange("abc");
		for(int i=0;i<result.length;i++){
			System.out.println("result[i]="+result[i]);
		}
		
//		System.out.println(getFactorial(3));
	}
	
	/** 
	* @Title: getArrange 
	* @Description: 全排列组合 
	* @param @param s
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	* 如：abcd
	* 找规律：
	* 	字符		排列
	* 	d		d
	* 	cd		cd;dc
	* 	bcd		bcd,cbd,cdb;bdc,dbc,dcb
	* 	abdc	abcd,bacd,bcad,bcda;acbd,cabd,cbad,cbda;......
	* 从只有一个字符d分析，多了一个字符c时，相当于在原有d的前后插入c，得到[cd,dc]2种排列结果。
	* 再加入b字符时，相当分别给[cd,dc]元素的每个字符前后插入b，得到[bcd,cbd,cdb;bdc,dbc,dcb]6中排列结果。
	* 再加入a字符时，相当于在[bcd,cbd,cdb;bdc,dbc,dcb]元素的每个字符前后插入a，得到[abcd,bacd,bcad,bcda;acbd,cabd,cbad,cbda;......]24种排列结果。
	* 结论：每多新一个字符，排列的结果，都是在前一个排列结果的基础上，对每一个排列结果拿出来，再把新字符分别放到元素的前后。
	* 所以，递归方法是：拿到前一个的排列结果，分别把新字符放到每一个结果中字符的前后，返回新的排列结果。
	* 		显然入参不是前一个排列结果，而是只要一个字符串，我们可以根据这个字符串，在方法中去间接得到上一次的排列结果。（只是多了一步转换）。
	* 	入参：因为是某一次的排列结果，可能有多个。所以应该用数组或集合表示。但是这个的入参显示不是某一个排列结果，是一个字符串。
	* 	返回值：排列结果，和入参一样，可能有多个。所用数组或集合表示。
	* 结束条件是：前一个排列结果只有一个元素，则直接返回此一个字符即可，不再使用字符前后插入新字符。
	*/ 
	public static String[] getArrange(String s){
		if(s.length()==1){
			String[] xx = {s};
			return xx;
		}
		/**
		 * 根据新增加的字符后的总字符串（入参），得到上一次的排列结果，来计算本次的排列结果。而上一次排列结果是根据拿掉本次新增加的字符剩下的字符串，调用此方法的排列结果。
		 */
		char newChar = s.substring(0,1).charAt(0);//本次新增加的字符
		String lastString = s.substring(1, s.length());//上一次字符串
		String[] lastRes = getArrange(lastString);//上一次字符串的排列结果
		String[] thisRes = new String[s.length()*lastRes.length];//本次排列结果
		for(int i=0;i<lastRes.length;i++){
			String item = lastRes[i];//上次排列结果中的一个元素
			//向上一个排列结果的每一个元素中的每个字符前后插入新字符。
			for(int k=0;k<(item.length()+1);k++){
				thisRes[i+k] = String.valueOf((ArrayUtils.add(item.toCharArray(),k,newChar)));
				System.out.println("thisRes["+i+"+"+k+"]="+thisRes[i+k]);
			}
		}
		return thisRes;
	}
	
	/** 
	* @Title: getFactorial 
	* @Description: 算阶乘n!
	* @param @param n
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	* 思路：
	* 1.把大问题分解成一个个逐渐推进的类似小问题。
	* 	1的阶乘是1
	* 	2的阶乘是1x2
	* 	3的阶乘是1x2x3
	* 	n的阶乘是1x2x3...(n-1)n
	* 	一个数的阶乘等于前一个数的阶乘乘以自己。
	* 	所以，这里的策略是：当前迭代的数，只要知道前一个数的阶乘是多少就能解决。每个数都去找自己前一个数的阶乘。直到当前数字是1。
	* 	所以这个方法本质就是找出前一个书的阶乘是多少。
	* 2.结束条件。即分解之后，最小问题的结束条件。
	* 	这里的最小问题就是最小只能到1了，不能再小到0了。所以如果当前数为1则结束递归。
	*/ 
	public static int getFactorial(int n){
		if(n==1){
			return 1;
		}
		System.out.println(n+"*"+(n-1));
		return n*getFactorial(n-1);
	} 

}
