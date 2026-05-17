# 191. Number of 1 Bits

## Problem
Given a positive integer `n`, return the number of set bits (1s) in its binary representation (also known as the Hamming weight).

Example: `n = 11` (`1011`) → `3`

---

## Approach 1 — Bit-by-Bit (32 iterations)

Extract the last bit with `n & 1`, count it if it's `1`, then shift `n` right by 1. Repeat exactly 32 times.

```java
public int hammingWeight(int n) {
    int f = 0;
    for (int i = 0; i < 32; i++) {
        if ((n & 1) == 1) f++;
        n >>>= 1;
    }
    return f;
}
```

**Why `>>>`and not `>>`?**
For negative `n`, `>>` fills vacated bits with `1`s (sign extension), which would count phantom set bits. `>>>` always fills with `0`s.

---

## Approach 2 — Brian Kernighan's Algorithm (optimal)

`n & (n - 1)` clears exactly the lowest set bit of `n` each time.

```
n     = 1100  (12)
n-1   = 1011  (11)
n&(n-1)= 1000  (8)   ← lowest 1 bit wiped
```

Loop until `n == 0`, counting iterations. Each iteration = one `1` bit consumed.

```java
public int hammingWeight(int n) {
    int count = 0;
    while (n != 0) {
        n &= (n - 1);
        count++;
    }
    return count;
}
```

**Trace for `n = 13` (`1101`):**
| n (binary) | n-1 (binary) | n & (n-1) | count |
|------------|--------------|-----------|-------|
| 1101       | 1100         | 1100      | 1     |
| 1100       | 1011         | 1000      | 2     |
| 1000       | 0111         | 0000      | 3     |
| 0 → stop   |              |           |       |

---

## Comparison

| | Approach 1 | Approach 2 |
|---|---|---|
| Iterations | Always 32 | Equal to number of `1` bits |
| Best case | 32 | 1 (single bit set) |
| Worst case | 32 | 32 (n = -1, all bits set) |
| Handles negatives | Yes (`>>>`) | Yes (loop stops at 0) |

---

## Why the String Approach is Worse

`Integer.toBinaryString(n)` strips leading zeros — fine for counting, but creates unnecessary O(n) string allocation. Both bit approaches are O(1) space.

---

## Complexity

| | Value |
|---|---|
| Time  | O(1) — at most 32 iterations |
| Space | O(1) — no extra data structures |

---

## Solution (Optimal)

```java
public class Solution {
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            n &= (n - 1);
            count++;
        }
        return count;
    }
}
```