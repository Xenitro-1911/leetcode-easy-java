package problems.p206_reverse_linked_list;

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
        ListNode current = head;
        for (int i = 1; i < vals.length; i++) {
            current.next = new ListNode(vals[i]);
            current = current.next;
        }
        return head;
    }

    // Helper: convert linked list to array for easy assertion
    private int[] toArray(ListNode head) {
        java.util.List<Integer> list = new java.util.ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    // --- Null / single node edge cases ---

    @Test
    void testNullHead_returnsNull() {
        assertNull(solution.reverseList(null));
    }

    @Test
    void testSingleNode_returnsSameNode() {
        ListNode head = new ListNode(1);
        ListNode result = solution.reverseList(head);
        assertArrayEquals(new int[]{1}, toArray(result));
    }

    // --- Basic reversal ---

    @Test
    void testTwoNodes_reversed() {
        assertArrayEquals(new int[]{2, 1}, toArray(solution.reverseList(buildList(new int[]{1, 2}))));
    }

    @Test
    void testThreeNodes_reversed() {
        assertArrayEquals(new int[]{3, 2, 1}, toArray(solution.reverseList(buildList(new int[]{1, 2, 3}))));
    }

    @Test
    void testFiveNodes_reversed() {
        assertArrayEquals(new int[]{5, 4, 3, 2, 1}, toArray(solution.reverseList(buildList(new int[]{1, 2, 3, 4, 5}))));
    }

    // --- Structural correctness ---

    @Test
    void testLastNodeNextIsNull() {
        // After reversal the last node (original head) must point to null
        ListNode result = solution.reverseList(buildList(new int[]{1, 2, 3}));
        ListNode tail = result;
        while (tail.next != null) tail = tail.next;
        assertNull(tail.next);
    }

    @Test
    void testNewHeadIsOldTail() {
        ListNode result = solution.reverseList(buildList(new int[]{1, 2, 3, 4, 5}));
        assertEquals(5, result.val);
    }

    @Test
    void testNewTailIsOldHead() {
        ListNode result = solution.reverseList(buildList(new int[]{1, 2, 3, 4, 5}));
        ListNode tail = result;
        while (tail.next != null) tail = tail.next;
        assertEquals(1, tail.val);
    }

    // --- Duplicate and negative values ---

    @Test
    void testDuplicateValues_reversed() {
        assertArrayEquals(new int[]{1, 1, 1}, toArray(solution.reverseList(buildList(new int[]{1, 1, 1}))));
    }

    @Test
    void testNegativeValues_reversed() {
        assertArrayEquals(new int[]{-1, -2, -3}, toArray(solution.reverseList(buildList(new int[]{-3, -2, -1}))));
    }

    @Test
    void testMixedValues_reversed() {
        assertArrayEquals(new int[]{0, -1, 5, 2}, toArray(solution.reverseList(buildList(new int[]{2, 5, -1, 0}))));
    }

    // --- Double reversal ---

    @Test
    void testDoubleReversal_restoresOriginal() {
        // Reversing twice should give back the original list
        int[] original = {1, 2, 3, 4, 5};
        ListNode head = buildList(original);
        ListNode reversed = solution.reverseList(head);
        ListNode restored = solution.reverseList(reversed);
        assertArrayEquals(original, toArray(restored));
    }
}