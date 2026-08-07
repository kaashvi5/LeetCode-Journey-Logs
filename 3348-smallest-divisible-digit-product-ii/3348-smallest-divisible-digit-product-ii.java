class Solution {
    int A, B;              // bounds for the (a,b) table
    int[][] f;             // f[a][b] = min digits to cover 2^a * 3^b
    int[][] EXP;            // EXP[digit] = {expOf2, expOf3, expOf5, expOf7}

    public String smallestNumber(String num, long t) {
        // ---- factor t into 2^a * 3^b * 5^c * 7^d * r ----
        long temp = t;
        int a = 0, b = 0, c = 0, d = 0;
        while (temp % 2 == 0) { temp /= 2; a++; }
        while (temp % 3 == 0) { temp /= 3; b++; }
        while (temp % 5 == 0) { temp /= 5; c++; }
        while (temp % 7 == 0) { temp /= 7; d++; }
        if (temp != 1) return "-1"; // impossible for ANY zero-free number

        EXP = new int[][] {
            {0,0,0,0}, // 0 (unused)
            {0,0,0,0}, // 1
            {1,0,0,0}, // 2
            {0,1,0,0}, // 3
            {2,0,0,0}, // 4
            {0,0,1,0}, // 5
            {1,1,0,0}, // 6
            {0,0,0,1}, // 7
            {3,0,0,0}, // 8
            {0,2,0,0}, // 9
        };

        A = a; B = b;
        f = new int[A + 1][B + 1];
        int[][] contrib = {{1,0},{2,0},{3,0},{0,1},{0,2},{1,1}};
        for (int aa = 0; aa <= A; aa++) {
            for (int bb = 0; bb <= B; bb++) {
                if (aa == 0 && bb == 0) { f[aa][bb] = 0; continue; }
                int best = Integer.MAX_VALUE;
                for (int[] cd : contrib) {
                    int na = Math.max(0, aa - cd[0]);
                    int nb = Math.max(0, bb - cd[1]);
                    if (na == aa && nb == bb) continue;
                    best = Math.min(best, f[na][nb] + 1);
                }
                f[aa][bb] = best;
            }
        }

        int L = num.length();
        char[] arr = num.toCharArray();
        int firstZero = L;
        for (int i = 0; i < L; i++) {
            if (arr[i] == '0') { firstZero = i; break; }
        }

        int[] pa = new int[firstZero + 1];
        int[] pb = new int[firstZero + 1];
        int[] pc = new int[firstZero + 1];
        int[] pd = new int[firstZero + 1];
        for (int i = 0; i < firstZero; i++) {
            int dig = arr[i] - '0';
            pa[i + 1] = pa[i] + EXP[dig][0];
            pb[i + 1] = pb[i] + EXP[dig][1];
            pc[i + 1] = pc[i] + EXP[dig][2];
            pd[i + 1] = pd[i] + EXP[dig][3];
        }

        // Case 0: num itself works
        if (firstZero == L) {
            if (pa[L] >= a && pb[L] >= b && pc[L] >= c && pd[L] >= d) {
                return num;
            }
        }

        int upper = (firstZero < L) ? firstZero : L - 1;
        for (int i = upper; i >= 0; i--) {
            int startDigit = (arr[i] - '0') + 1;
            for (int dgt = startDigit; dgt <= 9; dgt++) {
                int ra = a - pa[i] - EXP[dgt][0];
                int rb = b - pb[i] - EXP[dgt][1];
                int rc = c - pc[i] - EXP[dgt][2];
                int rd = d - pd[i] - EXP[dgt][3];
                int remLen = L - 1 - i;
                if (remLen >= minDigits(ra, rb, rc, rd)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(arr, 0, i);
                    sb.append((char) ('0' + dgt));
                    sb.append(greedyFill(remLen, ra, rb, rc, rd));
                    return sb.toString();
                }
            }
        }

        // No same-length answer -> go longer
        int M = minDigits(a, b, c, d);
        int newLen = Math.max(L + 1, M);
        return greedyFill(newLen, a, b, c, d);
    }

    private int minDigits(int ra, int rb, int rc, int rd) {
        if (ra < 0) ra = 0;
        if (rb < 0) rb = 0;
        if (rc < 0) rc = 0;
        if (rd < 0) rd = 0;
        if (ra > A) ra = A;
        if (rb > B) rb = B;
        return f[ra][rb] + rc + rd;
    }

    private String greedyFill(int len, int ra, int rb, int rc, int rd) {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < len; j++) {
            int remLenAfter = len - 1 - j;
            for (int dgt = 1; dgt <= 9; dgt++) {
                int na = ra - EXP[dgt][0];
                int nb = rb - EXP[dgt][1];
                int nc = rc - EXP[dgt][2];
                int nd = rd - EXP[dgt][3];
                if (remLenAfter >= minDigits(na, nb, nc, nd)) {
                    sb.append((char) ('0' + dgt));
                    ra = Math.max(0, na);
                    rb = Math.max(0, nb);
                    rc = Math.max(0, nc);
                    rd = Math.max(0, nd);
                    break;
                }
            }
        }
        return sb.toString();
    }
}