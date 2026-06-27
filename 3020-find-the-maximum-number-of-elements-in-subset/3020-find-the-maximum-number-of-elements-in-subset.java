class Solution {
    public int maximumLength(int[] nums) {
        Map<Long, Integer> freq = new HashMap<>();

        for (int x : nums) {
            freq.put((long) x, freq.getOrDefault((long) x, 0) + 1);
        }

        int ans = 1;

        // Special handling for 1
        if (freq.containsKey(1L)) {
            int f = freq.get(1L);
            ans = Math.max(ans, (f % 2 == 1) ? f : f - 1);
        }

        for (long start : freq.keySet()) {
            if (start == 1) continue;

            long cur = start;
            int len = 0;

            while (freq.getOrDefault(cur, 0) >= 2) {
                len += 2;

                if (cur > 1000000000L) break;
                cur = cur * cur;
            }

            if (freq.getOrDefault(cur, 0) >= 1) {
                len += 1;
            } else {
                len -= 1;
            }

            ans = Math.max(ans, len);
        }

        return ans;
    }
}