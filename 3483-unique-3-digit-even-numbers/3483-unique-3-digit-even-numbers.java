class Solution {
    public int totalNumbers(int[] digits) {
        int[] cnt = new int[10];
        for (int d : digits) cnt[d]++;
        
        int result = 0;
        int[] evens = {0, 2, 4, 6, 8};
        
        for (int h = 1; h <= 9; h++) {
            for (int t = 0; t <= 9; t++) {
                for (int u : evens) {
                    if (canForm(cnt, h, t, u)) {
                        result++;
                    }
                }
            }
        }
        
        return result;
    }
    
    private boolean canForm(int[] cnt, int h, int t, int u) {
        int[] need = new int[10];
        need[h]++;
        need[t]++;
        need[u]++;
        for (int i = 0; i < 10; i++) {
            if (need[i] > cnt[i]) return false;
        }
        return true;
    }
}