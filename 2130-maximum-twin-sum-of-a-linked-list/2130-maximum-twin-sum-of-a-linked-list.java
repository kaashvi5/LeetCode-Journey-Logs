class Solution {
    public int pairSum(ListNode head) {
        ArrayList<Integer> list = new ArrayList<>();

        while (head != null) {
            list.add(head.val);
            head = head.next;
        }

        int maxSum = 0;
        int n = list.size();

        for (int i = 0; i < n / 2; i++) {
            maxSum = Math.max(maxSum, list.get(i) + list.get(n - 1 - i));
        }

        return maxSum;
    }
}