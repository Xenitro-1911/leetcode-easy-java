# 206 - Reverse Linked List

## Problem Statement
Given the head of a singly linked list, reverse the list and return the new head.

## Approach: Iterative Pointer Reversal

### Key Insight
A singly linked list only has forward pointers. To reverse it, at each node you need to redirect `current.next` to point **backwards** to `prev`. But before doing that, you must save `current.next` in a `temp` variable — otherwise you lose your only reference to the rest of the list.

### Three variables needed at every step:
- `temp` — saves the next node before the pointer is overwritten
- `prev` — the node that `current` should now point to
- `current` — the node being processed

### Algorithm
1. Handle the null case — empty list returns null.
2. Start with `prev = null` and `current = head`.
3. At each step:
    - Save `current.next` into `temp`
    - Redirect `current.next = prev`
    - Advance `prev = current`
    - Advance `current = temp`
4. When `current` is null, `prev` is the new head — return it.

```java
public ListNode reverseList(ListNode head) {
    if (head == null) return null;

    ListNode prev = null;
    ListNode current = head;

    while (current != null) {
        ListNode temp = current.next;
        current.next = prev;
        prev = current;
        current = temp;
    }
    return prev;
}
```

### Why `prev` starts as `null`
The original `head` becomes the last node after reversal. The last node must point to `null`, so initialising `prev = null` handles this automatically.

### Why return `prev` and not `current`
When the loop exits, `current` is `null` (that's the exit condition). `prev` is the last node that was processed — which is the new head of the reversed list.

## Complexity
| | Value |
|---|---|
| Time | O(n) — single pass through the list |
| Space | O(1) — only three pointers, no extra data structures |

## Common Pitfalls
- **Saving `temp` too late** — if you do `current.next = prev` before saving `current.next`, you permanently lose the rest of the list.
- **Wrong order of `prev` and `current` updates** — doing `current = temp` before `prev = current` makes `prev` point to the next node instead of the current one, corrupting the reversal.
- **Wrong loop condition** — using `current.next != null` stops one node early; the last node never gets its pointer reversed.
- **Returning `current`** — it's `null` at loop exit. Always return `prev`.
- **Not handling null head** — calling `head.next` on a null head throws a NullPointerException.

## Alternative: Recursive
```java
public ListNode reverseList(ListNode head) {
    if (head == null || head.next == null) return head;
    ListNode newHead = reverseList(head.next);
    head.next.next = head;
    head.next = null;
    return newHead;
}
```
The recursive approach reverses from the tail back. Recurses until the last node (new head), then on the way back up each node's `next.next` is pointed back at itself and its own `next` set to null. Elegant but uses O(n) stack space — the iterative approach is preferred for large lists.