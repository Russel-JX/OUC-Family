package algorithm.recursion;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

/**
* @ClassName: FullPermutation
* @Package algorithm.recursion
* @Description: 递归算法——实现全排列
* @author Russell Xun Jiang
* @date 2016年11月25日 下午5:36:25
* 把大问题分解成一个个逐渐推进的类似小问题。然后以此确定代码的，***入参、返回值、方法、结束条件。***
* 诀窍：
* 	1.分析相邻元素的关系。因为递归是所有元素都有类似的逻辑，所以可以分析相邻元素有哪些关系，这样根据每每相邻的关系，逐渐关联到问题中所有左右元素的关系。
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
		
//		String[] xx = {"avvv","bsss","c"};
//		System.out.println(ArrayUtils.toString(xx));//ArrayUtils.toString(
		
		//递归算手机数字按键的字母排列
//		String aa = ArrayUtils.toString(getPosibility("123"));
//		System.out.println(aa);
//		
////		//递归全排列
//		String[] result = getArrange("abc");
//		System.out.println(ArrayUtils.toString(result));//ArrayUtils.toString(Object)把任意数组转成String
		
		//n个数中选m个，全排列（排列又组合）
		String[] inti = {"0","1","2","3","4","5","6","7","8","9"};
		String[] res = getPermutationCombination(inti);
		System.out.println(ArrayUtils.toString(res));	
		System.out.println(res.length);	
		Object[] objs = getRepeatDigital(res);
		System.out.println(ArrayUtils.toString(objs));
		System.out.println(objs.length);
		
		//递归算阶乘
//		System.out.println(getFactorial(3));
		
		
//		//复利计算——每年投入与回报
//		for(int i=1;i<YEAR+1;i++){
//			double a = getFuli(i,BENJIN,RATE);
//			if(i%5==0){
//				System.out.println("投"+i+"年,每年投"+BENJIN+"，利率"+(Math.round(100*(RATE-1)))+"%。本金+利润="+a+"，利润="+Math.round(a-i*BENJIN));
//			}
//			
//		}
		
	}
	
	private static final int YEAR = 41; //投资年限
	private static final double BENJIN = 1; //每次投入万元
	private static final double RATE = 1.05; //利息
	/** 
	* @Title: getFuli 
	* @Description: 每年投入与回报 
	* @param @param n  投入几年
	* @param @param benjin	每年投入的固定本金
	* @param @param rate	利息
	* @param @return    设定文件 
	* @return double    返回类型 
	* @throws 
	*/ 
	public static double getFuli(int n,double benjin,double rate){
		if(n==1){
			return benjin*rate;
		}
		return (getFuli(n-1,benjin,rate)+benjin)*rate;
	}
	
	
	
	/** 
	* @Title: getPosibility 
	* @Description: 手机数字按键代表的字母的所有组合。
	* @param @param numbers
	* @param @return    设定文件 
	* @return String[]    返回类型 
	* @throws 
	* 问题：根据按的手机数字，输出数字代表的字母的所组合。
	* 数字1没有字母，2:表示abc,3:def,4:ghi,...,9:wxyz。
	* 如：1232表示abc,def,abc的所有组合，
	* 	即ada,adb,adc;aea,aeb,aec;afa,afb,afc;  
	* 	 bda,bdb,bdc;bea,beb,bec;bfa,bfb,bfc;  
	* 	 cda,cdb,cdc;cea,ceb,cec;cfa,cfb,cfc;
	* 思路：
	* 1.把大问题逐渐分解成有以此关系的小问题。
	* 	按键		结果
	* 	3		d,e,f
	* 	23		ad,ae,af; bd,be,bf; cd,ce,cf;=a(d,e,f)
	* 	423		g(ad,ae,af; bd,be,bf; cd,ce,cf;); h(ad,ae,af; bd,be,bf; cd,ce,cf;); i(ad,ae,af; bd,be,bf; cd,ce,cf;);
	* 总结，每增加一个数字的可能输出结果：都是之前结果的基础上，分别和此数字代表的每一个字符的组合结果。
	* 入参：一串数字
	* 返回值：新增一个数字后的排列结果
	* 递归方法：本次调用方法的结果，是此新数字代表的每一个字符，分别和之前一串数字的结果的重新拼接。
	* 		这里要把当前这串数字，间接算出前一次数字的排列结果，多了一步转换；
	* 结束条件：当之前的一串数字只有一个时，只返回这一个数字代表的几个字符。
	*/ 
	public static String[] getPosibility(String numbers){
		String[][] numberString = {{""},{""},{"a","b","c"},{"d","e","f"},{"g","h","i"},
				{"j","k","l"},{"m","n","o"},{"p","q","r","s"},{"t","u","v"},{"w","z","y","z"}};//数字0和1不代表任何内容
		if(numbers.length()==1){//只有一个数字时，返回数字代表的字符。
			return numberString[Integer.valueOf(numbers)];
		}
		char thisNumber = numbers.charAt(0);//当前新数字
		String[] thisString = numberString[Integer.valueOf(String.valueOf(thisNumber))];//当前新数字代表的字符串
		String lastNumbers = numbers.substring(1,numbers.length());//上次的数字串
		String[] lastRes = getPosibility(lastNumbers);//上次的结果
		String[] thisRes = new String[thisString.length*lastRes.length];//本次的结果
		int index = 0;
		for(int i=0;i<thisString.length;i++){
			for(int k=0;k<lastRes.length;k++){
				thisRes[index] = thisString[i]+lastRes[k];
				System.out.println("thisRes["+index+"]="+thisRes[index]);
				index++;
			}
		}
		return thisRes;
	}
	
	/** 
	* @Title: getArrange 
	* @Description: 全组合 
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
		int index = 0;
		for(int i=0;i<lastRes.length;i++){
			String item = lastRes[i];//上次排列结果中的一个元素
			//向上一个排列结果的每一个元素中的每个字符前后插入新字符。
			for(int k=0;k<(item.length()+1);k++){
				thisRes[index] = String.valueOf((ArrayUtils.add(item.toCharArray(),k,newChar)));//thisRes[index]之前下表重复被覆盖了。String.valueOf(char[])，将字符数组转字符串
				System.out.println("thisRes["+i+"+"+k+"]="+thisRes[i+k]);
				index++;
			}
		}
		return thisRes;
	}
	
	/** 
	* @Title: getPermutationCombination 
	* @Description: n个数中选m个，全排列  
	* @param @param lastResult
	* @param @return    设定文件 
	* @return String[]    返回类型 
	* @throws 
	*/ 
	public static String[] getPermutationCombination(String[] lastResult){
		String[] base = {"0","1","2","3","4","5","6","7","8","9"};
		//如果结果中每个元素长度达到5个则停止。
		if(lastResult[0].length()==5){
			return lastResult;
		}
		
		//一直根据上一次结果，往下递归求下一次结果。
//		String[] thisResult = new String[lastResult.length*(base.length-lastResult[0].length())];
		String[] thisResult = new String[lastResult.length*base.length];
		int count = 0;
		for(int i=0;i<lastResult.length;i++){
			for(int k=0;k<base.length;k++){
//				//每个元素在排列中只出现一次
//				if(lastResult[i].indexOf(base[k])<0){
//					thisResult[count] = lastResult[i] + base[k];
//					count++;
//				}
				thisResult[count] = lastResult[i] + base[k];
				count++;
			}
		}
		return getPermutationCombination(thisResult);
	} 
	
	
	//每个元素的总位数
	public static final int DIGITAL_LENGTH = 5;
	//每个元素的个位数之和.2个一样，其他不一样。即每个元素中的数字出现除数是22111的，如10134,00123
	//即相加等于7的。3个一样相加之和为11。4个一样相加17。5个一样之和为25。
	//2个和3个一样的之和为14，如22288。2对一样，1个不一样各位之和位9，如11223
	public static final int DIGITAL_SUM = 9;
	/** 
	* @Title: getRepeatDigital 
	* @Description: 从全排列中，筛选结果 
	* @param @param result
	* @param @return    设定文件 
	* @return Object[]    返回类型 
	* @throws 
	*/ 
	public static Object[] getRepeatDigital(String[] result){
		List<String> newArray = new LinkedList<String>();
		for(int i=0;i<result.length;i++){
			char[] array = result[i].toCharArray();
			int[] countArray = new int[DIGITAL_LENGTH];
			for(int j=0;j<array.length;j++){
				int count = 0;
				for(int k=0;k<array.length;k++){
					if(array[j]==array[k]){
						count++;
					}
				}
				countArray[j] = count;
			}
			int sum = 0;
			for(int p=0;p<countArray.length;p++){
				sum +=  countArray[p];
			}
			if(sum==DIGITAL_SUM){
				newArray.add(result[i]);
			}
		}
		return newArray.toArray();
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
