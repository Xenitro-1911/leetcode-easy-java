# 203. Remove Linked List Elements

## Problem
Given the head of a linked list and an integer `val`, remove all nodes with `val` and return the updated head.

Example: `1 → 2 → 6 → 3 → 6`, val = 6 → `1 → 2 → 3`

---

## Why Linked List Removal is Tricky

To remove a node you must update the **previous node's** `next` pointer to skip over it:

```
Before: prev → target → next
After:  prev → next
```

This means you always need a reference to the node **before** the one you want to remove. This creates a problem for `head` — it has no previous node.

---

## Approach — Dummy (Sentinel) Node

Create a fake node before `head`. This gives every node (including `head`) a predecessor, making the logic uniform — no special cases needed.

```
dummy(0) → head → node → node → null
prev = dummy
```

Walk `prev` through the list checking `prev.next`:
- If `prev.next.val == val` → bypass it: `prev.next = prev.next.next` (prev stays)
- Otherwise → advance: `prev = prev.next`

Return `dummy.next` (the new head, which may differ from original if head was removed).

---

## Why `prev.next` Instead of `current`

Using `prev.next` as the look-ahead means you always have the predecessor ready to relink. A `current` pointer approach requires tracking both `prev` and `current` separately — more variables, same result.

---

## Why `prev` Doesn't Advance on Deletion

After `prev.next = prev.next.next`, the new `prev.next` is an unchecked node. If you advanced `prev` now you'd skip checking it. Keeping `prev` in place means the next iteration checks the newly linked node.

```
1 → 6 → 6 → 3
    ^
    prev.next = val, bypass:
1 → 6 → 3       ← prev.next is now 6, must check again
    ^
    prev.next = val, bypass:
1 → 3            ← prev.next is now 3, safe to advance
```

---

## Why the Dummy Node Eliminates Head Special-Casing

Without dummy, removing `head` requires returning `head.next` as the new head — a separate branch. With dummy, `head` is just another `prev.next`:

```
dummy → 6 → 1 → 2      val = 6
prev = dummy
prev.next.val == 6 → dummy.next = 1 → 2
return dummy.next = 1   ✅
```

No special case needed.

---

## Approach Vaibhav Tried First (Value Copying)

```java
current.val = current.next.val;  // Wrong
```

This overwrites a value but doesn't remove the node — list length stays the same. Also crashes when `current` is the last node (`current.next == null`).

---

## Complexity

| | Value |
|---|---|
| Time  | O(n) — single pass through the list |
| Space | O(1) — only pointer variables |

---

## Solution

```java
public class Solution {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        while (prev.next != null) {
            if (prev.next.val == val) {
                prev.next = prev.next.next; // Bypass the node
            } else {
                prev = prev.next; // Advance only if no deletion
            }
        }

        return dummy.next;
    }
}
```