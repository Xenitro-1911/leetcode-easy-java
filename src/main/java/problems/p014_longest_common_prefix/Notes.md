# Longest Common Prefix — Notes

## Problem
Given an array of strings, find the longest common prefix shared among all strings.

---

## Key Bugs Encountered

### 1. `StringIndexOutOfBoundsException`
- Caused by accessing `.charAt(j)` on an empty string `""`
- Also caused by loop condition `j <= l` instead of `j < l`
- Valid indices for a string of length `n` are `0` to `n-1`

### 2. Boundary check order matters
- Always validate index bounds **before** calling `.charAt()`

---

## Optimisation Journey

| Version | Change | Why |
|---|---|---|
| v1 | Basic char-by-char loop | Working but buggy |
| v2 | Fixed off-by-one (`j < l`) | Correct bounds |
| v3 | Find shortest string first | Fewer loop iterations |
| v4 | Replace `String +=` with `StringBuilder` | Avoids O(n²) string copies |
| v5 | Remove `StringBuilder`, use `substring(0, j)` | Less object allocation |
| v6 | Remove `toLowerCase` | Unnecessary overhead (not required by problem) |
| v7 | Move `charAt(j)` outside inner loop | Avoid redundant method calls |
| v8 | Use `indexOf` approach | Cleanest, 0ms solution |

---

## Final Solution (0ms)

```java
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";

        String prefix = strs[0];

        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        }
        return prefix;
    }
}
```

### Why this is fast
- `indexOf` is a **JVM-optimised native method** — faster than manual char-by-char comparison
- No extra objects created (`StringBuilder`, `char[]`, etc.)
- Shrinks prefix greedily — exits as soon as a match is found
- Checks `null` and empty array upfront

---

## Complexity

| | Value |
|---|---|
| Time | O(S) — S = total characters compared |
| Space | O(1) — no extra data structures |

> You **cannot** beat O(S) for this problem — every character must be checked at least once in the worst case.

---

## Key Java Concepts Learned

- **`String` is immutable** — `+=` in a loop creates a new object every iteration → use `StringBuilder` or `substring`
- **`==` vs `.equals()`** — use `==` to check same object reference, `.equals()` to check same content
- **`indexOf(str) != 0`** — checks if a string does NOT start with the prefix
- **`substring(0, j)`** — extracts prefix up to index `j` (exclusive)
- **JVM-native methods** (`indexOf`, `substring`) are faster than manual loops