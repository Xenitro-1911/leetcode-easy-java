package problems.p202_happy_number;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    private Solution solution;

    @BeforeEach
    void setUp() {
        solution = new Solution();
    }

    // --- Happy numbers ---

    @Test
    void testOne_isHappy() {
        // 1 → 1, trivially happy
        assertTrue(solution.isHappy(1));
    }

    @Test
    void test7_isHappy() {
        // 7 → 49 → 97 → 130 → 10 → 1
        assertTrue(solution.isHappy(7));
    }

    @Test
    void test19_isHappy() {
        // LeetCode example: 19 → 82 → 68 → 100 → 1
        assertTrue(solution.isHappy(19));
    }

    @Test
    void test100_isHappy() {
        // 1² + 0² + 0² = 1
        assertTrue(solution.isHappy(100));
    }

    @Test
    void test10_isHappy() {
        // 1² + 0² = 1
        assertTrue(solution.isHappy(10));
    }

    @Test
    void test13_isHappy() {
        // 1 + 9 = 10 → 1
        assertTrue(solution.isHappy(13));
    }

    // --- Unhappy numbers (cycle members) ---

    @Test
    void test2_isNotHappy() {
        assertFalse(solution.isHappy(2));
    }

    @Test
    void test3_isNotHappy() {
        assertFalse(solution.isHappy(3));
    }

    @Test
    void test4_isNotHappy() {
        // Entry point of the infamous cycle: 4 → 16 → 37 → 58 → 89 → 145 → 42 → 20 → 4
        assertFalse(solution.isHappy(4));
    }

    @Test
    void test89_isNotHappy() {
        // Mid-cycle member — tests that cycle detection works from inside the cycle
        assertFalse(solution.isHappy(89));
    }

    @Test
    void test116_isNotHappy() {
        assertFalse(solution.isHappy(116));
    }

    // --- Boundary values ---

    @Test
    void testLargeHappyNumber() {
        // 1000 → 1² = 1
        assertTrue(solution.isHappy(1000));
    }

    @Test
    void testLargeUnhappyNumber() {
        assertFalse(solution.isHappy(9999));
    }
}