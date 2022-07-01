package algorithm.dynamicprogramming;

public class DP {
    static int nonDPCount=0;
    static int DPCount=0;

    public static void main(String[] args){
        System.out.println("结果："+f(7)+"，共计算几次："+nonDPCount);//运行几次：25，结果13
        System.out.println("结果："+dp(7)+"动态规划共计算几次："+DPCount);//动态规划运行几次：5，结果13

    }
    //递归方式
    public static int f(int x){
        nonDPCount++;
        if(x<0) return 0;
        if(x<=2) return 1;
        return f(x-1)+f(x-2);
    }

    //动态规划方式
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
}
