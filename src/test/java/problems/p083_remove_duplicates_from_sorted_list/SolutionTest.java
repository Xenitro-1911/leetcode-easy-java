package problems.p083_remove_duplicates_from_sorted_list;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    // ── helpers ──────────────────────────────────────────────────────────────

    /** Build a linked list from varargs ints. */
    private ListNode list(int... vals) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for (int v : vals) { cur.next = new ListNode(v); cur = cur.next; }
        return dummy.next;
    }

    /** Convert a linked list to a readable string for assertion messages. */
    private String str(ListNode head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val);
            if (head.next != null) sb.append("->");
            head = head.next;
        }
        return sb.toString();
    }

    /** Assert two linked lists are value-equal. */
    private void assertList(ListNode expected, ListNode actual) {
        while (expected != null && actual != null) {
            assertEquals(expected.val, actual.val,
                    "Mismatch — expected " + str(expected) + " but got " + str(actual));
            expected = expected.next;
            actual   = actual.next;
        }
        assertNull(expected, "Actual list is shorter than expected");
        assertNull(actual,   "Actual list is longer than expected");
    }

    // ── tests ────────────────────────────────────────────────────────────────

    // 1. Basic duplicate in the middle
    @Test
    void testDuplicateInMiddle() {
        // [1,1,2] → [1,2]
        assertList(list(1, 2), new Solution().deleteDuplicates(list(1, 1, 2)));
    }

    // 2. Multiple duplicate groups
    @Test
    void testMultipleDuplicateGroups() {
        // [1,1,2,3,3] → [1,2,3]
        assertList(list(1, 2, 3), new Solution().deleteDuplicates(list(1, 1, 2, 3, 3)));
    }

    // 3. No duplicates at all
    @Test
    void testNoDuplicates() {
        // [1,2,3] → [1,2,3]
        assertList(list(1, 2, 3), new Solution().deleteDuplicates(list(1, 2, 3)));
    }

    // 4. All elements identical
    @Test
    void testAllSame() {
        // [3,3,3] → [3]
        assertList(list(3), new Solution().deleteDuplicates(list(3, 3, 3)));
    }

    // 5. Single element
    @Test
    void testSingleElement() {
        assertList(list(7), new Solution().deleteDuplicates(list(7)));
    }

    // 6. Empty list (null head)
    @Test
    void testEmptyList() {
        assertNull(new Solution().deleteDuplicates(null));
    }

    // 7. Two identical elements
    @Test
    void testTwoSameElements() {
        // [5,5] → [5]
        assertList(list(5), new Solution().deleteDuplicates(list(5, 5)));
    }

    // 8. Two distinct elements
    @Test
    void testTwoDistinctElements() {
        // [1,2] → [1,2]
        assertList(list(1, 2), new Solution().deleteDuplicates(list(1, 2)));
    }

    // 9. Duplicates only at the start
    @Test
    void testDuplicatesAtStart() {
        // [1,1,1,2,3] → [1,2,3]
        assertList(list(1, 2, 3), new Solution().deleteDuplicates(list(1, 1, 1, 2, 3)));
    }

    // 10. Duplicates only at the end
    @Test
    void testDuplicatesAtEnd() {
        // [1,2,3,3,3] → [1,2,3]
        assertList(list(1, 2, 3), new Solution().deleteDuplicates(list(1, 2, 3, 3, 3)));
    }

    // 11. Negative numbers with duplicates
    @Test
    void testNegativeNumbers() {
        // [-3,-3,-1,0,0,2] → [-3,-1,0,2]
        assertList(list(-3, -1, 0, 2),
                new Solution().deleteDuplicates(list(-3, -3, -1, 0, 0, 2)));
    }

    // 12. Long run of the same value
    @Test
    void testLongRunSameValue() {
        // [2,2,2,2,2] → [2]
        assertList(list(2), new Solution().deleteDuplicates(list(2, 2, 2, 2, 2)));
    }

    // 13. Alternating unique values (no duplicates, larger input)
    @Test
    void testAlternatingUnique() {
        // [1,2,3,4,5] → [1,2,3,4,5]
        assertList(list(1, 2, 3, 4, 5),
                new Solution().deleteDuplicates(list(1, 2, 3, 4, 5)));
    }
}