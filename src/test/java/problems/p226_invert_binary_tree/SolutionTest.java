package problems.p226_invert_binary_tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.LinkedList;
import java.util.Queue;

class SolutionTest {

    private Solution solution;

    @BeforeEach
    void setUp() {
        solution = new Solution();
    }

    // Helper: build tree from level-order array
    private TreeNode buildTree(Integer[] vals) {
        if (vals == null || vals.length == 0 || vals[0] == null) return null;
        TreeNode root = new TreeNode(vals[0]);
        Queue<TreeNode> queue = new LinkedList<>();
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

    // Helper: serialize tree to level-order array for assertions
    private String levelOrder(TreeNode root) {
        if (root == null) return "[]";
        StringBuilder sb = new StringBuilder("[");
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                sb.append("null,");
            } else {
                sb.append(node.val).append(",");
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }
        // trim trailing nulls
        String result = sb.toString().replaceAll("(,?null)+,$", "");
        return result + "]";
    }

    // --- Null and single node ---

    @Test
    void testNullRoot_returnsNull() {
        assertNull(solution.invertTree(null));
    }

    @Test
    void testSingleNode_returnsSameNode() {
        TreeNode root = new TreeNode(1);
        TreeNode result = solution.invertTree(root);
        assertEquals(1, result.val);
        assertNull(result.left);
        assertNull(result.right);
    }

    // --- Two node trees ---

    @Test
    void testLeftChildOnly_movesToRight() {
        TreeNode root = buildTree(new Integer[]{1, 2});
        TreeNode result = solution.invertTree(root);
        assertNull(result.left);
        assertEquals(2, result.right.val);
    }

    @Test
    void testRightChildOnly_movesToLeft() {
        TreeNode root = buildTree(new Integer[]{1, null, 2});
        TreeNode result = solution.invertTree(root);
        assertEquals(2, result.left.val);
        assertNull(result.right);
    }

    // --- LeetCode examples ---

    @Test
    void testLeetcodeExample1_correctInversion() {
        // [4,2,7,1,3,6,9] → [4,7,2,9,6,3,1]
        TreeNode root = buildTree(new Integer[]{4, 2, 7, 1, 3, 6, 9});
        TreeNode result = solution.invertTree(root);
        assertEquals(4, result.val);
        assertEquals(7, result.left.val);
        assertEquals(2, result.right.val);
        assertEquals(9, result.left.left.val);
        assertEquals(6, result.left.right.val);
        assertEquals(3, result.right.left.val);
        assertEquals(1, result.right.right.val);
    }

    @Test
    void testLeetcodeExample2_twoNodes() {
        // [2,1,3] → [2,3,1]
        TreeNode root = buildTree(new Integer[]{2, 1, 3});
        TreeNode result = solution.invertTree(root);
        assertEquals(3, result.left.val);
        assertEquals(1, result.right.val);
    }

    // --- Structural correctness ---

    @Test
    void testRootValueUnchanged() {
        TreeNode root = buildTree(new Integer[]{4, 2, 7, 1, 3, 6, 9});
        TreeNode result = solution.invertTree(root);
        assertEquals(4, result.val);
    }

    @Test
    void testInPlaceSwap_sameRootReference() {
        // invertTree should return the same root node, not a new one
        TreeNode root = buildTree(new Integer[]{1, 2, 3});
        TreeNode result = solution.invertTree(root);
        assertSame(root, result);
    }

    @Test
    void testLeafNodesHaveNoChildren() {
        TreeNode root = buildTree(new Integer[]{4, 2, 7, 1, 3, 6, 9});
        TreeNode result = solution.invertTree(root);
        assertNull(result.left.left.left);
        assertNull(result.left.left.right);
        assertNull(result.right.right.left);
        assertNull(result.right.right.right);
    }

    // --- Double inversion ---

    @Test
    void testDoubleInversion_restoresOriginal() {
        TreeNode root = buildTree(new Integer[]{4, 2, 7, 1, 3, 6, 9});
        String original = levelOrder(root);
        solution.invertTree(solution.invertTree(root));
        assertEquals(original, levelOrder(root));
    }

    // --- Symmetric tree stays symmetric ---

    @Test
    void testSymmetricTree_remainsSymmetric() {
        // [1,2,2,3,4,4,3] — symmetric; inversion should produce same structure
        TreeNode root = buildTree(new Integer[]{1, 2, 2, 3, 4, 4, 3});
        String before = levelOrder(root);
        solution.invertTree(root);
        String after = levelOrder(root);
        assertEquals(before, after);
    }

    // --- Negative values ---

    @Test
    void testNegativeValues_correctSwap() {
        TreeNode root = buildTree(new Integer[]{0, -1, 1});
        TreeNode result = solution.invertTree(root);
        assertEquals(1, result.left.val);
        assertEquals(-1, result.right.val);
    }
}