class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        int minIdx = 0, maxIdx = 0;
        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[minIdx]) minIdx = i;
            if (nums[i] > nums[maxIdx]) maxIdx = i;
        }
        
        int i = Math.min(minIdx, maxIdx);
        int j = Math.max(minIdx, maxIdx);
        
        int fromFront = j + 1;                  // remove everything up through the later index
        int fromBack = n - i;                   // remove everything from the earlier index to the end
        int mixed = (i + 1) + (n - j);           // remove up to and including i from front, and from j to end from back
        
        return Math.min(fromFront, Math.min(fromBack, mixed));
    }
}