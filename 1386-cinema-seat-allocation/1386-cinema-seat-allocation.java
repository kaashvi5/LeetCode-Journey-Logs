import java.util.*;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> rowMask = new HashMap<>();
        for (int[] rs : reservedSeats) {
            int row = rs[0], seat = rs[1];
            if (seat < 2 || seat > 9) continue; // seats 1 and 10 never block any 4-seat group
            int bit = 1 << (seat - 1); // seat s -> bit (s-1)
            rowMask.merge(row, bit, (a, b) -> a | b);
        }

        final int LEFT   = 0b0000011110; // seats 2-5 -> bits 1-4
        final int MIDDLE = 0b0001111000; // seats 4-7 -> bits 3-6
        final int RIGHT  = 0b0111100000; // seats 6-9 -> bits 5-8

        long result = (long) (n - rowMask.size()) * 2;

        for (int mask : rowMask.values()) {
            int count = 0;
            boolean leftOk = (mask & LEFT) == 0;
            boolean rightOk = (mask & RIGHT) == 0;
            if (leftOk) count++;
            if (rightOk) count++;
            if (count == 0 && (mask & MIDDLE) == 0) count = 1;
            result += count;
        }

        return (int) result;
    }
}