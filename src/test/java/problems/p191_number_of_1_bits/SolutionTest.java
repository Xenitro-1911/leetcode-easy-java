package problems.p191_number_of_1_bits;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    private Solution solution;

    @BeforeEach
    void setUp() {
        solution = new Solution();
    }

    // --- Basic cases ---

    @Test
    void testOne_returnsOne() {
        // 00000000000000000000000000000001
        assertEquals(1, solution.hammingWeight(1));
    }

    @Test
    void testTwo_returnsOne() {
        // 00000000000000000000000000000010
        assertEquals(1, solution.hammingWeight(2));
    }

    @Test
    void testThree_returnsTwo() {
        // 00000000000000000000000000000011
        assertEquals(2, solution.hammingWeight(3));
    }

    // --- LeetCode examples ---

    @Test
    void testEleven_returnsThree() {
        // 1011 → three 1s
        assertEquals(3, solution.hammingWeight(11));
    }

    @Test
    void testThirteen_returnsThree() {
        // 1101 → three 1s
        assertEquals(3, solution.hammingWeight(13));
    }

    @Test
    void test128_returnsOne() {
        // 10000000 → one 1
        assertEquals(1, solution.hammingWeight(128));
    }

    // --- Powers of 2 (always 1 set bit) ---

    @Test
    void testPowerOfTwo_alwaysReturnsOne() {
        assertEquals(1, solution.hammingWeight(1));
        assertEquals(1, solution.hammingWeight(1024));
        assertEquals(1, solution.hammingWeight(1 << 30));
    }

    // --- Boundary values ---

    @Test
    void testMaxInt_returns31() {
        // 01111111111111111111111111111111 → 31 ones
        assertEquals(31, solution.hammingWeight(Integer.MAX_VALUE));
    }

    @Test
    void testMinInt_returnsOne() {
        // 10000000000000000000000000000000 → 1 one
        assertEquals(1, solution.hammingWeight(Integer.MIN_VALUE));
    }

    @Test
    void testAllOnes_returns32() {
        // 11111111111111111111111111111111 = -1 in signed Java
        assertEquals(32, solution.hammingWeight(-1));
    }

    // --- Negative numbers ---

    @Test
    void testNegativeThree_returns31() {
        // -3 in two's complement = 11111111111111111111111111111101 → 31 ones
        assertEquals(31, solution.hammingWeight(-3));
    }

    @Test
    void testNegativeTwo_returns31() {
        // -2 = 11111111111111111111111111111110 → 31 ones
        assertEquals(31, solution.hammingWeight(-2));
    }

    // --- Alternating bit patterns ---

    @Test
    void testAlternatingBits_0x55555555() {
        // 01010101010101010101010101010101 → 16 ones
        assertEquals(16, solution.hammingWeight(0x55555555));
    }

    @Test
    void testAlternatingBits_0xAAAAAAAA() {
        // 10101010101010101010101010101010 → 16 ones
        assertEquals(16, solution.hammingWeight(0xAAAAAAAA));
    }
}