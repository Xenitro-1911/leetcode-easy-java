package problems.p258_add_digits;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    private Solution solution;

    @BeforeEach
    void setUp() {
        solution = new Solution();
    }

    // --- Base cases ---

    @Test
    void testZero_returnsZero() {
        assertEquals(0, solution.addDigits(0));
    }

    @Test
    void testSingleDigit_returnsItself() {
        assertEquals(5, solution.addDigits(5));
    }

    @Test
    void testOne_returnsOne() {
        assertEquals(1, solution.addDigits(1));
    }

    @Test
    void testNine_returnsNine() {
        // 9 is a multiple of 9 — digital root is 9 not 0
        assertEquals(9, solution.addDigits(9));
    }

    // --- Two digit numbers ---

    @Test
    void testTen_returnsOne() {
        // 1 + 0 = 1
        assertEquals(1, solution.addDigits(10));
    }

    @Test
    void testEleven_returnsTwo() {
        // 1 + 1 = 2
        assertEquals(2, solution.addDigits(11));
    }

    @Test
    void testEighteen_returnsNine() {
        // 1 + 8 = 9 — multiple of 9 edge case
        assertEquals(9, solution.addDigits(18));
    }

    @Test
    void testNineteen_returnsOne() {
        // 1 + 9 = 10 → 1 + 0 = 1
        assertEquals(1, solution.addDigits(19));
    }

    // --- LeetCode example ---

    @Test
    void testLeetcodeExample_thirtyEight() {
        // 3 + 8 = 11 → 1 + 1 = 2
        assertEquals(2, solution.addDigits(38));
    }

    // --- Multiples of 9 (critical edge cases for digital root formula) ---

    @Test
    void testMultipleOfNine_27() {
        assertEquals(9, solution.addDigits(27));
    }

    @Test
    void testMultipleOfNine_99() {
        // 9 + 9 = 18 → 1 + 8 = 9
        assertEquals(9, solution.addDigits(99));
    }

    @Test
    void testMultipleOfNine_999() {
        assertEquals(9, solution.addDigits(999));
    }

    // --- Multi-step reductions ---

    @Test
    void testMultiStep_199() {
        // 1 + 9 + 9 = 19 → 1 + 9 = 10 → 1 + 0 = 1
        assertEquals(1, solution.addDigits(199));
    }

    @Test
    void testMultiStep_9999() {
        // 9+9+9+9 = 36 → 3+6 = 9
        assertEquals(9, solution.addDigits(9999));
    }

    // --- Large numbers ---

    @Test
    void testLargeNumber() {
        // 1000000000 → 1+0+...= 1
        assertEquals(1, solution.addDigits(1000000000));
    }

    @Test
    void testIntMaxValue() {
        // Integer.MAX_VALUE = 2147483647 → 2+1+4+7+4+8+3+6+4+7 = 46 → 4+6 = 10 → 1
        assertEquals(1, solution.addDigits(Integer.MAX_VALUE));
    }
}