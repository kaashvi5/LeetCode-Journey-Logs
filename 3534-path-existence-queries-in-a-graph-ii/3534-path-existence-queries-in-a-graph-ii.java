import java.util.*;

class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        // 1. Sort node indices by value
        Integer[] orderObj = new Integer[n];
        for (int i = 0; i < n; i++) orderObj[i] = i;
        Arrays.sort(orderObj, (a, b) -> Integer.compare(nums[a], nums[b]));
        int[] order = new int[n];
        int[] sortedVal = new int[n];
        int[] pos = new int[n]; // pos[originalIndex] = sorted position
        for (int i = 0; i < n; i++) {
            order[i] = orderObj[i];
            sortedVal[i] = nums[order[i]];
            pos[order[i]] = i;
        }

        // 2. Two-pointer to compute R[i]
        int[] R = new int[n];
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (j < i) j = i;
            while (j + 1 < n && sortedVal[j + 1] - sortedVal[i] <= maxDiff) j++;
            R[i] = j;
        }

        // 3. Connected components via interval merging
        int[] compId = new int[n];
        int comp = 0;
        int farthest = R[0];
        compId[0] = 0;
        for (int i = 1; i < n; i++) {
            if (i > farthest) {
                comp++;
                farthest = R[i];
            } else {
                farthest = Math.max(farthest, R[i]);
            }
            compId[i] = comp;
        }

        // 4. Binary lifting table
        int LOG = Math.max(1, 32 - Integer.numberOfLeadingZeros(Math.max(n, 1)) + 1);
        int[][] up = new int[LOG][n];
        up[0] = R.clone();
        for (int k = 1; k < LOG; k++) {
            for (int i = 0; i < n; i++) {
                up[k][i] = up[k - 1][up[k - 1][i]];
            }
        }

        // 5. Answer queries
        int m = queries.length;
        int[] ans = new int[m];
        for (int qi = 0; qi < m; qi++) {
            int u = queries[qi][0], v = queries[qi][1];
            int pu = pos[u], pv = pos[v];
            if (pu == pv) {
                ans[qi] = 0;
                continue;
            }
            if (compId[pu] != compId[pv]) {
                ans[qi] = -1;
                continue;
            }
            int p = Math.min(pu, pv), q = Math.max(pu, pv);
            ans[qi] = minSteps(p, q, R, up, LOG);
        }
        return ans;
    }

    private int minSteps(int p, int q, int[] R, int[][] up, int LOG) {
        if (R[p] >= q) return 1;
        int cur = p, hops = 0;
        for (int k = LOG - 1; k >= 0; k--) {
            int nxt = up[k][cur];
            if (nxt < q) {
                cur = nxt;
                hops += (1 << k);
            }
        }
        return hops + 1;
    }
}