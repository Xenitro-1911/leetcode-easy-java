package problems.p035_search_insert_position;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    private Solution solution;

    @BeforeEach
    void setUp() {
        solution = new Solution();
    }

    // --- Target FOUND in array ---

    @Test
    void testTargetFoundAtStart() {
        assertEquals(0, solution.searchInsert(new int[]{1, 3, 5, 6}, 1));
    }

    @Test
    void testTargetFoundAtEnd() {
        assertEquals(3, solution.searchInsert(new int[]{1, 3, 5, 6}, 6));
    }

    @Test
    void testTargetFoundInMiddle() {
        assertEquals(2, solution.searchInsert(new int[]{1, 3, 5, 6}, 5));
    }

    // --- Target NOT found — insert position ---

    @Test
    void testInsertAtStart() {
        // 0 is less than all elements → insert at index 0
        assertEquals(0, solution.searchInsert(new int[]{1, 3, 5, 6}, 0));
    }

    @Test
    void testInsertAtEnd() {
        // 7 is greater than all elements → insert at index 4
        assertEquals(4, solution.searchInsert(new int[]{1, 3, 5, 6}, 7));
    }

    @Test
    void testInsertInMiddle() {
        // 2 goes between index 0 and 1
        assertEquals(1, solution.searchInsert(new int[]{1, 3, 5, 6}, 2));
    }

    @Test
    void testInsertInMiddle2() {
        // 4 goes between index 1 and 2
        assertEquals(2, solution.searchInsert(new int[]{1, 3, 5, 6}, 4));
    }

    // --- Edge cases ---

    @Test
    void testSingleElementFound() {
        assertEquals(0, solution.searchInsert(new int[]{5}, 5));
    }

    @Test
    void testSingleElementInsertBefore() {
        assertEquals(0, solution.searchInsert(new int[]{5}, 3));
    }

    @Test
    void testSingleElementInsertAfter() {
        assertEquals(1, solution.searchInsert(new int[]{5}, 7));
    }

    @Test
    void testTwoElementsInsertBetween() {
        assertEquals(1, solution.searchInsert(new int[]{1, 3}, 2));
    }

    @Test
    void testLargeArray() {
        int[] nums = new int[1000];
        for (int i = 0; i < 1000; i++) nums[i] = i * 2; // [0, 2, 4, ..., 1998]
        // Target 999 (odd, not in array) → should be inserted at index 500
        assertEquals(500, solution.searchInsert(nums, 999));
    }
}