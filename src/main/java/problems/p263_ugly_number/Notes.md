# 263 - Ugly Number

## Problem Statement
An ugly number is a positive integer whose only prime factors are 2, 3, and 5. Given an integer `n`, return `true` if `n` is an ugly number.

## Approach: Repeated Division

### Key Insight
If a number's only prime factors are 2, 3, and 5, then repeatedly dividing it by 2, 3, and 5 whenever possible must eventually reduce it to 1. If anything other than 1 remains, the number has another prime factor and is not ugly.

### Algorithm
1. Guard against non-positive numbers — ugly numbers are strictly positive.
2. Keep dividing by 2, 3, and 5 in any order whenever divisible.
3. When none of 2, 3, 5 divide evenly, break.
4. If `n == 1` → ugly. Otherwise → not ugly.

```java
if (n <= 0) return false;

while (n != 0) {
    if      (n % 2 == 0) n /= 2;
    else if (n % 3 == 0) n /= 3;
    else if (n % 5 == 0) n /= 5;
    else break;
}
return n == 1;
```

### Trace on n = 12:
```
12 % 2 == 0 → n = 6
6  % 2 == 0 → n = 3
3  % 3 == 0 → n = 1
n == 1 → true ✅
```

### Trace on n = 14:
```
14 % 2 == 0 → n = 7
7  % 2 != 0
7  % 3 != 0
7  % 5 != 0
break → n = 7
n != 1 → false ✅
```

### Why `n <= 0` returns false?
Ugly numbers are defined as **positive** integers only. `0` would cause an infinite loop since `0 % 2 == 0` forever. Negative numbers can never be ugly by definition.

## Complexity
| | Value |
|---|---|
| Time | O(log n) — each division reduces n by at least half |
| Space | O(1) |

## Common Pitfalls
- **Missing `n <= 0` guard** — `0` causes an infinite loop; negative numbers should return false.
- **Checking all factors instead of prime factors** — you only need 2, 3, and 5. Checking 4, 6, etc. is redundant since they are composed of 2s and 3s already handled.
- **Stopping too early** — must keep dividing until none of 2, 3, 5 apply, not just one pass each.
- **Returning false for n=1** — 1 is defined as ugly (it has no prime factors at all, so vacuously satisfies the condition).