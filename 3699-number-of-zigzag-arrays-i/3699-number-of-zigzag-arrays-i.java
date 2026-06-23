class Solution {
    private static final long MOD = 1_000_000_007L;

    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;

        long[] up = new long[m + 1];
        long[] down = new long[m + 1];

        // Length = 2
        for (int x = 1; x <= m; x++) {
            up[x] = x - 1;
            down[x] = m - x;
        }

        for (int len = 3; len <= n; len++) {
            long[] newUp = new long[m + 1];
            long[] newDown = new long[m + 1];

            long[] prefDown = new long[m + 1];
            long[] prefUp = new long[m + 1];

            for (int i = 1; i <= m; i++) {
                prefDown[i] = (prefDown[i - 1] + down[i]) % MOD;
                prefUp[i] = (prefUp[i - 1] + up[i]) % MOD;
            }

            for (int x = 1; x <= m; x++) {
                newUp[x] = prefDown[x - 1];

                long greaterUp = (prefUp[m] - prefUp[x] + MOD) % MOD;
                newDown[x] = greaterUp;
            }

            up = newUp;
            down = newDown;
        }

        long ans = 0;
        for (int x = 1; x <= m; x++) {
            ans = (ans + up[x] + down[x]) % MOD;
        }

        return (int) ans;
    }
}