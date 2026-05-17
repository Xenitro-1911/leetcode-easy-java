# 202. Happy Number

## Problem
A **happy number** is defined by the following process:
- Starting with any positive integer, replace the number by the sum of the squares of its digits.
- Repeat until the number equals 1 (happy), or it loops endlessly in a cycle (not happy).

Example: `19 → 82 → 68 → 100 → 1` ✅ happy

---

## What Actually Happens to Unhappy Numbers?

Every unhappy number eventually enters the same cycle:
```
4 → 16 → 37 → 58 → 89 → 145 → 42 → 20 → 4 → ...
```
It never escapes. This is a mathematical fact — all non-happy numbers collapse into this loop.

This means the problem reduces to: **does the sequence reach 1, or does it enter a cycle?**

---

## Approach 1 — HashSet (Vaibhav's solution)

Store every number seen. If you see a repeat → cycle detected → return false. If you reach 1 → return true.

```java
public boolean isHappy(int n) {
    int x = 0;
    List<Integer> num = new ArrayList<>();
    while (true) {
        x = 0;
        while (n != 0) {
            x = x + (n % 10) * (n % 10);
            n /= 10;
        }
        if (x == 1) return true;
        else if (num.contains(x)) return false;
        else { n = x; num.add(x); }
    }
}
```

**Note:** Using `HashSet` instead of `ArrayList` gives O(1) lookup vs O(n).

---

## Approach 2 — Floyd's Cycle-Finding Algorithm (Optimal)

### The Core Idea

Imagine two runners on a circular track. One runs at normal speed (slow), one at double speed (fast). If there's a loop, the fast runner will eventually **lap** the slow runner and they'll meet. If there's no loop (sequence ends at 1), the fast runner hits the end first.

This is Floyd's Cycle-Finding Algorithm, also called the **tortoise and hare** algorithm.

### Applied to Happy Numbers

The sequence of numbers generated (`n → getNext(n) → ...`) behaves like a linked list. Either it:
- Reaches `1` and stays there (1 → 1 → 1 → ...)
- Enters the unhappy cycle (4 → 16 → 37 → ... → 4 → ...)

We run two pointers through this sequence:
- `slow` advances **one step** at a time
- `fast` advances **two steps** at a time

```
slow: n → f(n) → f(f(n)) → ...
fast: f(n) → f(f(f(n))) → ...
```

**Case 1 — Happy number:** `fast` reaches `1` first. Loop exits, `fast == 1` → return true.

**Case 2 — Unhappy number:** Both pointers enter the cycle. Since `fast` moves twice as fast, it will eventually catch up to `slow` from behind inside the cycle. When `slow == fast`, a cycle is confirmed → return false.

### Why Do They Always Meet Inside a Cycle?

Think of it this way: once both pointers are in the cycle, the distance between them changes by 1 each step (fast closes the gap by 1 per iteration since it moves 1 extra step). Eventually the gap reaches 0 — they meet.

More formally: if the cycle has length `L`, after at most `L` steps inside the cycle, fast will have lapped slow and they meet.

### Trace for `n = 4` (unhappy):

```
slow        fast
4           16
16          58
37          42
58          4
89          89   ← slow == fast, cycle detected!
```

Return `fast == 1` → `89 == 1` → `false` ✅

### Trace for `n = 19` (happy):

```
slow        fast
82          68
68          1    ← fast == 1, exit loop
```

Return `fast == 1` → `true` ✅

---

## `getNext` Helper

```java
private int getNext(int n) {
    int totalSum = 0;
    while (n > 0) {
        int d = n % 10;
        totalSum += d * d;
        n /= 10;
    }
    return totalSum;
}
```

Extracts each digit via `% 10`, squares it, accumulates. Then `n /= 10` discards the used digit.

---

## Comparison

| | HashSet | Floyd's |
|---|---|---|
| Time | O(k) | O(k) |
| Space | O(k) — stores all seen values | O(1) — only two pointers |
| Cycle detection | Explicit seen-set | Implicit via pointer collision |

`k` = steps until cycle detection (small in practice, bounded by ~hundreds).

---

## Why `getNext` Uses `n > 0` Not `n != 0`

Input `n` is always a positive integer, so `> 0` is safe and equivalent here. In p190/p191 we used `n != 0` with `>>>` to handle negative bit patterns — not needed here since digit extraction always produces positive values.

---

## Complexity

| | Value |
|---|---|
| Time  | O(log n) per `getNext` call, O(1) calls until detection |
| Space | O(1) — two integer pointers only |

---

## Solution

```java
public class Solution {
    public boolean isHappy(int n) {
        int slow = n;
        int fast = getNext(n);

        while (fast != 1 && slow != fast) {
            slow = getNext(slow);
            fast = getNext(getNext(fast));
        }

        return fast == 1;
    }

    private int getNext(int n) {
        int totalSum = 0;
        while (n > 0) {
            int d = n % 10;
            totalSum += d * d;
            n /= 10;
        }
        return totalSum;
    }
}
```