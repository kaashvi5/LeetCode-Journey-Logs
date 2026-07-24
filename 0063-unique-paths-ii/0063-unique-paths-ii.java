class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        
        int[] dp = new int[n];
        dp[0] = (obstacleGrid[0][0] == 1) ? 0 : 1;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[j] = 0; // obstacle blocks this cell entirely
                } else if (j > 0) {
                    dp[j] += dp[j - 1]; // add ways from left (dp[j] already holds "from above")
                }
                // if j == 0 and no obstacle, dp[j] stays as is (only from above)
            }
        }
        
        return dp[n - 1];
    }
}