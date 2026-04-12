package problems.p145_binary_tree_postorder_traversal;

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
        public List<Integer> postorderTraversal(TreeNode root) {
            List<Integer> x = new ArrayList<>();
            if(root==null){
                return x;
            }
            helper(root,x);
            return x;
        }

        public void helper(TreeNode root,List<Integer> x){
            if(root==null){
                return;
            }
            helper(root.left,x);
            helper(root.right,x);
            x.add(root.val);
        }
    }