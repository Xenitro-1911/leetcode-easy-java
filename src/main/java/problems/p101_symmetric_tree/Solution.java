package problems.p101_symmetric_tree;

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
 }
}

public class Solution {

    public boolean isSymmetric(TreeNode root) {
        return helper(root.left,root.right);
    }

    public boolean helper(TreeNode left,TreeNode right){
        if(left==null&&right==null){
            return true;
        }
        if(left==null||right==null){
            return false;
        }
        if(left.val!=right.val){
            return false;
        }
        return helper(left.left,right.right) && helper(left.right,right.left);
    }
}