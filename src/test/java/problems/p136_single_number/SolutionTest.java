package problems.p136_single_number;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    private final Solution solution = new Solution();

    @Test
    void testSingleAtEnd() {
        assertEquals(1, solution.singleNumber(new int[]{4, 4, 1}));
    }

    @Test
    void testSingleAtStart() {
        assertEquals(1, solution.singleNumber(new int[]{1, 4, 4}));
    }

    @Test
    void testSingleInMiddle() {
        assertEquals(1, solution.singleNumber(new int[]{4, 1, 4}));
    }

    @Test
    void testSingleElement() {
        assertEquals(7, solution.singleNumber(new int[]{7}));
    }

    @Test
    void testLargerArray() {
        assertEquals(3, solution.singleNumber(new int[]{2, 3, 2, 4, 4}));
    }

    @Test
    void testNegativeNumbers() {
        assertEquals(-1, solution.singleNumber(new int[]{-1, 2, 2}));
    }

    @Test
    void testAllNegatives() {
        assertEquals(-5, solution.singleNumber(new int[]{-3, -5, -3}));
    }

    @Test
    void testMixedPositiveNegative() {
        assertEquals(4, solution.singleNumber(new int[]{-2, 4, -2}));
    }

    @Test
    void testZeroIsUnique() {
        assertEquals(0, solution.singleNumber(new int[]{1, 0, 1}));
    }

    @Test
    void testZeroIsNotUnique() {
        assertEquals(9, solution.singleNumber(new int[]{0, 9, 0}));
    }

    @Test
    void testLargeValues() {
        assertEquals(100000, solution.singleNumber(new int[]{99999, 100000, 99999}));
    }

    @Test
    void testManyPairs() {
        assertEquals(6, solution.singleNumber(new int[]{1, 2, 3, 4, 5, 6, 5, 4, 3, 2, 1}));
    }

    @Test
    void testSingleIsFirstOfMany() {
        assertEquals(10, solution.singleNumber(new int[]{10, 7, 8, 7, 8}));
    }

    @Test
    void testSingleIsLastOfMany() {
        assertEquals(10, solution.singleNumber(new int[]{7, 8, 7, 8, 10}));
    }

    @Test
    void testIntMinValue() {
        assertEquals(Integer.MIN_VALUE, solution.singleNumber(new int[]{Integer.MIN_VALUE, 1, 1}));
    }
}