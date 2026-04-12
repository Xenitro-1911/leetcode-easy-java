package problems.p141_linked_list_cycle;

class ListNode {
      int val;
      ListNode next;
      ListNode(int x) {
          val = x;
          next = null;
      }
  }

public class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null) return false;

        ListNode slow = head;
        ListNode fast = head;

        // Move fast twice as fast as slow
        while (fast != null && fast.next != null) {
            slow = slow.next;         // 1 step
            fast = fast.next.next;    // 2 steps

            if (slow == fast) {       // They met! There is a cycle.
                return true;
            }
        }

        return false; // Fast reached the end, no cycle.
    }
}
