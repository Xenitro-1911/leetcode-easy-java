# 88. Merge Sorted Array

## Problem
Merge two sorted arrays `nums1` (size `m+n`, first `m` valid) and `nums2` (size `n`) into `nums1` in-place, in non-decreasing order.

## Approach: Three-Pointer from the Right (Optimal)

### Key Insight
Filling `nums1` from the **front** risks overwriting elements you haven't compared yet.
Filling from the **back** is safe: the write pointer `z` can never catch up to `i` because every write consumes one slot that was either empty (the `n` trailing zeros) or already placed.

### Pointers
| Pointer | Starts at | Meaning |
|---------|-----------|---------|
| `i` | `m - 1` | Last valid element of `nums1` |
| `j` | `n - 1` | Last element of `nums2` |
| `z` | `m+n - 1` | Current write position in `nums1` |

### Algorithm
1. While both arrays have unplaced elements:
    - Place the larger of `nums1[i]` and `nums2[j]` at `nums1[z]`
    - Advance the consumed pointer and `z` backward
2. Drain any remaining `nums2` elements into the front of `nums1`
    - No drain needed for `nums1` — if `j < 0`, remaining `nums1` elements are already in place

## Complexity
- **Time:** O(m + n) — each element is visited exactly once
- **Space:** O(1) — fully in-place, no auxiliary array

## Bugs Encountered Along the Way

### Attempt 1 — Nested loop with cross-array swap
- Compared `nums1[i]` against `nums2[j]` but never actually inserted `nums2` elements
- Swap wrote to `nums1[i+1]` unconditionally, corrupting the array

### Attempt 2 — Auxiliary array with nested loop
- Nested loop caused O(m*n) writes into `x` — far more than `m+n` elements
- Off-by-one in drain loops: `while(i <= m)` accessed `nums1[m]` (out of bounds)

### Attempt 3 — Nested loop with break
- `break` in both branches fixed j at 0 forever — `nums2[1..n-1]` never merged in main loop

### Attempt 4 — Correct auxiliary array, single while loop ✓
- Two-pointer front-to-back merge into auxiliary array `x`, then copy back
- Correct but O(m+n) space

### Attempt 5 — In-place with temp tracking
- Overcomplicated: tried to track a running `temp` value with 4 conditions
- Unnecessary — the back-to-front approach needs no temp variable

### Final Solution — Three-pointer from the right ✓
- Missed drain loop for remaining `nums2` elements initially
- Added `while(j >= 0)` drain to handle cases like `nums2 = [1,2,3]`, `nums1 = [4,5,6,_,_,_]`

## Key Takeaways
- When merging in-place into an array with extra space at the back, **iterate from the right** to avoid overwriting unprocessed elements
- The drain loop for `nums1` is unnecessary — elements already in place don't need to move
- The drain loop for `nums2` is essential — elements smaller than all of `nums1` won't be placed by the main loop
- 0ms on LeetCode at small input sizes does not distinguish O(1) from O(m+n) space — timer resolution is the bottleneck, not the algorithm