package problems.p014_longest_common_prefix;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    private Solution solution;

    @BeforeEach
    void setUp() {
        solution = new Solution();
    }

    // --- Standard Cases ---

    @Test
    void testCommonPrefix() {
        // "fl" is the longest common prefix
        assertEquals("fl", solution.longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
    }

    @Test
    void testNoCommonPrefix() {
        // No common prefix at all
        assertEquals("", solution.longestCommonPrefix(new String[]{"dog", "racecar", "car"}));
    }

    @Test
    void testFullWordIsPrefix() {
        // One string is a prefix of all others
        assertEquals("flow", solution.longestCommonPrefix(new String[]{"flow", "flower", "flowing"}));
    }

    @Test
    void testAllSameStrings() {
        // All strings identical
        assertEquals("abc", solution.longestCommonPrefix(new String[]{"abc", "abc", "abc"}));
    }

    @Test
    void testSingleCharacterPrefix() {
        assertEquals("a", solution.longestCommonPrefix(new String[]{"ab", "ac", "ad"}));
    }

    // --- Edge Cases ---

    @Test
    void testSingleString() {
        // Only one string — prefix is itself
        assertEquals("hello", solution.longestCommonPrefix(new String[]{"hello"}));
    }

    @Test
    void testEmptyStringInArray() {
        // Empty string means no common prefix possible
        assertEquals("", solution.longestCommonPrefix(new String[]{"", "abc"}));
    }

    @Test
    void testAllEmptyStrings() {
        assertEquals("", solution.longestCommonPrefix(new String[]{"", "", ""}));
    }

    @Test
    void testEmptyArray() {
        // Null/empty array guard
        assertEquals("", solution.longestCommonPrefix(new String[]{}));
    }

    @Test
    void testNullInput() {
        assertEquals("", solution.longestCommonPrefix(null));
    }

    @Test
    void testPrefixIsShorterThanShortest() {
        // Shortest string shares only partial prefix
        assertEquals("ab", solution.longestCommonPrefix(new String[]{"abcd", "abef", "ab"}));
    }

    @Test
    void testLongStringsShortPrefix() {
        assertEquals("a", solution.longestCommonPrefix(new String[]{"abcdefgh", "azzzzzz"}));
    }

    @Test
    void testTwoStrings() {
        assertEquals("inter", solution.longestCommonPrefix(new String[]{"interview", "interstellar"}));
    }
}