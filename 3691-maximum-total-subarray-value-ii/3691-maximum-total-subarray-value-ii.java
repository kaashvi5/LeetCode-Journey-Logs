import java.util.*;

class Solution {

    // Count subarrays with (max - min) <= v
    private long countLE(int[] a, long v) {
        if (v < 0) return 0;
        int n = a.length, l = 0; long cnt = 0;
        Deque<Integer> mx = new ArrayDeque<>(), mn = new ArrayDeque<>();
        for (int r = 0; r < n; r++) {
            while (!mx.isEmpty() && a[mx.peekLast()] <= a[r]) mx.pollLast();
            while (!mn.isEmpty() && a[mn.peekLast()] >= a[r]) mn.pollLast();
            mx.addLast(r); mn.addLast(r);
            while ((long) a[mx.peekFirst()] - a[mn.peekFirst()] > v) {
                l++;
                if (mx.peekFirst() < l) mx.pollFirst();
                if (mn.peekFirst() < l) mn.pollFirst();
            }
            cnt += r - l + 1;
        }
        return cnt;
    }

    // Sum of window-max over all subarrays with (max - min) <= v
    // Monotonic (val, count) stack tracks: for current window [l..r],
    // runMx = sum of subarray-max for [l..r],[l+1..r],...,[r..r].
    // On left eviction, front entry loses one left boundary: decrement count,
    // subtract its value once from runMx.
    private long sumOfMaxLE(int[] a, long v) {
        if (v < 0) return 0;
        int n = a.length, l = 0; long res = 0, runMx = 0;
        Deque<Integer> smx = new ArrayDeque<>(), smn = new ArrayDeque<>(); // spread check
        Deque<long[]> mxS = new ArrayDeque<>();                               // [val, count]
        for (int r = 0; r < n; r++) {
            // update spread deques
            while (!smx.isEmpty() && a[smx.peekLast()] <= a[r]) smx.pollLast();
            while (!smn.isEmpty() && a[smn.peekLast()] >= a[r]) smn.pollLast();
            smx.addLast(r); smn.addLast(r);
            // push onto sum stack
            long mc = 1;
            while (!mxS.isEmpty() && mxS.peekLast()[0] <= a[r]) {
                long[] t = mxS.pollLast(); runMx -= t[0] * t[1]; mc += t[1];
            }
            mxS.addLast(new long[]{a[r], mc}); runMx += (long) a[r] * mc;
            // shrink left while spread > v
            while ((long) a[smx.peekFirst()] - a[smn.peekFirst()] > v) {
                if (smx.peekFirst() == l) smx.pollFirst();
                if (smn.peekFirst() == l) smn.pollFirst();
                // evict l from sum stack: front entry loses one left boundary
                runMx -= mxS.peekFirst()[0];
                mxS.peekFirst()[1]--;
                if (mxS.peekFirst()[1] == 0) mxS.pollFirst();
                l++;
            }
            res += runMx;
        }
        return res;
    }

    // Sum of window-min over all subarrays with (max - min) <= v
    // Identical logic, flip comparisons for min stack.
    private long sumOfMinLE(int[] a, long v) {
        if (v < 0) return 0;
        int n = a.length, l = 0; long res = 0, runMn = 0;
        Deque<Integer> smx = new ArrayDeque<>(), smn = new ArrayDeque<>();
        Deque<long[]> mnS = new ArrayDeque<>();
        for (int r = 0; r < n; r++) {
            while (!smx.isEmpty() && a[smx.peekLast()] <= a[r]) smx.pollLast();
            while (!smn.isEmpty() && a[smn.peekLast()] >= a[r]) smn.pollLast();
            smx.addLast(r); smn.addLast(r);
            long nc = 1;
            while (!mnS.isEmpty() && mnS.peekLast()[0] >= a[r]) {
                long[] t = mnS.pollLast(); runMn -= t[0] * t[1]; nc += t[1];
            }
            mnS.addLast(new long[]{a[r], nc}); runMn += (long) a[r] * nc;
            while ((long) a[smx.peekFirst()] - a[smn.peekFirst()] > v) {
                if (smx.peekFirst() == l) smx.pollFirst();
                if (smn.peekFirst() == l) smn.pollFirst();
                runMn -= mnS.peekFirst()[0];
                mnS.peekFirst()[1]--;
                if (mnS.peekFirst()[1] == 0) mnS.pollFirst();
                l++;
            }
            res += runMn;
        }
        return res;
    }

    public long maxTotalValue(int[] nums, int k) {
        int n = nums.length;
        long total = (long) n * (n + 1) / 2;

        // Binary search for the largest value vStar such that
        // at least k subarrays have value >= vStar
        int lo = 0, hi = 1_000_000_000;
        while (lo < hi) {
            int mid = lo + (hi - lo + 1) / 2;
            if (total - countLE(nums, mid - 1) >= k) lo = mid;
            else hi = mid - 1;
        }
        long vStar = lo;

        // subarrays with value strictly > vStar: take all of them
        long cntAbove = total - countLE(nums, vStar);
        long sumAbove = (sumOfMaxLE(nums, 1_000_000_000L) - sumOfMinLE(nums, 1_000_000_000L))
                      - (sumOfMaxLE(nums, vStar)            - sumOfMinLE(nums, vStar));

        // fill remaining slots with subarrays of value exactly vStar
        return sumAbove + (k - cntAbove) * vStar;
    }
}