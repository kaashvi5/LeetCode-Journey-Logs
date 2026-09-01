import java.util.*;

class Solution {

    public int minMoves(String[] classroom, int energy) {

        int m = classroom.length;
        int n = classroom[0].length();

        // Store positions of all litter
        List<int[]> litter = new ArrayList<>();

        int sr = 0, sc = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                char ch = classroom[i].charAt(j);

                if (ch == 'S') {
                    sr = i;
                    sc = j;
                } 
                else if (ch == 'L') {
                    litter.add(new int[]{i, j});
                }
            }
        }

        int k = litter.size();

        // All litter collected
        int fullMask = (1 << k) - 1;

        /*
         * best[r][c][mask] = maximum energy with which
         * we have reached (r,c) after collecting 'mask'.
         *
         * If we reach the same state with less/equal energy,
         * it is useless because the higher-energy state dominates it.
         */
        int[][][] best = new int[m][n][1 << k];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(best[i][j], -1);
            }
        }

        /*
         * Encode:
         * row, col, mask, energy
         */
        Queue<State> queue = new ArrayDeque<>();

        best[sr][sc][0] = energy;
        queue.offer(new State(sr, sc, 0, energy, 0));

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {

            State cur = queue.poll();

            int r = cur.r;
            int c = cur.c;
            int mask = cur.mask;
            int e = cur.energy;
            int moves = cur.moves;

            if (mask == fullMask) {
                return moves;
            }

            // Try 4 directions
            for (int d = 0; d < 4; d++) {

                int nr = r + dr[d];
                int nc = c + dc[d];

                // Outside grid
                if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                    continue;
                }

                // Obstacle
                if (classroom[nr].charAt(nc) == 'X') {
                    continue;
                }

                // No energy to make a move
                if (e == 0) {
                    continue;
                }

                int newEnergy = e - 1;
                int newMask = mask;

                char cell = classroom[nr].charAt(nc);

                // Collect litter
                if (cell == 'L') {

                    for (int i = 0; i < k; i++) {
                        if (litter.get(i)[0] == nr &&
                            litter.get(i)[1] == nc) {

                            newMask |= (1 << i);
                            break;
                        }
                    }
                }

                // Reset energy
                if (cell == 'R') {
                    newEnergy = energy;
                }

                /*
                 * If we've already reached this (cell, mask)
                 * with >= energy, this state cannot be better.
                 */
                if (newEnergy <= best[nr][nc][newMask]) {
                    continue;
                }

                best[nr][nc][newMask] = newEnergy;

                queue.offer(
                    new State(
                        nr,
                        nc,
                        newMask,
                        newEnergy,
                        moves + 1
                    )
                );
            }
        }

        return -1;
    }

    static class State {
        int r, c;
        int mask;
        int energy;
        int moves;

        State(int r, int c, int mask, int energy, int moves) {
            this.r = r;
            this.c = c;
            this.mask = mask;
            this.energy = energy;
            this.moves = moves;
        }
    }
}