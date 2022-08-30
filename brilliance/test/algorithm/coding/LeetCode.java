package algorithm.coding;


/**
 * https://leetcode.cn/problemset/all/
 */
public class LeetCode {
    public static void main(String[] args) {

        //找出数列中最大的差值
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

        //两数相加(链表)
        ListNode node1 = new ListNode (2,new ListNode (4,new ListNode (3)));
        ListNode node2 = new ListNode (5,new ListNode (6,new ListNode (4)));
        addTwoNumbers (node1, node2);
        /**
         * output:
         * combined node:7,0,8
         */


    }



    /**
     * 找出数列中最大的差值。注：只能是后面减去前面。
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

    /**两数相加(链表)
     * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
     请你将两个数相加，并以相同形式返回一个表示和的链表。
     你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     * 输入：l1 = [2,4,3], l2 = [5,6,4]
     输出：[7,0,8]
     解释：342 + 465 = 807.
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