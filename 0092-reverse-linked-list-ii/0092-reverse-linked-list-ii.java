class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || left == right) {
            return head;
        }
        
        // Dummy node to handle edge case where left == 1
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        // Move 'prev' to the node just before position 'left'
        ListNode prev = dummy;
        for (int i = 0; i < left - 1; i++) {
            prev = prev.next;
        }
        
        // 'curr' will be the first node of the sublist to reverse
        ListNode curr = prev.next;
        
        // Reverse the sublist between left and right using head insertion
        for (int i = 0; i < right - left; i++) {
            ListNode next = curr.next;
            curr.next = next.next;
            next.next = prev.next;
            prev.next = next;
        }
        
        return dummy.next;
    }
}