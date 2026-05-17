package problems.p268_missing_number;

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
        // [3,0,1] → 2
        assertEquals(2, solution.missingNumber(new int[]{3, 0, 1}));
    }

    @Test
    void testLeetcodeExample2() {
        // [0,1] → 2
        assertEquals(2, solution.missingNumber(new int[]{0, 1}));
    }

    @Test
    void testLeetcodeExample3() {
        // [9,6,4,2,3,5,7,0,1] → 8
        assertEquals(8, solution.missingNumber(new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1}));
    }

    // --- Missing 0 (first element) ---

    @Test
    void testMissingZero_singleElement() {
        // [1] → 0
        assertEquals(0, solution.missingNumber(new int[]{1}));
    }

    @Test
    void testMissingZero_multipleElements() {
        // [1,2,3] → 0
        assertEquals(0, solution.missingNumber(new int[]{1, 2, 3}));
    }

    // --- Missing last element (n) ---

    @Test
    void testMissingLast_singleElement() {
        // [0] → 1
        assertEquals(1, solution.missingNumber(new int[]{0}));
    }

    @Test
    void testMissingLast_multipleElements() {
        // [0,1,2] → 3
        assertEquals(3, solution.missingNumber(new int[]{0, 1, 2}));
    }

    // --- Missing middle elements ---

    @Test
    void testMissingMiddle_small() {
        // [0,2] → 1
        assertEquals(1, solution.missingNumber(new int[]{0, 2}));
    }

    @Test
    void testMissingMiddle_larger() {
        // [0,1,3,4] → 2
        assertEquals(2, solution.missingNumber(new int[]{0, 1, 3, 4}));
    }

    // --- Unsorted arrays ---

    @Test
    void testUnsorted_missingMiddle() {
        assertEquals(2, solution.missingNumber(new int[]{3, 0, 1}));
    }

    @Test
    void testUnsorted_missingZero() {
        assertEquals(0, solution.missingNumber(new int[]{2, 1, 3}));
    }

    @Test
    void testUnsorted_missingLast() {
        assertEquals(4, solution.missingNumber(new int[]{3, 1, 0, 2}));
    }

    // --- Single element edge cases ---

    @Test
    void testSingleElementZero_missingOne() {
        assertEquals(1, solution.missingNumber(new int[]{0}));
    }

    @Test
    void testSingleElementOne_missingZero() {
        assertEquals(0, solution.missingNumber(new int[]{1}));
    }

    // --- Larger arrays ---

    @Test
    void testLargeArray_missingMiddle() {
        int[] nums = new int[99];
        int missing = 50;
        int j = 0;
        for (int i = 0; i <= 99; i++) {
            if (i != missing) nums[j++] = i;
        }
        assertEquals(missing, solution.missingNumber(nums));
    }

    @Test
    void testLargeArray_missingLast() {
        int[] nums = new int[99];
        for (int i = 0; i < 99; i++) nums[i] = i;
        assertEquals(99, solution.missingNumber(nums));
    }
}