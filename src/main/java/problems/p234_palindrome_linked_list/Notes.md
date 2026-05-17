# 234 - Palindrome Linked List

## Problem Statement
Given the head of a singly linked list, return `true` if it is a palindrome, `false` otherwise.

## Approach: Slow/Fast Pointer + Reverse Second Half

### Key Insight
A palindrome reads the same forwards and backwards. For a linked list, we can't go backwards — so instead we reverse the second half in place and compare it against the first half node by node.

### Three-Phase Algorithm

**Phase 1 — Find the middle using slow/fast pointers:**
- `slow` moves one step at a time, `fast` moves two.
- When `fast` reaches the end, `slow` is at the middle.

```
Odd  [1,2,3,2,1]: fast lands on last node  → slow at 3 (middle)
Even [1,2,2,1]:   fast lands on null       → slow at second 2
```

In both cases, reversing from `slow` onwards gives the correct second half.

**Phase 2 — Reverse the second half in place:**
Standard iterative reversal with `prev`, `temp` variables (same as p206).

**Phase 3 — Compare first half with reversed second half:**
Walk `x` (from head) and `prev` (reversed second half head) together. If any values differ, return false.

```java
// Phase 1
while (Fast != null && Fast.next != null) {
    Slow = Slow.next;
    Fast = Fast.next.next;
}

// Phase 2
while (Slow != null) {
    ListNode temp = Slow.next;
    Slow.next = prev;
    prev = Slow;
    Slow = temp;
}

// Phase 3
while (prev != null) {
    if (prev.val != x.val) return false;
    prev = prev.next;
    x = x.next;
}
return true;
```

### Why does Phase 3 terminate correctly?
The reversed second half is always ≤ the first half in length (for odd lists, the middle node is included in the reversal but the comparison stops when `prev` runs out). So `prev` reaching null is the correct termination condition.

### Odd vs Even length trace:
```
[1, 2, 1]  (odd)
Phase 1: slow=2, fast=1(last)
Phase 2: reverse [2,1] → prev=[1,2]
Phase 3: 1==1 ✓, 2==2 ✓ → true ✅

[1, 2, 2, 1]  (even)
Phase 1: slow=2(second), fast=null
Phase 2: reverse [2,1] → prev=[1,2]
Phase 3: 1==1 ✓, 2==2 ✓ → true ✅
```

## Complexity
| | Value |
|---|---|
| Time | O(n) — three linear passes, each O(n) |
| Space | O(1) — only pointers, no extra data structures |

This is optimal — you must read every element at least once, so O(n) time is the theoretical minimum.

## Common Pitfalls
- **Comparing node references instead of values** — `prev != x` checks if they are the same object, not the same value. Always use `prev.val != x.val`.
- **Starting `fast` at `head.next.next`** — overshoots the middle. Both pointers must start at `head`.
- **Not guarding `Fast.next` in the while condition** — `Fast.next.next` throws NullPointerException if `Fast.next` is null. Always check `Fast != null && Fast.next != null`.
- **Reversing the whole list** — unnecessary; only the second half needs reversing.

## Alternative: Copy to Array
```java
List<Integer> vals = new ArrayList<>();
ListNode cur = head;
while (cur != null) { vals.add(cur.val); cur = cur.next; }
int l = 0, r = vals.size() - 1;
while (l < r) {
    if (!vals.get(l).equals(vals.get(r))) return false;
    l++; r--;
}
return true;
```
Copies all values into a list and uses two pointers to check palindrome. Simple but O(n) space — the in-place reversal approach is strictly better.