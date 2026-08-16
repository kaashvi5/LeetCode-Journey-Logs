import java.util.List;

class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        // dp array initialized with the last row
        int[] dp = new int[n + 1];
        for (int i = 0; i < triangle.get(n - 1).size(); i++) {
            dp[i] = triangle.get(n - 1).get(i);
        }
        
        // Move upward from second-last row to the top
        for (int row = n - 2; row >= 0; row--) {
            for (int col = 0; col <= row; col++) {
                dp[col] = triangle.get(row).get(col) + Math.min(dp[col], dp[col + 1]);
            }
        }
        
        return dp[0];
    }
}