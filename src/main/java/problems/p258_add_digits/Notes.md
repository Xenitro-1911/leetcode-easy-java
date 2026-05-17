# 258 - Add Digits

## Problem Statement
Given an integer `num`, repeatedly add all its digits until the result has only one digit, and return it.

## Approach: Digital Root Formula (O(1))

### Final Solution
```java
if (num == 0) return 0;
return 1 + (num - 1) % 9;
```

### What is the Digital Root?
The digital root of a number is the single digit you get by repeatedly summing its digits. It turns out this value has a direct mathematical relationship with the number modulo 9 — no iteration needed.

### Why does repeated digit summing relate to mod 9?

Consider a two-digit number: `num = 10a + b` (where `a` is the tens digit, `b` is the units digit).

The digit sum is `a + b`.

Now: `10a + b = 9a + (a + b)`

So `num = 9a + digitSum`. This means:
```
num % 9 == digitSum % 9
```

This holds for any number of digits — every place value (`10`, `100`, `1000`) is just `9k + 1` for some `k`, so all the "carry" from place values is a multiple of 9 and disappears under mod 9. The digit sum always has the same remainder mod 9 as the original number.

Applying this repeatedly: no matter how many times you sum the digits, the result always has the same mod 9 as the original.

### So why not just `num % 9`?

Almost — but two edge cases need fixing:

**Edge case 1: `num = 0`**
`0 % 9 = 0` ✅ — correct, but handled separately for clarity.

**Edge case 2: multiples of 9**
`18 % 9 = 0`, but the digital root of 18 is `1+8 = 9`, not 0.
`9 % 9 = 0`, but the digital root of 9 is 9, not 0.

So `num % 9` gives 0 for multiples of 9, when the correct answer is 9.

### The fix: `1 + (num - 1) % 9`

Subtracting 1 shifts the number line so that multiples of 9 map to 8 (mod 9), then adding 1 back shifts them to 9:

```
num=9:  1 + (9-1) % 9  = 1 + 8 % 9  = 1 + 8 = 9  ✅
num=18: 1 + (18-1) % 9 = 1 + 17 % 9 = 1 + 8 = 9  ✅
num=1:  1 + (1-1) % 9  = 1 + 0 % 9  = 1 + 0 = 1  ✅
num=10: 1 + (10-1) % 9 = 1 + 9 % 9  = 1 + 0 = 1  ✅
num=11: 1 + (11-1) % 9 = 1 + 10 % 9 = 1 + 1 = 2  ✅
```

The `- 1 / + 1` trick maps the range `[1..9]` to `[0..8]` under mod 9, then back to `[1..9]` — perfectly aligning multiples of 9 with the output 9 instead of 0.

## Complexity
| | Value |
|---|---|
| Time | O(1) — single arithmetic expression |
| Space | O(1) |

## Alternative: Iterative / Recursive Digit Summing
```java
if (num < 10) return num;
int sum = 0;
while (num != 0) {
    sum += num % 10;
    num /= 10;
}
return addDigits(sum);
```
Correct but O(log n) per reduction step, with multiple steps until single digit. Fine for small inputs but unnecessary given the O(1) formula.

## Common Pitfalls
- **Using `num % 9` directly** — returns 0 for multiples of 9 instead of 9.
- **Missing `num == 0` base case in recursive approach** — leads to infinite recursion since `addDigits(0)` keeps calling itself.
- **Forgetting `return` on recursive call** — `addDigits(sum)` without `return` compiles but returns nothing, causing a compile error or wrong result.
- **Infinite loop with `sum > 0` condition** — using `sum < 10 && sum > 0` as the base case misses `sum == 0`, causing infinite recursion on input 0.