package problems.p101_symmetric_tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    private Solution solution;

    // Helper: build tree from level-order array (null = missing node)
    private TreeNode build(Integer[] vals) {
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

    // --- Valid (symmetric) cases ---

    @Test
    void testSymmetricExample1() {
        // [1,2,2,3,4,4,3]
        TreeNode root = build(new Integer[]{1, 2, 2, 3, 4, 4, 3});
        assertTrue(solution.isSymmetric(root));
    }

    @Test
    void testSingleNode() {
        TreeNode root = build(new Integer[]{1});
        assertTrue(solution.isSymmetric(root));
    }

    @Test
    void testAllSameValues() {
        // [2,2,2,2,2,2,2]
        TreeNode root = build(new Integer[]{2, 2, 2, 2, 2, 2, 2});
        assertTrue(solution.isSymmetric(root));
    }

    @Test
    void testSymmetricTwoLevels() {
        // [1,2,2]
        TreeNode root = build(new Integer[]{1, 2, 2});
        assertTrue(solution.isSymmetric(root));
    }

    @Test
    void testSymmetricWithNullChildren() {
        // [1,2,2,null,3,3,null]
        TreeNode root = build(new Integer[]{1, 2, 2, null, 3, 3, null});
        assertTrue(solution.isSymmetric(root));
    }

    // --- Invalid (asymmetric) cases ---

    @Test
    void testAsymmetricExample2() {
        // [1,2,2,null,3,null,3]
        TreeNode root = build(new Integer[]{1, 2, 2, null, 3, null, 3});
        assertFalse(solution.isSymmetric(root));
    }

    @Test
    void testDifferentValues() {
        // [1,2,3]
        TreeNode root = build(new Integer[]{1, 2, 3});
        assertFalse(solution.isSymmetric(root));
    }

    @Test
    void testLeftHeavy() {
        // [1,2,null]
        TreeNode root = build(new Integer[]{1, 2, null});
        assertFalse(solution.isSymmetric(root));
    }

    @Test
    void testRightHeavy() {
        // [1,null,2]
        TreeNode root = build(new Integer[]{1, null, 2});
        assertFalse(solution.isSymmetric(root));
    }

    @Test
    void testDeepAsymmetricValues() {
        // Structurally symmetric but values differ at depth 3
        // [1,2,2,3,4,4,5]  ← last leaf differs (3 vs 5)
        TreeNode root = build(new Integer[]{1, 2, 2, 3, 4, 4, 5});
        assertFalse(solution.isSymmetric(root));
    }

    // --- Edge cases ---

    @Test
    void testTwoNodesLeftOnly() {
        // [1,2,null]
        TreeNode root = build(new Integer[]{1, 2, null});
        assertFalse(solution.isSymmetric(root));
    }

    @Test
    void testSymmetricInnerNulls() {
        // [1,2,2,3,null,null,3]
        TreeNode root = build(new Integer[]{1, 2, 2, 3, null, null, 3});
        assertTrue(solution.isSymmetric(root));
    }

    @Test
    void testSymmetricDeepTree() {
        // [1,2,2,2,2,2,2,2,null,null,2,2,null,null,2]
        TreeNode root = build(new Integer[]{1, 2, 2, 2, 2, 2, 2});
        assertTrue(solution.isSymmetric(root));
    }
}