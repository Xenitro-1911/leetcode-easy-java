package problems.p111_minimum_depth_of_binary_tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    private Solution solution;

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

    // --- Edge cases ---

    @Test
    void testNullTree() {
        assertEquals(0, solution.minDepth(null));
    }

    @Test
    void testSingleNode() {
        // Only node is both root and leaf — depth 1
        TreeNode root = new TreeNode(1);
        assertEquals(1, solution.minDepth(root));
    }

    // --- LeetCode examples ---

    @Test
    void testLeetcodeExample1() {
        // [3, 9, 20, null, null, 15, 7] — min depth is 2 (3 -> 9)
        TreeNode root = buildTree(new Integer[]{3, 9, 20, null, null, 15, 7});
        assertEquals(2, solution.minDepth(root));
    }

    @Test
    void testLeetcodeExample2() {
        // [2, null, 3, null, 4, null, 5, null, 6] — right-skewed, min depth 5
        TreeNode root = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(5);
        root.right.right.right.right = new TreeNode(6);
        assertEquals(5, solution.minDepth(root));
    }

    // --- Skewed trees ---

    @Test
    void testLeftSkewed() {
        // 1 -> 2 -> 3 -> 4, no right children — min depth 4
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(4);
        assertEquals(4, solution.minDepth(root));
    }

    @Test
    void testRightSkewed() {
        // 1 -> 2 -> 3 -> 4, no left children — min depth 4
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(4);
        assertEquals(4, solution.minDepth(root));
    }

    // --- One-sided child cases ---

    @Test
    void testRootWithOnlyLeftChild() {
        // Root has only left child — null right must not count as depth 1
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        assertEquals(2, solution.minDepth(root));
    }

    @Test
    void testRootWithOnlyRightChild() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        assertEquals(2, solution.minDepth(root));
    }

    // --- Unbalanced trees ---

    @Test
    void testMinLeafOnRight() {
        // Left subtree is deep, right is shallow — min depth should pick right
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
        assertEquals(2, solution.minDepth(root));
    }

    @Test
    void testMinLeafOnLeft() {
        // Right subtree is deep, left is shallow
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(5);
        assertEquals(2, solution.minDepth(root));
    }

    @Test
    void testPerfectTree() {
        // [1, 2, 3, 4, 5, 6, 7] — all leaves at depth 3
        TreeNode root = buildTree(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        assertEquals(3, solution.minDepth(root));
    }

    @Test
    void testTwoLevelComplete() {
        // [1, 2, 3] — both children are leaves, min depth 2
        TreeNode root = buildTree(new Integer[]{1, 2, 3});
        assertEquals(2, solution.minDepth(root));
    }
}