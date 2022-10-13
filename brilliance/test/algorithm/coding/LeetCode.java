package algorithm.coding;


import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problemset/all/
 * 策略：
 *  高效代码：
 *  先写大流程coding，边界和特殊情况问题写在纸上，大流程coding后，再补充边界和特殊情况。
 */
public class LeetCode {
    public static void main(String[] args) throws Exception{

        //(0)找出数列中最大的差值
        int[] arr = {1,3,8,16,7,50,0,20,100};
        int[] arr2 = {1,5,1,2 };
        int[] arr3 = {-6,5,-2,-5,10,4};
        getMaxDelta(arr);
        getMaxDelta(arr2);
        getMaxDelta(arr3);
        /**
         * output:
         * maxDelta:100
         maxDelta:4
         maxDelta:16
         */

        //(2)两数相加(链表)
        ListNode node1 = new ListNode (2,new ListNode (4,new ListNode (3)));
        ListNode node2 = new ListNode (5,new ListNode (6,new ListNode (4)));
        addTwoNumbers (node1, node2);
        /**
         * output:
         * combined node:7,0,8
         */


        //(3)无重复字符串的最长子串
        lengthOfLongestSubstring("");
        lengthOfLongestSubstring("abcabcbb");
        lengthOfLongestSubstring("pwwkew");
        lengthOfLongestSubstring("xyabcabcbb");
        /**
         * output:
         * maxSubLength:3,maxSubStr:abc
         maxSubLength:4,maxSubStr:wke
         maxSubLength:5,maxSubStr:xyabc
         */


        //(4)寻找两个正序数组的中位数
        findMedianSortedArrays(new int[]{1,2}, new int[]{3,4});
        findMedianSortedArrays(new int[]{1,2}, new int[]{3});
        /**
         * output:
         * 合并后数列：1.0,2.0,3.0,4.0,,中位数：2.5
         合并后数列：1.0,2.0,3.0,,中位数：2.0
         */

        //(5)最长回文子串

//        longestPalindrome("");
        longestPalindrome("babad");
        longestPalindrome("cbbd");
        longestPalindrome("xyzbabad");
        longestPalindrome("xyzbbbac");
        /**
         * oputput:
         * babad回文了：bab
         babad回文了：aba
         babad最长回文子串:bab
         cbbd相邻一样：bb
         cbbd最长回文子串:bb
         xyzbabad回文了：bab
         xyzbabad回文了：aba
         xyzbabad最长回文子串:bab
         xyzbbbac相邻一样：bb
         xyzbbbac相邻一样：bb
         xyzbbbac回文了：bbb
         xyzbbbac最长回文子串:bbb
         */

        //(6)Z字形变换
        convert("PAYPALISHIRING",3);
        convert("PAY",5);
        convert("PAYPALISH",5);
        convert("PAYPALISHIRING",5);
        /**
         * output:
         * 原串总数:14,总行数：3总列数：7
         原字符串：PAYPALISHIRING，行数：3,Z字型转换后：PAHNAPLSIIGYIR
         原串总数:3,总行数：5总列数：1
         原字符串：PAY，行数：5,Z字型转换后：PAY
         原串总数:9,总行数：5总列数：5
         原字符串：PAYPALISH，行数：5,Z字型转换后：PHASYIPLA
         原串总数:9,总行数：5总列数：5
         原串总数:14,总行数：5总列数：6
         原字符串：PAYPALISHIRING，行数：5,Z字型转换后：PHASIYIRPLIGAN
         */

        //(6.2)Z字形变换
        convert2("PAYPALISHIRING",3);
        convert2("PAY",5);
        convert2("PAYPALISH",5);
        convert2("PAYPALISHIRING",5);
        /**
         * output:
         *
         Z字形转换2。 原串：PAYPALISHIRING,总行数：3。变换后：PAHNAPLSIIGYIR
                     Z字形转换2。 原串：PAY,总行数：5。变换后：PAY
                     Z字形转换2。 原串：PAYPALISH,总行数：5。变换后：PHASYIPLA
                     Z字形转换2。 原串：PAYPALISHIRING,总行数：5。变换后：PHASIYIRPLIGAN
         */


        //(7)整数反转
        reverse(123);
        reverse(-123);
        reverse(0);
        reverse(Integer.MAX_VALUE);
        reverse(Integer.MIN_VALUE);
        /**
         * 7. 整数反转。原整数：123,反转后=321
           7. 整数反转。原整数：-123,反转后=-321
           7. 整数反转。原整数：0,反转后=0
           7. 整数反转。原整数：2147483647,反转后=0
           7. 整数反转。原整数：-2147483648,反转后=0
         */

        //(8)
        myAtoi("abc-0123xyz456");
        myAtoi("abc0123xyz456");
        myAtoi("abc0xyz123xyz456");
        myAtoi("abc+1xyz123xyz456");
        myAtoi("abc1xyz123xyz456");
        myAtoi("123");
        myAtoi("abc");
        /**
         * output:
         * (8)字符串转换整数. abc-0123xyz456，->-123
         (8)字符串转换整数. abc0123xyz456，->123
         (8)字符串转换整数. abc0xyz123xyz456，->0
         (8)字符串转换整数. abc+1xyz123xyz456，->1
         (8)字符串转换整数. abc1xyz123xyz456，->1
         (8)字符串转换整数. 123，->123
         (8)字符串转换整数. abc，->0
         */

        //(9)回文数
        System.out.println("(9)回文数。0->"+isPalindrome(0));
        System.out.println("(9)回文数。12->"+isPalindrome(12));
        System.out.println("(9)回文数。-121->"+isPalindrome(-121));
        System.out.println("(9)回文数。121->"+isPalindrome(121));
        System.out.println("(9)回文数。1234321->"+isPalindrome(1234321));
        System.out.println("(9)回文数。1221->"+isPalindrome(1221));

        //(10)正则表达式匹配
//        isMatch("aaaabxyzkkkdefvwwpqr","a*abxyz.*def...pqr");
//        isMatch("pqraaaabxyzkkkdefvwwpqr","pqra*abxyz.*def...pqr");
        isMatch("xxdefxxxpqrxabcxx","..def...pqr.abc..");

    }



