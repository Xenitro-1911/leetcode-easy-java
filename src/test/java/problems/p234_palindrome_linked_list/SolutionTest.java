package problems.p234_palindrome_linked_list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    private Solution solution;

    @BeforeEach
    void setUp() {
        solution = new Solution();
    }

    // Helper: build linked list from array
    private ListNode buildList(int[] vals) {
        if (vals.length == 0) return null;
        ListNode head = new ListNode(vals[0]);
        ListNode cur = head;
        for (int i = 1; i < vals.length; i++) {
            cur.next = new ListNode(vals[i]);
            cur = cur.next;
        }
        return head;
    }

    // --- Null and single node ---

    @Test
    void testNullHead_returnsTrue() {
        assertTrue(solution.isPalindrome(null));
    }

    @Test
    void testSingleNode_returnsTrue() {
        assertTrue(solution.isPalindrome(buildList(new int[]{1})));
    }

    // --- Two node cases ---

    @Test
    void testTwoSameNodes_returnsTrue() {
        assertTrue(solution.isPalindrome(buildList(new int[]{1, 1})));
    }

    @Test
    void testTwoDifferentNodes_returnsFalse() {
        assertFalse(solution.isPalindrome(buildList(new int[]{1, 2})));
    }

    // --- Odd length palindromes ---

    @Test
    void testOddLengthPalindrome_returnsTrue() {
        assertTrue(solution.isPalindrome(buildList(new int[]{1, 2, 1})));
    }

    @Test
    void testOddLengthLongerPalindrome_returnsTrue() {
        assertTrue(solution.isPalindrome(buildList(new int[]{1, 2, 3, 2, 1})));
    }

    @Test
    void testOddLengthNotPalindrome_returnsFalse() {
        assertFalse(solution.isPalindrome(buildList(new int[]{1, 2, 3, 4, 5})));
    }

    // --- Even length palindromes ---

    @Test
    void testEvenLengthPalindrome_returnsTrue() {
        assertTrue(solution.isPalindrome(buildList(new int[]{1, 2, 2, 1})));
    }

    @Test
    void testEvenLengthLongerPalindrome_returnsTrue() {
        assertTrue(solution.isPalindrome(buildList(new int[]{1, 2, 3, 3, 2, 1})));
    }

    @Test
    void testEvenLengthNotPalindrome_returnsFalse() {
        assertFalse(solution.isPalindrome(buildList(new int[]{1, 2, 3, 4})));
    }

    // --- LeetCode examples ---

    @Test
    void testLeetcodeExample1_returnsTrue() {
        // [1,2,2,1]
        assertTrue(solution.isPalindrome(buildList(new int[]{1, 2, 2, 1})));
    }

    @Test
    void testLeetcodeExample2_returnsFalse() {
        // [1,2]
        assertFalse(solution.isPalindrome(buildList(new int[]{1, 2})));
    }

    // --- Off-by-one / near-palindrome cases ---

    @Test
    void testAlmostPalindromeOffByOne_returnsFalse() {
        // [1,2,3,2,2] — last element breaks it
        assertFalse(solution.isPalindrome(buildList(new int[]{1, 2, 3, 2, 2})));
    }

    @Test
    void testFirstElementDiffers_returnsFalse() {
        assertFalse(solution.isPalindrome(buildList(new int[]{1, 2, 3, 2, 9})));
    }

    // --- All same elements ---

    @Test
    void testAllSameElements_returnsTrue() {
        assertTrue(solution.isPalindrome(buildList(new int[]{7, 7, 7, 7, 7})));
    }

    // --- Negative values ---

    @Test
    void testNegativePalindrome_returnsTrue() {
        assertTrue(solution.isPalindrome(buildList(new int[]{-1, -2, -1})));
    }

    @Test
    void testNegativeNotPalindrome_returnsFalse() {
        assertFalse(solution.isPalindrome(buildList(new int[]{-1, -2, -3})));
    }
}