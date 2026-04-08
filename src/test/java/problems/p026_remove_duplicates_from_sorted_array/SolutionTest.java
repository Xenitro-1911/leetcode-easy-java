package problems.p026_remove_duplicates_from_sorted_array;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SolutionTest {

    private final Solution solution = new Solution();

    // Helper to verify both k and the first k elements of nums
    private void assertResult(int[] nums, int[] expectedNums) {
        int k = solution.removeDuplicates(nums);
        assertEquals(expectedNums.length, k,
                "Expected k=" + expectedNums.length + " but got k=" + k);
        for (int i = 0; i < k; i++) {
            assertEquals(expectedNums[i], nums[i],
                    "Mismatch at index " + i + ": expected " + expectedNums[i] + " but got " + nums[i]);
        }
    }

    // -----------------------------------------------------------------------
    // Basic cases
    // -----------------------------------------------------------------------

    @Test
    public void testExample1() {
        // [1,1,2] → k=2, nums=[1,2,_]
        int[] nums = {1, 1, 2};
        int[] expected = {1, 2};
        assertResult(nums, expected);
    }

    @Test
    public void testExample2() {
        // [0,0,1,1,1,2,2,3,3,4] → k=5, nums=[0,1,2,3,4,...]
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int[] expected = {0, 1, 2, 3, 4};
        assertResult(nums, expected);
    }

    // -----------------------------------------------------------------------
    // Edge cases
    // -----------------------------------------------------------------------

    @Test
    public void testSingleElement() {
        // Only one element → k=1
        int[] nums = {42};
        int[] expected = {42};
        assertResult(nums, expected);
    }

    @Test
    public void testAllUnique() {
        // No duplicates at all → k = nums.length
        int[] nums = {1, 2, 3, 4, 5};
        int[] expected = {1, 2, 3, 4, 5};
        assertResult(nums, expected);
    }

    @Test
    public void testAllSame() {
        // All elements identical → k=1
        int[] nums = {7, 7, 7, 7};
        int[] expected = {7};
        assertResult(nums, expected);
    }

    @Test
    public void testTwoElements_Duplicate() {
        int[] nums = {3, 3};
        int[] expected = {3};
        assertResult(nums, expected);
    }

    @Test
    public void testTwoElements_Unique() {
        int[] nums = {3, 4};
        int[] expected = {3, 4};
        assertResult(nums, expected);
    }

    // -----------------------------------------------------------------------
    // Larger / varied cases
    // -----------------------------------------------------------------------

    @Test
    public void testNegativeNumbers() {
        int[] nums = {-3, -3, -1, 0, 0, 2};
        int[] expected = {-3, -1, 0, 2};
        assertResult(nums, expected);
    }

    @Test
    public void testLongRunOfDuplicates() {
        int[] nums = {1, 1, 1, 1, 1, 2};
        int[] expected = {1, 2};
        assertResult(nums, expected);
    }

    @Test
    public void testAlternatingPattern() {
        // Each value appears twice
        int[] nums = {1, 1, 2, 2, 3, 3, 4, 4};
        int[] expected = {1, 2, 3, 4};
        assertResult(nums, expected);
    }
}