import java.util.*;

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates); // helps prune early
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }
    
    private void backtrack(int[] candidates, int remaining, int start, 
                            List<Integer> current, List<List<Integer>> result) {
        if (remaining == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        
        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] > remaining) {
                break; // sorted array, no point continuing
            }
            
            current.add(candidates[i]);
            // pass 'i' (not i+1) since we can reuse the same element
            backtrack(candidates, remaining - candidates[i], i, current, result);
            current.remove(current.size() - 1); // backtrack
        }
    }
}