package problems.p283_move_zeroes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    private Solution solution;

    @BeforeEach
    void setUp() {
        solution = new Solution();
    }

    // --- LeetCode examples ---

    @Test
    void testLeetcodeExample1() {
        // [0,1,0,3,12] → [1,3,12,0,0]
        int[] nums = {0, 1, 0, 3, 12};
        solution.moveZeroes(nums);
        assertArrayEquals(new int[]{1, 3, 12, 0, 0}, nums);
    }

    @Test
    void testLeetcodeExample2() {
        // [0] → [0]
        int[] nums = {0};
        solution.moveZeroes(nums);
        assertArrayEquals(new int[]{0}, nums);
    }

    // --- No zeroes ---

    @Test
    void testNoZeroes_unchangedOrder() {
        int[] nums = {1, 2, 3};
        solution.moveZeroes(nums);
        assertArrayEquals(new int[]{1, 2, 3}, nums);
    }

    // --- All zeroes ---

    @Test
    void testAllZeroes_remainsAllZeroes() {
        int[] nums = {0, 0, 0};
        solution.moveZeroes(nums);
        assertArrayEquals(new int[]{0, 0, 0}, nums);
    }

    // --- Single element ---

    @Test
    void testSingleNonZero_unchanged() {
        int[] nums = {5};
        solution.moveZeroes(nums);
        assertArrayEquals(new int[]{5}, nums);
    }

    // --- Zero at start ---

    @Test
    void testZeroAtStart_movedToEnd() {
        int[] nums = {0, 1, 2};
        solution.moveZeroes(nums);
        assertArrayEquals(new int[]{1, 2, 0}, nums);
    }

    // --- Zero at end ---

    @Test
    void testZeroAtEnd_staysAtEnd() {
        int[] nums = {1, 2, 0};
        solution.moveZeroes(nums);
        assertArrayEquals(new int[]{1, 2, 0}, nums);
    }

    // --- Consecutive zeroes ---

    @Test
    void testConsecutiveZeroes_allMovedToEnd() {
        int[] nums = {0, 0, 1};
        solution.moveZeroes(nums);
        assertArrayEquals(new int[]{1, 0, 0}, nums);
    }

    @Test
    void testConsecutiveZeroesInMiddle() {
        int[] nums = {1, 0, 0, 2};
        solution.moveZeroes(nums);
        assertArrayEquals(new int[]{1, 2, 0, 0}, nums);
    }

    // --- Relative order preserved ---

    @Test
    void testRelativeOrderPreserved() {
        int[] nums = {3, 0, 1, 0, 2};
        solution.moveZeroes(nums);
        assertArrayEquals(new int[]{3, 1, 2, 0, 0}, nums);
    }

    @Test
    void testRelativeOrderPreserved_longerArray() {
        int[] nums = {0, 5, 0, 3, 0, 1, 0, 2};
        solution.moveZeroes(nums);
        assertArrayEquals(new int[]{5, 3, 1, 2, 0, 0, 0, 0}, nums);
    }

    // --- Negative values ---

    @Test
    void testNegativeValues_preservedAndOrdered() {
        int[] nums = {-1, 0, -3, 0, 2};
        solution.moveZeroes(nums);
        assertArrayEquals(new int[]{-1, -3, 2, 0, 0}, nums);
    }

    // --- Two elements ---

    @Test
    void testTwoElements_zeroFirst() {
        int[] nums = {0, 1};
        solution.moveZeroes(nums);
        assertArrayEquals(new int[]{1, 0}, nums);
    }

    @Test
    void testTwoElements_zeroLast() {
        int[] nums = {1, 0};
        solution.moveZeroes(nums);
        assertArrayEquals(new int[]{1, 0}, nums);
    }

    @Test
    void testTwoElements_bothZero() {
        int[] nums = {0, 0};
        solution.moveZeroes(nums);
        assertArrayEquals(new int[]{0, 0}, nums);
    }
}