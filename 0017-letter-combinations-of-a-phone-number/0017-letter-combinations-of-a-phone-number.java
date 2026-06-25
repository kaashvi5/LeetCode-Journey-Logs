import java.util.*;

class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();

        if (digits == null || digits.length() == 0) {
            return ans;
        }

        String[] map = {
            "", "", "abc", "def", "ghi",
            "jkl", "mno", "pqrs", "tuv", "wxyz"
        };

        backtrack(digits, 0, new StringBuilder(), ans, map);
        return ans;
    }

    private void backtrack(String digits, int idx,
                           StringBuilder curr,
                           List<String> ans,
                           String[] map) {

        if (idx == digits.length()) {
            ans.add(curr.toString());
            return;
        }

        String letters = map[digits.charAt(idx) - '0'];

        for (char ch : letters.toCharArray()) {
            curr.append(ch);
            backtrack(digits, idx + 1, curr, ans, map);
            curr.deleteCharAt(curr.length() - 1);
        }
    }
}