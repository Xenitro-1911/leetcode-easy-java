package problems.p112_path_sum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    private Solution solution;

    @BeforeEach
    void setUp() {
        solution = new Solution();
    }

    // --- Null / Empty ---

    @Test
    void nullRoot_returnsFalse() {
        assertFalse(solution.hasPathSum(null, 0));
    }

    @Test
    void nullRoot_nonZeroTarget_returnsFalse() {
        assertFalse(solution.hasPathSum(null, 10));
    }

    // --- Single node ---

    @Test
    void singleNode_matchesTarget_returnsTrue() {
        TreeNode root = new TreeNode(5);
        assertTrue(solution.hasPathSum(root, 5));
    }

    @Test
    void singleNode_doesNotMatchTarget_returnsFalse() {
        TreeNode root = new TreeNode(5);
        assertFalse(solution.hasPathSum(root, 10));
    }

    // --- LeetCode example cases ---

    @Test
    void leetcodeExample1_returnsTrue() {
        // Tree: [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
        // Path: 5 -> 4 -> 11 -> 2
        TreeNode root = new TreeNode(5,
                new TreeNode(4,
                        new TreeNode(11,
                                new TreeNode(7),
                                new TreeNode(2)),
                        null),
                new TreeNode(8,
                        new TreeNode(13),
                        new TreeNode(4,
                                null,
                                new TreeNode(1))));
        assertTrue(solution.hasPathSum(root, 22));
    }

    @Test
    void leetcodeExample2_returnsFalse() {
        // Tree: [1,2,3], targetSum = 5
        TreeNode root = new TreeNode(1,
                new TreeNode(2),
                new TreeNode(3));
        assertFalse(solution.hasPathSum(root, 5));
    }

    // --- Path selection ---

    @Test
    void onlyLeftPathMatches_returnsTrue() {
        //     1
        //    / \
        //   2   3
        // target = 3 (path: 1->2)
        TreeNode root = new TreeNode(1,
                new TreeNode(2),
                new TreeNode(3));
        assertTrue(solution.hasPathSum(root, 3));
    }

    @Test
    void onlyRightPathMatches_returnsTrue() {
        //     1
        //    / \
        //   2   3
        // target = 4 (path: 1->3)
        TreeNode root = new TreeNode(1,
                new TreeNode(2),
                new TreeNode(3));
        assertTrue(solution.hasPathSum(root, 4));
    }

    // --- Negative values ---

    @Test
    void negativeNodeValues_returnsTrue() {
        //     -2
        //       \
        //       -3
        // target = -5
        TreeNode root = new TreeNode(-2,
                null,
                new TreeNode(-3));
        assertTrue(solution.hasPathSum(root, -5));
    }

    @Test
    void negativeAndPositiveMixed_returnsTrue() {
        //     1
        //    /
        //  -2
        //  /
        // 3
        // target = 2 (path: 1 -> -2 -> 3)
        TreeNode root = new TreeNode(1,
                new TreeNode(-2,
                        new TreeNode(3),
                        null),
                null);
        assertTrue(solution.hasPathSum(root, 2));
    }

    // --- Edge: skewed trees ---

    @Test
    void rightSkewedTree_pathExists_returnsTrue() {
        // 1 -> 2 -> 3 -> 4, target = 10
        TreeNode root = new TreeNode(1,
                null,
                new TreeNode(2,
                        null,
                        new TreeNode(3,
                                null,
                                new TreeNode(4))));
        assertTrue(solution.hasPathSum(root, 10));
    }

    @Test
    void rightSkewedTree_pathMissing_returnsFalse() {
        // 1 -> 2 -> 3 -> 4, target = 7
        TreeNode root = new TreeNode(1,
                null,
                new TreeNode(2,
                        null,
                        new TreeNode(3,
                                null,
                                new TreeNode(4))));
        assertFalse(solution.hasPathSum(root, 7));
    }

    // --- Zero target ---

    @Test
    void zeroTarget_leafIsZero_returnsTrue() {
        //   5
        //  /
        // -5
        // target = 0
        TreeNode root = new TreeNode(5,
                new TreeNode(-5),
                null);
        assertTrue(solution.hasPathSum(root, 0));
    }
}