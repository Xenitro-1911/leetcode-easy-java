package problems.p231_power_of_two;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    private Solution solution;

    @BeforeEach
    void setUp() {
        solution = new Solution();
    }

    // --- Powers of two ---

    @Test
    void testOne_returnsTrue() {
        // 2^0 = 1
        assertTrue(solution.isPowerOfTwo(1));
    }

    @Test
    void testTwo_returnsTrue() {
        assertTrue(solution.isPowerOfTwo(2));
    }

    @Test
    void testFour_returnsTrue() {
        assertTrue(solution.isPowerOfTwo(4));
    }

    @Test
    void testEight_returnsTrue() {
        assertTrue(solution.isPowerOfTwo(8));
    }

    @Test
    void testLargePowerOfTwo_returnsTrue() {
        // 2^20 = 1048576
        assertTrue(solution.isPowerOfTwo(1 << 20));
    }

    @Test
    void testLargestPowerOfTwo_returnsTrue() {
        // 2^30 = 1073741824 (largest power of two within positive int range)
        assertTrue(solution.isPowerOfTwo(1 << 30));
    }

    // --- Not powers of two ---

    @Test
    void testThree_returnsFalse() {
        assertFalse(solution.isPowerOfTwo(3));
    }

    @Test
    void testSix_returnsFalse() {
        assertFalse(solution.isPowerOfTwo(6));
    }

    @Test
    void testTen_returnsFalse() {
        assertFalse(solution.isPowerOfTwo(10));
    }

    @Test
    void testLargeNonPower_returnsFalse() {
        assertFalse(solution.isPowerOfTwo(1000000007));
    }

    // --- Edge cases that catch missing n > 0 guard ---

    @Test
    void testZero_returnsFalse() {
        // 0 & -1 == 0 without guard — must return false
        assertFalse(solution.isPowerOfTwo(0));
    }

    @Test
    void testNegativeOne_returnsFalse() {
        assertFalse(solution.isPowerOfTwo(-1));
    }

    @Test
    void testNegativeTwo_returnsFalse() {
        assertFalse(solution.isPowerOfTwo(-2));
    }

    @Test
    void testIntMinValue_returnsFalse() {
        // Integer.MIN_VALUE = 0x80000000 — passes bit check without n > 0 guard
        assertFalse(solution.isPowerOfTwo(Integer.MIN_VALUE));
    }

    @Test
    void testIntMaxValue_returnsFalse() {
        // 2^31 - 1 — all bits set, not a power of two
        assertFalse(solution.isPowerOfTwo(Integer.MAX_VALUE));
    }
}