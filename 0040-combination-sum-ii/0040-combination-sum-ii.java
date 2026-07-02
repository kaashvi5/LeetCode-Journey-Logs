class Solution {
    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        backtrack(candidates, target, 0, new ArrayList<>());
        return ans;
    }

    private void backtrack(int[] arr, int target, int idx, List<Integer> curr) {
        if (target == 0) {
            ans.add(new ArrayList<>(curr));
            return;
        }

        for (int i = idx; i < arr.length; i++) {
            if (i > idx && arr[i] == arr[i - 1]) continue; // skip duplicates

            if (arr[i] > target) break;

            curr.add(arr[i]);
            backtrack(arr, target - arr[i], i + 1, curr);
            curr.remove(curr.size() - 1);
        }
    }
}