package problems.p203_remove_linked_list_elements;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    private Solution solution;

    @BeforeEach
    void setUp() {
        solution = new Solution();
    }

    // --- Helper: build list from array ---
    private ListNode build(int... vals) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for (int v : vals) {
            cur.next = new ListNode(v);
            cur = cur.next;
        }
        return dummy.next;
    }

    // --- Helper: convert list to array for easy assertion ---
    private int[] toArray(ListNode head) {
        java.util.List<Integer> result = new java.util.ArrayList<>();
        while (head != null) {
            result.add(head.val);
            head = head.next;
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    // --- Basic cases ---

    @Test
    void testRemoveFromMiddle() {
        // 1 → 2 → 6 → 3 → 6, val=6 → 1 → 2 → 3
        ListNode result = solution.removeElements(build(1, 2, 6, 3, 6), 6);
        assertArrayEquals(new int[]{1, 2, 3}, toArray(result));
    }

    @Test
    void testRemoveFromEnd() {
        // 1 → 2 → 3 → 3, val=3 → 1 → 2
        ListNode result = solution.removeElements(build(1, 2, 3, 3), 3);
        assertArrayEquals(new int[]{1, 2}, toArray(result));
    }

    @Test
    void testRemoveFromHead() {
        // 6 → 1 → 2, val=6 → 1 → 2
        ListNode result = solution.removeElements(build(6, 1, 2), 6);
        assertArrayEquals(new int[]{1, 2}, toArray(result));
    }

    // --- Edge cases ---

    @Test
    void testEmptyList_returnsNull() {
        assertNull(solution.removeElements(null, 1));
    }

    @Test
    void testSingleNodeMatch_returnsNull() {
        ListNode result = solution.removeElements(build(5), 5);
        assertNull(result);
    }

    @Test
    void testSingleNodeNoMatch_returnsSelf() {
        ListNode result = solution.removeElements(build(5), 3);
        assertArrayEquals(new int[]{5}, toArray(result));
    }

    @Test
    void testNoMatchingElements_listUnchanged() {
        // val not present
        ListNode result = solution.removeElements(build(1, 2, 3), 9);
        assertArrayEquals(new int[]{1, 2, 3}, toArray(result));
    }

    @Test
    void testAllElementsMatch_returnsNull() {
        // Every node should be removed
        ListNode result = solution.removeElements(build(7, 7, 7, 7), 7);
        assertNull(result);
    }

    // --- Consecutive duplicates ---

    @Test
    void testConsecutiveDuplicatesInMiddle() {
        // 1 → 6 → 6 → 6 → 2, val=6 → 1 → 2
        ListNode result = solution.removeElements(build(1, 6, 6, 6, 2), 6);
        assertArrayEquals(new int[]{1, 2}, toArray(result));
    }

    @Test
    void testConsecutiveDuplicatesAtHead() {
        // 6 → 6 → 1 → 2, val=6 → 1 → 2
        ListNode result = solution.removeElements(build(6, 6, 1, 2), 6);
        assertArrayEquals(new int[]{1, 2}, toArray(result));
    }

    @Test
    void testConsecutiveDuplicatesAtTail() {
        // 1 → 2 → 6 → 6, val=6 → 1 → 2
        ListNode result = solution.removeElements(build(1, 2, 6, 6), 6);
        assertArrayEquals(new int[]{1, 2}, toArray(result));
    }

    // --- LeetCode example ---

    @Test
    void testLeetcodeExample() {
        // [1,2,6,3,4,5,6], val=6 → [1,2,3,4,5]
        ListNode result = solution.removeElements(build(1, 2, 6, 3, 4, 5, 6), 6);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, toArray(result));
    }
}