class Solution {
    public int[][] updateMatrix(int[][] mat) {
        
        int m = mat.length;
        int n = mat[0].length;

        int[][] dist = new int[m][n];

        Queue<int[]> q = new LinkedList<>();

        // Step 1: Add all 0s to queue
        // Put large value for 1s
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (mat[i][j] == 0) {
                    q.offer(new int[]{i, j});
                } else {
                    dist[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        // Directions: up, down, left, right
        int[][] dir = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1}
        };

        // Step 2: BFS
        while (!q.isEmpty()) {

            int[] curr = q.poll();

            int r = curr[0];
            int c = curr[1];

            for (int[] d : dir) {

                int nr = r + d[0];
                int nc = c + d[1];

                // Check bounds
                if (nr >= 0 && nr < m && nc >= 0 && nc < n) {

                    // Relaxation
                    if (dist[nr][nc] > dist[r][c] + 1) {

                        dist[nr][nc] = dist[r][c] + 1;

                        q.offer(new int[]{nr, nc});
                    }
                }
            }
        }

        return dist;
    }
}