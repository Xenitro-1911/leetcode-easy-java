# 119. Pascal's Triangle II

**Difficulty:** Easy  
**Topic:** Array, Dynamic Programming, Math

---

## Problem
Given an integer `rowIndex`, return the `rowIndex`-th (0-indexed) row of Pascal's triangle using only O(1) extra space (beyond the output).

---

## Approach: Binomial Coefficients

Instead of building all rows up to `rowIndex`, each element in row `n` can be computed directly using the binomial coefficient formula:

```
C(n, k) = n! / (k! * (n-k)!)
```

Rather than computing factorials (which overflow quickly), we use the **multiplicative recurrence**:

```
C(n, 0) = 1
C(n, k) = C(n, k-1) * (n - k + 1) / k
```

Each next value is derived from the previous one by multiplying and dividing incrementally — no factorial storage needed.

### Key Insight
The recurrence `current = current * (rowIndex - i) / (i + 1)` computes C(rowIndex, i+1) from C(rowIndex, i).  
Using `long` for `current` before casting to `int` prevents intermediate overflow — the multiplication `current * (rowIndex - i)` can exceed `Integer.MAX_VALUE` even if the final result fits in an `int`.

---

## Solution

```java
public List<Integer> getRow(int rowIndex) {
    List<Integer> row = new ArrayList<>();
    long current = 1;
    for (int i = 0; i <= rowIndex; i++) {
        row.add((int) current);
        current = current * (rowIndex - i) / (i + 1);
    }
    return row;
}
```

---

## Complexity

| | |
|---|---|
| **Time** | O(n) — single pass, one element computed per iteration |
| **Space** | O(n) — only the output row is stored; no auxiliary rows |

This is a significant improvement over the row-by-row approach (O(n²) time and space) from problem 118.

---

## Common Pitfalls

- **Integer overflow:** `current * (rowIndex - i)` must be computed as `long` before dividing. Doing this in `int` overflows for large `rowIndex` values even if the final binomial coefficient fits in an `int`.
- **Division ordering:** The recurrence multiplies before dividing. This works because C(n, k) is always an integer — the division is always exact at each step. Dividing first would produce incorrect results due to integer truncation.
- **Off-by-one:** The loop runs from `0` to `rowIndex` inclusive — that's `rowIndex + 1` elements, which is correct for a 0-indexed row.

---

## Alternatives

- **Row-by-row (problem 118 approach):** O(n²) time and space — works but wasteful when only one row is needed.
- **In-place right-to-left update:** Build the row iteratively, updating from right to left to avoid overwriting values still needed. O(n²) time, O(n) space — valid but slower than the binomial approach.