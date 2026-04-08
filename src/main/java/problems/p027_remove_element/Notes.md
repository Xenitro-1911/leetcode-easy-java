# LeetCode 27 - Remove Element

## Problem Summary
Given an integer array `nums` and an integer `val`, remove all occurrences of `val` **in-place**. Return `k` — the count of elements **not equal** to `val`. The first `k` elements of `nums` must hold the valid result.

---

## Approach: Two-Pointer (Fast & Slow)

We use a **write pointer** `k` and a **read pointer** `i`.

- `i` scans every element in the array.
- `k` only advances when we find an element worth keeping (i.e., `nums[i] != val`).
- When we keep an element, we write it to `nums[k]` then increment `k`.

At the end, `k` equals the number of valid elements, and the first `k` positions of `nums` hold them.

---

## Walkthrough Example

```
nums = [3, 2, 2, 3],  val = 3

i=0: nums[0]=3 == val  → skip,       k=0
i=1: nums[1]=2 != val  → nums[0]=2,  k=1
i=2: nums[2]=2 != val  → nums[1]=2,  k=2
i=3: nums[3]=3 == val  → skip,       k=2

Result: k=2, nums = [2, 2, _, _]
```

---

## The Code

```java
public int removeElement(int[] nums, int val) {
    int k = 0;
    for (int i = 0; i < nums.length; i++) {
        if (nums[i] != val) {
            nums[k] = nums[i];
            k++;
        }
    }
    return k;
}
```

---

## Complexity

| | Value |
|---|---|
| **Time** | O(n) — single pass through the array |
| **Space** | O(1) — in-place, no extra array needed |

---

## Key Concepts

- **In-place modification**: We overwrite the array itself; no new array is allocated.
- **Two-pointer pattern**: One pointer reads (`i`), one pointer writes (`k`). This is the classic "fast-slow" pointer technique.
- **Order may change**: The problem allows the first `k` elements to be in any order, so we don't need to worry about stability.

---

## Edge Cases to Know

| Input | Expected k |
|---|---|
| `nums = []` | `0` |
| All elements equal `val` | `0` |
| No elements equal `val` | `nums.length` |
| Single element == val | `0` |
| Single element != val | `1` |