class Solution {
    public String smallestPalindrome(String s) {
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        
        StringBuilder half = new StringBuilder();
        char mid = 0;
        
        for (int i = 0; i < 26; i++) {
            char c = (char) ('a' + i);
            if (count[i] % 2 == 1) {
                mid = c;
            }
            for (int j = 0; j < count[i] / 2; j++) {
                half.append(c);
            }
        }
        
        StringBuilder result = new StringBuilder();
        result.append(half);
        if (mid != 0) {
            result.append(mid);
        }
        result.append(half.reverse());
        
        return result.toString();
    }
}