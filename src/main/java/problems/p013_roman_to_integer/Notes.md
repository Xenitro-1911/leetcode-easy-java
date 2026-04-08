# Roman to Integer - Notes

## Problem
Convert a Roman numeral string to an integer.

## Key Insights

### Roman Numeral Rules
- If current value >= next value → **ADD** current
- If current value < next value → **SUBTRACT** current
- Last character is **always added**

### Mistakes to Avoid
1. **Method name must match** what the driver expects (`romanToInt` not `toInteger`)
2. **Off-by-one errors** — use `i < s.length()` not `i <= s.length()`
3. **No need for a char array** — access characters directly with `s.charAt(j)`
4. **No need for a special case for j==0** — the look-ahead logic handles it naturally

## Optimization Journey
| Version | Data Structure | Lookup Cost |
|---------|---------------|-------------|
| v1 | HashMap | O(1) avg but with hashing overhead |
| v2 | int[128] array | O(1) true constant, direct ASCII index |

### Why int[128] beats HashMap
- `char` in Java has an underlying ASCII integer value
- e.g. `'I'` = 73, `'V'` = 86 etc.
- So `romanArr['I']` = `romanArr[73]` — **direct memory access, zero hashing**
- Size 128 covers all uppercase ASCII characters

### Why 2ms on LeetCode doesn't mean faster code
- LeetCode runtimes **fluctuate** due to shared servers
- Java's **JIT compiler** warms up differently each run
- Roman numeral strings are **max 15 chars** — differences are negligible
- Same code can show 2ms or 6ms on different submissions

## Complexity
- **Time:** O(n) — single pass, theoretical minimum
- **Space:** O(1) — fixed size array (128), no extra allocations

## Why Not Recursion?
- Same O(n) time but with function call stack overhead
- No overlapping subproblems or optimal substructure
- Iterative is faster and safer (no stack overflow risk)