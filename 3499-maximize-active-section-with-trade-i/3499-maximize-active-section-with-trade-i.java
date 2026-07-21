import java.util.*;

class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        String t = "1" + s + "1";
        int n = t.length();
        
        // Build runs: each run = [char (0/1), length]
        List<int[]> runs = new ArrayList<>();
        int i = 0;
        while (i < n) {
            int j = i;
            while (j < n && t.charAt(j) == t.charAt(i)) j++;
            runs.add(new int[]{t.charAt(i) - '0', j - i});
            i = j;
        }
        
        int ones = 0;
        for (char c : s.toCharArray()) if (c == '1') ones++;
        
        int maxGain = 0;
        int m = runs.size();
        // Only interior runs (not first, not last) are eligible as the
        // "1-block surrounded by 0s" for the first step of the trade.
        for (int k = 1; k < m - 1; k++) {
            if (runs.get(k)[0] == 1) {
                // runs alternate, so k-1 and k+1 are guaranteed to be '0' runs
                int left = runs.get(k - 1)[1];
                int right = runs.get(k + 1)[1];
                maxGain = Math.max(maxGain, left + right);
            }
        }
        
        return ones + maxGain;
    }
}