package problems.p263_ugly_number;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    private Solution solution;

    @BeforeEach
    void setUp() {
        solution = new Solution();
    }

    // --- Non-positive edge cases ---

    @Test
    void testZero_returnsFalse() {
        assertFalse(solution.isUgly(0));
    }

    @Test
    void testNegativeOne_returnsFalse() {
        assertFalse(solution.isUgly(-1));
    }

    @Test
    void testNegativeLarge_returnsFalse() {
        assertFalse(solution.isUgly(-12));
    }

    // --- Special case: 1 ---

    @Test
    void testOne_returnsTrue() {
        // 1 has no prime factors — defined as ugly
        assertTrue(solution.isUgly(1));
    }

    // --- Pure powers of 2, 3, 5 ---

    @Test
    void testPowerOfTwo_returnsTrue() {
        assertTrue(solution.isUgly(8));   // 2^3
    }

    @Test
    void testPowerOfThree_returnsTrue() {
        assertTrue(solution.isUgly(27));  // 3^3
    }

    @Test
    void testPowerOfFive_returnsTrue() {
        assertTrue(solution.isUgly(25));  // 5^2
    }

    // --- Mixed prime factors 2, 3, 5 ---

    @Test
    void testTwelve_returnsTrue() {
        // 12 = 2^2 * 3
        assertTrue(solution.isUgly(12));
    }

    @Test
    void testThirty_returnsTrue() {
        // 30 = 2 * 3 * 5
        assertTrue(solution.isUgly(30));
    }

    @Test
    void testSixty_returnsTrue() {
        // 60 = 2^2 * 3 * 5
        assertTrue(solution.isUgly(60));
    }

    // --- LeetCode examples ---

    @Test
    void testLeetcodeExample_six_returnsTrue() {
        assertTrue(solution.isUgly(6));
    }

    @Test
    void testLeetcodeExample_fourteen_returnsFalse() {
        // 14 = 2 * 7 — 7 is not 2, 3, or 5
        assertFalse(solution.isUgly(14));
    }

    // --- Non-ugly numbers with other prime factors ---

    @Test
    void testSeven_returnsFalse() {
        assertFalse(solution.isUgly(7));
    }

    @Test
    void testEleven_returnsFalse() {
        assertFalse(solution.isUgly(11));
    }

    @Test
    void testThirteen_returnsFalse() {
        assertFalse(solution.isUgly(13));
    }

    @Test
    void testFortyTwo_returnsFalse() {
        // 42 = 2 * 3 * 7 — has prime factor 7
        assertFalse(solution.isUgly(42));
    }

    // --- Large ugly number ---

    @Test
    void testLargeUglyNumber_returnsTrue() {
        // 2^10 = 1024
        assertTrue(solution.isUgly(1024));
    }

    @Test
    void testLargeNonUgly_returnsFalse() {
        // 1000000007 is prime
        assertFalse(solution.isUgly(1000000007));
    }
}