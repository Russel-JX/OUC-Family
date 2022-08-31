package algorithm.coding;


/**
 * https://leetcode.cn/problemset/all/
 */
public class LeetCode {
    public static void main(String[] args) {

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
     对xyzbabad,从左到右，循环取每一位的元素。
     比较x和y是否相等，不等(手里有xy)，则比较x和z是否相等，不等(手里有xyz),则比较y和b是否相等，
     不等(手里有xyzb),则比较z和a是否相等，不等(手里有xyzba),则比较b和b是否相等，相等，则有个回文串bab，暂存起来作为目前最长的回文子串，
     继续比较z和a是否相等，不等则目前最长的回文子串是bab，且手里有xyzbab了。
     继续用之前的比较(因为可能存在xyzbabadababzxy,所以继续用xyzbab进行)，比较a和a是否相等，
     相等则又有1个新回文子串aba，比较旧回文bab和新回文aba的长度，取最长的最为最终回文子串。
     循环到最后一个元素结束。




     * @param s
     * @return
     */
    public String longestPalindrome(String s) throws Exception {
        if(s.length ()<2){
            throw new Exception("必须提供不低于2位长度的字符串来计算回文子串！");
        }
//        //字符串转数组。方便后续不用截取子串，而是直接从数组下标取元素。
//        char[] strArr = s.toCharArray();
        //回文子串
        String huiwen = "";
        //遍历了的元素
        String loopedEles = s.substring(0,2);
        for(int i=0;i<s.length-1;i++){
            if(){

            }
        }

        return "";
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