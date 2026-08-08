class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length(), m = word2.length();

        // suf[i] = number of trailing chars of word2 matchable as a subsequence in word1[i:]
        int[] suf = new int[n + 1];
        suf[n] = 0;
        int j = m - 1;
        for (int i = n - 1; i >= 0; i--) {
            if (j >= 0 && word1.charAt(i) == word2.charAt(j)) {
                j--;
            }
            suf[i] = m - 1 - j;
        }

        int[] result = new int[m];
        int i = 0;
        j = 0;
        boolean changeUsed = false;

        while (i < n && j < m) {
            if (word1.charAt(i) == word2.charAt(j)) {
                result[j] = i;
                i++;
                j++;
            } else {
                // Try using the single allowed change at this position
                if (!changeUsed && suf[i + 1] >= m - j - 1) {
                    result[j] = i;
                    changeUsed = true;
                    i++;
                    j++;
                } else {
                    i++;
                }
            }
        }

        if (j < m) {
            return new int[0]; // no valid sequence
        }
        return result;
    }
}