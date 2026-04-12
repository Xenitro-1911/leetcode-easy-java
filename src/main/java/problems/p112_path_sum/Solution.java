package problems.p112_path_sum;

import java.util.LinkedList;
import java.util.Queue;

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

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root==null)return false;

        int sum=0;
        boolean x = helper(sum,root,targetSum);
        return x;
    }

    public boolean helper(int sum,TreeNode root,int targetSum){
        if(root==null){
            return false;
        }
        if(root.left==null&&root.right==null){
            sum = sum + root.val;
            if(sum==targetSum){
                return true;
            }else{
                return false;
            }
        } else{
            sum = sum + root.val;
        }
        return helper(sum,root.left,targetSum)||helper(sum,root.right,targetSum);
    }
}