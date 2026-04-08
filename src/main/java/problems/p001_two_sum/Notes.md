# 001 - Two Sum

**Platform:** LeetCode  
**Difficulty:** Easy  
**Link:** https://leetcode.com/problems/two-sum/  
**Date Solved:** 2026-04-06

---

## Problem Summary
Given an array of integers and a target, return the indices of the two numbers that add up to the target.
Exactly one solution exists. Cannot use the same element twice.

---

## Approach: Hash Map (One Pass)

For each element, calculate its complement (`target - current`).
Check if the complement is already in the map — if yes, return both indices.
Otherwise, store the current element and its index in the map.

The key insight: instead of looking forward for a pair, we look *backward*
using the map as memory of what we've already seen.

---

## Complexity

| | |
|---|---|
| Time | O(n) — one pass through the array |
| Space | O(n) — map stores up to n elements |

---

## Alternatives Considered

**Brute Force — O(n²)**  
Two nested loops checking every pair. Works but too slow for large inputs.
```java
for (int i = 0; i < nums.length; i++)
    for (int j = i + 1; j < nums.length; j++)
        if (nums[i] + nums[j] == target) return new int[]{i, j};
```

---

## What I Learned
- HashMap is the go-to tool when you need O(1) lookups to avoid nested loops
- Store value → index (not index → value) so you can check complements instantly
- Always handle the complement check *before* inserting into the map
  to avoid using the same index twice