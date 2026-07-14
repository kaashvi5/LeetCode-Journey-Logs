class Solution {
    public int subsequencePairCount(int[] nums) {
        final int MOD = 1_000_000_007;
        int maxV = 0;
        for (int v : nums) maxV = Math.max(maxV, v);
        
        // dp[g1][g2] = number of ways, g1/g2 = 0 means that subsequence is still empty
        long[][] dp = new long[maxV + 1][maxV + 1];
        dp[0][0] = 1;
        
        for (int v : nums) {
            long[][] ndp = new long[maxV + 1][maxV + 1];
            // copy "skip" case
            for (int g1 = 0; g1 <= maxV; g1++) {
                for (int g2 = 0; g2 <= maxV; g2++) {
                    ndp[g1][g2] = dp[g1][g2];
                }
            }
            // add "assign to seq1" and "assign to seq2" cases
            for (int g1 = 0; g1 <= maxV; g1++) {
                for (int g2 = 0; g2 <= maxV; g2++) {
                    long cnt = dp[g1][g2];
                    if (cnt == 0) continue;
                    
                    int ng1 = gcd(g1, v);
                    ndp[ng1][g2] = (ndp[ng1][g2] + cnt) % MOD;
                    
                    int ng2 = gcd(g2, v);
                    ndp[g1][ng2] = (ndp[g1][ng2] + cnt) % MOD;
                }
            }
            dp = ndp;
        }
        
        long ans = 0;
        for (int g = 1; g <= maxV; g++) {
            ans = (ans + dp[g][g]) % MOD;
        }
        return (int) ans;
    }
    
    private int gcd(int a, int b) {
        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }
}