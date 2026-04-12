package problems.p088_merge_sorted_array;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SolutionTest {

    private Solution solution;

    @BeforeEach
    public void setUp() {
        solution = new Solution();
    }

    // --- Basic cases ---

    @Test
    public void testBasicMerge() {
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        solution.merge(nums1, 3, new int[]{2, 5, 6}, 3);
        assertArrayEquals(new int[]{1, 2, 2, 3, 5, 6}, nums1);
    }

    @Test
    public void testNums2AllSmaller() {
        // Drain loop critical: all of nums2 ends up at the front
        int[] nums1 = {4, 5, 6, 0, 0, 0};
        solution.merge(nums1, 3, new int[]{1, 2, 3}, 3);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6}, nums1);
    }

    @Test
    public void testNums2AllLarger() {
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        solution.merge(nums1, 3, new int[]{4, 5, 6}, 3);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6}, nums1);
    }

    @Test
    public void testInterleaved() {
        int[] nums1 = {1, 3, 5, 0, 0, 0};
        solution.merge(nums1, 3, new int[]{2, 4, 6}, 3);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6}, nums1);
    }

    // --- Edge cases ---

    @Test
    public void testNums2Empty() {
        // n=0: nums1 unchanged
        int[] nums1 = {1, 2, 3};
        solution.merge(nums1, 3, new int[]{}, 0);
        assertArrayEquals(new int[]{1, 2, 3}, nums1);
    }

    @Test
    public void testNums1Empty() {
        // m=0: result is entirely nums2
        int[] nums1 = {0, 0, 0};
        solution.merge(nums1, 0, new int[]{2, 5, 6}, 3);
        assertArrayEquals(new int[]{2, 5, 6}, nums1);
    }

    @Test
    public void testSingleElementEach() {
        int[] nums1 = {1, 0};
        solution.merge(nums1, 1, new int[]{2}, 1);
        assertArrayEquals(new int[]{1, 2}, nums1);
    }

    @Test
    public void testSingleElementEachNums2Smaller() {
        int[] nums1 = {2, 0};
        solution.merge(nums1, 1, new int[]{1}, 1);
        assertArrayEquals(new int[]{1, 2}, nums1);
    }

    @Test
    public void testBothSingleElementEqual() {
        int[] nums1 = {1, 0};
        solution.merge(nums1, 1, new int[]{1}, 1);
        assertArrayEquals(new int[]{1, 1}, nums1);
    }

    // --- Duplicate cases ---

    @Test
    public void testDuplicatesAcrossArrays() {
        int[] nums1 = {1, 2, 2, 0, 0};
        solution.merge(nums1, 3, new int[]{2, 3}, 2);
        assertArrayEquals(new int[]{1, 2, 2, 2, 3}, nums1);
    }

    @Test
    public void testAllSameElements() {
        int[] nums1 = {2, 2, 0, 0};
        solution.merge(nums1, 2, new int[]{2, 2}, 2);
        assertArrayEquals(new int[]{2, 2, 2, 2}, nums1);
    }

    // --- Larger input ---

    @Test
    public void testLargerArrays() {
        int[] nums1 = {1, 4, 7, 9, 11, 0, 0, 0, 0, 0};
        solution.merge(nums1, 5, new int[]{2, 3, 8, 10, 12}, 5);
        assertArrayEquals(new int[]{1, 2, 3, 4, 7, 8, 9, 10, 11, 12}, nums1);
    }

    @Test
    public void testNegativeNumbers() {
        int[] nums1 = {-5, -3, -1, 0, 0, 0};
        solution.merge(nums1, 3, new int[]{-4, -2, 0}, 3);
        assertArrayEquals(new int[]{-5, -4, -3, -2, -1, 0}, nums1);
    }
}