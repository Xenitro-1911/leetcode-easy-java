package problems.p222_count_complete_tree_nodes;

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
    public int countNodes(TreeNode root) {
        if (root == null) return 0;

        int leftH = 0, rightH = 0;
        TreeNode l = root, r = root;

        while (l != null) { leftH++; l = l.left; }
        while (r != null) { rightH++; r = r.right; }

        if (leftH == rightH) return (1 << leftH) - 1;

        return 1 + countNodes(root.left) + countNodes(root.right);
    }
}