import java.util.*;

class Solution {

    static final long MOD = 1_000_000_007L;

    static class Node {
        long val;
        int len;

        Node(long val, int len) {
            this.val = val;
            this.len = len;
        }
    }

    Node[] tree;
    long[] pow10;
    int[] pos;
    int[] digit;
    int n;

    public int[] sumAndMultiply(String s, int[][] queries) {

        int m = s.length();

        ArrayList<Integer> positions = new ArrayList<>();
        ArrayList<Integer> digits = new ArrayList<>();

        int[] prefix = new int[m + 1];

        for (int i = 0; i < m; i++) {
            int d = s.charAt(i) - '0';
            prefix[i + 1] = prefix[i];
            if (d != 0) {
                positions.add(i);
                digits.add(d);
                prefix[i + 1] += d;
            }
        }

        n = positions.size();

        pos = new int[n];
        digit = new int[n];

        for (int i = 0; i < n; i++) {
            pos[i] = positions.get(i);
            digit[i] = digits.get(i);
        }

        pow10 = new long[n + 1];
        pow10[0] = 1;
        for (int i = 1; i <= n; i++) {
            pow10[i] = (pow10[i - 1] * 10) % MOD;
        }

        if (n > 0) {
            tree = new Node[4 * n];
            build(1, 0, n - 1);
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0];
            int r = queries[i][1];

            int left = lowerBound(pos, l);
            int right = upperBound(pos, r) - 1;

            if (left > right) {
                ans[i] = 0;
                continue;
            }

            Node res = query(1, 0, n - 1, left, right);

            long sum = prefix[r + 1] - prefix[l];
            ans[i] = (int) ((res.val * sum) % MOD);
        }

        return ans;
    }

    private void build(int idx, int l, int r) {
        if (l == r) {
            tree[idx] = new Node(digit[l], 1);
            return;
        }

        int mid = (l + r) / 2;

        build(idx * 2, l, mid);
        build(idx * 2 + 1, mid + 1, r);

        tree[idx] = merge(tree[idx * 2], tree[idx * 2 + 1]);
    }

    private Node query(int idx, int l, int r, int ql, int qr) {
        if (ql == l && qr == r) {
            return tree[idx];
        }

        int mid = (l + r) / 2;

        if (qr <= mid) {
            return query(idx * 2, l, mid, ql, qr);
        }

        if (ql > mid) {
            return query(idx * 2 + 1, mid + 1, r, ql, qr);
        }

        Node left = query(idx * 2, l, mid, ql, mid);
        Node right = query(idx * 2 + 1, mid + 1, r, mid + 1, qr);

        return merge(left, right);
    }

    private Node merge(Node a, Node b) {
        long value = (a.val * pow10[b.len] + b.val) % MOD;
        return new Node(value, a.len + b.len);
    }

    private int lowerBound(int[] arr, int target) {
        int l = 0, r = arr.length;

        while (l < r) {
            int mid = (l + r) / 2;
            if (arr[mid] < target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }

        return l;
    }

    private int upperBound(int[] arr, int target) {
        int l = 0, r = arr.length;

        while (l < r) {
            int mid = (l + r) / 2;
            if (arr[mid] <= target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }

        return l;
    }
}