class Solution {
    private int[] parent;
    
    public int countCompleteComponents(int n, int[][] edges) {
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        
        for (int[] edge : edges) {
            union(edge[0], edge[1]);
        }
        
        Map<Integer, Integer> nodeCount = new HashMap<>();
        Map<Integer, Integer> edgeCount = new HashMap<>();
        
        for (int v = 0; v < n; v++) {
            int root = find(v);
            nodeCount.put(root, nodeCount.getOrDefault(root, 0) + 1);
        }
        
        for (int[] edge : edges) {
            int root = find(edge[0]);
            edgeCount.put(root, edgeCount.getOrDefault(root, 0) + 1);
        }
        
        int result = 0;
        for (Map.Entry<Integer, Integer> entry : nodeCount.entrySet()) {
            int root = entry.getKey();
            int k = entry.getValue();
            int actualEdges = edgeCount.getOrDefault(root, 0);
            if (actualEdges == k * (k - 1) / 2) {
                result++;
            }
        }
        
        return result;
    }
    
    private int find(int x) {
        while (parent[x] != x) {
            parent[x] = parent[parent[x]]; // path compression
            x = parent[x];
        }
        return x;
    }
    
    private void union(int x, int y) {
        int rx = find(x);
        int ry = find(y);
        if (rx != ry) {
            parent[rx] = ry;
        }
    }
}