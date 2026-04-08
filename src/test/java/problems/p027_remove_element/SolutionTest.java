package problems.p027_remove_element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    private Solution solution;

    @BeforeEach
    void setUp() {
        solution = new Solution();
    }

    // -------------------------------------------------------
    // Helper: extract the valid prefix and sort both for easy
    // comparison (LeetCode allows any order in the first k slots)
    // -------------------------------------------------------
    private int[] validPrefix(int[] nums, int k) {
        int[] prefix = Arrays.copyOf(nums, k);
        Arrays.sort(prefix);
        return prefix;
    }

    // -------------------------------------------------------
    // Basic cases
    // -------------------------------------------------------

    @Test
    void testExample1() {
        // LeetCode example: remove 3s → [2,2]
        int[] nums = {3, 2, 2, 3};
        int k = solution.removeElement(nums, 3);

        assertEquals(2, k);
        assertArrayEquals(new int[]{2, 2}, validPrefix(nums, k));
    }

    @Test
    void testExample2() {
        // LeetCode example: remove 2s → [0,1,3,4]  (order-agnostic)
        int[] nums = {0, 1, 2, 2, 3, 0, 4, 2};
        int k = solution.removeElement(nums, 2);

        assertEquals(5, k);
        assertArrayEquals(new int[]{0, 0, 1, 3, 4}, validPrefix(nums, k));
    }

    // -------------------------------------------------------
    // Edge cases
    // -------------------------------------------------------

    @Test
    void testEmptyArray() {
        int[] nums = {};
        int k = solution.removeElement(nums, 1);
        assertEquals(0, k);
    }

    @Test
    void testAllElementsEqualVal() {
        int[] nums = {5, 5, 5, 5};
        int k = solution.removeElement(nums, 5);
        assertEquals(0, k);
    }

    @Test
    void testNoElementEqualsVal() {
        int[] nums = {1, 2, 3, 4};
        int k = solution.removeElement(nums, 9);
        assertEquals(4, k);
        assertArrayEquals(new int[]{1, 2, 3, 4}, validPrefix(nums, k));
    }

    @Test
    void testSingleElementEqualsVal() {
        int[] nums = {7};
        int k = solution.removeElement(nums, 7);
        assertEquals(0, k);
    }

    @Test
    void testSingleElementNotEqualsVal() {
        int[] nums = {7};
        int k = solution.removeElement(nums, 3);
        assertEquals(1, k);
        assertArrayEquals(new int[]{7}, validPrefix(nums, k));
    }

    // -------------------------------------------------------
    // Additional cases
    // -------------------------------------------------------

    @Test
    void testValAtStart() {
        int[] nums = {2, 1, 3, 4};
        int k = solution.removeElement(nums, 2);
        assertEquals(3, k);
        assertArrayEquals(new int[]{1, 3, 4}, validPrefix(nums, k));
    }

    @Test
    void testValAtEnd() {
        int[] nums = {1, 3, 4, 2};
        int k = solution.removeElement(nums, 2);
        assertEquals(3, k);
        assertArrayEquals(new int[]{1, 3, 4}, validPrefix(nums, k));
    }

    @Test
    void testAlternatingVal() {
        int[] nums = {1, 0, 1, 0, 1};
        int k = solution.removeElement(nums, 0);
        assertEquals(3, k);
        assertArrayEquals(new int[]{1, 1, 1}, validPrefix(nums, k));
    }

    @Test
    void testAllSameNonMatchingValue() {
        int[] nums = {4, 4, 4};
        int k = solution.removeElement(nums, 3);
        assertEquals(3, k);
        assertArrayEquals(new int[]{4, 4, 4}, validPrefix(nums, k));
    }
}