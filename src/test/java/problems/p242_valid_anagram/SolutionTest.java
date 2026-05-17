package problems.p242_valid_anagram;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    private Solution solution;

    @BeforeEach
    void setUp() {
        solution = new Solution();
    }

    // --- Basic anagram cases ---

    @Test
    void testAnagramExample1_returnsTrue() {
        // LeetCode example: anagram/nagaram
        assertTrue(solution.isAnagram("anagram", "nagaram"));
    }

    @Test
    void testAnagramExample2_returnsFalse() {
        // LeetCode example: rat/car
        assertFalse(solution.isAnagram("rat", "car"));
    }

    @Test
    void testSameString_returnsTrue() {
        assertTrue(solution.isAnagram("abc", "abc"));
    }

    @Test
    void testReversedString_returnsTrue() {
        assertTrue(solution.isAnagram("abcd", "dcba"));
    }

    // --- Length mismatch ---

    @Test
    void testDifferentLengths_returnsFalse() {
        assertFalse(solution.isAnagram("ab", "abc"));
    }

    @Test
    void testShorterT_returnsFalse() {
        assertFalse(solution.isAnagram("abc", "ab"));
    }

    // --- Single character ---

    @Test
    void testSingleCharSame_returnsTrue() {
        assertTrue(solution.isAnagram("a", "a"));
    }

    @Test
    void testSingleCharDifferent_returnsFalse() {
        assertFalse(solution.isAnagram("a", "b"));
    }

    // --- Duplicate characters ---

    @Test
    void testDuplicateCharsAnagram_returnsTrue() {
        assertTrue(solution.isAnagram("aab", "baa"));
    }

    @Test
    void testDuplicateCharsMismatch_returnsFalse() {
        // same letters but wrong counts
        assertFalse(solution.isAnagram("aab", "bab"));
    }

    @Test
    void testAllSameChars_returnsTrue() {
        assertTrue(solution.isAnagram("aaaa", "aaaa"));
    }

    @Test
    void testAllSameCharsDifferentCount_returnsFalse() {
        assertFalse(solution.isAnagram("aaa", "aab"));
    }

    // --- Frequency cancellation edge cases ---

    @Test
    void testOneCharOffByOne_returnsFalse() {
        // s has one extra 'a', t has one extra 'b'
        assertFalse(solution.isAnagram("aab", "abb"));
    }

    @Test
    void testAllLettersUsed_returnsTrue() {
        assertTrue(solution.isAnagram("abcdefghijklmnopqrstuvwxyz",
                "zyxwvutsrqponmlkjihgfedcba"));
    }

    // --- Two character strings ---

    @Test
    void testTwoCharAnagram_returnsTrue() {
        assertTrue(solution.isAnagram("ab", "ba"));
    }

    @Test
    void testTwoCharNotAnagram_returnsFalse() {
        assertFalse(solution.isAnagram("aa", "ab"));
    }
}