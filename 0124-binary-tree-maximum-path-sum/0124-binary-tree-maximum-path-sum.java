class Solution {
    private int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxGain(root);
        return maxSum;
    }

    private int maxGain(TreeNode node) {
        if (node == null) return 0;

        // Only take positive contributions from children; ignore negative gains
        int leftGain = Math.max(maxGain(node.left), 0);
        int rightGain = Math.max(maxGain(node.right), 0);

        // Price of a path that passes through this node (as the "peak")
        int priceNewPath = node.val + leftGain + rightGain;

        // Update the global max if this path is better
        maxSum = Math.max(maxSum, priceNewPath);

        // Return the max gain if continuing the path through this node upward
        // (can only extend through one child, not both)
        return node.val + Math.max(leftGain, rightGain);
    }
}