package problems.p257_binary_tree_paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

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

    // Helper: sort paths for order-independent comparison
    private List<String> sorted(List<String> list) {
        List<String> copy = new ArrayList<>(list);
        Collections.sort(copy);
        return copy;
    }

    // --- Null and single node ---

    @Test
    void testNullRoot_returnsEmptyList() {
        assertTrue(solution.binaryTreePaths(null).isEmpty());
    }

    @Test
    void testSingleNode_returnsJustRootValue() {
        assertEquals(List.of("1"), solution.binaryTreePaths(buildTree(new Integer[]{1})));
    }

    // --- LeetCode examples ---

    @Test
    void testLeetcodeExample1() {
        // [1,2,3,null,5] → ["1->2->5","1->3"]
        List<String> result = solution.binaryTreePaths(buildTree(new Integer[]{1, 2, 3, null, 5}));
        assertEquals(sorted(List.of("1->2->5", "1->3")), sorted(result));
    }

    @Test
    void testLeetcodeExample2() {
        // [1] → ["1"]
        assertEquals(List.of("1"), solution.binaryTreePaths(buildTree(new Integer[]{1})));
    }

    // --- Two node trees ---

    @Test
    void testLeftChildOnly_returnsOnePathWithArrow() {
        // [1,2] → ["1->2"]
        assertEquals(List.of("1->2"), solution.binaryTreePaths(buildTree(new Integer[]{1, 2})));
    }

    @Test
    void testRightChildOnly_returnsOnePathWithArrow() {
        // [1,null,2] → ["1->2"]
        assertEquals(List.of("1->2"), solution.binaryTreePaths(buildTree(new Integer[]{1, null, 2})));
    }

    // --- Perfect binary trees ---

    @Test
    void testThreeNodePerfect_returnsTwoPaths() {
        // [1,2,3] → ["1->2","1->3"]
        List<String> result = solution.binaryTreePaths(buildTree(new Integer[]{1, 2, 3}));
        assertEquals(sorted(List.of("1->2", "1->3")), sorted(result));
    }

    @Test
    void testSevenNodePerfect_returnsFourPaths() {
        // [1,2,3,4,5,6,7]
        List<String> result = solution.binaryTreePaths(buildTree(new Integer[]{1, 2, 3, 4, 5, 6, 7}));
        List<String> expected = List.of("1->2->4", "1->2->5", "1->3->6", "1->3->7");
        assertEquals(sorted(expected), sorted(result));
    }

    // --- Skewed trees (stress test backtracking) ---

    @Test
    void testLeftSkewed_returnsOnePath() {
        // [1,2,null,3] → ["1->2->3"]
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        assertEquals(List.of("1->2->3"), solution.binaryTreePaths(root));
    }

    @Test
    void testRightSkewed_returnsOnePath() {
        // [1,null,2,null,3] → ["1->2->3"]
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        assertEquals(List.of("1->2->3"), solution.binaryTreePaths(root));
    }

    // --- Path count correctness ---

    @Test
    void testPathCount_matchesLeafCount() {
        // Number of paths == number of leaves
        TreeNode root = buildTree(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        assertEquals(4, solution.binaryTreePaths(root).size());
    }

    // --- Negative values ---

    @Test
    void testNegativeValues_correctPaths() {
        // [-1,-2,-3] → ["-1->-2","-1->-3"]
        List<String> result = solution.binaryTreePaths(buildTree(new Integer[]{-1, -2, -3}));
        assertEquals(sorted(List.of("-1->-2", "-1->-3")), sorted(result));
    }

    // --- No leading arrow on root ---

    @Test
    void testNoLeadingArrow_singleNode() {
        String path = solution.binaryTreePaths(buildTree(new Integer[]{5})).get(0);
        assertFalse(path.startsWith("->"));
    }

    @Test
    void testNoLeadingArrow_multiNode() {
        List<String> paths = solution.binaryTreePaths(buildTree(new Integer[]{1, 2, 3}));
        for (String path : paths) {
            assertFalse(path.startsWith("->"));
        }
    }
}