package algorithm;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

/**
* @ClassName: Sort
* @Package algorithm
* @Description: 各种排序
* @author Russell Xun Jiang
* @date 2016年12月2日 下午8:29:35
* 1.直接插入排序		T
* 2.希尔排序
* 3.直接选择排序
* 4.堆排序
* 5.冒泡排序			T
* 6.快速排序
* 7.归并排序
* 8.基数排序
* 
*/
public class Sort {

	public static void main(String[] args) {
//		//java排序。任何数字序列都可以直接使用java工具类直接排序
//		javaSort(new double[]{76,45,74,26,38,63});
//		
//		//直接插入排序。
//		directInsertSort(new double[]{76,45,74,26,38,63});
		
		//冒泡排序
//		bubleSort(new double[]{76.45,74,26,38,63});

		//快速排序
		fastSort(new double[]{76.45,74,26,38,63},0,4);



	}

	public static void test(double[] a){
		a[0] = 1;
		a[1] = 2;
	}
	
	/** 
	* @Title: javaSort 
	* @Description: java方式排序。任何数字序列都可以直接使用java工具类直接排序
	* @param @param sourceNumber
	* @param @return    设定文件 
	* @return double[]    返回类型 
	* @throws 
	* 方式一：Arrays.sort(数组)。最快。
	* 方式二：Collections.sort(Object[])。可能要类型转换。
	*/ 
	public static double[] javaSort(double[] sourceNumber){
		/*
		 * 方式一：集合的排序方法
		 */
		//int转Integer，便于再转集合
		Double[] arr = new Double[sourceNumber.length];
		for(int i=0;i<sourceNumber.length;i++){
			arr[i] = sourceNumber[i];
		}
		List<Double> list = Arrays.asList(arr);//数组转集合
		Collections.sort(list);
		Object[] arr2 = list.toArray();//集合转数组
		double[] resultNumber = new double[sourceNumber.length]; 
		//将Object数组转int数组
		for(int i=0;i<arr2.length;i++){
			resultNumber[i] = (Double) arr2[i];
		}
		
		/*
		 * 方式二：数组的排序方法
		 */
		Arrays.sort(sourceNumber);
		System.out.println("java排序Collections.sort()："+ArrayUtils.toString(resultNumber));
		System.out.println("java排序Arrays.sort()："+ArrayUtils.toString(sourceNumber));
		return sourceNumber;
	}
	
	/** 
	* @Title: directInsertSort 
	* @Description: 直接插入排序 
	* @param @param sourceNumber
	* @param @return    设定文件 
	* @return double[]    返回类型 
	* @throws 
	* 从前往后或从后往前，每一次排序是取序列中的未排序元素，和已排序的序列元素比较。当当前未排序元素数值在已排序列的某2个元素之间时，插入2者之间。
	* 总结：直接插入排序其实就是把序列中的元素一个一个拿出来，放到之前已经有序的序列中的合适位置，形成新的有序序列
	* 例子：
	* 	初始序列			76	45	74	26	38	63	
	* 	第1次取第2个元素（45）和第一个元素比较（76）
	* 	第1次排序结果		45	76	74	26	38	63
	* 	第2次取第3个元素74和已排序列（45,76）比较
	* 	第2次排序结果		45	74	76	26	38	63
	* 	第3次取第4个元素26和已排序列（45,74,76）比较
	* 	第3次排序结果		26	45	74	76	38	63
	* 	...
	* 入参：初始无序数列
	* 返回值：有序数列
	* n-1次排序
	* 第n次排序要和前面的已经有序的序列比较n-1次
	* 每一次的排序规则：
	* 	当前元素x。
	* 	已排序列an
	* 	x<a0,放在第一位。
	* 	an-1<x<=an,放在an位置上
	* {76,45,74,26,38,63}
	*/ 
	public static double[] directInsertSort(double[] sourceNumber){
		for(int i=1;i<sourceNumber.length;i++){
			for(int k=0;k<i;k++){
				if(sourceNumber[i]<sourceNumber[0]){
					double temp = sourceNumber[i];
					sourceNumber = ArrayUtils.remove(sourceNumber, i);
					sourceNumber = ArrayUtils.add(sourceNumber, 0, temp);
					break;
				}
				if((sourceNumber[i]<=sourceNumber[k+1])&&(sourceNumber[i]>=sourceNumber[k])){//插到k+1位置上。关键字大于有序序列最后一个元素的情况包括在此。
					double temp = sourceNumber[i];
					sourceNumber = ArrayUtils.remove(sourceNumber, i);
					sourceNumber = ArrayUtils.add(sourceNumber, k+1, temp);
					break;
				}
			}
		}
		System.out.println("直接插入排序："+ArrayUtils.toString(sourceNumber));
		return sourceNumber;
	}
	
