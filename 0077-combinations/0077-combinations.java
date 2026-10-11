import java.util.*;

class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(n, k, 1, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int n, int k, int start, List<Integer> current, List<List<Integer>> result) {
        // Add a copy when combination size becomes k
        if (current.size() == k) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i <= n; i++) {
            current.add(i);                       // choose
            backtrack(n, k, i + 1, current, result); // explore
            current.remove(current.size() - 1);   // un-choose (backtrack)
        }
    }
}