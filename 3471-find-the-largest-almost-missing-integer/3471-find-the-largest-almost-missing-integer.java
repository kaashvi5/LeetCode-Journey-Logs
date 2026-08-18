import java.util.*;

class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        int ans = -1;

        // check each distinct value
        Set<Integer> seen = new HashSet<>();
        for (int v : nums) {
            if (!seen.add(v)) continue; // already processed this value

            int count = 0;
            for (int start = 0; start + k <= n; start++) {
                boolean present = false;
                for (int j = start; j < start + k; j++) {
                    if (nums[j] == v) {
                        present = true;
                        break;
                    }
                }
                if (present) count++;
                if (count > 1) break; // no need to keep counting
            }

            if (count == 1) {
                ans = Math.max(ans, v);
            }
        }

        return ans;
    }
}