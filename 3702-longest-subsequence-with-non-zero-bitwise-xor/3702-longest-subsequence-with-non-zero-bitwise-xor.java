class Solution {
    public int longestSubsequence(int[] nums) {
        int n = nums.length;
        int totalXor = 0;
        boolean hasNonZero = false;
        
        for (int num : nums) {
            totalXor ^= num;
            if (num != 0) {
                hasNonZero = true;
            }
        }
        
        // If XOR of entire array is already non-zero, take all elements
        if (totalXor != 0) {
            return n;
        }
        
        // XOR is zero: removing any single non-zero element makes 
        // the remaining XOR equal to that removed element (non-zero)
        if (hasNonZero) {
            return n - 1;
        }
        
        // All elements are zero -> no subsequence can have non-zero XOR
        return 0;
    }
}