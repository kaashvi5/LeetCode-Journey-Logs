class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] cnt = new int[26];
        for (char ch : s.toCharArray()) cnt[ch - 'a']++;

        int oddCount = 0, oddChar = -1;
        for (int i = 0; i < 26; i++) {
            if (cnt[i] % 2 != 0) { oddCount++; oddChar = i; }
        }

        boolean hasMid = (n % 2 == 1);
        if (hasMid) { if (oddCount != 1) return ""; }
        else        { if (oddCount != 0) return ""; }

        int[] pairs = new int[26];
        for (int i = 0; i < 26; i++) pairs[i] = cnt[i] / 2;

        int h = n / 2;
        char midChar = hasMid ? (char) ('a' + oddChar) : 0;
        String T1 = target.substring(0, h);

        // ---- Case B: half == T1 exactly ----
        int[] counts = pairs.clone();
        int L = 0;
        for (int i = 0; i < h; i++) {
            int c = T1.charAt(i) - 'a';
            if (counts[c] > 0) { counts[c]--; L++; }
            else break;
        }

        if (L == h) {
            StringBuilder sb = new StringBuilder();
            sb.append(T1);
            if (hasMid) sb.append(midChar);
            sb.append(new StringBuilder(T1).reverse());
            String full = sb.toString();
            if (full.compareTo(target) > 0) return full;
        }

        // ---- Case A: smallest half > T1 ----
        int[] remaining = counts.clone(); // consumed T1[0..L-1]
        int p = L;
        if (L == h) {
            if (h == 0) p = -1;
            else {
                remaining[T1.charAt(h - 1) - 'a']++;
                p = h - 1;
            }
        }

        while (p >= 0) {
            int tc = T1.charAt(p) - 'a';
            int chosen = -1;
            for (int c = tc + 1; c < 26; c++) {
                if (remaining[c] > 0) { chosen = c; break; }
            }
            if (chosen != -1) {
                char[] half = new char[h];
                for (int i = 0; i < p; i++) half[i] = T1.charAt(i);
                half[p] = (char) ('a' + chosen);
                remaining[chosen]--;
                int idx = p + 1;
                for (int c = 0; c < 26; c++) {
                    for (int k = 0; k < remaining[c]; k++) half[idx++] = (char) ('a' + c);
                }
                StringBuilder sb = new StringBuilder();
                sb.append(half);
                if (hasMid) sb.append(midChar);
                sb.append(new StringBuilder(new String(half)).reverse());
                return sb.toString();
            } else {
                if (p == 0) break;
                remaining[T1.charAt(p - 1) - 'a']++;
                p--;
            }
        }

        return "";
    }
}