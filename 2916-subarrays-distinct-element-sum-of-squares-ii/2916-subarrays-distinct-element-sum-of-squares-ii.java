class Solution {
    static final long MOD = 1_000_000_007L;
    long[] sum, sq, lazy;

    public int sumCounts(int[] nums) {
        int n = nums.length;
        sum = new long[4 * n];
        sq = new long[4 * n];
        lazy = new long[4 * n];

        int[] last = new int[100001];
        java.util.Arrays.fill(last, -1);

        long ans = 0;
        for (int i = 0; i < n; i++) {
            int v = nums[i];
            update(1, 0, n - 1, last[v] + 1, i);
            last[v] = i;
            ans = (ans + sq[1]) % MOD;
        }
        return (int) ans;
    }

    // add 1 to every element in [l, r]
    private void update(int node, int lo, int hi, int l, int r) {
        if (l <= lo && hi <= r) {
            apply(node, hi - lo + 1, 1);
            return;
        }
        pushDown(node, lo, hi);
        int mid = (lo + hi) >>> 1;
        if (l <= mid) update(node * 2, lo, mid, l, r);
        if (r > mid) update(node * 2 + 1, mid + 1, hi, l, r);
        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
        sq[node] = (sq[node * 2] + sq[node * 2 + 1]) % MOD;
    }

    private void apply(int node, int len, long v) {
        // (x + v)^2 = x^2 + 2vx + v^2, summed over len elements
        sq[node] = (sq[node] + 2 * v % MOD * sum[node] + v * v % MOD * len) % MOD;
        sum[node] = (sum[node] + v * len) % MOD;
        lazy[node] += v;
    }

    private void pushDown(int node, int lo, int hi) {
        if (lazy[node] != 0) {
            int mid = (lo + hi) >>> 1;
            apply(node * 2, mid - lo + 1, lazy[node]);
            apply(node * 2 + 1, hi - mid, lazy[node]);
            lazy[node] = 0;
        }
    }
}