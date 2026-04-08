package problems.p020_valid_parentheses;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    private Solution solution;

    @BeforeEach
    void setUp() {
        solution = new Solution();
    }

    // --- Valid cases ---

    @Test
    void testSimpleParentheses() {
        assertTrue(solution.isValid("()"));
    }

    @Test
    void testMixedBrackets() {
        assertTrue(solution.isValid("()[]{}"));
    }

    @Test
    void testNestedBrackets() {
        assertTrue(solution.isValid("{[()]}"));
    }

    @Test
    void testEmptyString() {
        assertTrue(solution.isValid(""));
    }

    @Test
    void testDeeplyNested() {
        assertTrue(solution.isValid("(({{[[]]}}))"));
    }

    // --- Invalid cases ---

    @Test
    void testMismatchedParentheses() {
        assertFalse(solution.isValid("(]"));
    }

    @Test
    void testWrongOrder() {
        assertFalse(solution.isValid("([)]"));
    }

    @Test
    void testUnclosedBracket() {
        assertFalse(solution.isValid("{[]"));
    }

    @Test
    void testOnlyClosing() {
        assertFalse(solution.isValid(")"));
    }

    @Test
    void testOnlyOpening() {
        assertFalse(solution.isValid("("));
    }

    @Test
    void testOddLength() {
        assertFalse(solution.isValid("(()"));
    }

    @Test
    void testClosingOnEmptyStack() {
        assertFalse(solution.isValid(")()"));
    }

    @Test
    void testExtraClosing() {
        assertFalse(solution.isValid("())"));
    }

    // --- Edge cases ---

    @Test
    void testLongValidString() {
        assertTrue(solution.isValid("()".repeat(10000)));
    }

    @Test
    void testLongInvalidString() {
        assertFalse(solution.isValid("((".repeat(10000)));
    }
}