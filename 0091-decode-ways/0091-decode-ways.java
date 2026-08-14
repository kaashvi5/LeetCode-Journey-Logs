class Solution {
    public int numDecodings(String s) {
        int n = s.length();
        if (n == 0 || s.charAt(0) == '0') return 0;
        
        // dp[i] = number of ways to decode s[0..i-1]
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        
        for (int i = 2; i <= n; i++) {
            // Check single digit (s[i-1])
            int oneDigit = s.charAt(i - 1) - '0';
            if (oneDigit >= 1) {
                dp[i] += dp[i - 1];
            }
            
            // Check two digits (s[i-2..i-1])
            int twoDigit = (s.charAt(i - 2) - '0') * 10 + oneDigit;
            if (twoDigit >= 10 && twoDigit <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        
        return dp[n];
    }
}