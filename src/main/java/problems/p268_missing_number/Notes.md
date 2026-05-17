# 268 - Missing Number

## Problem Statement
Given an array `nums` containing `n` distinct numbers in the range `[0, n]`, return the only number in the range that is missing from the array.

## Approach: Gauss Formula

### Key Insight
The sum of all integers from `0` to `n` is given by the Gauss formula: `n*(n+1)/2`. The actual sum of the array is missing exactly one number. The difference between the expected sum and the actual sum is the missing number.

```java
int sum = 0;
int l = nums.length;
for (int i = 0; i < l; i++) {
    sum += nums[i];
}
return (l * (l + 1) / 2) - sum;
```

### What is `n` here?
`n` is `nums.length` — the array has `n` elements drawn from the range `[0, n]`, so the largest possible value is `n` itself (the array length).

### Trace on `[3, 0, 1]`:
```
l = 3
Expected sum = 3*4/2 = 6
Actual sum   = 3+0+1 = 4
Missing      = 6-4   = 2 ✅
```

### Trace on `[0, 1]`:
```
l = 2
Expected sum = 2*3/2 = 3
Actual sum   = 0+1   = 1
Missing      = 3-1   = 2 ✅
```

### Trace on `[1]` (missing 0):
```
l = 1
Expected sum = 1*2/2 = 1
Actual sum   = 1
Missing      = 1-1   = 0 ✅
```

## Complexity
| | Value |
|---|---|
| Time | O(n) — single pass to compute the sum |
| Space | O(1) — only a sum variable |

## Why Not Sort?
Sorting is O(n log n) and requires checking adjacent elements plus special cases for missing 0 and missing last element. The Gauss formula eliminates all of that in one pass.

## Alternative: XOR
```java
int xor = nums.length;
for (int i = 0; i < nums.length; i++) {
    xor ^= i ^ nums[i];
}
return xor;
```
XOR all indices `0` to `n` with all array values. Since XOR is self-inverse (`a ^ a = 0`), every number that appears in both cancels out, leaving only the missing number. Also O(n) time, O(1) space — avoids any risk of integer overflow since no addition is involved.

## Common Pitfalls
- **Sorting approach is O(n log n)** — and requires special casing for missing 0 and missing last element.
- **Off-by-one on Gauss formula** — the range is `[0, n]` so the expected sum is `n*(n+1)/2` where `n = nums.length`, not `n-1`.
- **Integer overflow** — for very large `n`, `n*(n+1)` could overflow `int`. Safe here since LeetCode constrains `n <= 10^4`, but for larger inputs use `long`.