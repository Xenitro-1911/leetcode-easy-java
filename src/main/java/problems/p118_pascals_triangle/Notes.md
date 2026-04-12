# 118. Pascal's Triangle

**Difficulty:** Easy  
**Topic:** Array, Dynamic Programming

---

## Problem
Given an integer `numRows`, return the first `numRows` of Pascal's triangle.  
Each number is the sum of the two numbers directly above it.

---

## Approach: Row-by-Row Construction

Build each row iteratively. The first and last element of every row is always `1`.  
Every interior element at column `j` is the sum of the two elements directly above it from the previous row: `prev.get(j) + prev.get(j-1)`.

### Key Insight
Each row `i` has exactly `i+1` elements (0-indexed). The previous row is always at `x.get(i-1)`, and column indices are 0-indexed, so interior elements use `get(j)` and `get(j-1)` from the previous row — not `get(j+1)`.

---

## Solution

```java
public List<List<Integer>> generate(int numRows) {
    List<List<Integer>> x = new ArrayList<>();
    for (int i = 0; i < numRows; i++) {
        List<Integer> y = new ArrayList<>();
        for (int j = 0; j <= i; j++) {
            if (j == 0 || j == i) {
                y.add(1);
            } else {
                y.add(x.get(i - 1).get(j) + x.get(i - 1).get(j - 1));
            }
        }
        x.add(y);
    }
    return x;
}
```

---

## Complexity

| | |
|---|---|
| **Time** | O(n²) — every cell is visited exactly once |
| **Space** | O(n²) — all rows are stored in the result |

Both are optimal: you must produce n² / 2 values, so neither can be improved.

---

## Common Pitfalls

- **Off-by-one on row index:** Previous row is at `i-1`, not `i`. Easy to get wrong when mixing 0- and 1-indexed loops.
- **Off-by-one on column index:** `j` is 0-indexed, so interior access is `get(j)` and `get(j-1)` — not `get(j+1)` and `get(j)`.
- **1-indexed loops:** Switching to 0-indexed loops eliminates the mismatch between loop variable and ArrayList index entirely.

---

## Alternatives

- **Math formula:** Each element can be computed directly as C(row, col) (binomial coefficient), but this doesn't improve complexity and adds factorial overhead.
- **In-place / single list:** Some solutions build each row by modifying from right to left, reusing a single list — reduces space slightly but output still requires O(n²).