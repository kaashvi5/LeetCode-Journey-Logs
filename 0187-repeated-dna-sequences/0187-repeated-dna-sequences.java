import java.util.*;

class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> ans = new ArrayList<>();
        Set<String> seen = new HashSet<>();
        Set<String> repeated = new HashSet<>();

        for (int i = 0; i + 10 <= s.length(); i++) {
            String sub = s.substring(i, i + 10);

            if (!seen.add(sub)) {
                repeated.add(sub);
            }
        }

        ans.addAll(repeated);
        return ans;
    }
}