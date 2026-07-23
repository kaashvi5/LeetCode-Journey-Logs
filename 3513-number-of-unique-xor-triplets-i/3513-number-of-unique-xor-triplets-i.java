class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;
        if (n == 1) return 1;
        if (n == 2) return 2;
        
        int pow2 = 1;
        while (pow2 <= n) {
            pow2 <<= 1;
        }
        return pow2;
    }
}