# 168. Excel Sheet Column Title

## Problem
Given an integer `columnNumber`, return its corresponding Excel column title.
- 1 → "A", 26 → "Z", 27 → "AA", 702 → "ZZ"

## Approach — Reverse Base-26 Conversion

Excel columns are **base-26 with no zero digit** (digits are A–Z, i.e. 1–26).
Standard base conversion doesn't work directly because `% 26` gives 0 for multiples of 26,
but there is no "0" character — 26 maps to 'Z', not a zero-roll.

### Key Insight
Build the result **right-to-left** (least significant digit first), then reverse.

At each step:
1. `x = columnNumber % 26` — get the current last digit
2. If `x == 0`, treat it as 26 (i.e. the character is 'Z') — this is the "no zero" adjustment
3. Append `(char)(x + 64)` — 'A' is 65, so offset by 64
4. `columnNumber = (columnNumber - 1) / 26` — the `-1` accounts for the borrow when x was 0

### Why `(columnNumber - 1) / 26`?
When `x == 0` (e.g. columnNumber = 26), we set x = 26 instead.
This means we consumed a full 26, so the remaining value should be one less unit before dividing.
Using `(columnNumber - 1) / 26` uniformly handles this correctly for all cases.

## Solution

```java
public class Solution {
    public String convertToTitle(int columnNumber) {
        StringBuilder s = new StringBuilder();

        while (columnNumber != 0) {
            int x = columnNumber % 26;
            if (x == 0) x = 26;
            s.append((char) (x + 64));
            columnNumber = (columnNumber - 1) / 26;
        }

        return s.reverse().toString();
    }
}
```

## Complexity
- **Time:** O(log₂₆ n) — number of digits in base-26 representation
- **Space:** O(log₂₆ n) — for the StringBuilder

## Common Mistakes
- Using `columnNumber / 26` instead of `(columnNumber - 1) / 26` — breaks on multiples of 26
- Building left-to-right by dividing first — harder to handle the no-zero quirk
- Not handling `x == 0` — maps to `'@'` (char 64) instead of `'Z'`
- Checking `if (x == 0) columnNumber++` before dividing — doesn't fix the root issue