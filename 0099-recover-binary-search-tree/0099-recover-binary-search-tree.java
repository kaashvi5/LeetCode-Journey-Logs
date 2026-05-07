/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    List<Integer> list = new ArrayList<>();

    public void recoverTree(TreeNode root) {
        // Step 1:let's store inorder
        inorder(root);

        // Step 2: then let's sort
        Collections.sort(list);

        // Step 3: fixing the tree
        int[] i = new int[1];
        fix(root, i);
    }

    void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        list.add(root.val);
        inorder(root.right);
    }

    void fix(TreeNode root, int[] i) {
        if (root == null) return;
        fix(root.left, i);
        root.val = list.get(i[0]++);
        fix(root.right, i);
    }
}