package problems.p125_valid_palindrome;
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
    void testClassicPalindrome() {
        // "amanaplanacanalpanama" after cleaning
        assertTrue(solution.isPalindrome("A man, a plan, a canal: Panama"));
    }

    @Test
    void testNotPalindrome() {
        assertFalse(solution.isPalindrome("race a car"));
    }

    @Test
    void testSingleSpace() {
        // Only non-alphanumeric → cleaned string is empty → palindrome
        assertTrue(solution.isPalindrome(" "));
    }

    // --- Case sensitivity ---

    @Test
    void testMixedCase() {
        assertTrue(solution.isPalindrome("Aba"));
    }

    @Test
    void testMixedCaseNotPalindrome() {
        assertFalse(solution.isPalindrome("Ab c d"));
    }

    // --- Edge cases ---

    @Test
    void testEmptyString() {
        assertTrue(solution.isPalindrome(""));
    }

    @Test
    void testSingleCharacter() {
        assertTrue(solution.isPalindrome("a"));
    }

    @Test
    void testSingleNonAlphanumeric() {
        assertTrue(solution.isPalindrome("!"));
    }

    // --- Numbers ---

    @Test
    void testWithDigits() {
        assertTrue(solution.isPalindrome("12321"));
    }

    @Test
    void testDigitsNotPalindrome() {
        assertFalse(solution.isPalindrome("12345"));
    }

    @Test
    void testMixedAlphanumeric() {
        // "a1b2b1a" → palindrome
        assertTrue(solution.isPalindrome("a1b2b1a"));
    }

    // --- Punctuation heavy ---

    @Test
    void testAllPunctuation() {
        // No alphanumeric characters → empty → palindrome
        assertTrue(solution.isPalindrome(".,!?;:"));
    }

    @Test
    void testPunctuationAroundPalindrome() {
        assertTrue(solution.isPalindrome("...racecar..."));
    }
}