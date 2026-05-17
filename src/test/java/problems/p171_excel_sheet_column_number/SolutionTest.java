package problems.p171_excel_sheet_column_number;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    private Solution solution;

    @BeforeEach
    void setUp() {
        solution = new Solution();
    }

    // --- Single letters ---

    @Test
    void testA_returns1() {
        assertEquals(1, solution.titleToNumber("A"));
    }

    @Test
    void testB_returns2() {
        assertEquals(2, solution.titleToNumber("B"));
    }

    @Test
    void testZ_returns26() {
        assertEquals(26, solution.titleToNumber("Z"));
    }

    // --- Two-letter columns ---

    @Test
    void testAA_returns27() {
        assertEquals(27, solution.titleToNumber("AA"));
    }

    @Test
    void testAB_returns28() {
        assertEquals(28, solution.titleToNumber("AB"));
    }

    @Test
    void testAZ_returns52() {
        assertEquals(52, solution.titleToNumber("AZ"));
    }

    @Test
    void testBA_returns53() {
        assertEquals(53, solution.titleToNumber("BA"));
    }

    @Test
    void testZY_returns701() {
        assertEquals(701, solution.titleToNumber("ZY"));
    }

    @Test
    void testZZ_returns702() {
        assertEquals(702, solution.titleToNumber("ZZ"));
    }

    // --- Three-letter columns ---

    @Test
    void testAAA_returns703() {
        assertEquals(703, solution.titleToNumber("AAA"));
    }

    @Test
    void testAAB_returns704() {
        assertEquals(704, solution.titleToNumber("AAB"));
    }

    @Test
    void testXFD_returns16384() {
        // Max column in Excel (2^14)
        assertEquals(16384, solution.titleToNumber("XFD"));
    }

    // --- Edge: single char boundary ---

    @Test
    void testM_returns13() {
        assertEquals(13, solution.titleToNumber("M"));
    }
}