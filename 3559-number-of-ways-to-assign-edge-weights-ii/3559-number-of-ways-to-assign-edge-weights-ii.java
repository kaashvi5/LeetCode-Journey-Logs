class Solution {
    static final int MOD = 1_000_000_007;
    int[][] up;
    int[] depth, pow2;
    int LOG;

    public int[] assignEdgeWeights(int[][] edges, int[][] queries) {
        int n = edges.length + 1;

        LOG = 1;
        while ((1 << LOG) <= n) LOG++;

        List<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }

        up = new int[n + 1][LOG];
        depth = new int[n + 1];

        dfs(1, 1, graph);

        for (int j = 1; j < LOG; j++) {
            for (int i = 1; i <= n; i++) {
                up[i][j] = up[up[i][j - 1]][j - 1];
            }
        }

        pow2 = new int[n];
        pow2[0] = 1;

        for (int i = 1; i < n; i++) {
            pow2[i] = (int)((pow2[i - 1] * 2L) % MOD);
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0];
            int v = queries[i][1];

            int d = distance(u, v);

            if (d == 0) {
                ans[i] = 0;
            } else {
                ans[i] = pow2[d - 1];
            }
        }

        return ans;
    }

    void dfs(int node, int parent, List<Integer>[] graph) {
        up[node][0] = parent;

        for (int nei : graph[node]) {
            if (nei == parent) continue;

            depth[nei] = depth[node] + 1;
            dfs(nei, node, graph);
        }
    }

    int lca(int u, int v) {
        if (depth[u] < depth[v]) {
            int t = u;
            u = v;
            v = t;
        }

        int diff = depth[u] - depth[v];

        for (int i = 0; i < LOG; i++) {
            if (((diff >> i) & 1) == 1) {
                u = up[u][i];
            }
        }

        if (u == v) return u;

        for (int i = LOG - 1; i >= 0; i--) {
            if (up[u][i] != up[v][i]) {
                u = up[u][i];
                v = up[v][i];
            }
        }

        return up[u][0];
    }

    int distance(int u, int v) {
        int l = lca(u, v);
        return depth[u] + depth[v] - 2 * depth[l];
    }
}