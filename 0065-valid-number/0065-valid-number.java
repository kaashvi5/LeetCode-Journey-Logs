class Solution {
    public boolean isNumber(String s) {
        int n = s.length();
        int i = 0;
        
        // Skip leading whitespace (not usually needed for LeetCode but safe)
        while (i < n && s.charAt(i) == ' ') i++;
        
        boolean seenDigit = false;
        boolean seenDot = false;
        boolean seenExp = false;
        
        // Optional sign at start
        if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) i++;
        
        while (i < n) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                seenDigit = true;
            } else if (c == '.') {
                // Dot not allowed after exponent or if already seen
                if (seenDot || seenExp) return false;
                seenDot = true;
            } else if (c == 'e' || c == 'E') {
                // Exponent not allowed if already seen, or if no digit seen yet
                if (seenExp || !seenDigit) return false;
                seenExp = true;
                seenDigit = false; // require digit after exponent
                // Optional sign after e/E
                if (i + 1 < n && (s.charAt(i + 1) == '+' || s.charAt(i + 1) == '-')) i++;
            } else {
                return false; // invalid character
            }
            i++;
        }
        
        return seenDigit;
    }
}