    /**
     * (0)找出数列中最大的差值。注：只能是后面减去前面。
     * from citi
     * 如1，5，1，2 ->5-1=4
     * -6,5,-2,-5,10,4->10-(6)=16
     * 分析：
     * 依次循环数列，后比前大，则用当前元素-最小值=maxDelta;否则，后比前小的话，取当前元素和最小值之间的最小值。
     */
    public static int getMaxDelta(int[] arr) {
        int maxDelta = arr[0];
        int min = arr[0];
        for(int i=1;i<arr.length;i++) {
            if(arr[i]-arr[i-1]<0) {
                if(arr[i]<min) {
                    min = arr[i];
                }
            }else {
                if(arr[i]-min>maxDelta) {
                    maxDelta = arr[i]-min;
                }
            }
        }
        System.out.println ("maxDelta:" + maxDelta);
        return maxDelta;
    }

    /**(2)两数相加(链表)
     * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
     请你将两个数相加，并以相同形式返回一个表示和的链表。
     你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     * 输入：l1 = [2,4,3], l2 = [5,6,4]
     输出：[7,0,8]
     解释：342 + 465 = 807.

     分析：逐个取下一个节点的值(有就直接取，没有就默认为0)相加。
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode headNode = new ListNode (l1.getVal ()+l2.getVal ());
        //备份头结点。防止在下面转化中，丢失头结点位置。
        ListNode backNode = new ListNode (0);
        backNode = headNode;
        ListNode nextN1 = l1.getNext ();
        ListNode nextN2 = l2.getNext ();
        int jinWei = 0;
        while(nextN1 !=null || nextN2 !=null){
            int val1=0,val2 =0;
            if(nextN1 != null){
                val1 = nextN1.getVal ();
                nextN1 = nextN1.getNext ();
            }
            if(nextN2 != null){
                val2 = nextN2.getVal ();
                nextN2 = nextN2.getNext ();
            }
            int newVal = 0;
            ListNode childNode;
            //当前位置之和达到10，则用当前之和-10作为新节点的值，并给下个节点进位1.
            if(val1+val2-10>=0){
                newVal = val1+val2-10;
                childNode = new ListNode (newVal+jinWei);
                jinWei = 1;
            }else{
                newVal = val1+val2;
                childNode = new ListNode (newVal+jinWei);
            }
            headNode.setNext (childNode);
            //每次最后，让子节点作为新的头结点。
            headNode = childNode;

        }

        //打印
        String value = String.valueOf (backNode.getVal ());
        ListNode tmp = backNode.getNext ();
        while(tmp != null){
            value += ","+tmp.getVal ();
            tmp = tmp.getNext ();

        }
        System.out.println ("combined node:" + value);
        return backNode;
    }

    /**
     * (3)无重复字符串的最长子串.即子串中
     * 输入: s = "abcabcbb"
     输出: 3
     解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。

     输入: s = "bbbbb"
     输出: 1
     解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。

     输入: s = "pwwkew"
     输出: 3
     解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
          请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。

     分析：从头到尾遍历每个字符，逐个放到OK子串中。如果当前字符不在串中，则加入串并串长度+1；
     否则OK子串变成有重复字符后面所有字符。
     例子：xyabcabcbb
     一开始xyabc最长且长度5，比较到第二个a时，有重复，新的OK子串是bca.

     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        if(s.length () == 0){
            return 0;
        }
        String subStr = new String(s.substring (0,1));
        //最大子串长度
        int maxSubLength = 1;
        //maxSubStr打印用。
        String maxSubStr = "";
        for(int i=1; i<s.length ();i++){
            String item = s.substring(i,i+1);
            if(!subStr.contains(item)){
                subStr += item;
                maxSubStr = subStr;
            }else{
                //有重复。则截取既有子串中，新的子串作为OK子串。
                //这里取已OK串中有重复字符的后面的全部。
                subStr = subStr.substring(subStr.indexOf(item)+1)+item;
            }
            maxSubLength = Math.max(maxSubLength, subStr.length ());
        }

        System.out.println("maxSubLength:"+maxSubLength+",maxSubStr:"+maxSubStr);
        return maxSubLength;
    }

    /**
     * 寻找两个正序数组的中位数.
     * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
     算法的时间复杂度应该为 O(log (m+n)) 。
     注：中位数定义：此数的前面一半比此数小，后一半比此数大。
     则当N为奇数时，=X(n+1)/2,即中间那个数就是；当N为偶数时，=[X(n/2)+X(n/2+1)]/2，即取中间那2个数的平均值。
     示例 1：

     输入：nums1 = [1,3], nums2 = [2]
     输出：2.00000
     解释：合并数组 = [1,2,3] ，中位数 2
     示例 2：

     输入：nums1 = [1,2], nums2 = [3,4]
     输出：2.50000
     解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5

     分析：
     解法一。关键点：如何排列，合并后的有序序列。
     得到合并后的有序数列，就可以用公式直接计算中位数。
     普通归并排序:同时从2个数列相同位置比较元素，小的作为新数列的元素。
     时间复杂度：O(m+n)

     解法二：TODO
     解法三：

     */

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        double[] newNums = new double[len1+len2];
        int i=0,j=0,k=0;
        //合并2个数列
        while(k<len1+len2){
            //防止有1个序列遍历完
            if(i>=len1){
                newNums[k++] = nums2[j++];
                continue;
            }
            if(j>=len2){
                newNums[k++] = nums1[i++];
                continue;
            }

            if(nums1[i]<nums2[j]){
                newNums[k++] = nums1[i++];
            }else{
                newNums[k++] = nums2[j++];
            }
        }

