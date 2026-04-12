package problems.p094_binary_tree_inorder_traversal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SolutionTest {

    private Solution solution;

    @BeforeEach
    void setUp() {
        solution = new Solution();
    }

    // --- Basic Cases ---

    @Test
    void testSingleNode() {
        // Tree: [1]
        TreeNode root = new TreeNode(1);
        assertEquals(List.of(1), solution.inorderTraversal(root));
    }

    @Test
    void testThreeNodeBalanced() {
        // Tree:   2
        //        / \
        //       1   3
        TreeNode root = new TreeNode(2, new TreeNode(1), new TreeNode(3));
        assertEquals(List.of(1, 2, 3), solution.inorderTraversal(root));
    }

    @Test
    void testLeftSkewed() {
        // Tree:     3
        //          /
        //         2
        //        /
        //       1
        TreeNode root = new TreeNode(3, new TreeNode(2, new TreeNode(1), null), null);
        assertEquals(List.of(1, 2, 3), solution.inorderTraversal(root));
    }

    @Test
    void testRightSkewed() {
        // Tree: 1
        //        \
        //         2
        //          \
        //           3
        TreeNode root = new TreeNode(1, null, new TreeNode(2, null, new TreeNode(3)));
        assertEquals(List.of(1, 2, 3), solution.inorderTraversal(root));
    }

    @Test
    void testFiveNodeTree() {
        // Tree:      4
        //           / \
        //          2   5
        //         / \
        //        1   3
        TreeNode root = new TreeNode(4,
                new TreeNode(2, new TreeNode(1), new TreeNode(3)),
                new TreeNode(5));
        assertEquals(List.of(1, 2, 3, 4, 5), solution.inorderTraversal(root));
    }

    // --- Edge Cases ---

    @Test
    void testNullRoot() {
        assertEquals(Collections.emptyList(), solution.inorderTraversal(null));
    }

    @Test
    void testOnlyLeftChild() {
        // Tree: 2
        //      /
        //     1
        TreeNode root = new TreeNode(2, new TreeNode(1), null);
        assertEquals(List.of(1, 2), solution.inorderTraversal(root));
    }

    @Test
    void testOnlyRightChild() {
        // Tree: 1
        //        \
        //         2
        TreeNode root = new TreeNode(1, null, new TreeNode(2));
        assertEquals(List.of(1, 2), solution.inorderTraversal(root));
    }

    @Test
    void testNegativeValues() {
        // Tree:   0
        //        / \
        //      -2   1
        TreeNode root = new TreeNode(0, new TreeNode(-2), new TreeNode(1));
        assertEquals(List.of(-2, 0, 1), solution.inorderTraversal(root));
    }

    @Test
    void testDuplicateValues() {
        // Tree:   2
        //        / \
        //       2   2
        TreeNode root = new TreeNode(2, new TreeNode(2), new TreeNode(2));
        assertEquals(List.of(2, 2, 2), solution.inorderTraversal(root));
    }

    // --- Reuse Safety ---

    @Test
    void testSolutionReusedAcrossCalls() {
        // Ensures no stale state from instance variables
        TreeNode root1 = new TreeNode(1);
        TreeNode root2 = new TreeNode(2, new TreeNode(1), new TreeNode(3));

        assertEquals(List.of(1), solution.inorderTraversal(root1));
        assertEquals(List.of(1, 2, 3), solution.inorderTraversal(root2));
    }

    @Test
    void testLargerTree() {
        // Tree:        5
        //            /   \
        //           3     7
        //          / \   / \
        //         2   4 6   8
        //        /
        //       1
        TreeNode root = new TreeNode(5,
                new TreeNode(3,
                        new TreeNode(2, new TreeNode(1), null),
                        new TreeNode(4)),
                new TreeNode(7,
                        new TreeNode(6),
                        new TreeNode(8)));
        assertEquals(List.of(1, 2, 3, 4, 5, 6, 7, 8), solution.inorderTraversal(root));
    }

    @Test
    void testResultIsSorted_BST() {
        // For a BST, inorder traversal must produce a sorted list
        TreeNode root = new TreeNode(5,
                new TreeNode(3, new TreeNode(1), new TreeNode(4)),
                new TreeNode(8, new TreeNode(7), new TreeNode(9)));
        List<Integer> result = solution.inorderTraversal(root);
        for (int i = 0; i < result.size() - 1; i++) {
            assertTrue(result.get(i) <= result.get(i + 1),
                    "Expected sorted order at index " + i);
        }
    }
}