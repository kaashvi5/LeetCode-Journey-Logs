class Solution {
    public int jump(int[] nums) {
        int jumps = 0;
        int currentEnd = 0;   // farthest index reachable with 'jumps' jumps
        int farthest = 0;     // farthest index reachable with 'jumps + 1' jumps

        for (int i = 0; i < nums.length - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);

            if (i == currentEnd) {          // must jump now to keep moving forward
                jumps++;
                currentEnd = farthest;

                if (currentEnd >= nums.length - 1) break;
            }
        }
        return jumps;
    }
}