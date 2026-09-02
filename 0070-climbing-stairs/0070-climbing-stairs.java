class Solution {
    public int climbStairs(int n) {
        int[] dp=new int[n+1];
        for(int i=0;i<=n;i++){
            dp[i]=-1;
        }
        return combi(n,dp);
    }
    private int combi(int n,int[] dp){
        if(n<=2){
            return n;
        }
        if(dp[n]!=-1){
            return dp[n];
        }
        dp[n]=combi(n-1,dp)+combi(n-2,dp);
        return dp[n];
    }
}