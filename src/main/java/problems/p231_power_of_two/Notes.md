# 231 - Power of Two

## Problem Statement
Given an integer `n`, return `true` if it is a power of two, and `false` otherwise. A number is a power of two if there exists an integer `x` such that `n == 2^x`.

## Approach: Bit Manipulation

### Key Insight
Every power of two in binary has exactly **one bit set**:
```
1  = 0001  (2^0)
2  = 0010  (2^1)
4  = 0100  (2^2)
8  = 1000  (2^3)
```

Subtracting 1 from a power of two flips that single bit to 0 and sets all lower bits to 1:
```
8  = 1000
7  = 0111
8 & 7 = 0000  ✅ power of two
```

For a non-power of two (multiple bits set), `n-1` only flips the lowest set bit — the others remain, so `n & (n-1)` is never zero:
```
6  = 0110
5  = 0101
6 & 5 = 0100  ≠ 0  ✅ not a power of two
```

### Why `n > 0`?
- `n = 0`: `0 & (-1) = 0` would incorrectly return true without the guard.
- Negative numbers: can never be powers of two, and some (like `Integer.MIN_VALUE = -2147483648 = 0x80000000`) would pass the bit check without the guard since `n & (n-1) = 0`.

### Final solution:
```java
return n > 0 && (n & (n-1)) == 0;
```

### Operator Precedence Warning
`==` has higher precedence than `&` in Java. Without extra parentheses:
```java
n & (n-1) == 0   // parsed as: n & ((n-1) == 0) — WRONG
```
Always wrap the `&` expression: `(n & (n-1)) == 0`.

## Complexity
| | Value |
|---|---|
| Time | O(1) — single bitwise operation |
| Space | O(1) |

## Alternative: Loop Division
```java
if (n <= 0) return false;
while (n % 2 == 0) n /= 2;
return n == 1;
```
Repeatedly divides by 2 until odd. If the result is 1, it was a power of two. O(log n) time — correct but slower than the bit trick.

## Alternative: Built-in
```java
return n > 0 && Integer.bitCount(n) == 1;
```
`Integer.bitCount()` counts the number of set bits. A power of two has exactly one. Clean and readable but slightly more overhead than the direct `n & (n-1)` trick.

## Common Pitfalls
- **Missing `n > 0` guard** — `0` and `Integer.MIN_VALUE` both pass the bit check without it.
- **Operator precedence** — forgetting parentheses around `(n & (n-1))` causes a compile error or wrong result.
- **Using `Math.pow()`** — involves floating point and can have precision issues for large values.