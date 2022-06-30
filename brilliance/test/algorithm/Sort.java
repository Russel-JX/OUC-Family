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
* 4.堆排序			T
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
		
		//堆排序
		heapSort(new int[]{4,5,8,2,3,9,7,1});
		
		//归并排序
		mergesort(new int[]{4,5,8,2,3,9,7,1});
		



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

	
	//堆排序开始
	public static void heapSort(int[] arr) {
		if (arr == null || arr.length == 0) {
			return;
		}
		int len = arr.length;
		// 构建大顶堆，这里其实就是把待排序序列，变成一个大顶堆结构的数组
		buildMaxHeap(arr, len);
 
		// 交换堆顶和当前末尾的节点，重置大顶堆
		for (int i = len - 1; i > 0; i--) {
			swap(arr, 0, i);
			len--;
			heapify(arr, 0, len);
		}
		
		for(int k=0;k<arr.length;k++) {
			System.out.println("堆排序："+arr[k]);
		}
		
	}
 
	private static void buildMaxHeap(int[] arr, int len) {
		// 从最后一个非叶节点开始向前遍历，调整节点性质，使之成为大顶堆
		for (int i = (int)Math.floor(len / 2) - 1; i >= 0; i--) {
			heapify(arr, i, len);
		}
	}
 
	private static void heapify(int[] arr, int i, int len) {
		// 先根据堆性质，找出它左右节点的索引
		int left = 2 * i + 1;
		int right = 2 * i + 2;
		// 默认当前节点（父节点）是最大值。
		int largestIndex = i;
		if (left < len && arr[left] > arr[largestIndex]) {
			// 如果有左节点，并且左节点的值更大，更新最大值的索引
			largestIndex = left;
		}
		if (right < len && arr[right] > arr[largestIndex]) {
			// 如果有右节点，并且右节点的值更大，更新最大值的索引
			largestIndex = right;
		}
 
		if (largestIndex != i) {
			// 如果最大值不是当前非叶子节点的值，那么就把当前节点和最大值的子节点值互换
			swap(arr, i, largestIndex);
			// 因为互换之后，子节点的值变了，如果该子节点也有自己的子节点，仍需要再次调整。
			heapify(arr, largestIndex, len);
		}
	}
 
	private static void swap (int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;

	}
	//堆排序结束

	
	//归并排序开始
	/**
	 * 递归方式
	 * @param arr
	 */
	public static void mergesort(int []arr){
        int []temp = new int[arr.length];//在排序前，先建好一个长度等于原数组长度的临时数组，避免递归中频繁开辟空间
        sort(arr,0,arr.length-1,temp);
        
        for(int k=0;k<arr.length;k++) {
			System.out.println("归并排序："+arr[k]);
		}
    }
    private static void sort(int[] arr,int left,int right,int []temp){
        if(left<right){
            int mid = (left+right)/2;
            sort(arr,left,mid,temp);//左边归并排序，使得左子序列有序
            sort(arr,mid+1,right,temp);//右边归并排序，使得右子序列有序
            merge(arr,left,mid,right,temp);//将两个有序子数组合并操作
        }
    }
    private static void merge(int[] arr,int left,int mid,int right,int[] temp){
        int i = left;//左序列指针
        int j = mid+1;//右序列指针
        int t = 0;//临时数组指针
        while (i<=mid && j<=right){
            if(arr[i]<=arr[j]){
                temp[t++] = arr[i++];
            }else {
                temp[t++] = arr[j++];
            }
        }
        while(i<=mid){//将左边剩余元素填充进temp中
            temp[t++] = arr[i++];
        }
        while(j<=right){//将右序列剩余元素填充进temp中
            temp[t++] = arr[j++];
        }
        t = 0;
        //将temp中的元素全部拷贝到原数组中
        while(left <= right){
            arr[left++] = temp[t++];
        }
    }
	
	public static <E extends Comparable<E>> void mergeSort(E[] arr){
        if(arr == null){
            return;
        }

        //使用公共的临时数组辅助进行数组顺序的归并 merge()方法专用
        E[] temp = Arrays.copyOf(arr, arr.length);

        //从1开始，按2的倍数进行分解
        for (int sz = 1; sz < arr.length; sz *= 2) {

            //内层循环 从0出发，每一轮按两个sz区间的增长遍历合并，即起始位置为i
            //合并 [i, i + sz - 1] 和 [i + sz, Math.min(i + sz + sz - 1, arr.length - 1)] 区间的元素
            for (int i = 0; i + sz < arr.length; i += sz + sz){
                //i += sz + sz 即i按sz的2倍递增
                //i + sz < arr.length 说明后面的区间还有元素,后面的区间有元素，则就要和前面的区间进行合并
                if(arr[i + sz - 1].compareTo(arr[i + sz]) > 0){
                    //注意 i + sz + sz - 1有可能会越界，所以使用Math.min(i + sz + sz - 1, arr.length - 1)
                    merge(arr, i, i + sz - 1, Math.min(i + sz + sz - 1, arr.length - 1) ,temp);
                }
            }
        }
    }

    /**
     * 归并
     * 合并两个区间 arr[left, mid] 和arr[mid+1, right]
     * @param arr 包含两个有序区间的数组
     * @param left 第1个有序区间的起始地址
     * @param mid 第1个有序区间的结束地址。也是第2个有序区间的起始地址
     * @param right 第2个有序区间的结束地址
     * @param temp 公共的临时空间，辅助进行数组顺序的归并
     * @param <E> 泛型
     */
    private static <E extends Comparable<E>> void merge(E[] arr, int left, int mid, int right, E[] temp){
        //将arr数组从left位置开始拷贝到temp数组的left位置开始
        //拷贝right - left + 1个元素，(前闭后开，所以要 + 1)
        //因为没有额外的内存开辟，性能优化很高
        System.arraycopy(arr, left, temp, left,right - left + 1);

        int i = left;
        int j = mid + 1;

        //每轮循环对arr[k]进行赋值
        for(int k = left; k <= right; k++){
            //主要就是比较temp[i]和temp[j]的值大小，进行调整
            if(i > mid){
                //如果i大于mid，直接归并temp[j,right]区间的元素到arr
                arr[k] = temp[j];
                j++;
            }else if(j > right){
                //如果j大于right，直接归并temp[i,mid]区间的元素到arr
                arr[k] = temp[i];
                i ++;
            }else if(temp[i].compareTo(temp[j]) <= 0) {
                //如果temp[i]小于等于temp[j]，直接归并temp[i]的值到arr
                arr[k] = temp[i];
                i ++;
            }else {
                //如果temp[i]大于temp[j]，直接归并temp[j]的值到arr
                arr[k] = temp[j];
                j++;
            }
        }
    }
	//归并排序结束

	

}
