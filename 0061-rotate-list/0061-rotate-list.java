/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) return head;
        
        // Find length and tail
        int length = 1;
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
            length++;
        }
        
        // Make it circular
        tail.next = head;
        
        // Find new tail: (length - k % length - 1) steps from head
        k = k % length;
        int stepsToNewTail = length - k;
        ListNode newTail = head;
        for (int i = 1; i < stepsToNewTail; i++) {
            newTail = newTail.next;
        }
        
        ListNode newHead = newTail.next;
        newTail.next = null; // break the circle
        
        return newHead;
    }
}