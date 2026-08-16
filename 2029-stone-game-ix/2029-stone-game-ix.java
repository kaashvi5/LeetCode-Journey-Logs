class Solution {
    public boolean stoneGameIX(int[] stones) {
        int[] cnt = new int[3];
        for (int s : stones) {
            cnt[s % 3]++;
        }
        
        if (cnt[0] % 2 == 0) {
            // Even number of 0-remainder stones: need both nonzero groups non-empty
            return cnt[1] >= 1 && cnt[2] >= 1;
        } else {
            // Odd number of 0-remainder stones: need imbalance strictly greater than 2
            return Math.abs(cnt[1] - cnt[2]) > 2;
        }
    }
}