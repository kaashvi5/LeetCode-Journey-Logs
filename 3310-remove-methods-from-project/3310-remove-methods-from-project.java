class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] inv : invocations) {
            adj.get(inv[0]).add(inv[1]);
        }
        
        boolean[] suspicious = new boolean[n];
        suspicious[k] = true;
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(k);
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int nxt : adj.get(cur)) {
                if (!suspicious[nxt]) {
                    suspicious[nxt] = true;
                    queue.add(nxt);
                }
            }
        }
        
        // Check validity: no edge from a non-suspicious method into a suspicious method
        boolean valid = true;
        for (int[] inv : invocations) {
            int a = inv[0], b = inv[1];
            if (suspicious[b] && !suspicious[a]) {
                valid = false;
                break;
            }
        }
        
        List<Integer> result = new ArrayList<>();
        if (!valid) {
            for (int i = 0; i < n; i++) result.add(i);
        } else {
            for (int i = 0; i < n; i++) {
                if (!suspicious[i]) result.add(i);
            }
        }
        
        return result;
    }
}