# 160. Intersection of Two Linked Lists

**Difficulty:** Easy  
**Topic:** Linked List, Two Pointers

---

## Problem Summary
Given the heads of two singly linked lists, return the node where they intersect. Return `null` if they don't intersect. The lists must retain their original structure after the function returns.

---

## Approach: Length Equalization + Two Pointers

### Key Insight
If two lists intersect, they share a common tail. The challenge is that the lists may have different lengths, so naive simultaneous traversal won't align the pointers at the intersection.

### Steps
1. **Compute lengths** of both lists (`a` and `b`) using two passes.
2. **Skip the head of the longer list** by `|a - b|` steps, so both pointers are the same distance from the end.
3. **Walk both pointers together** one step at a time until `headA == headB`.
4. **Return `headA`** — if they intersect it's the intersection node; if not, both are `null`.

### Why `headA == headB` works
- Intersection means shared nodes — once two paths meet at a node, every subsequent `.next` is also shared. They cannot diverge after meeting.
- If there's no intersection, both pointers reach `null` simultaneously → `null == null` exits the loop, and `null` is returned.

---

## Solution

```java
public class Solution {
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
```

---

## Complexity
| | |
|---|---|
| **Time** | O(m + n) — two length passes + at most m+n steps |
| **Space** | O(1) — no extra data structures |

---

## Common Mistakes
- Comparing `.val` instead of `==` — two different nodes can have the same value but not be the same node.
- Using `||` in the walk-together loop — causes NullPointerException when one pointer hits `null` before the other.
- Advancing both pointers in the skip phase — only the longer list's pointer should advance.
- Continuing to walk after `headA == headB` — once they're equal, that's your answer; no need to go further.

---

## Alternative: Two-Pointer without Length (Elegant)
There's a clever O(1) space, no-length-calc approach:
- When `pA` reaches the end, redirect it to `headB`
- When `pB` reaches the end, redirect it to `headA`
- They will meet at the intersection (or both reach `null`)

This works because both pointers travel `a + b` total steps, equalizing automatically.