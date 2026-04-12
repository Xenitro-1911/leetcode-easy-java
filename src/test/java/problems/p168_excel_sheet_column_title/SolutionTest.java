package problems.p168_excel_sheet_column_title;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    private Solution solution;

    @BeforeEach
    void setUp() {
        solution = new Solution();
    }

    // --- Single letter columns ---

    @Test
    void testColumnOne_returnsA() {
        assertEquals("A", solution.convertToTitle(1));
    }

    @Test
    void testColumnThree_returnsC() {
        assertEquals("C", solution.convertToTitle(3));
    }

    @Test
    void testColumnTwentySix_returnsZ() {
        // Classic edge case: multiple of 26, x % 26 == 0 → must map to 'Z'
        assertEquals("Z", solution.convertToTitle(26));
    }

    // --- Two letter columns ---

    @Test
    void testColumnTwentySeven_returnsAA() {
        assertEquals("AA", solution.convertToTitle(27));
    }

    @Test
    void testColumnTwentyEight_returnsAB() {
        assertEquals("AB", solution.convertToTitle(28));
    }

    @Test
    void testColumnFiftyTwo_returnsAZ() {
        // 52 = 2*26, both digits are edge cases
        assertEquals("AZ", solution.convertToTitle(52));
    }

    @Test
    void testColumnFiftyThree_returnsBA() {
        assertEquals("BA", solution.convertToTitle(53));
    }

    @Test
    void testColumnSevenHundredOne_returnsZY() {
        assertEquals("ZY", solution.convertToTitle(701));
    }

    @Test
    void testColumnSevenHundredTwo_returnsZZ() {
        // 702 = 26*27, double Z — stress test for the (columnNumber-1)/26 logic
        assertEquals("ZZ", solution.convertToTitle(702));
    }

    // --- Three letter columns ---

    @Test
    void testColumnSevenHundredThree_returnsAAA() {
        assertEquals("AAA", solution.convertToTitle(703));
    }

    @Test
    void testColumnSevenHundredFour_returnsAAB() {
        assertEquals("AAB", solution.convertToTitle(704));
    }

    @Test
    void testColumnEighteenTwo_returnsABZ() {
        // 18278 = ZZZ, all-Z three-letter
        assertEquals("ZZZ", solution.convertToTitle(18278));
    }

    // --- Large values ---

    @Test
    void testLargeValue_returnsCorrectTitle() {
        // 16384 → known LeetCode example
        assertEquals("XFD", solution.convertToTitle(16384));
    }

    @Test
    void testMaxInt_doesNotThrow() {
        // Should complete without overflow or infinite loop
        assertDoesNotThrow(() -> solution.convertToTitle(Integer.MAX_VALUE));
    }

    @Test
    void testColumnFourteen_returnsN() {
        assertEquals("N", solution.convertToTitle(14));
    }
}