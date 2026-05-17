package problems.p169_majority_element;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    private Solution solution;

    @BeforeEach
    void setUp() {
        solution = new Solution();
    }

    // --- Provided Examples ---

    @Test
    void example1_oddLength() {
        // [3,2,3] → 3 appears twice out of 3
        assertEquals(3, solution.majorityElement(new int[]{3, 2, 3}));
    }

    @Test
    void example2_mixed() {
        // [2,2,1,1,1,2,2] → 2 appears 4 times out of 7
        assertEquals(2, solution.majorityElement(new int[]{2, 2, 1, 1, 1, 2, 2}));
    }

    // --- Single Element ---

    @Test
    void singleElement() {
        assertEquals(42, solution.majorityElement(new int[]{42}));
    }

    // --- All Same ---

    @Test
    void allSameElement() {
        assertEquals(5, solution.majorityElement(new int[]{5, 5, 5, 5, 5}));
    }

    // --- Two Distinct Values ---

    @Test
    void twoDistinctMajorityAtFront() {
        // majority element appears first and dominates
        assertEquals(1, solution.majorityElement(new int[]{1, 1, 1, 2, 2}));
    }

    @Test
    void twoDistinctMajorityAtBack() {
        // minority element leads; candidate must switch
        assertEquals(2, solution.majorityElement(new int[]{1, 1, 2, 2, 2}));
    }

    // --- Candidate Switching ---

    @Test
    void candidateSwitchesMultipleTimes() {
        // count hits 0 several times forcing candidate changes
        assertEquals(3, solution.majorityElement(new int[]{1, 2, 3, 3, 3, 1, 3, 2, 3}));
    }

    @Test
    void minorityLeadsBeforeMajorityTakesOver() {
        assertEquals(7, solution.majorityElement(new int[]{1, 2, 3, 7, 7, 7, 7}));
    }

    // --- Negative Numbers ---

    @Test
    void negativeNumbers() {
        assertEquals(-1, solution.majorityElement(new int[]{-1, -1, -2, -1, -2}));
    }

    @Test
    void mixedPositiveAndNegative() {
        assertEquals(-3, solution.majorityElement(new int[]{-3, 1, -3, -3, 2}));
    }

    // --- Even Length Arrays ---

    @Test
    void evenLengthArray() {
        // majority must appear > n/2 = 3 times, so at least 4
        assertEquals(4, solution.majorityElement(new int[]{4, 4, 4, 4, 1, 2}));
    }

    // --- Large Input ---

    @Test
    void largeArrayAllSame() {
        int[] nums = new int[10001];
        java.util.Arrays.fill(nums, 99);
        assertEquals(99, solution.majorityElement(nums));
    }

    @Test
    void largeArrayMajorityInterspersed() {
        // 6001 nines among 10001 elements
        int[] nums = new int[10001];
        java.util.Arrays.fill(nums, 0);
        for (int i = 0; i < 6001; i++) {
            nums[i * 10001 / 6001 % 10001] = 9;
        }
        // recount to find actual majority (may shift due to modular placement)
        // just verify the contract: result appears > 5000 times
        int result = solution.majorityElement(nums);
        long freq = 0;
        for (int n : nums) if (n == result) freq++;
        assertTrue(freq > 10001 / 2);
    }
}