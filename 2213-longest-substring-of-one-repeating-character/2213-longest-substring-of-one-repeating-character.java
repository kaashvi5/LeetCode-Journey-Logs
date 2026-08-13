import java.util.*;

class Solution {
    static class Node {
        int len;
        char leftChar, rightChar;
        int prefix, suffix, best;
    }

    private Node[] tree;
    private char[] arr;
    private int n;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        n = s.length();
        arr = s.toCharArray();
        tree = new Node[4 * n];
        build(1, 0, n - 1);

        int k = queryCharacters.length();
        int[] result = new int[k];

        for (int i = 0; i < k; i++) {
            int idx = queryIndices[i];
            char c = queryCharacters.charAt(i);
            if (arr[idx] != c) {
                arr[idx] = c;
                update(1, 0, n - 1, idx);
            }
            result[i] = tree[1].best;
        }

        return result;
    }

    private void build(int node, int l, int r) {
        tree[node] = new Node();
        if (l == r) {
            tree[node].len = 1;
            tree[node].leftChar = arr[l];
            tree[node].rightChar = arr[l];
            tree[node].prefix = 1;
            tree[node].suffix = 1;
            tree[node].best = 1;
            return;
        }
        int mid = (l + r) / 2;
        build(2 * node, l, mid);
        build(2 * node + 1, mid + 1, r);
        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    private void update(int node, int l, int r, int idx) {
        if (l == r) {
            tree[node].leftChar = arr[idx];
            tree[node].rightChar = arr[idx];
            // len, prefix, suffix, best remain 1
            return;
        }
        int mid = (l + r) / 2;
        if (idx <= mid) {
            update(2 * node, l, mid, idx);
        } else {
            update(2 * node + 1, mid + 1, r, idx);
        }
        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    private Node merge(Node left, Node right) {
        Node res = new Node();
        res.len = left.len + right.len;
        res.leftChar = left.leftChar;
        res.rightChar = right.rightChar;

        res.prefix = left.prefix;
        if (left.prefix == left.len && left.rightChar == right.leftChar) {
            res.prefix = left.len + right.prefix;
        }

        res.suffix = right.suffix;
        if (right.suffix == right.len && right.leftChar == left.rightChar) {
            res.suffix = right.len + left.suffix;
        }

        res.best = Math.max(left.best, right.best);
        if (left.rightChar == right.leftChar) {
            res.best = Math.max(res.best, left.suffix + right.prefix);
        }

        return res;
    }
}