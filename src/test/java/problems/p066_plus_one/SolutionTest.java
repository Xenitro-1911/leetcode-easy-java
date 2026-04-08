package problems.p066_plus_one;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    private final Solution solution = new Solution();

    // --- Basic cases ---

    @Test
    void testSimpleIncrement() {
        // [1,2,3] → [1,2,4]
        assertArrayEquals(new int[]{1, 2, 4}, solution.plusOne(new int[]{1, 2, 3}));
    }

    @Test
    void testSimpleIncrement2() {
        // [4,3,2,1] → [4,3,2,2]
        assertArrayEquals(new int[]{4, 3, 2, 2}, solution.plusOne(new int[]{4, 3, 2, 1}));
    }

    // --- Carry propagation ---

    @Test
    void testSingleNine() {
        // [9] → [1,0]
        assertArrayEquals(new int[]{1, 0}, solution.plusOne(new int[]{9}));
    }

    @Test
    void testTrailingNines() {
        // [1,9,9] → [2,0,0]
        assertArrayEquals(new int[]{2, 0, 0}, solution.plusOne(new int[]{1, 9, 9}));
    }

    @Test
    void testMiddleNine() {
        // [1,9,0] → [1,9,1]
        assertArrayEquals(new int[]{1, 9, 1}, solution.plusOne(new int[]{1, 9, 0}));
    }

    // --- All nines edge cases ---

    @Test
    void testAllNines_two_digits() {
        // [9,9] → [1,0,0]
        assertArrayEquals(new int[]{1, 0, 0}, solution.plusOne(new int[]{9, 9}));
    }

    @Test
    void testAllNines_three_digits() {
        // [9,9,9] → [1,0,0,0]
        assertArrayEquals(new int[]{1, 0, 0, 0}, solution.plusOne(new int[]{9, 9, 9}));
    }

    // --- Single digit ---

    @Test
    void testSingleDigitNonNine() {
        // [5] → [6]
        assertArrayEquals(new int[]{6}, solution.plusOne(new int[]{5}));
    }

    @Test
    void testSingleDigitZero() {
        // [0] → [1]
        assertArrayEquals(new int[]{1}, solution.plusOne(new int[]{0}));
    }

    // --- Larger numbers ---

    @Test
    void testLargeNumber() {
        // [1,0,0,0,0] → [1,0,0,0,1]
        assertArrayEquals(new int[]{1, 0, 0, 0, 1}, solution.plusOne(new int[]{1, 0, 0, 0, 0}));
    }

    @Test
    void testNineAtEndOnly() {
        // [2,9] → [3,0]
        assertArrayEquals(new int[]{3, 0}, solution.plusOne(new int[]{2, 9}));
    }
}