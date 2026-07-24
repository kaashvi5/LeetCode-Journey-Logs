import java.util.*;

class Solution {
    public int uniqueXorTriplets(int[] nums) {
        // Deduplicate values
        boolean[] seen = new boolean[2048];
        for (int v : nums) seen[v] = true;
        
        List<Integer> distinct = new ArrayList<>();
        for (int v = 0; v < 2048; v++) {
            if (seen[v]) distinct.add(v);
        }
        int d = distinct.size();
        
        int bound = 2048; // since nums[i] <= 1500 < 2^11
        
        // Optimization: if we have enough distinct elements, all values in [0, bound) are reachable
        // (standard result for this type of problem), but we compute exactly to be safe.
        
        boolean[] pairSet = new boolean[bound];
        for (int i = 0; i < d; i++) {
            int a = distinct.get(i);
            for (int j = i; j < d; j++) {
                int b = distinct.get(j);
                pairSet[a ^ b] = true;
            }
        }
        
        boolean[] tripleSet = new boolean[bound];
        for (int i = 0; i < d; i++) {
            int a = distinct.get(i);
            for (int y = 0; y < bound; y++) {
                if (pairSet[y]) {
                    tripleSet[a ^ y] = true;
                }
            }
        }
        
        int count = 0;
        for (boolean b : tripleSet) {
            if (b) count++;
        }
        return count;
    }
}