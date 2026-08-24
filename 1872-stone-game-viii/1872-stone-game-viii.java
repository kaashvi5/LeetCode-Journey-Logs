class Solution {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;

        // Build prefix sums
        long[] prefix = new long[n];
        prefix[0] = stones[0];

        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + stones[i];
        }

        // dp[n - 2]:
        // When two stones remain, the current player must merge both.
        long[] dp = new long[n];
        dp[n - 2] = prefix[n - 1];

        // Compute DP from right to left
        for (int i = n - 3; i >= 0; i--) {
            dp[i] = Math.max(dp[i + 1], prefix[i + 1] - dp[i + 1]);
        }

        return (int) dp[0];
    }
}