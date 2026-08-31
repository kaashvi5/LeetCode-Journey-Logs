class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {

        int first = -1;
        int last = -1;
        int minDistance = Integer.MAX_VALUE;

        ListNode prev = head;
        ListNode curr = head.next;

        int index = 1;

        while (curr.next != null) {

            ListNode next = curr.next;

            // Check if current node is a critical point
            boolean isCritical =
                    (curr.val > prev.val && curr.val > next.val) ||
                    (curr.val < prev.val && curr.val < next.val);

            if (isCritical) {

                // First critical point
                if (first == -1) {
                    first = index;
                } else {
                    // Distance from previous critical point
                    minDistance = Math.min(minDistance, index - last);
                }

                last = index;
            }

            prev = curr;
            curr = next;
            index++;
        }

        // Fewer than 2 critical points
        if (first == -1 || first == last) {
            return new int[]{-1, -1};
        }

        int maxDistance = last - first;

        return new int[]{minDistance, maxDistance};
    }
}