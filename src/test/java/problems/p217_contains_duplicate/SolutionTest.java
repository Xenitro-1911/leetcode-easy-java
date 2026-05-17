package problems.p217_contains_duplicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    private Solution solution;

    @BeforeEach
    void setUp() {
        solution = new Solution();
    }

    // --- Basic duplicate cases ---

    @Test
    void testSimpleDuplicate_returnsTrue() {
        assertTrue(solution.containsDuplicate(new int[]{1, 2, 3, 1}));
    }

    @Test
    void testAllSame_returnsTrue() {
        assertTrue(solution.containsDuplicate(new int[]{1, 1, 1, 1}));
    }

    @Test
    void testAdjacentDuplicate_returnsTrue() {
        assertTrue(solution.containsDuplicate(new int[]{1, 1, 2, 3}));
    }

    @Test
    void testDuplicateAtEnd_returnsTrue() {
        assertTrue(solution.containsDuplicate(new int[]{1, 2, 3, 4, 5, 2}));
    }

    // --- No duplicate cases ---

    @Test
    void testAllDistinct_returnsFalse() {
        assertFalse(solution.containsDuplicate(new int[]{1, 2, 3, 4}));
    }

    @Test
    void testSingleElement_returnsFalse() {
        assertFalse(solution.containsDuplicate(new int[]{1}));
    }

    @Test
    void testTwoDistinct_returnsFalse() {
        assertFalse(solution.containsDuplicate(new int[]{1, 2}));
    }

    @Test
    void testTwoSame_returnsTrue() {
        assertTrue(solution.containsDuplicate(new int[]{5, 5}));
    }

    // --- Negative and mixed values ---

    @Test
    void testNegativeDuplicate_returnsTrue() {
        assertTrue(solution.containsDuplicate(new int[]{-1, -2, -3, -1}));
    }

    @Test
    void testNegativeDistinct_returnsFalse() {
        assertFalse(solution.containsDuplicate(new int[]{-1, -2, -3, -4}));
    }

    @Test
    void testMixedSignsDuplicate_returnsTrue() {
        assertTrue(solution.containsDuplicate(new int[]{-1, 0, 1, -1}));
    }

    @Test
    void testMixedSignsDistinct_returnsFalse() {
        assertFalse(solution.containsDuplicate(new int[]{-3, -1, 0, 1, 3}));
    }

    // --- Boundary values ---

    @Test
    void testIntMinAndMaxDistinct_returnsFalse() {
        assertFalse(solution.containsDuplicate(new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE}));
    }

    @Test
    void testIntMinDuplicate_returnsTrue() {
        assertTrue(solution.containsDuplicate(new int[]{Integer.MIN_VALUE, 0, Integer.MIN_VALUE}));
    }

    @Test
    void testLargeDistinctArray_returnsFalse() {
        int[] nums = new int[1000];
        for (int i = 0; i < 1000; i++) nums[i] = i;
        assertFalse(solution.containsDuplicate(nums));
    }

    @Test
    void testLargeArrayWithDuplicate_returnsTrue() {
        int[] nums = new int[1001];
        for (int i = 0; i < 1000; i++) nums[i] = i;
        nums[1000] = 0; // duplicate of first element
        assertTrue(solution.containsDuplicate(nums));
    }
}