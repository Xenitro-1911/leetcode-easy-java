# LeetCode 69 - Sqrt(x)

**Difficulty:** Easy  
**Topic:** Math / Newton's Method

---

## Problem Summary

Given a non-negative integer `x`, return the **integer square root** (floor value), without using built-in exponent functions.

---

## Approach: Newton's Method (Babylonian Method)

Instead of binary search, this uses a **numerical convergence** technique.

If `r` is your current guess, then `x / r` is the complementary guess.  
The average of both gives a **better** estimate:

```
r = (r + x/r) / 2
```

Repeat until `r * r <= x` — at that point `r` is the floor of the square root.

---

## Final Solution

```java
public class Solution {
    public int mySqrt(int x) {
        if (x < 2) return x;
        long r = x;
        while (r * r > x) {
            r = (r + x / r) / 2;
        }
        return (int) r;
    }
}
```

---

## Line-by-Line Breakdown

| Line | Why it's there |
|---|---|
| `if (x < 2) return x` | Guards `x = 0` (division by zero) and `x = 1` (r never changes → infinite loop) |
| `long r = x` | Starting guess. `r * r` can overflow `int` for large `x`, so `long` is required |
| `while (r * r > x)` | Keep refining while our guess is still overshooting |
| `r = (r + x / r) / 2` | Newton's update — average of `r` and its complement `x/r`. Converges very fast |
| `return (int) r` | Safe cast — `r` is guaranteed to fit in `int` at this point |

---

## Why `long`?

`x` can be up to `2^31 - 1`.  
The expression `r * r` in the while condition can exceed `int` max for large values.  
`long` prevents overflow. ✅

---

## Why `if (x < 2)`?

- `x = 0` → `r = 0`, then `x / r` = division by zero 💥
- `x = 1` → without guard, `r = 1`, loop condition `1 > 1` is already false so it exits fine — but the guard makes it explicit and safe for both cases

---

## Newton's Method vs Binary Search

| | Newton's Method | Binary Search |
|---|---|---|
| Time | O(log log x) | O(log x) |
| Space | O(1) | O(1) |
| Convergence | Quadratic (very fast) | Steady halving |
| Code length | Very short | Slightly more explicit |

Newton's Method typically converges in only **5–6 iterations** for any valid `x`.

---

## Common Mistakes

| Mistake | Fix |
|---|---|
| Using `int r` | Use `long` — `r * r` overflows for large `x` |
| Skipping `x < 2` guard | Division by zero for `x = 0`, infinite loop for `x = 1` |
| Wrong loop condition | Must be `r * r > x`, not `<` |