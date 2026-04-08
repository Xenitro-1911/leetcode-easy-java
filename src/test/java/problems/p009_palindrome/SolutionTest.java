package problems.p009_palindrome;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    private Solution solution;

    @BeforeEach
    void setUp() {
        solution = new Solution();
    }

    @Test
    void testBasicPalindrome_evenDigits() {
        // 1221 → reversed half: 12 == 12
        assertTrue(solution.isPalindrome(1221));
    }

    @Test
    void testBasicPalindrome_oddDigits() {
        // 121 → reversed half: 1 == 12/10
        assertTrue(solution.isPalindrome(121));
    }

    @Test
    void testSingleDigit_alwaysPalindrome() {
        // All single digits are palindromes
        assertTrue(solution.isPalindrome(0));
        assertTrue(solution.isPalindrome(7));
    }

    @Test
    void testNegativeNumber_notPalindrome() {
        // Negative numbers are never palindromes
        assertFalse(solution.isPalindrome(-121));
        assertFalse(solution.isPalindrome(-1));
    }

    @Test
    void testTrailingZero_notPalindrome() {
        // 10 ends in 0, cannot be a palindrome
        assertFalse(solution.isPalindrome(10));
        assertFalse(solution.isPalindrome(100));
    }

    @Test
    void testLargePalindrome() {
        // 1000000001
        assertTrue(solution.isPalindrome(1000000001));
    }

    @Test
    void testNonPalindrome() {
        assertFalse(solution.isPalindrome(123));
        assertFalse(solution.isPalindrome(1231));
    }
}
