class Solution {
    static final long MOD = 1_000_000_007L;

    private long[][] multiply(long[][] A, long[][] B) {
        int n = A.length;
        long[][] C = new long[n][n];

        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                if (A[i][k] == 0) continue;

                long val = A[i][k];

                for (int j = 0; j < n; j++) {
                    if (B[k][j] == 0) continue;

                    C[i][j] = (C[i][j] + val * B[k][j]) % MOD;
                }
            }
        }
        return C;
    }

    private long[][] power(long[][] base, long exp) {
        int n = base.length;

        long[][] res = new long[n][n];
        for (int i = 0; i < n; i++) {
            res[i][i] = 1;
        }

        while (exp > 0) {
            if ((exp & 1) == 1) {
                res = multiply(res, base);
            }

            base = multiply(base, base);
            exp >>= 1;
        }

        return res;
    }

    private long[] multiplyMatVec(long[][] A, long[] v) {
        int n = A.length;
        long[] res = new long[n];

        for (int i = 0; i < n; i++) {
            long cur = 0;

            for (int j = 0; j < n; j++) {
                cur = (cur + A[i][j] * v[j]) % MOD;
            }

            res[i] = cur;
        }

        return res;
    }

    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;
        int size = 2 * m;

        long[][] T = new long[size][size];

        // U' = A * D
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < j; i++) {
                T[j][m + i] = 1;
            }
        }

        // D' = B * U
        for (int j = 0; j < m; j++) {
            for (int i = j + 1; i < m; i++) {
                T[m + j][i] = 1;
            }
        }

        long[] V2 = new long[size];

        for (int j = 0; j < m; j++) {
            V2[j] = j;              // U2[j]
            V2[m + j] = m - 1 - j; // D2[j]
        }

        long[][] P = power(T, n - 2L);
        long[] Vn = multiplyMatVec(P, V2);

        long ans = 0;
        for (long x : Vn) {
            ans = (ans + x) % MOD;
        }

        return (int) ans;
    }
}