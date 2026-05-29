class Solution {
    public void nextPermutation(int[] nums) {

        int i = nums.length - 2;

        // Step 1: find breaking point
        while(i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        // Step 2: find just greater element
        if(i >= 0) {
            int j = nums.length - 1;

            while(nums[j] <= nums[i]) {
                j--;
            }

            swap(nums, i, j);
        }

        // Step 3: reverse remaining part
        reverse(nums, i + 1, nums.length - 1);
    }

    void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    void reverse(int[] nums, int start, int end) {
        while(start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }
}