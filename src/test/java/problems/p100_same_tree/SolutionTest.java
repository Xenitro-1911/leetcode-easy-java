package problems.p100_same_tree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    private Solution solution;

    // Helper to build a tree from level-order array (null = missing node)
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

    // --- Identical trees ---

    @Test
    void bothEmpty() {
        assertTrue(solution.isSameTree(null, null));
    }

    @Test
    void singleNodeSameValue() {
        TreeNode p = new TreeNode(1);
        TreeNode q = new TreeNode(1);
        assertTrue(solution.isSameTree(p, q));
    }

    @Test
    void identicalThreeNodeTrees() {
        TreeNode p = build(new Integer[]{1, 2, 3});
        TreeNode q = build(new Integer[]{1, 2, 3});
        assertTrue(solution.isSameTree(p, q));
    }

    @Test
    void identicalLargerTrees() {
        TreeNode p = build(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        TreeNode q = build(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        assertTrue(solution.isSameTree(p, q));
    }

    // --- Different values ---

    @Test
    void rootValuesDiffer() {
        TreeNode p = new TreeNode(1);
        TreeNode q = new TreeNode(2);
        assertFalse(solution.isSameTree(p, q));
    }

    @Test
    void leafValueDiffers() {
        TreeNode p = build(new Integer[]{1, 2, 3});
        TreeNode q = build(new Integer[]{1, 2, 4});
        assertFalse(solution.isSameTree(p, q));
    }

    @Test
    void deepValueDiffers() {
        TreeNode p = build(new Integer[]{1, 2, 3, 4, 5, null, null});
        TreeNode q = build(new Integer[]{1, 2, 3, 9, 5, null, null});
        assertFalse(solution.isSameTree(p, q));
    }

    // --- Structural differences ---

    @Test
    void oneNullOneNot() {
        TreeNode p = new TreeNode(1);
        assertFalse(solution.isSameTree(p, null));
        assertFalse(solution.isSameTree(null, p));
    }

    @Test
    void differentStructureSameValues() {
        // p: 1->2->null(right) vs q: 1->null(left)->2
        TreeNode p = new TreeNode(1);
        p.left = new TreeNode(2);

        TreeNode q = new TreeNode(1);
        q.right = new TreeNode(2);

        assertFalse(solution.isSameTree(p, q));
    }

    @Test
    void differentDepths() {
        TreeNode p = build(new Integer[]{1, 2, 3, 4, null, null, null});
        TreeNode q = build(new Integer[]{1, 2, 3});
        assertFalse(solution.isSameTree(p, q));
    }

    // --- Edge cases ---

    @Test
    void negativeValues() {
        TreeNode p = build(new Integer[]{-1, -2, -3});
        TreeNode q = build(new Integer[]{-1, -2, -3});
        assertTrue(solution.isSameTree(p, q));
    }

    @Test
    void sameValuesWrongStructure() {
        // Both have nodes 1,2,2 but mirrored
        TreeNode p = new TreeNode(1);
        p.left = new TreeNode(2);

        TreeNode q = new TreeNode(1);
        q.right = new TreeNode(2);

        assertFalse(solution.isSameTree(p, q));
    }
}