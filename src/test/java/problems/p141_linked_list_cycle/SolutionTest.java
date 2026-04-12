package problems.p141_linked_list_cycle;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    private Solution solution = new Solution();

    // Helper: build a list from array, with cycle at cyclePos (-1 = no cycle)
    private ListNode build(int[] vals, int cyclePos) {
        if (vals.length == 0) return null;
        ListNode[] nodes = new ListNode[vals.length];
        for (int i = 0; i < vals.length; i++) nodes[i] = new ListNode(vals[i]);
        for (int i = 0; i < vals.length - 1; i++) nodes[i].next = nodes[i + 1];
        if (cyclePos >= 0) nodes[vals.length - 1].next = nodes[cyclePos];
        return nodes[0];
    }

    // --- No cycle ---

    @Test
    void testNullHead() {
        assertFalse(solution.hasCycle(null));
    }

    @Test
    void testSingleNodeNoCycle() {
        ListNode head = new ListNode(1);
        assertFalse(solution.hasCycle(head));
    }

    @Test
    void testTwoNodesNoCycle() {
        assertFalse(solution.hasCycle(build(new int[]{1, 2}, -1)));
    }

    @Test
    void testThreeNodesNoCycle() {
        assertFalse(solution.hasCycle(build(new int[]{1, 2, 3}, -1)));
    }

    @Test
    void testLongListNoCycle() {
        assertFalse(solution.hasCycle(build(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, -1)));
    }

    // --- Has cycle ---

    @Test
    void testSingleNodeSelfCycle() {
        ListNode head = new ListNode(1);
        head.next = head;
        assertTrue(solution.hasCycle(head));
    }

    @Test
    void testTwoNodesCycleAtHead() {
        assertTrue(solution.hasCycle(build(new int[]{1, 2}, 0)));
    }

    @Test
    void testTwoNodesCycleAtSecond() {
        assertTrue(solution.hasCycle(build(new int[]{1, 2}, 1)));
    }

    @Test
    void testCycleAtHead() {
        assertTrue(solution.hasCycle(build(new int[]{3, 2, 0, -4}, 0)));
    }

    @Test
    void testCycleAtMiddle() {
        assertTrue(solution.hasCycle(build(new int[]{3, 2, 0, -4}, 1)));
    }

    @Test
    void testCycleAtLastNode() {
        assertTrue(solution.hasCycle(build(new int[]{1, 2, 3, 4}, 3)));
    }

    @Test
    void testLongListCycleAtStart() {
        assertTrue(solution.hasCycle(build(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, 0)));
    }

    @Test
    void testLongListCycleAtMiddle() {
        assertTrue(solution.hasCycle(build(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, 4)));
    }

    // --- Duplicate values (must compare references, not values) ---

    @Test
    void testDuplicateValuesNoCycle() {
        assertFalse(solution.hasCycle(build(new int[]{1, 1, 1, 1}, -1)));
    }

    @Test
    void testDuplicateValuesCycle() {
        assertTrue(solution.hasCycle(build(new int[]{1, 1, 1, 1}, 1)));
    }
}