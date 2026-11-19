import java.util.Arrays;

class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        int[][] cost = new int[n][n];

        for (int len = 2; len <= n; len++) {
            for (int l = 0; l + len <= n; l++) {
                int r = l + len - 1;
                cost[l][r] = getCost(s, l, r);
            }
        }

        int INF = 1000000;
        int[][] dp = new int[k + 1][n + 1];

        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }

        dp[0][0] = 0;

        for (int parts = 1; parts <= k; parts++) {
            for (int i = parts * 2; i <= n; i++) {
                for (int j = (parts - 1) * 2; j < i - 1; j++) {
                    dp[parts][i] = Math.min(
                        dp[parts][i],
                        dp[parts - 1][j] + cost[j][i - 1]
                    );
                }
            }
        }

        return dp[k][n];
    }

    private int getCost(String s, int l, int r) {
        int len = r - l + 1;
        int ans = Integer.MAX_VALUE;

        for (int d = 1; d < len; d++) {
            if (len % d != 0) continue;

            int changes = 0;

            for (int i = 0; i < d; i++) {
                int left = l + i;
                int right = l + i + ((len - 1 - i) / d) * d;

                while (left < right) {
                    if (s.charAt(left) != s.charAt(right)) {
                        changes++;
                    }

                    left += d;
                    right -= d;
                }
            }

            ans = Math.min(ans, changes);
        }

        return ans;
    }
}