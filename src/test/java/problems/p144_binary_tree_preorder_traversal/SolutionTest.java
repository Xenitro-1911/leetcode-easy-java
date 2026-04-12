package problems.p144_binary_tree_preorder_traversal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    private Solution solution;


    @BeforeEach
    void setUp() {
        solution = new Solution();
    }

    // Helper to build a tree from level-order array (null = missing node)
    private TreeNode build(Integer[] vals) {
        if (vals == null || vals.length == 0 || vals[0] == null) return null;
        TreeNode root = new TreeNode(vals[0]);
        java.util.Queue<TreeNode> q = new java.util.LinkedList<>();
        q.add(root);
        int i = 1;
        while (!q.isEmpty() && i < vals.length) {
            TreeNode node = q.poll();
            if (i < vals.length && vals[i] != null) {
                node.left = new TreeNode(vals[i]);
                q.add(node.left);
            }
            i++;
            if (i < vals.length && vals[i] != null) {
                node.right = new TreeNode(vals[i]);
                q.add(node.right);
            }
            i++;
        }
        return root;
    }

    // 1. null root → empty list
    @Test
    void testNullRoot() {
        assertEquals(Collections.emptyList(), solution.preorderTraversal(null));
    }

    // 2. Single node
    @Test
    void testSingleNode() {
        TreeNode root = new TreeNode(1);
        assertEquals(List.of(1), solution.preorderTraversal(root));
    }

    // 3. LeetCode example 1: [1,null,2,3] → [1,2,3]
    @Test
    void testExample1() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        assertEquals(Arrays.asList(1, 2, 3), solution.preorderTraversal(root));
    }

    // 4. LeetCode example 2: [] → []
    @Test
    void testEmptyTree() {
        assertEquals(Collections.emptyList(), solution.preorderTraversal(null));
    }

    // 5. LeetCode example 3: [1] → [1]
    @Test
    void testSingleNodeExample3() {
        assertEquals(List.of(1), solution.preorderTraversal(new TreeNode(1)));
    }

    // 6. Perfect binary tree [1,2,3,4,5,6,7] → [1,2,4,5,3,6,7]
    @Test
    void testPerfectTree() {
        TreeNode root = build(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        assertEquals(Arrays.asList(1, 2, 4, 5, 3, 6, 7), solution.preorderTraversal(root));
    }

    // 7. Left-skewed tree: 1→2→3→4
    @Test
    void testLeftSkewed() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(4);
        assertEquals(Arrays.asList(1, 2, 3, 4), solution.preorderTraversal(root));
    }

    // 8. Right-skewed tree: 1→2→3→4
    @Test
    void testRightSkewed() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(4);
        assertEquals(Arrays.asList(1, 2, 3, 4), solution.preorderTraversal(root));
    }

    // 9. Two-node tree: root with only left child
    @Test
    void testRootWithLeftOnly() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        assertEquals(Arrays.asList(5, 3), solution.preorderTraversal(root));
    }

    // 10. Two-node tree: root with only right child
    @Test
    void testRootWithRightOnly() {
        TreeNode root = new TreeNode(5);
        root.right = new TreeNode(7);
        assertEquals(Arrays.asList(5, 7), solution.preorderTraversal(root));
    }

    // 11. Negative values
    @Test
    void testNegativeValues() {
        TreeNode root = new TreeNode(-1);
        root.left = new TreeNode(-2);
        root.right = new TreeNode(-3);
        assertEquals(Arrays.asList(-1, -2, -3), solution.preorderTraversal(root));
    }

    // 12. All same values
    @Test
    void testAllSameValues() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(1);
        root.right = new TreeNode(1);
        assertEquals(Arrays.asList(1, 1, 1), solution.preorderTraversal(root));
    }

    // 13. Unbalanced tree with deeper right subtree
    @Test
    void testUnbalancedRight() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(5);
        assertEquals(Arrays.asList(1, 2, 3, 4, 5), solution.preorderTraversal(root));
    }

    // 14. Root is first (preorder property check)
    @Test
    void testRootIsFirst() {
        TreeNode root = build(new Integer[]{42, 10, 99});
        List<Integer> result = solution.preorderTraversal(root);
        assertEquals(42, result.get(0), "Root must be first in preorder");
    }

    // 15. Result size equals number of nodes
    @Test
    void testResultSizeMatchesNodeCount() {
        TreeNode root = build(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        assertEquals(7, solution.preorderTraversal(root).size());
    }
}