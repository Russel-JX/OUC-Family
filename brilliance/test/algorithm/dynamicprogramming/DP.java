package algorithm.dynamicprogramming;

import java.util.HashSet;
import java.util.Set;

public class DP {
    static int nonDPCount=0;
    static int DPCount=0;

    public static void main(String[] args){
        //斐波那切数列。递归和动态规划比较
//        System.out.println("结果："+f(7)+"，共计算几次："+nonDPCount);//运行几次：25，结果13
//        System.out.println("结果："+dp(7)+"动态规划共计算几次："+DPCount);//动态规划运行几次：5，结果13

        //动态规划找数列中最长的递增子数列的长度
        int[] arr = new int[]{5,7,2,6,10};
        getMaxIncreaseSubList(arr);
        /**
         * output:3
         * 5, tmp=57。新正确组合
         5, tmp=72. 新错误组合，放入错误暂存
         5, tmp=76. 新错误组合，放入错误暂存
         5, tmp=710。新正确组合
         5, 内循环递增序列goodCombination=5710
         5, tmp=52. 新错误组合，放入错误暂存
         5, tmp=56。新正确组合
         5, tmp=610。新正确组合
         5, 内循环递增序列goodCombination=5610
         5, tmp=56已知正确组合，记录
         5, tmp=610已知正确组合，记录
         5, 内循环递增序列goodCombination=5610
         5, tmp=510。新正确组合
         5, 内循环递增序列goodCombination=510
         5, 内循环最大递增序列innerMaxGoodStr=5610
         7, tmp=72已知错误组合，跳过
         7, tmp=76已知错误组合，跳过
         7, tmp=710已知正确组合，记录
         7, 内循环递增序列goodCombination=710
         7, tmp=76已知错误组合，跳过
         7, tmp=710已知正确组合，记录
         7, 内循环递增序列goodCombination=710
         7, tmp=710已知正确组合，记录
         7, 内循环递增序列goodCombination=710
         7, 内循环最大递增序列innerMaxGoodStr=710
         2, tmp=26。新正确组合
         2, tmp=610已知正确组合，记录
         2, 内循环递增序列goodCombination=2610
         2, tmp=210。新正确组合
         2, 内循环递增序列goodCombination=210
         2, 内循环最大递增序列innerMaxGoodStr=2610
         6, tmp=610已知正确组合，记录
         6, 内循环递增序列goodCombination=610
         6, 内循环最大递增序列innerMaxGoodStr=610
         10, 内循环最大递增序列innerMaxGoodStr=
         内循环最大递增序列outterMaxGoodStr=2610
         */

    }
    //斐波那切数列 - 递归方式
    public static int f(int x){
        nonDPCount++;
        if(x<0) return 0;
        if(x<=2) return 1;
        return f(x-1)+f(x-2);
    }

    //斐波那切数列 - 动态规划方式
    public static int dp(int x){

        int[] dp = new int[x+1];
        dp[0]=1;
        dp[1]=1;
        DPCount++;
        DPCount++;
        for(int i=2;i<x;i++){
            DPCount++;
            //当前值的计算，通过前两个值相加。
            //而前两个值被暂存到了上次循环的记过中，不用再次计算。
            dp[i]=dp[i-1]+dp[i-2];
        }
        return dp[x-1];
    }

    /**
     * 动态规划
     * 找数列中最长的递增子数列的长度
     *
     * 把之前计算过的，匹配的组合和不匹配的组合都暂存，每次计算时，先校验一下。减少计算。空间换时间
     * 这里每个元素的计算量少，所以节省的时间不多，如果生产场景对每个元素计算复杂，则此策略会节省大量时间。
     *
     * 例子：5，7，2，6，10
     * 外层
     * 从5开始
     *  内层
     *  第一轮：57比，然后72比，然后76比，然后710比。(因为72是错误组合，所以76比。 57组合放入goodCombinationSet，72组合放入badCombinationSet)
     *  第二轮：52比，然后56比，然后610比(因为52是错误组合，所以76比)
     *  第二轮：56比，然后610比
     * 然后从7开始
     *  第一轮：72比，然后76比，然后710比。(因为72是错误组合，所以76比)
     *  第二轮：76比，然后710比。(因为76是错误组合，所以710比)
     *  第二轮：710比
     * ...
     *
     * @param arr
     * @return
     */
    public static int getMaxIncreaseSubList(int[] arr){
//        int[] arr = new int[]{5,7,2,6,10};
        //暂存子循环中的递增序列.如57，26，56
        String goodCombination;
        //前者<=后者的两两组合的集合。
        Set<String> goodCombinationSet = new HashSet<String>();
        //前者>后者的两两组合的集合. 注：goodCombinationSet和badCombinationSet不是互补关系，因为他们是动态增加的。所以不能只用其中一个
        Set<String> badCombinationSet = new HashSet<String>();
        //外循环最大递增序列的字符串
        String outterMaxGoodStr = "";
        for(int i=0;i<arr.length;i++){
            //内循环最大递增序列的字符串
            String innerMaxGoodStr = "";
            //以5开头。分别以57...;52...;56..;510...各开始1轮遍历。每轮遍历记录好的组合和坏的组合。
            //在以7开头...
            for(int m=i+1;m<arr.length;m++){
                goodCombination = String.valueOf(arr[i]);
                String tmp = "";
                int nextEle = arr[i];
                for(int k=m;k<arr.length;k++){
                    tmp = nextEle + String.valueOf(arr[k]);
                    //根据之前的错误组合，跳过后续所有以此错误组合开头的遍历。如5开头这一轮中，72，76错误，则7开头这一轮，直接跳过72，76.
                    if(badCombinationSet.contains(tmp)){
                        System.out.println(arr[i]+", tmp="+tmp+"已知错误组合，跳过");
                        continue;
                    }
                    if(goodCombinationSet.contains(tmp)){
                        System.out.println(arr[i]+", tmp="+tmp+"已知正确组合，记录");
                        goodCombination += String.valueOf(arr[k]);
                        nextEle = arr[k];
                        continue;
                    }
                    //如果相邻两两元素递增，则下次子循环比较再后面两两元素。如对1，2，3。如果1<=2,则比较 2<=3
                    if(nextEle<=arr[k]){
                        goodCombination += String.valueOf(arr[k]);
                        nextEle = arr[k];
                        goodCombinationSet.add(tmp);
                        System.out.println(arr[i]+", tmp="+tmp+"。新正确组合");
                        continue;
                    }else{
                        badCombinationSet.add(tmp);
                        System.out.println(arr[i]+", tmp="+tmp+". 新错误组合，放入错误暂存");
                    }
                }
                System.out.println(arr[i]+", 内循环递增序列goodCombination="+goodCombination);
                if(goodCombination.length()>=innerMaxGoodStr.length()){
                    innerMaxGoodStr = goodCombination;
                }
            }
            System.out.println(arr[i]+", 内循环最大递增序列innerMaxGoodStr="+innerMaxGoodStr);

            if(innerMaxGoodStr.length()>=outterMaxGoodStr.length()){
                outterMaxGoodStr = innerMaxGoodStr;
            }

        }
        System.out.println("外循环最大递增序列outterMaxGoodStr="+outterMaxGoodStr);
        return outterMaxGoodStr.length();
    }
}
