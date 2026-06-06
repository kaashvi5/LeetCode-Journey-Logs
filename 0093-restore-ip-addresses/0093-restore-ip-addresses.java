import java.util.*;

class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        backtrack(s, 0, new ArrayList<>(), res);
        return res;
    }

    private void backtrack(String s, int index, List<String> parts, List<String> res) {
        // If we already have 4 parts
        if (parts.size() == 4) {
            if (index == s.length()) {
                res.add(String.join(".", parts));
            }
            return;
        }

        // Try segments of length 1 to 3
        for (int len = 1; len <= 3; len++) {
            if (index + len > s.length()) return;

            String part = s.substring(index, index + len);

            if (isValid(part)) {
                parts.add(part);
                backtrack(s, index + len, parts, res);
                parts.remove(parts.size() - 1);
            }
        }
    }

    private boolean isValid(String part) {
        // leading zero case
        if (part.length() > 1 && part.charAt(0) == '0') return false;

        int val = Integer.parseInt(part);
        return val >= 0 && val <= 255;
    }
}