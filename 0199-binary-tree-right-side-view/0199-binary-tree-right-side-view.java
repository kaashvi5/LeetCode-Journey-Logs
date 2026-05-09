class Solution {
    public List<Integer> rightSideView(TreeNode root) {
         List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;

        LinkedList<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(q.size()>0){
            int levelSize=q.size();
            for(int i=0;i<levelSize;i++){
                TreeNode rm=q.removeFirst();
                if(i==levelSize-1){
                    ans.add(rm.val);
                }
                if(rm.left!=null){
                     q.add(rm.left);
                }

                if (rm.right != null) {
                    q.add(rm.right);

                }
            }
        }
        return ans; 
    }
}
        
    
