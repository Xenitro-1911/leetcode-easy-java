# 171. Excel Sheet Column Number

## Problem
Given a string `columnTitle` representing a column title in an Excel sheet, return its corresponding column number.

Examples: `"A" → 1`, `"Z" → 26`, `"AA" → 27`, `"AB" → 28`, `"ZY" → 701`

---

## Approach — Left-to-Right Base-26 Accumulation

This is standard positional base conversion, identical to parsing a decimal string — but base 26 instead of base 10, and digits run 1–26 (A–Z) instead of 0–9.

**Key formula applied at each character:**
```
result = result * 26 + (currentChar - 'A' + 1)
```

The `- 'A' + 1` maps:
- `'A'` → 1
- `'B'` → 2
- ...
- `'Z'` → 26

**Trace for `"AB"`:**
| i | char | digit | result        |
|---|------|-------|---------------|
| 0 | 'A'  | 1     | 0 * 26 + 1 = 1 |
| 1 | 'B'  | 2     | 1 * 26 + 2 = 28 |

Result: `28` ✅

---

## Why No Special Case for 'Z'?

Unlike p168 (int → title), the **reverse direction has no "no-zero" problem**. `'Z'` simply maps to `26` via `'Z' - 'A' + 1 = 26`. The `(n-1)` adjustment was only needed when converting *from* a number (where `% 26 == 0` requires special handling).

---

## Complexity

| | Value |
|---|---|
| Time  | O(n) — one pass through the string |
| Space | O(1) — only a running accumulator |

---

## Relationship to p168

| Problem | Direction       | Base-0 issue?         |
|---------|-----------------|-----------------------|
| p168    | int → String    | Yes — `(n-1)` trick needed before `% 26` |
| p171    | String → int    | No — `char - 'A' + 1` maps cleanly to 1–26 |

---

## Solution

```java
class Solution {
    public int titleToNumber(String columnTitle) {
        int result = 0;
        for (int i = 0; i < columnTitle.length(); i++) {
            result = result * 26 + (columnTitle.charAt(i) - 'A' + 1);
        }
        return result;
    }
}
```