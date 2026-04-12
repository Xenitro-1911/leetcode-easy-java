package problems.p104_maximum_depth_of_binary_tree;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    private final Solution solution = new Solution();

    // Helper to build a TreeNode tree from a level-order array (null = missing node)
    private TreeNode build(Integer[] vals) {
        if (vals == null || vals.length == 0 || vals[0] == null) return null;
        TreeNode root = new TreeNode(vals[0]);
        java.util.Queue<TreeNode> queue = new java.util.LinkedList<>();
        queue.offer(root);
        int i = 1;
        while (i < vals.length) {
            TreeNode node = queue.poll();
            if (i < vals.length && vals[i] != null) {
                node.left = new TreeNode(vals[i]);
                queue.offer(node.left);
            }
            i++;
            if (i < vals.length && vals[i] != null) {
                node.right = new TreeNode(vals[i]);
                queue.offer(node.right);
            }
            i++;
        }
        return root;
    }

    // --- Base / Edge Cases ---

    @Test
    void testNullRoot() {
        // Empty tree has depth 0
        assertEquals(0, solution.maxDepth(null));
    }

    @Test
    void testSingleNode() {
        // A lone root has depth 1
        TreeNode root = new TreeNode(1);
        assertEquals(1, solution.maxDepth(root));
    }

    @Test
    void testTwoLevelsLeftOnly() {
        // Root with only a left child
        TreeNode root = build(new Integer[]{1, 2});
        assertEquals(2, solution.maxDepth(root));
    }

    @Test
    void testTwoLevelsRightOnly() {
        // Root with only a right child
        TreeNode root = build(new Integer[]{1, null, 2});
        assertEquals(2, solution.maxDepth(root));
    }

    // --- LeetCode Examples ---

    @Test
    void testLeetCodeExample1() {
        // [3,9,20,null,null,15,7] → depth 3
        TreeNode root = build(new Integer[]{3, 9, 20, null, null, 15, 7});
        assertEquals(3, solution.maxDepth(root));
    }

    @Test
    void testLeetCodeExample2() {
        // [1,null,2] → depth 2
        TreeNode root = build(new Integer[]{1, null, 2});
        assertEquals(2, solution.maxDepth(root));
    }

    // --- Structural Variations ---

    @Test
    void testLeftSkewedTree() {
        // 1 → 2 → 3 → 4 (all left children) → depth 4
        TreeNode root = build(new Integer[]{1, 2, null, 3, null, 4});
        assertEquals(4, solution.maxDepth(root));
    }

    @Test
    void testRightSkewedTree() {
        // 1 → 2 → 3 → 4 (all right children) → depth 4
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(4);
        assertEquals(4, solution.maxDepth(root));
    }

    @Test
    void testPerfectTree() {
        // Perfect binary tree with 3 levels → depth 3
        TreeNode root = build(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        assertEquals(3, solution.maxDepth(root));
    }

    @Test
    void testLeftHeavyUnbalanced() {
        // Left subtree deeper than right
        //        1
        //       / \
        //      2   3
        //     /
        //    4
        TreeNode root = build(new Integer[]{1, 2, 3, 4});
        assertEquals(3, solution.maxDepth(root));
    }

    @Test
    void testRightHeavyUnbalanced() {
        // Right subtree deeper than left
        //    1
        //   / \
        //  2   3
        //       \
        //        4
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(4);
        assertEquals(3, solution.maxDepth(root));
    }

    @Test
    void testFiveLevelTree() {
        // Straight left spine of depth 5
        TreeNode root = new TreeNode(1);
        TreeNode cur = root;
        for (int i = 2; i <= 5; i++) {
            cur.left = new TreeNode(i);
            cur = cur.left;
        }
        assertEquals(5, solution.maxDepth(root));
    }

    @Test
    void testDepthOneLargerOnRight() {
        // Right path is one deeper than left path
        //      1
        //     / \
        //    2   3
        //         \
        //          4
        //           \
        //            5
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(5);
        assertEquals(4, solution.maxDepth(root));
    }
}