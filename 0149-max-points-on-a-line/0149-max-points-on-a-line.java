import java.util.*;

class Solution {
    public int maxPoints(int[][] points) {
        int n = points.length;

        if (n <= 2) return n;

        int ans = 0;

        for (int i = 0; i < n; i++) {
            HashMap<String, Integer> map = new HashMap<>();
            int duplicates = 0;
            int maxSlope = 0;

            for (int j = i + 1; j < n; j++) {
                int dx = points[j][0] - points[i][0];
                int dy = points[j][1] - points[i][1];

                if (dx == 0 && dy == 0) {
                    duplicates++;
                    continue;
                }

                // Vertical line
                if (dx == 0) {
                    dy = 1;
                    dx = 0;
                }
                // Horizontal line
                else if (dy == 0) {
                    dx = 1;
                    dy = 0;
                }
                else {
                    int g = gcd(Math.abs(dx), Math.abs(dy));
                    dx /= g;
                    dy /= g;

                    // Make sign consistent
                    if (dx < 0) {
                        dx = -dx;
                        dy = -dy;
                    }
                }

                String slope = dx + "/" + dy;

                int count = map.getOrDefault(slope, 0) + 1;
                map.put(slope, count);

                maxSlope = Math.max(maxSlope, count);
            }

            ans = Math.max(ans, maxSlope + duplicates + 1);
        }

        return ans;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}