package corejava;

/**
* @ClassName: ShiftOperation
* @Package corejava
* @Description: 二进制和移位运算
* @author Russell Xun Jiang
* @date 2016年11月28日 上午10:17:31
* 
*  负数的二进制：以补码表示，负数的符号位，即高位永远是1。符号位不变，其他位取反加1。
*  int类型为32位二进制表示。
*  	如：-8的二级制是1(27个1)1000
*  		原码：1(27个0)1000
*  		反码：1(27个1)0111
*  		补码：1(27个1)1000
* 
* 移位：将数字用二进制表示，向左或向右移动。
* <<左移，二级制整体向左移动，**总长度不变**。
* 		末尾添0.移动1位相当于乘以2
* >>右移，有符号右移，**总长度不变**。
* 		二级制整体向右移动。如果结果是正数（高位是0），则高位补0；负数（高位是1），高位补1，计算出的结果再去反加1即为最终结果。移动1位相当于除以2
* >>>无符号移位，**总长度减少**。
* 		整体向右移动，把末尾数字去掉剩下的就是结果。不管是正数还是负数移动后，高位都添0，所以不管是正数还是负数，移位后都变成了正数。直接拿移动后的数计算结果，不需要取反加1等操作。
*/

public class BinaryShiftOperation {

	public static void main(String[] args) {
		System.out.println("8的二级制是："+Integer.toBinaryString(8));
		System.out.println("-8的二级制是："+Integer.toBinaryString(-8));
		
		System.out.println((8<<1)+"二进制表示："+Integer.toBinaryString(8<<1));//左移1位。1000<<1=10000=16。相当于乘以2.
		System.out.println((-8<<1)+"二进制表示："+Integer.toBinaryString(-8<<1));//左移1位。1(27个1)1000<<1=(28个1)0000,求原负数取反加1，=1(26个0)10000=-16
		System.out.println((8>>1)+"二进制表示："+Integer.toBinaryString(8>>1));//右移1位。1000>>1=100=4
		System.out.println((7>>1)+"二进制表示："+Integer.toBinaryString(7>>1));//右移1位。111>>1=11=3
		System.out.println((-8>>1)+"二进制表示："+Integer.toBinaryString(-8>>1));//右移1位。1(27个1)1000>>1=(30个1)00,求原负数取反加1，=1(28个0)11=-4
		System.out.println((8>>>1)+"二进制表示："+Integer.toBinaryString(8>>>1));//右移1位。1000>>>1=100=4
		System.out.println((-8>>>1)+"二进制表示："+Integer.toBinaryString(-8>>>1));//右移1位。1(27个1)1000>>>1=1(27个1)100=2的31次方-4-1-1=2147483644
	}

}
