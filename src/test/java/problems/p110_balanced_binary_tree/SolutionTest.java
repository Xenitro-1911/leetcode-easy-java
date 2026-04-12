package problems.p110_balanced_binary_tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    private Solution solution;

    // Helper to build tree from level-order array (null = absent node)
    private TreeNode buildTree(Integer[] vals) {
        if (vals == null || vals.length == 0 || vals[0] == null) return null;
        TreeNode root = new TreeNode(vals[0]);
        java.util.Queue<TreeNode> queue = new java.util.LinkedList<>();
        queue.add(root);
        int i = 1;
        while (!queue.isEmpty() && i < vals.length) {
            TreeNode node = queue.poll();
            if (i < vals.length && vals[i] != null) {
                node.left = new TreeNode(vals[i]);
                queue.add(node.left);
            }
            i++;
            if (i < vals.length && vals[i] != null) {
                node.right = new TreeNode(vals[i]);
                queue.add(node.right);
            }
            i++;
        }
        return root;
    }

    @BeforeEach
    void setUp() {
        solution = new Solution();
    }

    // --- Balanced trees ---

    @Test
    void testNullTree() {
        // Empty tree is balanced by definition
        assertTrue(solution.isBalanced(null));
    }

    @Test
    void testSingleNode() {
        TreeNode root = new TreeNode(1);
        assertTrue(solution.isBalanced(root));
    }

    @Test
    void testPerfectTree() {
        // [1, 2, 3, 4, 5, 6, 7] — perfect binary tree, fully balanced
        TreeNode root = buildTree(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        assertTrue(solution.isBalanced(root));
    }

    @Test
    void testBalancedThreeNodes() {
        // [1, 2, 3]
        TreeNode root = buildTree(new Integer[]{1, 2, 3});
        assertTrue(solution.isBalanced(root));
    }

    @Test
    void testBalancedWithNulls() {
        // [1, 2, 3, null, null, null, 4] — right subtree one level deeper on one side
        TreeNode root = buildTree(new Integer[]{1, 2, 3, null, null, null, 4});
        assertTrue(solution.isBalanced(root));
    }

    @Test
    void testBalancedLeetcodeExample1() {
        // LeetCode example: [3, 9, 20, null, null, 15, 7]
        TreeNode root = buildTree(new Integer[]{3, 9, 20, null, null, 15, 7});
        assertTrue(solution.isBalanced(root));
    }

    // --- Unbalanced trees ---

    @Test
    void testUnbalancedLeetcodeExample2() {
        // LeetCode example: [1, 2, 2, 3, 3, null, null, 4, 4]
        TreeNode root = buildTree(new Integer[]{1, 2, 2, 3, 3, null, null, 4, 4});
        assertFalse(solution.isBalanced(root));
    }

    @Test
    void testUnbalancedLeetcodeExample3() {
        // LeetCode example: [1, 2, 2, 3, null, null, 3, 4, null, null, 4]
        // Root looks balanced but node 3 on both sides is unbalanced
        TreeNode root = buildTree(new Integer[]{1, 2, 2, 3, null, null, 3, 4, null, null, 4});
        assertFalse(solution.isBalanced(root));
    }

    @Test
    void testLinkedListLeft() {
        // Fully left-skewed: 1 -> 2 -> 3 -> 4
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(4);
        assertFalse(solution.isBalanced(root));
    }

    @Test
    void testLinkedListRight() {
        // Fully right-skewed: 1 -> 2 -> 3 -> 4
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(4);
        assertFalse(solution.isBalanced(root));
    }

    @Test
    void testImbalanceDeepLeft() {
        // Root balanced, but deep imbalance in left subtree
        //       1
        //      / \
        //     2   3
        //    /
        //   4
        //  /
        // 5
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.left.left = new TreeNode(5);
        assertFalse(solution.isBalanced(root));
    }

    @Test
    void testImbalanceDeepRight() {
        // Mirror of above — imbalance in right subtree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(5);
        assertFalse(solution.isBalanced(root));
    }
}