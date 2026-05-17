package problems.p257_binary_tree_paths;

import java.util.ArrayList;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left,TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) return result;
        helper(root, new StringBuilder(), result);
        return result;
    }

    public void helper(TreeNode root, StringBuilder path, List<String> result) {
        if (root == null) return;

        int len = path.length();
        if (len > 0) path.append("->");
        path.append(root.val);

        if (root.left == null && root.right == null) {
            result.add(path.toString());
        } else {
            helper(root.left, path, result);
            helper(root.right, path, result);
        }

        // Backtrack — restore path to what it was before this node
        path.setLength(len);
    }
}