package problems.p108_convert_sorted_array_to_binary_search_tree;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    private final Solution solution = new Solution();

    // Helper: build a sorted array → BST, then collect in-order to verify BST property
    private int[] inOrder(TreeNode root) {
        java.util.List<Integer> list = new java.util.ArrayList<>();
        inOrderHelper(root, list);
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    private void inOrderHelper(TreeNode node, java.util.List<Integer> list) {
        if (node == null) return;
        inOrderHelper(node.left, list);
        list.add(node.val);
        inOrderHelper(node.right, list);
    }

    // Helper: compute height of tree
    private int height(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(height(root.left), height(root.right));
    }

    // Helper: verify height-balanced (no subtree differs by more than 1)
    private boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        int diff = Math.abs(height(root.left) - height(root.right));
        return diff <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    // --- Valid cases ---

    @Test
    void oddLengthArray() {
        int[] nums = {-10, -3, 0, 5, 9};
        TreeNode root = solution.sortedArrayToBST(nums);
        assertArrayEquals(nums, inOrder(root));
        assertTrue(isBalanced(root));
    }

    @Test
    void evenLengthArray() {
        int[] nums = {1, 2, 3, 4};
        TreeNode root = solution.sortedArrayToBST(nums);
        assertArrayEquals(nums, inOrder(root));
        assertTrue(isBalanced(root));
    }

    @Test
    void lcExampleSeven() {
        int[] nums = {-10, -3, 0, 5, 9, 14, 20};
        TreeNode root = solution.sortedArrayToBST(nums);
        assertArrayEquals(nums, inOrder(root));
        assertTrue(isBalanced(root));
    }

    @Test
    void allNegatives() {
        int[] nums = {-9, -7, -5, -3, -1};
        TreeNode root = solution.sortedArrayToBST(nums);
        assertArrayEquals(nums, inOrder(root));
        assertTrue(isBalanced(root));
    }

    @Test
    void allPositives() {
        int[] nums = {1, 3, 5, 7, 9, 11};
        TreeNode root = solution.sortedArrayToBST(nums);
        assertArrayEquals(nums, inOrder(root));
        assertTrue(isBalanced(root));
    }

    @Test
    void containsZero() {
        int[] nums = {-5, 0, 5};
        TreeNode root = solution.sortedArrayToBST(nums);
        assertArrayEquals(nums, inOrder(root));
        assertEquals(0, root.val); // mid of 3-element array
        assertTrue(isBalanced(root));
    }

    @Test
    void duplicateValues() {
        int[] nums = {1, 1, 1, 1, 1};
        TreeNode root = solution.sortedArrayToBST(nums);
        assertArrayEquals(nums, inOrder(root));
        assertTrue(isBalanced(root));
    }

    @Test
    void largeSortedRange() {
        int n = 1000;
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = i;
        TreeNode root = solution.sortedArrayToBST(nums);
        assertArrayEquals(nums, inOrder(root));
        assertTrue(isBalanced(root));
        // Height should be at most ceil(log2(n)) + 1 ≈ 11
        assertTrue(height(root) <= 12);
    }

    // --- Edge cases ---

    @Test
    void singleElement() {
        int[] nums = {42};
        TreeNode root = solution.sortedArrayToBST(nums);
        assertNotNull(root);
        assertEquals(42, root.val);
        assertNull(root.left);
        assertNull(root.right);
    }

    @Test
    void twoElements() {
        int[] nums = {1, 2};
        TreeNode root = solution.sortedArrayToBST(nums);
        assertArrayEquals(nums, inOrder(root));
        assertTrue(isBalanced(root));
    }

    @Test
    void threeElements() {
        int[] nums = {1, 2, 3};
        TreeNode root = solution.sortedArrayToBST(nums);
        assertEquals(2, root.val); // mid of [0..2]
        assertEquals(1, root.left.val);
        assertEquals(3, root.right.val);
    }

    @Test
    void extremeValues() {
        int[] nums = {Integer.MIN_VALUE, 0, Integer.MAX_VALUE};
        TreeNode root = solution.sortedArrayToBST(nums);
        assertArrayEquals(nums, inOrder(root));
        assertTrue(isBalanced(root));
    }
}