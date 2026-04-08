package problems.p069_sqrt_x;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    private final Solution solution = new Solution();

    // --- Edge cases (guarded by x < 2) ---

    @Test
    void testZero() {
        // x < 2 guard: returns 0 directly, avoids division by zero
        assertEquals(0, solution.mySqrt(0));
    }

    @Test
    void testOne() {
        // x < 2 guard: returns 1 directly, avoids infinite loop
        assertEquals(1, solution.mySqrt(1));
    }

    // --- Perfect squares ---

    @Test
    void testPerfectSquare_4() {
        assertEquals(2, solution.mySqrt(4));
    }

    @Test
    void testPerfectSquare_9() {
        assertEquals(3, solution.mySqrt(9));
    }

    @Test
    void testPerfectSquare_25() {
        assertEquals(5, solution.mySqrt(25));
    }

    @Test
    void testPerfectSquare_100() {
        assertEquals(10, solution.mySqrt(100));
    }

    @Test
    void testPerfectSquare_1000000() {
        assertEquals(1000, solution.mySqrt(1000000));
    }

    // --- Floor rounding (non-perfect squares) ---

    @Test
    void testFloor_2() {
        // sqrt(2) = 1.414... → 1
        assertEquals(1, solution.mySqrt(2));
    }

    @Test
    void testFloor_3() {
        // sqrt(3) = 1.732... → 1
        assertEquals(1, solution.mySqrt(3));
    }

    @Test
    void testFloor_8() {
        // sqrt(8) = 2.828... → 2
        assertEquals(2, solution.mySqrt(8));
    }

    @Test
    void testFloor_99() {
        // sqrt(99) = 9.949... → 9
        assertEquals(9, solution.mySqrt(99));
    }

    @Test
    void testFloor_26() {
        // sqrt(26) = 5.099... → 5
        assertEquals(5, solution.mySqrt(26));
    }

    // --- Large values (overflow protection via long) ---

    @Test
    void testLargeValue_IntMax() {
        // sqrt(2147483647) = 46340.99... → 46340
        // Key test: r*r would overflow int without long
        assertEquals(46340, solution.mySqrt(Integer.MAX_VALUE));
    }

    @Test
    void testLargeValue_PerfectSquareNearIntMax() {
        // 46340^2 = 2147395600
        assertEquals(46340, solution.mySqrt(2147395600));
    }

    @Test
    void testLargeValue_2000000000() {
        // sqrt(2000000000) = 44721.35... → 44721
        assertEquals(44721, solution.mySqrt(2000000000));
    }
}