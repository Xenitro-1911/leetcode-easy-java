# Search Insert Position — Notes

## Problem Summary
Given a **sorted array** of distinct integers and a `target`, return the index of the target if found, or the index where it **would be inserted** to keep the array sorted.

---

## Algorithm: Binary Search

Binary search works by repeatedly halving the search space.

### Key Variables
| Variable | Role |
|----------|------|
| `left` | Left boundary of search window |
| `right` | Right boundary of search window |
| `mid` | Middle index: `left + (right - left) / 2` |

> ⚠️ Use `left + (right - left) / 2` instead of `(left + right) / 2` to **avoid integer overflow**.

---

## Step-by-Step Logic

```
while left <= right:
    mid = left + (right - left) / 2

    if nums[mid] == target  → return mid           (found!)
    if nums[mid] < target   → left = mid + 1       (look right)
    if nums[mid] > target   → right = mid - 1      (look left)

return left  ← insert position if not found
```

---

## Why Does `return left` Work?

When the loop ends without finding the target:
- `left > right`
- The search window has collapsed
- `left` points to the **first element greater than target** — exactly where target should be inserted

### Example
```
nums = [1, 3, 5, 6], target = 2

Step 1: left=0, right=3, mid=1 → nums[1]=3 > 2 → right=0
Step 2: left=0, right=0, mid=0 → nums[0]=1 < 2 → left=1
Loop ends: left=1, right=0

return left = 1  ✓ (insert 2 between index 0 and 1)
```

---

## Complexity

| | Value |
|---|---|
| **Time** | O(log n) — halves the search space each step |
| **Space** | O(1) — only uses a few integer variables |

---

## Common Pitfalls

- Forgetting `=` in `left <= right` — causes off-by-one errors
- Using `mid = (left + right) / 2` — can overflow for large arrays
- Returning `-1` instead of `left` when not found — wrong for insert position problems

---

## Pattern Recognition

Use this exact template when you see:
- Sorted array ✓
- Find target OR find insertion point ✓
- O(log n) required ✓

The `return left` trick applies to many binary search variants — memorise it!