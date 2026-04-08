# LeetCode Problem 26 — Remove Duplicates from Sorted Array

## Problem Summary
Given a sorted integer array `nums`, remove duplicates **in-place** so each unique element appears once. Return `k` — the count of unique elements. The first `k` elements of `nums` must hold the unique values in order.

---

## Key Insight
Since the array is **already sorted**, all duplicates are adjacent. You only need to compare each element with the previous one — no need for nested loops or extra space.

---

## Approach — Two Pointer (Write Pointer)

Use two variables:
- `f` — the **write pointer**, tracks where the next unique element should go. Starts at `1` because `nums[0]` is always unique.
- `x` — stores the **last seen unique value**, initialized to `nums[0]`.

### Steps
1. Start `f = 1`, `x = nums[0]`
2. Loop from index `1` to end:
    - If `nums[i] != x` → new unique element found
        - Write it: `nums[f] = nums[i]`
        - Increment `f++`
    - Update `x = nums[i]` every iteration
3. Return `f`

---

## Solution

```java
public class Solution {
    public int removeDuplicates(int[] nums) {
        int f = 1;
        int x = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (x != nums[i]) {
                nums[f] = nums[i];
                f++;
            }
            x = nums[i];
        }
        return f;
    }
}
```

---

## Complexity

| | Complexity |
|---|---|
| Time | O(n) — single pass through the array |
| Space | O(1) — no extra space, modified in-place |

---

## Common Mistakes
- Starting `f = 0` → overwrites `nums[0]`, losing the first unique element
- Using `nums[i+1]` → causes ArrayIndexOutOfBoundsException on the last element
- Using nested loops → unnecessary, O(n²), and the sorted property makes it redundant
- Trying to "delete" or shift elements → not needed; just overwrite the front portion

---

## Example Walkthrough

**Input:** `nums = [1, 1, 2]`

| i | nums[i] | x | Action | f |
|---|---------|---|--------|---|
| 1 | 1 | 1 | duplicate, skip | 1 |
| 2 | 2 | 1 | unique → nums[1]=2, f++ | 2 |

**Output:** `k = 2`, `nums = [1, 2, _]` ✅

---

## Pattern Tag
`Two Pointers` · `Array` · `In-Place Modification`