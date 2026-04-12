# 141. Linked List Cycle

**Difficulty:** Easy  
**Pattern:** Two Pointers (Floyd's Cycle Detection)

---

## Problem
Given the head of a linked list, determine if it contains a cycle. Return `true` if yes, `false` otherwise.

---

## Approach — Floyd's Tortoise and Hare

Use two pointers starting at `head`:
- `slow` moves **1 step** per iteration
- `fast` moves **2 steps** per iteration

If there is a cycle, fast will eventually lap slow and they will point to the **same node** (`slow == fast`).  
If there is no cycle, fast will reach the end of the list.

---

## Key Insights

- Compare **object references** (`slow == fast`), not values (`slow.val == fast.val`) — two different nodes can hold the same value.
- The while condition `fast != null && fast.next != null` has two distinct jobs:
    - `fast != null` — prevents NPE when fast has fallen off the end of the list
    - `fast.next != null` — prevents NPE on `fast.next.next` when fast is at the last node
- Fast moving 2 steps and slow moving 1 step **guarantees** they will meet if a cycle exists — fast can never skip over slow (the gap closes by 1 each iteration).

---

## Complexity
- **Time:** O(n)
- **Space:** O(1) — no extra data structures, just two pointers

---

## Solution

```java
public class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null) return false;

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                return true;
            }
        }

        return false;
    }
}
```

---

## Common Mistakes
- Using recursion and recalculating fast from head each call — pointers must persist across iterations.
- Comparing `.val` instead of the references themselves.
- Forgetting `fast.next != null` — causes NPE when fast lands on the last node.