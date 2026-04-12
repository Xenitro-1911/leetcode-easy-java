package problems.p145_binary_tree_postorder_traversal;

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

    // 1. Null root → empty list
    @Test
    void testNullRoot() {
        assertEquals(Collections.emptyList(), solution.postorderTraversal(null));
    }

    // 2. Single node
    @Test
    void testSingleNode() {
        assertEquals(List.of(1), solution.postorderTraversal(new TreeNode(1)));
    }

    // 3. LeetCode example 1: [1,null,2,3] → [3,2,1]
    @Test
    void testExample1() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        assertEquals(Arrays.asList(3, 2, 1), solution.postorderTraversal(root));
    }

    // 4. LeetCode example 2: [] → []
    @Test
    void testEmptyTree() {
        assertEquals(Collections.emptyList(), solution.postorderTraversal(null));
    }

    // 5. LeetCode example 3: [1] → [1]
    @Test
    void testSingleNodeExample3() {
        assertEquals(List.of(1), solution.postorderTraversal(new TreeNode(1)));
    }

    // 6. Perfect binary tree [1,2,3,4,5,6,7] → [4,5,2,6,7,3,1]
    @Test
    void testPerfectTree() {
        TreeNode root = build(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        assertEquals(Arrays.asList(4, 5, 2, 6, 7, 3, 1), solution.postorderTraversal(root));
    }

    // 7. Left-skewed tree: 1→2→3→4 → [4,3,2,1]
    @Test
    void testLeftSkewed() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(4);
        assertEquals(Arrays.asList(4, 3, 2, 1), solution.postorderTraversal(root));
    }

    // 8. Right-skewed tree: 1→2→3→4 → [4,3,2,1]
    @Test
    void testRightSkewed() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(4);
        assertEquals(Arrays.asList(4, 3, 2, 1), solution.postorderTraversal(root));
    }

    // 9. Root with only left child → [left, root]
    @Test
    void testRootWithLeftOnly() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        assertEquals(Arrays.asList(3, 5), solution.postorderTraversal(root));
    }

    // 10. Root with only right child → [right, root]
    @Test
    void testRootWithRightOnly() {
        TreeNode root = new TreeNode(5);
        root.right = new TreeNode(7);
        assertEquals(Arrays.asList(7, 5), solution.postorderTraversal(root));
    }

    // 11. Negative values
    @Test
    void testNegativeValues() {
        TreeNode root = new TreeNode(-1);
        root.left = new TreeNode(-2);
        root.right = new TreeNode(-3);
        assertEquals(Arrays.asList(-2, -3, -1), solution.postorderTraversal(root));
    }

    // 12. All same values
    @Test
    void testAllSameValues() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(1);
        root.right = new TreeNode(1);
        assertEquals(Arrays.asList(1, 1, 1), solution.postorderTraversal(root));
    }

    // 13. Unbalanced tree with deeper right subtree
    @Test
    void testUnbalancedRight() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(5);
        assertEquals(Arrays.asList(2, 5, 4, 3, 1), solution.postorderTraversal(root));
    }

    // 14. Root is last (postorder property check)
    @Test
    void testRootIsLast() {
        TreeNode root = build(new Integer[]{42, 10, 99});
        List<Integer> result = solution.postorderTraversal(root);
        assertEquals(42, result.get(result.size() - 1), "Root must be last in postorder");
    }

    // 15. Result size equals number of nodes
    @Test
    void testResultSizeMatchesNodeCount() {
        TreeNode root = build(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        assertEquals(7, solution.postorderTraversal(root).size());
    }
}