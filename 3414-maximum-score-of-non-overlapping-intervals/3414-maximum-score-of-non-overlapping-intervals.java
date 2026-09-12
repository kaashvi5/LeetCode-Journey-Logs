import java.util.*;

class Solution {

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();

        // [start, end, weight, originalIndex]
        int[][] arr = new int[n][4];

        for (int i = 0; i < n; i++) {
            arr[i][0] = intervals.get(i).get(0);
            arr[i][1] = intervals.get(i).get(1);
            arr[i][2] = intervals.get(i).get(2);
            arr[i][3] = i;
        }

        // Sort by start time
        Arrays.sort(arr, (a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });

        // dp[i][k] = best answer starting from i, choosing at most k intervals
        long[][] dp = new long[n + 1][5];
        List<Integer>[][] best = new ArrayList[n + 1][5];

        for (int i = n - 1; i >= 0; i--) {
            int next = findNext(arr, i);

            for (int k = 1; k <= 4; k++) {
                // Skip current interval
                long skip = dp[i + 1][k];

                // Take current interval
                long take = arr[i][2] + dp[next][k - 1];

                if (take > skip) {
                    dp[i][k] = take;
                    best[i][k] = new ArrayList<>();

                    best[i][k].add(arr[i][3]);

                    if (k > 1 && best[next][k - 1] != null)
                        best[i][k].addAll(best[next][k - 1]);

                } else if (take < skip) {
                    dp[i][k] = skip;
                    best[i][k] = best[i + 1][k];

                } else {
                    // Same score → choose lexicographically smaller indices
                    List<Integer> takeList = new ArrayList<>();
                    takeList.add(arr[i][3]);

                    if (k > 1 && best[next][k - 1] != null)
                        takeList.addAll(best[next][k - 1]);

                    List<Integer> skipList = best[i + 1][k];

                    Collections.sort(takeList);

                    if (skipList == null ||
                        compare(takeList, skipList) < 0) {
                        best[i][k] = takeList;
                    } else {
                        best[i][k] = skipList;
                    }

                    dp[i][k] = take;
                }
            }
        }

        List<Integer> ans = best[0][4];
        Collections.sort(ans);

        int[] result = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++)
            result[i] = ans.get(i);

        return result;
    }

    // First interval whose start > current end
    private int findNext(int[][] arr, int i) {
        int lo = i + 1;
        int hi = arr.length;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (arr[mid][0] > arr[i][1])
                hi = mid;
            else
                lo = mid + 1;
        }

        return lo;
    }

    private int compare(List<Integer> a, List<Integer> b) {
        int n = Math.min(a.size(), b.size());

        for (int i = 0; i < n; i++) {
            if (!a.get(i).equals(b.get(i)))
                return Integer.compare(a.get(i), b.get(i));
        }

        return Integer.compare(a.size(), b.size());
    }
}