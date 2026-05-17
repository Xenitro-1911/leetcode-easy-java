package problems.p222_count_complete_tree_nodes;

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

    // Helper: build complete binary tree from level-order array
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

    // --- Null / single node ---

    @Test
    void testNullRoot_returnsZero() {
        assertEquals(0, solution.countNodes(null));
    }

    @Test
    void testSingleNode_returnsOne() {
        assertEquals(1, solution.countNodes(buildTree(new Integer[]{1})));
    }

    // --- Perfect binary trees (leftH == rightH at root) ---

    @Test
    void testTwoLevelPerfect_returnsThree() {
        // 3 nodes: 1,2,3
        assertEquals(3, solution.countNodes(buildTree(new Integer[]{1, 2, 3})));
    }

    @Test
    void testThreeLevelPerfect_returnsSeven() {
        // 7 nodes: perfect tree
        assertEquals(7, solution.countNodes(buildTree(new Integer[]{1, 2, 3, 4, 5, 6, 7})));
    }

    @Test
    void testFourLevelPerfect_returnsFifteen() {
        assertEquals(15, solution.countNodes(buildTree(new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15})));
    }

    // --- Complete but not perfect trees ---

    @Test
    void testLeetcodeExample_returnsSix() {
        // [1,2,3,4,5,6] — last level not full
        assertEquals(6, solution.countNodes(buildTree(new Integer[]{1, 2, 3, 4, 5, 6})));
    }

    @Test
    void testTwoNodes_returnsTwo() {
        assertEquals(2, solution.countNodes(buildTree(new Integer[]{1, 2})));
    }

    @Test
    void testFourNodes_returnsFour() {
        assertEquals(4, solution.countNodes(buildTree(new Integer[]{1, 2, 3, 4})));
    }

    @Test
    void testFiveNodes_returnsFive() {
        assertEquals(5, solution.countNodes(buildTree(new Integer[]{1, 2, 3, 4, 5})));
    }

    @Test
    void testEightNodes_returnsEight() {
        assertEquals(8, solution.countNodes(buildTree(new Integer[]{1,2,3,4,5,6,7,8})));
    }

    @Test
    void testNineNodes_returnsNine() {
        assertEquals(9, solution.countNodes(buildTree(new Integer[]{1,2,3,4,5,6,7,8,9})));
    }

    // --- Bit shift boundary: powers of 2 minus 1 ---

    @Test
    void testOneLevelPerfect_returnsOne() {
        // 2^1 - 1 = 1
        assertEquals(1, solution.countNodes(buildTree(new Integer[]{1})));
    }

    @Test
    void testPowerOfTwoMinusOne_correctCount() {
        // 2^4 - 1 = 15, already covered above
        assertEquals(15, solution.countNodes(buildTree(new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15})));
    }

    // --- Last level partially filled on left only ---

    @Test
    void testOnlyLeftChildFilled_returnsCorrect() {
        // 4 level tree with only leftmost node on last level
        assertEquals(8, solution.countNodes(buildTree(new Integer[]{1,2,3,4,5,6,7,8})));
    }

    @Test
    void testLastLevelHalfFull_returnsCorrect() {
        // 12 nodes — left half of last level full, right half empty
        assertEquals(12, solution.countNodes(buildTree(new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12})));
    }
}