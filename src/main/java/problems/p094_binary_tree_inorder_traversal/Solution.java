package problems.p094_binary_tree_inorder_traversal;

import java.util.ArrayList;
import java.util.List;

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

    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> x = new ArrayList<>();
        helper(root,x);
        return x;
    }

    public void helper(TreeNode root,List<Integer> x){
        if(root==null){
            return;
        }
        helper(root.left,x);
        x.add(root.val);
        helper(root.right,x);
    }
}