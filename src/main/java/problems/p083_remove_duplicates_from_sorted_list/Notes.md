# 083. Remove Duplicates from Sorted List

## Problem
Given the head of a **sorted** linked list, delete all duplicates so each element appears only once. Return the sorted linked list.

## Approach 1 — New List (what we built during debugging)

Build a dummy-headed new list, copying only nodes that should survive:

```java
public ListNode deleteDuplicates(ListNode head) {
    ListNode dummy = new ListNode(0);
    ListNode cur = dummy;
    while (head != null) {
        if ((head.next != null && head.val != head.next.val) || head.next == null) {
            cur.next = new ListNode(head.val);
            cur = cur.next;
        }
        head = head.next;
    }
    return dummy.next;
}
```

### Key bugs fixed during development
1. **NullPointerException on last node** — `head.next.val` crashes when `head.next == null`. Guard with `head.next != null` *before* accessing `.val` (short-circuit `&&`).
2. **Guard order matters** — `head.val != head.next.val && head.next != null` still crashes; the null check must come **first**.
3. **Last node always skipped** — when `head.next == null` the value-comparison branch is skipped entirely, so the final node was never added. Fixed by adding `|| head.next == null`.
4. **`||` short-circuit direction** — with `||`, put the *safe* branch first. `(head.next != null && ...) || head.next == null` is safe; `head.next == null || head.val != head.next.val` would also work (null → skip second operand).

## Approach 2 — In-place pointer rewriting (optimal)

Because the list is already sorted, duplicates are always adjacent. Just skip them by rewiring `next`:

```java
public ListNode deleteDuplicates(ListNode head) {
    ListNode cur = head;
    while (cur != null && cur.next != null) {
        if (cur.val == cur.next.val) {
            cur.next = cur.next.next;   // skip the duplicate
        } else {
            cur = cur.next;             // only advance when no duplicate
        }
    }
    return head;
}
```

No dummy node needed. No new node allocation. The original head is always valid to return.

## Complexity

| | Time | Space |
|---|---|---|
| Approach 1 | O(n) | O(n) — new nodes |
| Approach 2 | O(n) | O(1) — in-place |

## Key Principles Reinforced
- **Null checks must precede the operation they protect** — short-circuit evaluation (`&&`, `||`) is your enforcement mechanism.
- **In-place pointer manipulation beats allocation** when the structure already satisfies invariants (sorted → duplicates are adjacent).
- **Don't advance the pointer on a deletion** — after `cur.next = cur.next.next`, recheck `cur.next` before moving forward.