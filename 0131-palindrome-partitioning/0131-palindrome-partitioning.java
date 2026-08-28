import java.util.*;

class Solution {
    public List<List<String>> partition(String s) {
        int n = s.length();
        boolean[][] isPal = new boolean[n][n];
        for (int i = 0; i < n; i++) isPal[i][i] = true;
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                if (s.charAt(i) == s.charAt(j)) {
                    isPal[i][j] = (len == 2) || isPal[i + 1][j - 1];
                }
            }
        }

        List<List<String>> result = new ArrayList<>();
        List<String> path = new ArrayList<>();
        backtrack(s, 0, isPal, path, result);
        return result;
    }

    private void backtrack(String s, int start, boolean[][] isPal,
                            List<String> path, List<List<String>> result) {
        int n = s.length();
        if (start == n) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int end = start; end < n; end++) {
            if (isPal[start][end]) {
                path.add(s.substring(start, end + 1));
                backtrack(s, end + 1, isPal, path, result);
                path.remove(path.size() - 1);
            }
        }
    }
}