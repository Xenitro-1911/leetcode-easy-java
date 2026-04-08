package problems.p028_find_the_index_of_the_first_occurrence_in_a_string;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolutionTest {

    private Solution solution;

    @BeforeEach
    void setUp() {
        solution = new Solution();
    }

    // --- Provided examples ---

    @Test
    void example1_needleAtStart() {
        // "sad" appears at index 0 and 6 — first occurrence is 0
        assertEquals(0, solution.strStr("sadbutsad", "sad"));
    }

    @Test
    void example2_needleNotPresent() {
        // "leeto" does not appear in "leetcode"
        assertEquals(-1, solution.strStr("leetcode", "leeto"));
    }

    // --- Edge cases ---

    @Test
    void needleAtEnd() {
        assertEquals(5, solution.strStr("helloworld", "world"));
    }

    @Test
    void needleInMiddle() {
        assertEquals(2, solution.strStr("aabcaa", "bc"));
    }

    @Test
    void needleEqualsHaystack() {
        assertEquals(0, solution.strStr("abc", "abc"));
    }

    @Test
    void needleLongerThanHaystack() {
        assertEquals(-1, solution.strStr("ab", "abcd"));
    }

    @Test
    void singleCharNeedleFound() {
        assertEquals(0, solution.strStr("a", "a"));
    }

    @Test
    void singleCharNeedleNotFound() {
        assertEquals(-1, solution.strStr("a", "b"));
    }

    @Test
    void repeatingCharacters() {
        // First occurrence of "aa" in "aaaa" is at index 0
        assertEquals(0, solution.strStr("aaaa", "aa"));
    }

    @Test
    void needleAppearsOnlyOnce() {
        assertEquals(3, solution.strStr("abcdef", "def"));
    }

    @Test
    void haystackAndNeedleSameSingleChar() {
        assertEquals(0, solution.strStr("z", "z"));
    }

    @Test
    void needleNotFoundSimilarStart() {
        // Shares prefix but not a full match
        assertEquals(-1, solution.strStr("aab", "aac"));
    }
}