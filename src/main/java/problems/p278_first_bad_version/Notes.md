# 278 - First Bad Version

## Problem Statement
Given `n` versions `[1, 2, ..., n]` and an API `isBadVersion(version)` that returns whether a version is bad, find the first bad version while minimising API calls. All versions after the first bad one are also bad.

## Approach: Binary Search

### Key Insight
The versions form a sorted boolean sequence: `[good, good, ..., bad, bad, bad]`. This is a classic binary search on a monotonic condition — find the leftmost `true` in the sequence.

### Algorithm
1. Set `left = 1`, `right = n`.
2. Compute `mid = left + (right - left) / 2`.
3. If `isBadVersion(mid)` is true → the first bad version is at `mid` or earlier → `right = mid`.
4. If false → first bad version is after `mid` → `left = mid + 1`.
5. When `left == right`, both pointers converge on the first bad version — return `left`.

```java
int left = 1, right = n;
while (left < right) {
    int mid = left + (right - left) / 2;
    if (isBadVersion(mid)) {
        right = mid;       // mid could be the answer, keep it in range
    } else {
        left = mid + 1;    // mid is good, first bad must be after
    }
}
return left;
```

### Why `left < right` instead of `left <= right`?
With `left <= right`, when `isBadVersion(mid)` is true you'd set `right = mid - 1`, potentially excluding the actual answer. Using `left < right` with `right = mid` (not `mid - 1`) keeps the candidate in the search range and naturally converges without a separate return check.

### Why `right = mid` not `right = mid - 1`?
If `isBadVersion(mid)` is true, `mid` itself could be the first bad version. Setting `right = mid - 1` would skip it. Setting `right = mid` keeps it as a candidate.

### Why return `left` not `right`?
When the loop exits, `left == right` — both point to the same answer. Either works, but `left` is conventional.

### Safe mid calculation: `left + (right - left) / 2`
The naive `(left + right) / 2` can overflow when both values are large integers (e.g. `left = right = Integer.MAX_VALUE / 2 + 1`). Subtracting first keeps the intermediate value within `int` range.

## Complexity
| | Value |
|---|---|
| Time | O(log n) — search space halves each iteration |
| Space | O(1) |
| API calls | O(log n) — exactly one call per iteration |

## Common Pitfalls
- **`(left + right) / 2` overflow** — for large `n`, `left + right` can exceed `Integer.MAX_VALUE`. Always use `left + (right - left) / 2`.
- **`right = mid - 1` when bad** — excludes `mid` as a candidate; use `right = mid` instead.
- **`left <= right` with `right = mid`** — causes infinite loop when `left == right` since mid never changes. Use `left < right`.
- **Returning `mid` after the loop** — `mid` is the last computed midpoint, not guaranteed to be the answer. Return `left` (or `right`).
- **Calling `isBadVersion(mid - 1)` inside loop** — doubles API calls unnecessarily and risks calling version 0 when `mid = 1`.# Notes.md - Notes