        //计算中位数
        double zhongwei = 0;
        int len = newNums.length;
        if(len%2==0){
            zhongwei = (newNums[len/2-1]+newNums[len/2])/2;

        }else{
            zhongwei = newNums[(len-1)/2];

        }

        //打印
        String value = "";
        int p = 0;
        while(p != newNums.length){
            value += newNums[p++]+",";

        }
        System.out.println("合并后数列："+value+",中位数："+zhongwei);
        return zhongwei;
    }

    /**
     * (5)最长回文子串
     给你一个字符串 s，找到 s 中最长的回文子串。
     回文定义：即左右对称的。
     示例 1：

     输入：s = "babad"
     输出："bab"
     解释："aba" 同样是符合题意的答案。
     示例 2：

     输入：s = "cbbd"
     输出："bb"

     分析：
     方案一：coding 略。
     对xyzbabad,从左到右，循环取每一位的元素。
     比较x和y是否相等，不等(手里有xy)，则比较x和z是否相等，不等(手里有xyz),则比较y和b是否相等，
     不等(手里有xyzb),则比较z和a是否相等，不等(手里有xyzba),则比较b和b是否相等，相等，则有个回文串bab，暂存起来作为目前最长的回文子串，
     继续比较z和a是否相等，不等则目前最长的回文子串是bab，且手里有xyzbab了。
     继续用之前的比较(因为可能存在xyzbabadababzxy,所以继续用xyzbab进行)，比较a和a是否相等，
     相等则又有1个新回文子串aba，比较旧回文bab和新回文aba的长度，取最长的最为最终回文子串。
     循环到最后一个元素结束。

     方案二：
     对xyzbbbac
     从头到尾对每个字符，每次都是先比较当前字符和下一个字符,

     当前是x，先比较x和y，不等。不管x和y是否相等，继续比较x前面的（空）和y，不等，则无回文。继续下一个元素。
     当前是y,先比较y和z，不等。不管y和z是否相等，继续比较y前面的x和z，不等，则无回文。继续下一个元素。
     z同理。
     当前是b同理。
     当前是第二个b，比较b和b，相等则是一个回文bb。不管b和b是否相等，继续比较b前面的z和b，不等，则无回文。继续下一个元素。此时回文=bb
     当前是第三个b，比较b和b，相等则是一个回文bb。不管b和b是否相等，继续比较b前面的b和b，相等，则回文是bbb,继续比较z和a是否相等，不等。继续下一个元素。此时回文=bbb
     当前是a,先比较a和c，不等。不管a和c是否相等，继续比较a前面的b和c，不等，则无回文。继续下一个元素。
     ...
     一直到当前字符是最后一个字符操作后停止。
     方案二的优势是，把连续2个相同字符的情况融入逻辑，而不是像方案一要单独写代码逻辑,简单。
     且没有重复操作。

     * @param s
     * @return
     */
    public static String longestPalindrome(String s) throws Exception {
        //方案二coding
        if(s.length ()<2){
            throw new Exception("必须提供不低于2位长度的字符串来计算回文子串！");
        }
        //当前元素的前一个元素
        String preItem = "";
        //遍历到的当前元素
        String currentItem = s.substring(0,1);
        //回文子串。初始时，1个字符就回文。
        String huiwen = currentItem;
        //最长回文子串
        String maxHuiwen = "";
        //默认当前元素是第一个元素，即下标是0，则开始用第二个元素与之匹配。
        for(int i=1;i<s.length();i++) {
            //每次循环都认为当前元素1个字符和自己就回文
            huiwen = currentItem;
            preItem = (i<=1)?"":s.substring(i-2,i-1);
            String nextItem = s.substring(i,i+1);
            if(currentItem.equals(nextItem)) {
                huiwen = currentItem+nextItem;
                System.out.println(s+"相邻一样："+huiwen);
            }
            //要匹配是否回文的下一个元素。即当前元素的右边的一个字符
            String piPeiNextItem = nextItem;
            //要匹配是否回文的上一个元素。即当前元素的左边的一个字符
            String piPeiPreItem = preItem;
            while(piPeiPreItem.equals(piPeiNextItem)) {
                //如果上面有相邻一样的，且这里两边又有回文。则以此处的为准，因为此处至少有3位。
                huiwen = huiwen.length()==2?huiwen.substring(0,1):huiwen;
                huiwen = piPeiPreItem+huiwen+piPeiPreItem;
                //左右2边匹配。接着往外围匹配左右2边。
                piPeiPreItem = (i-1<=1)?"":s.substring(i-3,i-2);
                piPeiNextItem = s.substring(i+1,i+2);
                System.out.println(s+"回文了："+huiwen);
            }
            if(huiwen.length()>maxHuiwen.length()) {
                maxHuiwen = huiwen;
            }
            //下面代码表示当前元素前后的所有回文屁匹配结束。重置变量
            currentItem = nextItem;
        }

        System.out.println(s+"最长回文子串:"+maxHuiwen);
        return maxHuiwen;
    }



    /**(6)Z 字形变换
     * 注：属于找规律类型问题。
     * 将一个给定字符串 s 根据给定的行数 numRows ，以先从上往下、再从左到右进行 Z 字形排列。
           比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
           P   A   H   N
           A P L S I I G
           Y   I   R
           比如："PAHNAPLSIIGYIR"。
           分析：
           容易想到二维数组存放。
           第一列是原始一维数组排列，
           可见每一行的前后2个数字之差是固定值，最后顺序输出数组元素即可。
           如第一行7-1=13-7=6=差值=(row-1)*2；即总行数-1的2倍，假设是P(6)。
           第二行6-2=4=(row-2)*2, 8-6=2=(row-3)*2, 12-8=4=(row-2)*2, 14-12=(row-3)*2.即先是总行数-2的2倍，假设是Q(4)，然后差值是P-Q(6-4),然后又是Q，最后是P-Q
           第三行5-3=2=(row-3)*2, 9-5=4=(row-2)*2,11-9=(row-3)*2,15-11=(row-2)*2.即先是总行数-3的2倍，假设是R(2)，然后差值是P-R(6-2),然后又是R，最后是P-R
           第四行10-4=6=(row-1)*2。即总行数-1的2倍，假设是P(6)。
           即对首行和尾行，左右差值=(row-1)*2；
           第二行第一对左右差值(6-2)=4, 第二队左右差值(8-6)=2, 这两个差值之和4+2=6=(row-1)*2
           第三行和第二行同理。
           1     7       13
           2   6 8    12 14
           3 5   9 11    15
           4     10      16


     */
    public static String convert(String s, int numRows) {
        /**总列数
            * x是字符串长度。
            * k = (x-row)/(row-1)
            * 总是先竖向向下row个，再斜向上row-2个的排列规律。
            * 第一个竖向向下:k=[-1,0] 如12345。共用第1列
            * 第一个斜向上:k=(0,1)  如，678。占234列
            * 第二个竖向向下:k=[1,2] 如910111213。共用第5列.**表示有1个完整的Z字型。
            * 第二个斜向上:k=(2,3) 如141516。占678列
            * 注：
            */

        //总列数
        int colNum = getColNum(s.length(),numRows);

        String[][] finalStr = new String[numRows][colNum];
        //先排第一列
        int firstColCount = 0;
        for(int i=0;i<numRows && i<s.length();i++){
            finalStr[i][0] = s.substring(i,i+1);
            firstColCount++;
        }
        //根据第一列。计算第一列中每一行后面的值。
        for(int j=0;j<firstColCount;j++){
            //下个元素在字符串的原始位置。如9在位置8.
            //如果是竖向的最后一个，则在原字符串的初始位置=第一列的元素个数-1
            int nextIndex = (j==firstColCount-1)?(firstColCount-1):0;
            int nextNewIndex = 0;//下个元素在新二维数组的位置。
            int count = 0;//在一行中，第几个元素了。因为一行中两两元素的跨度不同
            //判断每行下一个值是否在字符串范围内。
            while(nextIndex+1<=s.length()){
                //首尾两行特殊。直接+(row-1)
                if(j==0 || j==firstColCount-1){
                    nextIndex += (numRows-1)*2;
                    nextNewIndex += numRows-1;
                }else{//中间行
                    if(count%2==0){
                        nextIndex += ((count==0)?j:0)+(numRows-1-j)*2;//只对每行的第二个元素加行号，其他元素不加
                        nextNewIndex += numRows-1-j;
                    }else{
                        nextIndex = nextIndex+2*j;//(其实就=2numRows-2+j)
                        nextNewIndex = nextNewIndex+j;
                    }

                }
                if(nextIndex<s.length() && nextNewIndex<colNum){
                    finalStr[j][nextNewIndex] = s.substring (nextIndex,nextIndex+1);
                    count++;
                }else{
                    break;
                }

            }
        }
        //数组转字符串
        StringBuilder sb = new StringBuilder ();
        for(int p=0;p<finalStr.length;p++){
            for(int q=0;q<finalStr[p].length;q++){
                if(finalStr[p][q] !=null){
                    sb.append (finalStr[p][q]);
                }
            }
        }
        System.out.println("原字符串："+s+"，行数："+numRows+",Z字型转换后："+sb);
        return sb.toString ();
    }

    //根据字符串总数和行数，计算总列数。列数作为二维数组的初始化大小。
    private static int getColNum(int len, int numRows){
        //先转成float类型，再做除法，才能得到浮点型结果。
        float k =(float)(len-numRows)/(float)(numRows-1);
        int kMod = (len-numRows)%(numRows-1);
        double floor = Math.floor(k);
        double ceil = Math.ceil(k);
        //如果相等，表示正好是竖向的第一个元素。此时ceil+1方便下面计算colNum
        if(k == ceil){
            ceil++;
        }

        int section = (int) (ceil/2);
        int sectionMod = (int) (ceil%2);
        int colNum = 1;

        //当余数为0或k本身就是整数时，表示在竖向向下的列。
        if(sectionMod==0) {
            //根据K的值。有1+k/2个竖向和k/2个斜的(而每个斜的占row-2列)。即总列数=1+k/2+k*(row-2)/2=1+k*row/2-k/2
            colNum = 1+(int)ceil*numRows/2-(int)ceil/2;
        }else {//斜向上时，列数=前面完整的列数+kMod。而，前面完整的列数=3倍行数减去收尾共用的2个
            //斜向上时，列数=前一轮的列数+此处的余数。而前一轮的列数=(用当前字符串长度-行数)和numRows计算而来。
            colNum = getColNum(len-numRows, numRows)+kMod;
        }
        System.out.println("原串总数:"+len+",总行数："+numRows+"总列数："+colNum);
        return colNum;
    }

    /**
     * (6.2)Z字形变换
     * 推荐
        * 因为最终是按行输出，所以可遍历原字符串把字符分到N行，计算每行的子字符串，最后把所有行子字符串拼接即可。
        * 其中每行字符串的计算：
        *    从上到下，先累加，加到第一行或最后一行时，再反向加到行中。
        * 如：
        * 先(逻辑是行号逐渐增大)
        * 行1：1
        * 行2：2
        * 行3：3
        * 行4：4
        * 再(反向.先算第3行，再算第2行。即反转逻辑是行号逐渐减小)
        * 行3：35
        * 行2：26
        * 行1：17
        * 此时行4：4
        * 再(再反向.先算第2行，再算第3行。即反转逻辑是行号逐渐增大)
        * 行2：268
        * 行3：359
        * 行4：410
        * 此时行1：17
        * 时间复杂度：O(n).遍历一遍。
        * 空间复杂度：O(numRows).有几行，就需要几个储存。
     * @param s
     * @param numRows
     * @return
     */
    public static String convert2(String s, int numRows) {
        List<StringBuilder> rowList = new ArrayList<StringBuilder> (5);
        for(int i=0;i<numRows;i++) {
            rowList.add(new StringBuilder());
        }
        //行号
        int i=0;
        int forwardFlag = -1;
        for(char item: s.toCharArray()) {
            rowList.get(i).append(item);
            if(i==0 || i%(numRows-1)==0) {
                //反转。即行号从逐渐增大，改成逐渐减小。或者反之。
                forwardFlag = -forwardFlag;
            }
            i = i+forwardFlag;
        }
        StringBuilder sb = new StringBuilder();
        for(int k=0;k<rowList.size();k++) {
            sb.append(rowList.get(k));
        }
        System.out.println("Z字形转换2。 原串："+s+",总行数："+numRows+"。变换后："+sb.toString());

//        stream 遍历（效果同for）
//        StringBuilder sb2 = new StringBuilder();
//        rowList.stream().forEach(e->sb2.append(e));
//        System.out.println("Z字形转换2。 原串："+s+",总行数："+numRows+"。变换后："+sb2.toString());
        return sb.toString();
    }

    /**
     * (7)整数反转
     * 如果反转后整数超过 32 位的有符号整数的范围 [−2的31次方,  2的31次方 − 1. 即-2147483648到2147483647] ，就返回 0
     * 除了符号位，其他位置的数字正好倒序。

     示例 1：
           输入：x = 123
           输出：321

     示例 2：
           输入：x = -123
           输出：-321

     示例 3：
           输入：x = 120
           输出：21

     示例 4：
           输入：x = 0
           输出：0
           分析：
                 先int->String->char[]字符数组.
                 循环数组，把两边的字符互换。最后转成int.
     */
    public static int reverse(int x) {
        String str = String.valueOf(x);
        char[] chars = str.toCharArray();
        int midIndex = chars.length/2;//因为无论是奇数还是偶数个，正好都是交换中间哪个数左右的元素。
        char tmp;
        for(int i=0;i<midIndex;i++) {
            tmp = chars[i];
            chars[i] = chars[chars.length-i-1];
                chars[chars.length-i-1] = tmp;
        }
        String finalStr = String.valueOf(chars);
        //转换后的数字可能超int范围，用double再判断大小。
        double y;
        //对-123 之后的"321-"处理:去掉最后一位符号位。
        if(x<0) {
            finalStr = finalStr.substring(0, finalStr.length()-1);
            y = -Double.valueOf(finalStr);
        }else {
            y = Double.valueOf(finalStr);
        }
        if(y<Integer.MIN_VALUE || y>Integer.MAX_VALUE) {
            y = 0;
        }
        System.out.println("7. 整数反转。原整数："+x+",反转后="+(int)y);
        return (int)y;
    }


    /**
     * (8)字符串转换整数 (atoi)
     * 注：被花旗面过
     * 请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。

                 函数 myAtoi(string s) 的算法如下：

     读入字符串并丢弃无用的前导空格
                 检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
                 读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。
                 将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 2 开始）。
                 如果整数数超过 32 位有符号整数范围 [−231,  231 − 1] ，需要截断这个整数，使其保持在这个范围内。
                 具体来说，小于 −2的31次方 的整数应该被固定为 −2的31次方 ，大于 2的31次方 − 1 的整数应该被固定为 2的31次方 − 1 。
                 返回整数作为最终结果。
                 注意：

     本题中的空白字符只包括空格字符 ' ' 。
                 除前导空格或数字后的其余字符串外，请勿忽略 任何其他字符。

     如"abc-0123xyz456"-> -123
     "abc0123xyz456"-> 123
     "abc0xyz123xyz456"-> 0

     分析：
       找到第1个非0数字，看前面哪个字符是否是正好或负号。继续往后找，截取后面有其他字符出现时的一整段数字。如-0123.
       判断范围返回。

     时间复杂度：O(n).因为遍历一遍
     空间复杂度：O(1).元素共用一个空间
     */
    public static int myAtoi(String s) {
        //String.split(reg)返回满足正则的字符串数组。""时，都匹配。不用String.toCharArray,因为char比较时，没有contains类似的方法比较。
        String[] strArr = s.split ("");
        StringBuilder sb = new StringBuilder();
        //符号位
        String symbol = "";
        //是否找到第一个数字
        boolean firstNumFlag = false;
        for(String item: strArr) {
            //找第一个数字
            if(isNumber(item) && !firstNumFlag) {
                firstNumFlag = true;
                //判断符号位。非首位是数字时，才判断前面的符号位
                symbol = s.indexOf(item)!=0 &&s.substring(s.indexOf(item)-1,s.indexOf(item)).equals ("-")?"-":"";

                //把先找到的连续0扔掉，且标记找到数字了。否则最后还要额外去掉连续0.
                if(item.equals ("0")){
//                    firstNum = item;
                    continue;
                }

                sb.append (item);
                continue;
            }
            //根据第一个数字，找后面的数字
            if(firstNumFlag){
                if(isNumber(item)){
                    sb.append (item);
                }else{
                    break;
                }
            }
        }
        double doubleNum;
        int finalNum;

        if(symbol.equals ("-")){
            doubleNum = -Double.valueOf(sb.toString ());
        }else if(sb.toString ().equals ("")){
            doubleNum = 0;
        }else{
            doubleNum = Double.valueOf(sb.toString ());
        }
        if(doubleNum>Integer.MAX_VALUE){
            finalNum = Integer.MAX_VALUE;
        }
        if(doubleNum<Integer.MIN_VALUE){
            finalNum = Integer.MIN_VALUE;
        }
        finalNum = (int)doubleNum;


        System.out.println("(8)字符串转换整数. "+s+"，->"+finalNum);
        return (int)finalNum;
    }

    public static boolean isNumber(String s){
        String nums = "0123456789";
        return nums.contains(s);
    }

    /**
     * (9)回文数
     * 判断int数是否回文。
     * 回文：正读和反读是一样的。
     * 如121  回文
     * 1234321  回文
     * 1221 回文
     * -121 不回文
     * -1234321  不回文
     *
     * 分析：
     *  负数有符号，一定不回文。
     *  正数，一次比较两边是否相等。
     *
     * @param x
     * @return
     */
    public static boolean isPalindrome(int x) {
        if(x<0){
            return false;
        }
        String[] strArr = String.valueOf(x).split("");
        int len = strArr.length;
        //比较前一半和后一半。偶数个和奇数个，都是比较中间位置。
        //只是奇数个时，最后是中间和中间自己比。偶数时没有中间数(中间数不在原有数列中)，是虚拟中间数两边的比。
        for(int i=0; i<len/2; i++){
            if(!strArr[i].equals(strArr[len-i-1])){
                return false;
            }
        }
        return true;

    }

    /**
     * (10)正则表达式匹配。
     * 要求：
     * 给定一个字符串s和字符串规律p。
     * p中可能带"."或"*"。
     * 已知：
     *  '.' 匹配任意单个字符
     *  '*' 匹配零个或多个前面的那一个元素
     * 判断s是否完全匹配p。
     * 注：s只包括小写字母a-z，p只包括小写字母和".","*"。即没有括号、特殊字符等特殊情况。
     * 如
     * s:"aa", p:"a" 不匹配，因为不是完全匹配
     * s:"aa", p:"a*" 匹配。因为"a*"表示要匹配a开头且后面有任意多个a都可以的。"aa","aaa","aaaa"等都会匹配。"ab"不会匹配。
     * s:"ab", p:".*" 匹配。因为"."能匹配任意单个字符，所以".*"表示要匹配任意字符出现任意次。
     * s:"abc", p:"ab.xyz*" 。 因为"ab.xyz*"表示要匹配ab开头第三个字符是任意，且后面接xy字符，最后有任意多个z字符的。
     *  "abvxyzzzzzz"，"abkxyz"会匹配到,"abcxyp"不匹配。
     *
     *  分析：
     *      P="a*abxyz.*def..pqr",因为*和前面一个字符特殊，根据"x*"把字符串划分成多个部分。
     *
     *      如划分成"(a*)(abxyz)(.*)(def..pqr)"4个部分，可匹配到"(aaaaa任意多个a)(abxyz)(abcdefghi任意多个任意字符)(def..pqr)"
     *      先匹配最严的即：
     *      第2部分：(abxyz)
     *      第4部分(def..pqr)。其中(def..pqr)中的".."只表示位置，不用去匹配，因为"."匹配任意1个字符。所以拆成"def"和"pqr"分别匹配。
     *          判断第2、4部分是否按顺序匹配。
     *      再匹配
     *      第1部分："(aaaaa任意多个a)"
     *      第3部分："(abcdefghi任意多个任意字符)"
     *
     *具体方案：
     * 对s="pqraaaabxyzkkkdefvwwpqr",p="pqra*abxyz.*def...pqr"
     * 根据"x*",把规则p分开成几个部分。pNormalMatchParts=["pqr", "abxyz", "def...pqr"]
     *
     * 根据 pNormalMatchParts ，计算P中普通字符位置：
     * pNormalIndexs=[(0到2), (5到9), (12到20)]，
     * 进而得到P中通配符位置，为中间间隔：
     * pWildIndexs=[(3到4), (10到11)]；
     *
     * 根据 pNormalMatchParts ，从头到尾，计算S中普通字符位置：
     * sNormalIndexs=[(0到2), (6到10), (14到22)]，（如果sNormalIndexs中的元素位置后面比前面小，则直接返回不匹配）
     * 进而得到S中通配符位置，为中间间隔：
     * sWildIndexs=[(3到5), (11到13)].
     *
     * 先比较pNormalIndexs和sNormalIndexs位置的字符是否匹配，
     * 再比较pWildIndexs和sWildIndexs位置的字符是否匹配。
     *
     *
     *
     * @param s
     * @param p
     * @return
     */
    public static boolean isMatch(String s, String p) {
        //非"x*部分。即非通配符匹配部分。"按照符号"x*"分割。String.split(reg)  "*"号是正则字符，要转义成"\\*"。所以"x*",用".\\*"表示
        //"a*abxyz.*def...pqr"->["", "abxyz", "def...pqr"]
        //"pqra*abxyz.*def...pqr"->["pqr", "abxyz", "def...pqr"]
        String[] pNormalMatchParts = p.split (".\\*");
        String[] sNormalMatchParts = new String[pNormalMatchParts.length];
        //"x*"部分。即通配符匹配部分。
        String[] pWildMatchParts = new String[pNormalMatchParts.length-1];
        String[] sWildMatchParts = new String[sNormalMatchParts.length-1];
        //上一次普通部分的位置。用于计算后面通配符部分位置。
        int prePStartIndex = 0;
        int prePEndIndex = 0;
        int preSStartIndex = 0;
        int preSEndIndex = 0;
        //
//        pWildIndexs
//        sWildIndexs
        //遍历字面值部分去匹配时，动态找到缺失的通配符部分。后者夹在前者之间。
        for(int i=0; i<pNormalMatchParts.length; i++){
//            if(pNormalMatchParts[i].equals ("")){//在两端有"x*"或者在中间有连续"x*"，视作这部分匹配。接着下一个就是夹在中间的"x*"
//                continue;
//            }

            //根据P中普通部分，找到P中通配符部分和S中普通部分。TODO 对"def...pqr"情况处理。
            int pNormalStartIndex = p.indexOf(pNormalMatchParts[i]);
            int pNormalEndIndex = pNormalStartIndex+pNormalMatchParts[i].length()-1;


            //P中有"."时，从之前S已经匹配过的普通部分，向后去找S中对应的普通部分。这里是从"kkkdefvwwpqr"，即S的第11位置去匹配P的"def...pqr"
            //方法是，只要非"."号的多个字符一致，且非"."号的多个字符之间的位置差相同，则去拿到S中的对应普通部分。如
            //
            //特别的，对P中普通部分是最后一个时，如pNormalMatchParts=["pqr", "abxyz", "def...pqr"]中的"def...pqr"，要截取S中严格的尾部
            //s="pqraaaabxyzkkkdefvwwpqr" 中的"defvwwpqr"，且"pqr"后面不能再有字符了(即要完全匹配)。
            //对P中普通部分不是最后一个时，如pNormalMatchParts=["pqr", "abxyz", "def...pqr", "abc"]中的"def...pqr"，要截取S中严格的尾部
            //s="pqraaaabxyzkkkdefvwwpqrjjjjj" 中的"defvwwpqr"，且"pqr"后面不能再有字符了(即要完全匹配)。
            int sNormalStartIndex = 0;//s.indexOf(pNormalMatchParts[i]);
            int sNormalEndIndex = 0;//sNormalStartIndex+pNormalMatchParts[i].length()-1;
//            sNormalMatchParts[i] = s.substring(sNormalStartIndex, sNormalEndIndex+1);
//            System.out.println("sNormalMatchParts[i]="+sNormalMatchParts[i]+",pNormalMatchParts[i]="+pNormalMatchParts[i]+",i="+i);

            /*
            p="..def...pqr.abc.." ->["","","def","","","pqr","abc"]
            p="..def...pqr.abc.." ->["","def","pqr","abc"]
            s="xxdefxxxpqrxabcxx"


             */

            if(pNormalMatchParts[i].contains(".")) {
                //取出不带"."的各个部分。split("\\.+")表示按照至少1个或多个"."号去分隔。p="..def...pqr.abc.." ->["","def","pqr","abc"]。
                //好让其到原P中找到位置(indexOf)，继而判断非"."字符之间的位置差。
                String[] dotArr = pNormalMatchParts[i].split("\\.+").;
                int pSubNormalIndex = 0;
                int sSubSNormalIndex = 0;
                int prePSubNormalIndex = 0;
                int preSSubNormalIndex = 0;
                int deltaIndex = 0;
                boolean firstFlag = true;

                for(int k=0; k<dotArr.length; k++){
//                    //TODO 先不管第一个是""的情况
//                    if(dotArr[k].equals ("")){
//                        continue;
//                    }else{
//                        firstFlag = false;
//                    }

                    pSubNormalIndex = pNormalMatchParts[i].indexOf (dotArr[k]);
                    sSubSNormalIndex = s.indexOf(dotArr[k], preSEndIndex);//从S上一个普通部分之后找
                    if(k>0){
                        deltaIndex = pSubNormalIndex-prePSubNormalIndex;
                        if(s.indexOf(sSubSNormalIndex, preSEndIndex) != sSubSNormalIndex+deltaIndex ){
                            return false;
                        }else{
                            //nothing
                        }
                    }




                    prePSubNormalIndex = pSubNormalIndex;
                    preSSubNormalIndex = sSubSNormalIndex;
                }
                int pp = 0;

                //TODO 最后设置带"."号子串在原串的位置。
                sNormalStartIndex = 0;//="def"在S中的位置往前找两个".."
                sNormalEndIndex = 0;//="abc"在S中的位置往后找两个".."
            }



            //普通位置不对(如找不到或下一个普通位置在上衣普通位置之前)，则不匹配
            if(sNormalStartIndex < 0 || sNormalStartIndex <= preSStartIndex){//TODO test case要覆盖。
                return false;
            }

            //比较普通部分。含“.”的逐个字符比较。否则字符串整体比较。
            if(pNormalMatchParts[i].contains(".")) {
                int matchIndex = 0;
                while(matchIndex<pNormalMatchParts[i].length()) {
                    if(pNormalMatchParts[i].substring(matchIndex, matchIndex+1).equals(".")) {
                        matchIndex++;
                        continue;
                    }
                    if(!pNormalMatchParts[i].substring(matchIndex, matchIndex+1).equals(sNormalMatchParts[i].substring(matchIndex, matchIndex+1))) {
                        return false;
                    }
                    matchIndex++;
                }
            }else {
                if(!sNormalMatchParts[i].equals(pNormalMatchParts[i])) {
                    return false;
                }
            }


            //得到通配符部分并比较
            if (i > 0) {
                pWildMatchParts[i-1] = p.substring (prePEndIndex + 1, pNormalStartIndex);
                sWildMatchParts[i-1] = s.substring (preSEndIndex + 1, sNormalStartIndex);
                System.out.println("pWildMatchParts[i-1]="+pWildMatchParts[i-1]+",i="+i);
                System.out.println("sWildMatchParts[i-1]="+sWildMatchParts[i-1]+",i="+i);
                String wildChar = pWildMatchParts[i-1].substring (0, 1);//第一个字符就是要通配的。
                if (wildChar.equals(".")) {//".*"和任何都匹配
                    //nothing
                } else {//"a*",只匹配"aaaaaaa"情况.即全是“a”才匹配。
                    if (sWildMatchParts[i-1].split(wildChar).length != 0) {
                        return false;
                    }
                }
            }

//            //TODO 先匹配普通部分(如先"abxyz"，"def...pqr" )；都OK了，再匹配通配符部分。否则先通配符匹配会匹配很长，可能导致本来全局匹配的不匹配了。
//            String[] normalParts = pNormalMatchParts[i].split("\\.");//"def...pqr"->["def", "", "", "pqr"]
//            System.out.println("(10)正则表达式匹配。"+s+"->normalParts:"+normalParts);

            prePStartIndex = pNormalStartIndex;
            prePEndIndex = pNormalEndIndex;
            preSStartIndex = sNormalStartIndex;
            preSEndIndex = sNormalEndIndex;



        }

        System.out.println("(10)正则表达式匹配。"+s+"->"+pNormalMatchParts);
        return true;
    }

    /**
     * (10.2)正则表达式匹配。
     * TODO 直接用正则的方式判定
     * @param s
     * @param p
     * @return
     */
    public static boolean isMatch2(String s, String p) {
        return true;
    }






}
class ListNode {
    int val;
    ListNode next;
    ListNode(ListNode listNode) {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }   

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }
}