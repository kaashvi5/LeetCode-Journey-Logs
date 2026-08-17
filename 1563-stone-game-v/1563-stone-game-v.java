class Solution {
    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        long[] prefix = new long[n + 1];
        for (int i = 0; i < n; i++) prefix[i + 1] = prefix[i] + stoneValue[i];

        int[][] dp = new int[n][n];
        long[][] maxL = new long[n][n]; // max_{k=i..j} (dp[i][k] + sum(i,k)), fixed i
        long[][] maxR = new long[n][n]; // max_{k=i..j} (dp[k][j] + sum(k,j)), fixed j

        for (int i = 0; i < n; i++) {
            dp[i][i] = 0;
            maxL[i][i] = stoneValue[i];
            maxR[i][i] = stoneValue[i];
        }

        for (int len = 1; len < n; len++) {
            for (int i = 0; i + len < n; i++) {
                int j = i + len;
                long total = prefix[j + 1] - prefix[i];

                // largest k in [i, j-1] with 2*sum(i,k) <= total
                int lo = i, hi = j - 1, k1 = i - 1;
                while (lo <= hi) {
                    int mid = (lo + hi) / 2;
                    long leftSum = prefix[mid + 1] - prefix[i];
                    if (2 * leftSum <= total) {
                        k1 = mid;
                        lo = mid + 1;
                    } else {
                        hi = mid - 1;
                    }
                }

                long best = 0;
                if (k1 >= i) {
                    best = Math.max(best, maxL[i][k1]);
                }

                int zoneBStart;
                if (k1 >= i && 2 * (prefix[k1 + 1] - prefix[i]) == total) {
                    zoneBStart = k1 + 1; // equal case: also consider right side
                } else {
                    zoneBStart = k1 + 2;
                }
                if (zoneBStart <= j) {
                    best = Math.max(best, maxR[zoneBStart][j]);
                }

                dp[i][j] = (int) best;

                maxL[i][j] = Math.max(maxL[i][j - 1], dp[i][j] + total);
                maxR[i][j] = Math.max(maxR[i + 1][j], dp[i][j] + total);
            }
        }

        return dp[0][n - 1];
    }
}