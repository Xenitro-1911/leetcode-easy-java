package problems.p205_isomorphic_strings;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    private Solution solution;

    @BeforeEach
    void setUp() {
        solution = new Solution();
    }

    // --- Basic isomorphic cases ---

    @Test
    void testEggAdd_isIsomorphic() {
        // e->a, g->d, g->d — consistent mapping
        assertTrue(solution.isIsomorphic("egg", "add"));
    }

    @Test
    void testPaperTitle_isIsomorphic() {
        // p->t, a->i, p->t, e->l, r->e — consistent
        assertTrue(solution.isIsomorphic("paper", "title"));
    }

    @Test
    void testSameStrings_isIsomorphic() {
        // identity mapping: every char maps to itself
        assertTrue(solution.isIsomorphic("abc", "abc"));
    }

    @Test
    void testSingleChar_isIsomorphic() {
        assertTrue(solution.isIsomorphic("a", "b"));
    }

    @Test
    void testAllSameChars_isIsomorphic() {
        // aaa -> bbb: a always maps to b
        assertTrue(solution.isIsomorphic("aaa", "bbb"));
    }

    // --- Non-isomorphic cases ---

    @Test
    void testFoo_bar_notIsomorphic() {
        // f->b, o->a, o->r — o cannot map to both a and r
        assertFalse(solution.isIsomorphic("foo", "bar"));
    }

    @Test
    void testTwoDistinctToOne_notIsomorphic() {
        // a->c, b->c — two different s-chars map to same t-char
        assertFalse(solution.isIsomorphic("ab", "cc"));
    }

    @Test
    void testOneToTwo_notIsomorphic() {
        // a->a, a->b — same s-char maps to two different t-chars
        assertFalse(solution.isIsomorphic("aa", "ab"));
    }

    // --- Index 0 edge case (catches i vs i+1 bug) ---

    @Test
    void testFirstCharConflict_notIsomorphic() {
        // ba -> aa: b->a at i=0, a->a at i=1 — but b and a are different s-chars both mapping to a
        assertFalse(solution.isIsomorphic("ba", "aa"));
    }

    @Test
    void testPositionZeroSameChar_isIsomorphic() {
        // a seen at i=0 in both — should not be confused with default 0
        assertTrue(solution.isIsomorphic("ab", "xy"));
    }

    // --- Symmetric / ordering cases ---

    @Test
    void testSymmetricMapping_isIsomorphic() {
        // ab -> ba: a->b, b->a — valid bijection
        assertTrue(solution.isIsomorphic("ab", "ba"));
    }

    @Test
    void testRepeatedPatternMatch_isIsomorphic() {
        // abab -> cdcd: a->c, b->d consistently
        assertTrue(solution.isIsomorphic("abab", "cdcd"));
    }

    @Test
    void testRepeatedPatternMismatch_notIsomorphic() {
        // abab -> cdce: at index 3, b should map to d but t has e
        assertFalse(solution.isIsomorphic("abab", "cdce"));
    }

    // --- Longer string ---

    @Test
    void testLongerIsomorphic() {
        assertTrue(solution.isIsomorphic("aabbcc", "xxyyzz"));
    }

    @Test
    void testLongerNotIsomorphic() {
        // c and a both map to z — violates one-to-one
        assertFalse(solution.isIsomorphic("aabbcc", "xxyyzx"));
    }
}