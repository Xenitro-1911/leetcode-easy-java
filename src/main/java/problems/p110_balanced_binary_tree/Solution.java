package problems.p110_balanced_binary_tree;

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

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        int z=helper(root);
        if(z==-1){
            return false;
        }else{
            return true;
        }
    }

    public int helper(TreeNode x) {
        if (x == null) return 0;

        int left = helper(x.left);   // get left height (or -1 if unbalanced)
        int right = helper(x.right); // get right height (or -1 if unbalanced)

        // if either subtree already found an imbalance, propagate -1 up
        if (left == -1 || right == -1) {
            return -1;
        }

        // if THIS node is unbalanced, signal -1
        if (Math.abs(left - right) > 1) {
            return -1;
        }

        // otherwise return actual height
        return Math.max(left, right) + 1;
    }
}