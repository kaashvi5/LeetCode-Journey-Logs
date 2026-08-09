class Solution {
    public int stoneGameII(int[] piles) {
        int n = piles.length;
        int[] suffixSum = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + piles[i];
        }
        
        // dp[i][M] = max stones the current player can get from piles[i..n-1] given this M
        Integer[][] dp = new Integer[n][n + 1];
        
        return solve(0, 1, suffixSum, dp, n);
    }
    
    private int solve(int i, int M, int[] suffixSum, Integer[][] dp, int n) {
        if (i >= n) return 0;
        
        // If remaining piles can all be taken (i + 2M >= n), take them all
        if (i + 2 * M >= n) {
            return suffixSum[i];
        }
        
        if (dp[i][M] != null) return dp[i][M];
        
        int best = 0;
        for (int X = 1; X <= 2 * M; X++) {
            if (i + X > n) break;
            int newM = Math.max(M, X);
            int opponentGets = solve(i + X, newM, suffixSum, dp, n);
            int myStones = suffixSum[i] - opponentGets;
            best = Math.max(best, myStones);
        }
        
        dp[i][M] = best;
        return best;
    }
}