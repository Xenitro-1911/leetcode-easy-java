package problems.p160_intersection_of_two_linked_lists;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    static class Solution {
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            if (headA == null || headB == null) return null;

            int a = 0, b = 0;
            ListNode headA2 = headA;
            ListNode headB2 = headB;

            while (headA2 != null) { a++; headA2 = headA2.next; }
            while (headB2 != null) { b++; headB2 = headB2.next; }

            for (int i = 1; i <= Math.abs(a - b); i++) {
                if (a > b) headA = headA.next;
                else headB = headB.next;
            }

            while (headA != headB) {
                headA = headA.next;
                headB = headB.next;
            }

            return headA;
        }
    }

    private Solution solution;

    @BeforeEach
    void setUp() {
        solution = new Solution();
    }

    // Helper: build a chain of nodes from values
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

    // Helper: attach a tail to the end of a list, return the first node of the tail
    private ListNode attachTail(ListNode head, ListNode tail) {
        ListNode cur = head;
        while (cur.next != null) cur = cur.next;
        cur.next = tail;
        return tail;
    }

    // --- Intersection exists ---

    @Test
    void testIntersectionAtFirstNode() {
        // A: 1 -> [2 -> 3]
        // B:      [2 -> 3]
        ListNode shared = buildList(new int[]{2, 3});
        ListNode headA = new ListNode(1);
        headA.next = shared;
        ListNode headB = shared;

        assertEquals(shared, solution.getIntersectionNode(headA, headB));
    }

    @Test
    void testIntersectionSameLengthLists() {
        // A: 1 -> 2 -> [3 -> 4]
        // B: 5 -> 6 -> [3 -> 4]
        ListNode shared = buildList(new int[]{3, 4});
        ListNode headA = buildList(new int[]{1, 2});
        attachTail(headA, shared);
        ListNode headB = buildList(new int[]{5, 6});
        attachTail(headB, shared);

        assertEquals(shared, solution.getIntersectionNode(headA, headB));
    }

    @Test
    void testIntersectionLongerA() {
        // A: 1 -> 2 -> 3 -> [4 -> 5]
        // B:           6 -> [4 -> 5]
        ListNode shared = buildList(new int[]{4, 5});
        ListNode headA = buildList(new int[]{1, 2, 3});
        attachTail(headA, shared);
        ListNode headB = new ListNode(6);
        headB.next = shared;

        assertEquals(shared, solution.getIntersectionNode(headA, headB));
    }

    @Test
    void testIntersectionLongerB() {
        // A:           1 -> [2 -> 3]
        // B: 4 -> 5 -> 6 -> [2 -> 3]
        ListNode shared = buildList(new int[]{2, 3});
        ListNode headA = new ListNode(1);
        headA.next = shared;
        ListNode headB = buildList(new int[]{4, 5, 6});
        attachTail(headB, shared);

        assertEquals(shared, solution.getIntersectionNode(headA, headB));
    }

    @Test
    void testIntersectionAtLastNode() {
        // A: 1 -> 2 -> [3]
        // B: 4 -> 5 -> [3]
        ListNode shared = new ListNode(3);
        ListNode headA = buildList(new int[]{1, 2});
        attachTail(headA, shared);
        ListNode headB = buildList(new int[]{4, 5});
        attachTail(headB, shared);

        assertEquals(shared, solution.getIntersectionNode(headA, headB));
    }

    @Test
    void testIntersectionSingleNodeEach() {
        // A: [1]
        // B: [1]  (same node)
        ListNode shared = new ListNode(1);
        assertEquals(shared, solution.getIntersectionNode(shared, shared));
    }

    @Test
    void testIntersectionLargeDifference() {
        // A: 1->2->3->4->5->[6->7]
        // B:                [6->7]
        ListNode shared = buildList(new int[]{6, 7});
        ListNode headA = buildList(new int[]{1, 2, 3, 4, 5});
        attachTail(headA, shared);
        ListNode headB = shared;

        assertEquals(shared, solution.getIntersectionNode(headA, headB));
    }

    // --- No intersection ---

    @Test
    void testNoIntersection() {
        ListNode headA = buildList(new int[]{1, 2, 3});
        ListNode headB = buildList(new int[]{4, 5, 6});

        assertNull(solution.getIntersectionNode(headA, headB));
    }

    @Test
    void testNoIntersectionDifferentLengths() {
        ListNode headA = buildList(new int[]{1, 2, 3, 4});
        ListNode headB = buildList(new int[]{5, 6});

        assertNull(solution.getIntersectionNode(headA, headB));
    }

    @Test
    void testNoIntersectionSameValues() {
        // Same values but different nodes — must use == not .val ==
        ListNode headA = buildList(new int[]{1, 2, 3});
        ListNode headB = buildList(new int[]{1, 2, 3});

        assertNull(solution.getIntersectionNode(headA, headB));
    }

    // --- Null / edge cases ---

    @Test
    void testHeadANull() {
        ListNode headB = buildList(new int[]{1, 2});
        assertNull(solution.getIntersectionNode(null, headB));
    }

    @Test
    void testHeadBNull() {
        ListNode headA = buildList(new int[]{1, 2});
        assertNull(solution.getIntersectionNode(headA, null));
    }

    @Test
    void testBothNull() {
        assertNull(solution.getIntersectionNode(null, null));
    }

    @Test
    void testSingleNodeNoIntersection() {
        ListNode headA = new ListNode(1);
        ListNode headB = new ListNode(1);
        assertNull(solution.getIntersectionNode(headA, headB));
    }
}