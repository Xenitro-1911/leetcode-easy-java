package problems.p219_contains_duplicate_ii;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    private Solution solution;

    @BeforeEach
    void setUp() {
        solution = new Solution();
    }

    // --- Basic true cases ---

    @Test
    void testBasicDuplicateWithinK_returnsTrue() {
        // indices 0 and 3, diff = 3 = k
        assertTrue(solution.containsNearbyDuplicate(new int[]{1, 2, 3, 1}, 3));
    }

    @Test
    void testAdjacentDuplicate_returnsTrue() {
        // indices 0 and 1, diff = 1 <= k=2
        assertTrue(solution.containsNearbyDuplicate(new int[]{1, 1, 2, 3}, 2));
    }

    @Test
    void testDuplicateExactlyAtK_returnsTrue() {
        // distance exactly equals k
        assertTrue(solution.containsNearbyDuplicate(new int[]{1, 2, 1}, 2));
    }

    // --- Basic false cases ---

    @Test
    void testDuplicateOutsideK_returnsFalse() {
        // duplicate exists but distance > k
        assertFalse(solution.containsNearbyDuplicate(new int[]{1, 2, 3, 1, 2, 3}, 2));
    }

    @Test
    void testAllDistinct_returnsFalse() {
        assertFalse(solution.containsNearbyDuplicate(new int[]{1, 2, 3, 4}, 3));
    }

    // --- Edge cases ---

    @Test
    void testKEqualsZero_returnsFalse() {
        // k=0 means indices must be equal — impossible for two distinct indices
        assertFalse(solution.containsNearbyDuplicate(new int[]{1, 1, 1}, 0));
    }

    @Test
    void testSingleElement_returnsFalse() {
        assertFalse(solution.containsNearbyDuplicate(new int[]{1}, 1));
    }

    @Test
    void testTwoSameElements_returnsTrue() {
        assertTrue(solution.containsNearbyDuplicate(new int[]{1, 1}, 1));
    }

    @Test
    void testTwoSameElementsKTooSmall_returnsFalse() {
        // distance is 2 but k=1
        assertFalse(solution.containsNearbyDuplicate(new int[]{1, 2, 1}, 1));
    }

    // --- Sliding window eviction cases ---

    @Test
    void testDuplicateJustOutsideWindow_returnsFalse() {
        // 1 appears at i=0 and i=4, k=3 → distance 4 > k
        assertFalse(solution.containsNearbyDuplicate(new int[]{1, 2, 3, 4, 1}, 3));
    }

    @Test
    void testDuplicateJustInsideWindow_returnsTrue() {
        // 1 appears at i=0 and i=3, k=3 → distance 3 = k
        assertTrue(solution.containsNearbyDuplicate(new int[]{1, 2, 3, 1, 4}, 3));
    }

    @Test
    void testWindowSlidesCorrectly_returnsFalse() {
        // ensures evicted elements don't falsely trigger
        assertFalse(solution.containsNearbyDuplicate(new int[]{1, 2, 3, 4, 5, 1}, 4));
    }

    // --- Negative and boundary values ---

    @Test
    void testNegativeDuplicateWithinK_returnsTrue() {
        assertTrue(solution.containsNearbyDuplicate(new int[]{-1, 0, -1}, 2));
    }

    @Test
    void testIntMinDuplicateWithinK_returnsTrue() {
        assertTrue(solution.containsNearbyDuplicate(new int[]{Integer.MIN_VALUE, 0, Integer.MIN_VALUE}, 2));
    }

    @Test
    void testKLargerThanArray_returnsTrue() {
        // k larger than array length — any duplicate qualifies
        assertTrue(solution.containsNearbyDuplicate(new int[]{1, 2, 3, 1}, 100));
    }
}