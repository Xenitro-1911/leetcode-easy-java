package problems.p111_minimum_depth_of_binary_tree;

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

class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int depth = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();

            // Process all nodes at the current level
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();

                // The first leaf node we hit is the minimum depth
                if (node.left == null && node.right == null) {
                    return depth;
                }

                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            depth++;
        }
        return depth;
    }
}
