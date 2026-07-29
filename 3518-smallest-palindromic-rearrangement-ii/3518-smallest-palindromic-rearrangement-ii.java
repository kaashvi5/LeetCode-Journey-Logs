class Solution {
    public String smallestPalindrome(String s, int k) {
        int n = s.length();
        int[] count = new int[26];
        for (char ch : s.toCharArray()) count[ch - 'a']++;

        int[] halfCounts = new int[26];
        char mid = 0;
        boolean hasMid = false;
        for (int c = 0; c < 26; c++) {
            halfCounts[c] = count[c] / 2;
            if (count[c] % 2 == 1) {
                mid = (char) ('a' + c);
                hasMid = true;
            }
        }
        int halfLen = n / 2;

        long kk = k;
        long total = permCount(halfCounts, halfLen, kk);
        if (total < kk) return "";

        char[] half = new char[halfLen];
        int[] cur = halfCounts.clone();
        for (int pos = 0; pos < halfLen; pos++) {
            for (int c = 0; c < 26; c++) {
                if (cur[c] == 0) continue;
                cur[c]--;
                long cnt = permCount(cur, halfLen - pos - 1, kk);
                if (cnt >= kk) {
                    half[pos] = (char) ('a' + c);
                    break;
                } else {
                    kk -= cnt;
                    cur[c]++;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(half);
        if (hasMid) sb.append(mid);
        sb.append(new StringBuilder(new String(half)).reverse());
        return sb.toString();
    }

    // number of distinct permutations of the multiset described by counts,
    // total length L, capped at `cap` (returns exact value if <= cap, else > cap)
    private long permCount(int[] counts, int L, long cap) {
        long result = 1;
        int remaining = L;
        for (int c = 0; c < 26; c++) {
            if (counts[c] > 0) {
                long cval = C(remaining, counts[c], cap);
                result *= cval;
                if (result > cap) return cap + 1;
                remaining -= counts[c];
            }
        }
        return result;
    }

    // binomial coefficient C(n, r), capped
    private long C(int n, int r, long cap) {
        if (r < 0 || r > n) return 0;
        if (r == 0 || r == n) return 1;
        r = Math.min(r, n - r);
        long res = 1;
        for (int i = 1; i <= r; i++) {
            res = res * (n - r + i) / i;
            if (res > cap) return cap + 1;
        }
        return res;
    }
}