import java.util.*;

class Solution {
    int i = 0;

    public List<String> braceExpansionII(String expression) {
        Set<String> set = parseUnion(expression);

        List<String> ans = new ArrayList<>(set);
        Collections.sort(ans);
        return ans;
    }

    // Handles comma-separated expressions
    private Set<String> parseUnion(String s) {
        Set<String> result = new HashSet<>();

        while (i < s.length() && s.charAt(i) != '}') {
            Set<String> part = parseConcat(s);
            result.addAll(part);

            if (i < s.length() && s.charAt(i) == ',') {
                i++;
            } else {
                break;
            }
        }

        return result;
    }

    // Handles concatenation
    private Set<String> parseConcat(String s) {
        Set<String> result = new HashSet<>();
        result.add("");

        while (i < s.length()
                && s.charAt(i) != ','
                && s.charAt(i) != '}') {

            Set<String> part;

            if (s.charAt(i) == '{') {
                i++; // skip '{'
                part = parseUnion(s);
                i++; // skip '}'
            } else {
                part = new HashSet<>();
                part.add(String.valueOf(s.charAt(i)));
                i++;
            }

            result = multiply(result, part);
        }

        return result;
    }

    private Set<String> multiply(Set<String> a, Set<String> b) {
        Set<String> result = new HashSet<>();

        for (String x : a) {
            for (String y : b) {
                result.add(x + y);
            }
        }

        return result;
    }
}