package problems.p001_two_sum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    private Solution solution;

    @BeforeEach
    void setUp() {
        solution = new Solution();
    }

    @Test
    void testBasicCase() {
        // [2,7] add up to 9, at indices 0 and 1
        assertArrayEquals(new int[]{0, 1}, solution.twoSum(new int[]{2, 7, 11, 15}, 9));
    }

    @Test
    void testAnswerNotAtStart() {
        // [1,3] add up to 6, at indices 1 and 2 — not the first two elements
        assertArrayEquals(new int[]{1, 2}, solution.twoSum(new int[]{3, 2, 4}, 6));
    }

    @Test
    void testDuplicateNumbers() {
        // same number used twice but at different indices
        assertArrayEquals(new int[]{0, 1}, solution.twoSum(new int[]{3, 3}, 6));
    }

    @Test
    void testNegativeNumbers() {
        assertArrayEquals(new int[]{0, 2}, solution.twoSum(new int[]{-3, 1, 7}, 4));
    }

    @Test
    void testLargerArray() {
        assertArrayEquals(new int[]{1, 4}, solution.twoSum(new int[]{1, 4, 5, 6, 11}, 15));
    }
}