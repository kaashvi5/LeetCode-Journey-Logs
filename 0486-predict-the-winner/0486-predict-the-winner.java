class Solution {
    public boolean predictTheWinner(int[] nums) {
        int n = nums.length;
        if (n == 1) return true; // trivially player 1 wins/ties
        
        int[][] dp = new int[n][n];
        
        // base case: single element subarrays
        for (int i = 0; i < n; i++) {
            dp[i][i] = nums[i];
        }
        
        // fill dp for increasing subarray lengths
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                dp[i][j] = Math.max(
                    nums[i] - dp[i + 1][j],
                    nums[j] - dp[i][j - 1]
                );
            }
        }
        
        return dp[0][n - 1] >= 0;
    }
}