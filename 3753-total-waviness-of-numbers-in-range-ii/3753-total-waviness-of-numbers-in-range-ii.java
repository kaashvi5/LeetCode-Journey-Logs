class Solution {

    static class Pair {
        long cnt, wav;

        Pair(long cnt, long wav) {
            this.cnt = cnt;
            this.wav = wav;
        }
    }

    private char[] digits;
    private Pair[][][][][] memo;

    public long totalWaviness(long num1, long num2) {
        return solve(num2) - solve(num1 - 1);
    }

    private long solve(long n) {
        if (n < 0) return 0;

        digits = String.valueOf(n).toCharArray();

        memo = new Pair[17][11][11][2][2];

        return dfs(0, 10, 10, 1, 0).wav;
    }

    private Pair dfs(int pos, int prev1, int prev2,
                     int tight, int started) {

        if (pos == digits.length) {
            return new Pair(1, 0);
        }

        if (tight == 0 &&
            memo[pos][prev1][prev2][started][0] != null) {
            return memo[pos][prev1][prev2][started][0];
        }

        int limit = tight == 1 ? digits[pos] - '0' : 9;

        long totalCnt = 0;
        long totalWav = 0;

        for (int d = 0; d <= limit; d++) {

            int ntight = (tight == 1 && d == limit) ? 1 : 0;

            if (started == 0 && d == 0) {
                Pair nxt = dfs(pos + 1, 10, 10, ntight, 0);

                totalCnt += nxt.cnt;
                totalWav += nxt.wav;
            } else {

                int add = 0;

                if (prev2 != 10) {
                    if ((prev1 > prev2 && prev1 > d) ||
                        (prev1 < prev2 && prev1 < d)) {
                        add = 1;
                    }
                }

                Pair nxt = dfs(pos + 1, d, prev1, ntight, 1);

                totalCnt += nxt.cnt;
                totalWav += nxt.wav + (long) add * nxt.cnt;
            }
        }

        Pair res = new Pair(totalCnt, totalWav);

        if (tight == 0) {
            memo[pos][prev1][prev2][started][0] = res;
        }

        return res;
    }
}