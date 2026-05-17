package problems.p228_summary_ranges;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class SolutionTest {

    private Solution solution;

    @BeforeEach
    void setUp() {
        solution = new Solution();
    }

    // --- Empty and single element ---

    @Test
    void testEmptyArray_returnsEmptyList() {
        assertTrue(solution.summaryRanges(new int[]{}).isEmpty());
    }

    @Test
    void testSingleElement_returnsSingleRange() {
        assertEquals(List.of("0"), solution.summaryRanges(new int[]{0}));
    }

    @Test
    void testSingleNegative_returnsSingleRange() {
        assertEquals(List.of("-1"), solution.summaryRanges(new int[]{-1}));
    }

    // --- All consecutive (one range) ---

    @Test
    void testAllConsecutive_returnsOneRange() {
        assertEquals(List.of("0->4"), solution.summaryRanges(new int[]{0, 1, 2, 3, 4}));
    }

    @Test
    void testTwoConsecutive_returnsOneRange() {
        assertEquals(List.of("1->2"), solution.summaryRanges(new int[]{1, 2}));
    }

    // --- All isolated (no ranges) ---

    @Test
    void testNoConsecutive_allSingles() {
        assertEquals(List.of("0", "2", "4"), solution.summaryRanges(new int[]{0, 2, 4}));
    }

    // --- LeetCode examples ---

    @Test
    void testLeetcodeExample1() {
        // [0,1,2,4,5,7] → ["0->2","4->5","7"]
        assertEquals(List.of("0->2", "4->5", "7"),
                solution.summaryRanges(new int[]{0, 1, 2, 4, 5, 7}));
    }

    @Test
    void testLeetcodeExample2() {
        // [0,2,3,4,6,8,9] → ["0","2->4","6","8->9"]
        assertEquals(List.of("0", "2->4", "6", "8->9"),
                solution.summaryRanges(new int[]{0, 2, 3, 4, 6, 8, 9}));
    }

    // --- Single element ranges mixed with multi-element ---

    @Test
    void testMixedSingleAndRange() {
        assertEquals(List.of("-1", "1->3", "5"),
                solution.summaryRanges(new int[]{-1, 1, 2, 3, 5}));
    }

    @Test
    void testRangeAtStartAndEnd() {
        assertEquals(List.of("1->3", "5", "7->9"),
                solution.summaryRanges(new int[]{1, 2, 3, 5, 7, 8, 9}));
    }

    // --- Boundary / edge values ---

    @Test
    void testNegativeRange() {
        assertEquals(List.of("-3->-1"), solution.summaryRanges(new int[]{-3, -2, -1}));
    }

    @Test
    void testNegativeToPositiveRange() {
        assertEquals(List.of("-2->2"), solution.summaryRanges(new int[]{-2, -1, 0, 1, 2}));
    }

    @Test
    void testIntMinValue_singleElement() {
        assertEquals(List.of(String.valueOf(Integer.MIN_VALUE)),
                solution.summaryRanges(new int[]{Integer.MIN_VALUE}));
    }

    @Test
    void testIntMaxValue_singleElement() {
        assertEquals(List.of(String.valueOf(Integer.MAX_VALUE)),
                solution.summaryRanges(new int[]{Integer.MAX_VALUE}));
    }

    // --- Two elements ---

    @Test
    void testTwoNonConsecutive_twoSingles() {
        assertEquals(List.of("1", "3"), solution.summaryRanges(new int[]{1, 3}));
    }
}