# 136. Single Number

## Problem
Given a non-empty array of integers where every element appears twice except for one, find that single one.

## Constraints
- O(n) time complexity
- O(1) space complexity

## Approach — XOR Bit Manipulation

Use XOR across all elements in the array.

**Key properties:**
- `a ^ a = 0` → duplicates cancel out
- `a ^ 0 = a` → the unique number survives
- XOR is commutative and associative, so order doesn't matter

**Example:** `[4, 1, 4]`
```
0 ^ 4 = 4
4 ^ 1 = 5
5 ^ 4 = 1  ✓
```

## Solution

```java
public int singleNumber(int[] nums) {
    int x = 0;
    for (int i = 0; i < nums.length; i++) {
        x = x ^ nums[i];
    }
    return x;
}
```

## Complexity
- **Time:** O(n) — single pass through the array
- **Space:** O(1) — only one integer variable used