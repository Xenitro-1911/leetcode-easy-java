package problems.p190_reverse_bits;

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
    void testZero_returnsZero() {
        assertEquals(0, solution.reverseBits(0));
    }

    @Test
    void testOne_returnsMinValue() {
        // 00000000000000000000000000000001 → 10000000000000000000000000000000
        assertEquals(Integer.MIN_VALUE, solution.reverseBits(1));
    }

    @Test
    void testAllOnes_returnsAllOnes() {
        // 11111111111111111111111111111111 → 11111111111111111111111111111111
        assertEquals(-1, solution.reverseBits(-1));
    }

    // --- LeetCode examples ---

    @Test
    void testLeetcodeExample1() {
        // n =  43261596  (00000010100101000001111010011100)
        // ans = 964176192 (00111001011110000010100101000000)
        assertEquals(964176192, solution.reverseBits(43261596));
    }

    @Test
    void testLeetcodeExample2() {
        // n =  -3 (unsigned: 4294967293) (11111111111111111111111111111101)
        // ans = -1073741825             (10111111111111111111111111111111)
        assertEquals(-1073741825, solution.reverseBits(-3));
    }

    // --- Bit boundary cases ---

    @Test
    void testMinValue_returnsOne() {
        // 10000000000000000000000000000000 → 00000000000000000000000000000001
        assertEquals(1, solution.reverseBits(Integer.MIN_VALUE));
    }

    @Test
    void testMaxValue_returnsMaxValueReversed() {
        // 01111111111111111111111111111111 → 11111111111111111111111111111110
        assertEquals(-2, solution.reverseBits(Integer.MAX_VALUE));
    }

    // --- Powers of 2 ---

    @Test
    void testTwo_returnsBitAt30() {
        // 00000000000000000000000000000010 → 01000000000000000000000000000000
        assertEquals(1 << 30, solution.reverseBits(2));
    }

    @Test
    void testHighBitOnly_returnsOne() {
        // Already tested via MIN_VALUE → 1
        assertEquals(1, solution.reverseBits(0x80000000));
    }

    // --- Palindromic bit patterns ---

    @Test
    void testPalindromicBits_returnsSelf() {
        // 10000000000000000000000000000001 reversed = itself
        int n = 0x80000001;
        assertEquals(n, solution.reverseBits(n));
    }

    // --- Arbitrary values ---

    @Test
    void testArbitraryValue_13() {
        // 00000000000000000000000000001101 → 10110000000000000000000000000000
        assertEquals(0xB0000000, solution.reverseBits(13));
    }

    @Test
    void testArbitraryValue_reverseTwiceIsIdentity() {
        int n = 123456789;
        assertEquals(n, solution.reverseBits(solution.reverseBits(n)));
    }
}