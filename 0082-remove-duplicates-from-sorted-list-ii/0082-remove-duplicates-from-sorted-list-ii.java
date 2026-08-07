class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return null;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode cur = head;

        while (cur != null) {
            // If current node is start of a duplicate run
            if (cur.next != null && cur.val == cur.next.val) {
                int dupVal = cur.val;
                // Skip all nodes with this value
                while (cur != null && cur.val == dupVal) {
                    cur = cur.next;
                }
                // Link prev directly to the node after the duplicate run
                prev.next = cur;
            } else {
                // No duplicate, move prev forward
                prev = cur;
                cur = cur.next;
            }
        }

        return dummy.next;
    }
}