class Solution:
    def palindromePath(self, n, edges, s, queries):
        adj = [[] for _ in range(n)]
        for u, v in edges:
            adj[u].append(v)
            adj[v].append(u)

        s = list(s)
        bit = lambda ch: 1 << (ord(ch) - 97)

        LOG = max(1, (n).bit_length())
        par = [0] * n
        depth = [0] * n
        base = [0] * n          # root-to-node xor of initial masks
        tin = [0] * n
        order = []

        base[0] = bit(s[0])
        stack = [0]
        par[0] = -1
        while stack:
            v = stack.pop()
            tin[v] = len(order)
            order.append(v)
            for w in adj[v]:
                if w != par[v]:
                    par[w] = v
                    depth[w] = depth[v] + 1
                    base[w] = base[v] ^ bit(s[w])
                    stack.append(w)

        size = [1] * n
        for v in reversed(order):
            if par[v] >= 0:
                size[par[v]] += size[v]

        # binary lifting
        up = [[0] * n for _ in range(LOG)]
        for v in range(n):
            up[0][v] = par[v] if par[v] >= 0 else 0
        for k in range(1, LOG):
            prev, cur = up[k - 1], up[k]
            for v in range(n):
                cur[v] = prev[prev[v]]

        def lca(a, b):
            if depth[a] < depth[b]:
                a, b = b, a
            diff = depth[a] - depth[b]
            for k in range(LOG):
                if diff >> k & 1:
                    a = up[k][a]
            if a == b:
                return a
            for k in range(LOG - 1, -1, -1):
                if up[k][a] != up[k][b]:
                    a = up[k][a]
                    b = up[k][b]
            return up[0][a]

        # Fenwick (xor), 1-indexed, size n + 2
        tree = [0] * (n + 2)

        def add(i, d):
            i += 1
            while i <= n + 1:
                tree[i] ^= d
                i += i & -i

        def pref(i):
            i += 1
            r = 0
            while i > 0:
                r ^= tree[i]
                i -= i & -i
            return r

        def P(v):
            return base[v] ^ pref(tin[v])

        ans = []
        for q in queries:
            parts = q.split()
            if parts[0] == "update":
                x, c = int(parts[1]), parts[2]
                if s[x] != c:
                    d = bit(s[x]) ^ bit(c)
                    s[x] = c
                    add(tin[x], d)
                    add(tin[x] + size[x], d)
            else:
                u, v = int(parts[1]), int(parts[2])
                l = lca(u, v)
                mask = P(u) ^ P(v) ^ bit(s[l])
                ans.append(mask & (mask - 1) == 0)
        return ans