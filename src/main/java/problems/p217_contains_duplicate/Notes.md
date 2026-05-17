# 217 - Contains Duplicate

## Problem Statement
Given an integer array `nums`, return `true` if any value appears **at least twice**, and `false` if every element is distinct.

## Approach: HashSet Membership Check

### Key Insight
For each element, we need to know whether we've seen it before. A `HashSet` gives O(1) average-time lookup and insertion, making a single pass through the array sufficient.

### Why `HashSet.add()` instead of `HashSet.contains()`
`add()` returns `false` if the element was already present — combining the check and insertion into one operation, eliminating a redundant `contains()` call.

### Initial Capacity Hint
Passing `nums.length` as the initial capacity to the `HashSet` constructor reduces the number of internal resize/rehash operations as elements are added, providing a modest performance improvement.

```java
HashSet<Integer> x = new HashSet<>(nums.length);

for (int num : nums) {
    if (!x.add(num)) {
        return true;
    }
}
return false;
```

## Complexity
| | Value |
|---|---|
| Time | O(n) — single pass, O(1) per add/lookup |
| Space | O(n) — worst case all elements are distinct |

## Common Pitfalls
- **Using ArrayList** — `contains()` on a list is O(n), making the overall solution O(n²).
- **Using HashMap** — works but unnecessarily stores key-value pairs; a `HashSet` is the right tool when you only care about presence, not mapping.
- **Sorting first** — sorting is O(n log n) and then checking adjacent elements works, but is strictly worse than the HashSet approach in time complexity.

## Why Not Sort?
Sorting `nums` and checking adjacent elements for equality does work:
```java
Arrays.sort(nums);
for (int i = 1; i < nums.length; i++) {
    if (nums[i] == nums[i-1]) return true;
}
return false;
```
But this is O(n log n) time and O(1) space. The HashSet approach trades space for better time — O(n) vs O(n log n). For this problem LeetCode accepts either, but HashSet is the canonical answer.