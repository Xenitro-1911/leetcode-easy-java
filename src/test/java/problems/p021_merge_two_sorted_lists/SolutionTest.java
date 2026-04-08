package problems.p021_merge_two_sorted_lists;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MergeTwoListsTest {

    // ---- ListNode definition ----
    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    // ---- Solution ----
    static class Solution {
        public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            ListNode dummy = new ListNode(0);
            ListNode current = dummy;

            while (list1 != null && list2 != null) {
                if (list1.val <= list2.val) {
                    current.next = list1;
                    list1 = list1.next;
                } else {
                    current.next = list2;
                    list2 = list2.next;
                }
                current = current.next;
            }

            if (list1 != null) current.next = list1;
            if (list2 != null) current.next = list2;

            return dummy.next;
        }
    }

    // ---- Helpers ----
    private ListNode build(int... vals) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for (int v : vals) { cur.next = new ListNode(v); cur = cur.next; }
        return dummy.next;
    }

    private int[] toArray(ListNode head) {
        java.util.List<Integer> list = new java.util.ArrayList<>();
        while (head != null) { list.add(head.val); head = head.next; }
        return list.stream().mapToInt(i -> i).toArray();
    }

    Solution sol = new Solution();

    // ---- Tests ----
    @Test
    void testExample1() {
        // [1,2,4] + [1,3,4] = [1,1,2,3,4,4]
        assertArrayEquals(new int[]{1,1,2,3,4,4},
                toArray(sol.mergeTwoLists(build(1,2,4), build(1,3,4))));
    }

    @Test
    void testBothEmpty() {
        assertNull(sol.mergeTwoLists(null, null));
    }

    @Test
    void testOneEmpty() {
        assertArrayEquals(new int[]{1,2,3},
                toArray(sol.mergeTwoLists(null, build(1,2,3))));
    }

    @Test
    void testOtherEmpty() {
        assertArrayEquals(new int[]{1,2,3},
                toArray(sol.mergeTwoLists(build(1,2,3), null)));
    }

    @Test
    void testSingleElements() {
        assertArrayEquals(new int[]{1,2},
                toArray(sol.mergeTwoLists(build(1), build(2))));
    }

    @Test
    void testAlreadyInterleaved() {
        // [1,3,5] + [2,4,6] = [1,2,3,4,5,6]
        assertArrayEquals(new int[]{1,2,3,4,5,6},
                toArray(sol.mergeTwoLists(build(1,3,5), build(2,4,6))));
    }

    @Test
    void testAllSameValues() {
        assertArrayEquals(new int[]{2,2,2,2},
                toArray(sol.mergeTwoLists(build(2,2), build(2,2))));
    }
}