	/** 
	* @Title: bubleSort 
	* @Description: 冒泡排序 
	* @param @param sourceNumber
	* @param @return    设定文件 
	* @return double[]    返回类型 
	* @throws 
	* 每一轮排序，元素从头到尾依相邻的两两依次比较，小的和大的交换位置，最后一个元素一定是此轮排序最大的。
	* 例子：
	* 	初始序列			76	45	74	26	38	63	
	* 	第1次76比45大，2者交换；76比74大，2者交换；76比后面的都大，所以76放到最后。
	* 	第1次排序结果		45	74	26	38	63	76	
	* 	第2次45和74比较，45小位置不变；74和26比较，74大，2者交换；74比剩下的都大，所以74放在倒数第二的位置。
	* 	第2次排序结果		45	26	38	63	74	76
	* 	...
	* n个元素，n-1次排序
	* 每次排序只排前面没有筛选出最大值剩下的序列。
	*/ 
	public static double[]  bubleSort(double[] sourceNumber){
		for(int i=0;i<sourceNumber.length-1;i++){
			for(int k=0;k<sourceNumber.length-i-1;k++){
				if(sourceNumber[k]>sourceNumber[k+1]){
					double temp = sourceNumber[k+1];
					sourceNumber = ArrayUtils.remove(sourceNumber, k+1);
					sourceNumber = ArrayUtils.add(sourceNumber, k,temp);
				}
			}
		}
		System.out.println("冒泡排序："+Arrays.toString(sourceNumber));
		return sourceNumber;
	}


	/*快速排序
	如果分区中数列长度<=1,则已经有序,直接返回;
	否则对此分区进行一轮排序,排序后,把产生的新的分区数列再进行新一轮排序.
	一轮排序的规则是:
		取分区的第一个元素作为基准.从首尾两端找出比基准小的和比基准大的,2个数交换位置.
		一直从首尾向内寻找,每找到一对都交换位置,直到首尾两个数的位置重叠,则将重叠位置的元素和基准交换位置.完成一轮排序.
	* @Title: fastSort
	* @Description:
	* @param srcNmb	分区数列
	* @param i 	本次待排序的开始位置
	* @param j 	本次待排序的结束位置
	* @param @return
	* @return double[]    此分区排序后的有序序列
	* @throws
	* {76.45,74,26,38,63}
	 */
	public static void fastSort(double[] srcNmb,int i,int j){
		if(i>j){
			return ;
		}
		//本轮排序的将要找到的正确位置
		int lastFixedIndex = j;
		double e1 = srcNmb[i];
		int baseIndex = i;
		int startMatch = i;//本轮排序的开始位置,就是下一轮左边排序的开始位置
		int endMatch = j;//本轮排序的结束位置,就是下一轮右边排序的结束位置
		for(;i<j && j!=0;i++,j--){
			for(;j>-1;j--){
				if(srcNmb[j]<e1){
					break;
				}
			}
			j = Math.max(j,0);//防止数据越界i;

			for(;i<srcNmb.length && i<j;i++){
				if(srcNmb[i]>e1){
					break;
				}
			}
			i = Math.min(i,srcNmb.length-1);//如果都没有,则取最后一个,防止数组越界

			double tmp = srcNmb[i];//不管什么情况都选2个交换
			srcNmb[i] = srcNmb[j];
			srcNmb[j] = tmp;

			if(i==j) break;
		}
		i = Math.min(i,srcNmb.length-1);

		double tmp2 = srcNmb[j];//不管什么情况,都把基准和重叠交换(重叠出就是i或 j)
		srcNmb[j] = srcNmb[baseIndex];
		srcNmb[baseIndex] = tmp2;
		lastFixedIndex = j;

		System.out.println("lastFixedIndex="+j+",lastFixedElement"+srcNmb[j]+",array="+ArrayUtils.toString(srcNmb));

		//下一轮的待排序数列 - 左边(继续从上次找到的开始位置到刚才找到的正确位置给数列排序;
		// 开始位置==上一轮的开始位置,结束位置==刚才找到的正确位置-1)
		if(lastFixedIndex>1) fastSort(srcNmb,startMatch,lastFixedIndex-1);
		//下一轮的待排序数列 - 右边(继续从刚找到的正确位置到上次找到的结束位置给数列排序)
		// 开始位置==刚才找到的正确位置+1,结束位置==上一轮的结束位置)
		if(lastFixedIndex<srcNmb.length-2) fastSort(srcNmb,lastFixedIndex+1,endMatch);
	}



	

}
