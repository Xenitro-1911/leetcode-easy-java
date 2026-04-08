package problems.p058_length_of_last_word;

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
    void testTwoWords() {
        assertEquals(5, solution.lengthOfLastWord("Hello World"));
    }

    @Test
    void testSingleWord() {
        assertEquals(5, solution.lengthOfLastWord("Hello"));
    }

    @Test
    void testSingleLetter() {
        assertEquals(1, solution.lengthOfLastWord("a"));
    }

    // --- Trailing spaces ---

    @Test
    void testTrailingSpaces() {
        assertEquals(5, solution.lengthOfLastWord("Hello World   "));
    }

    @Test
    void testOnlyTrailingSpacesAfterWord() {
        assertEquals(3, solution.lengthOfLastWord("fly   "));
    }

    // --- Leading spaces ---

    @Test
    void testLeadingSpaces() {
        assertEquals(5, solution.lengthOfLastWord("   World"));
    }

    // --- Multiple spaces between words ---

    @Test
    void testMultipleSpacesBetweenWords() {
        assertEquals(4, solution.lengthOfLastWord("fly me   to  the moon"));
    }

    @Test
    void testMultipleSpacesEverywhere() {
        assertEquals(3, solution.lengthOfLastWord("   hello   world   "));
    }

    // --- Single character words ---

    @Test
    void testLastWordIsSingleChar() {
        assertEquals(1, solution.lengthOfLastWord("Hello a"));
    }

    @Test
    void testAllSingleCharWords() {
        assertEquals(1, solution.lengthOfLastWord("a b c d"));
    }

    // --- Longer / real-world strings ---

    @Test
    void testLongerLastWord() {
        assertEquals(11, solution.lengthOfLastWord("day lubrication"));
    }

    @Test
    void testSentenceEndingWithLongWord() {
        assertEquals(13, solution.lengthOfLastWord("the quick brown fox jumps over the lazy hippopotamus"));
    }
}