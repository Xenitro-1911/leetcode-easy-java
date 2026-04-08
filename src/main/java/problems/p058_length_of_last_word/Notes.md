# Length of Last Word — Notes

## Problem Summary
Given a string `s` consisting of words and spaces, return the **length of the last word** in the string. A word is a maximal substring consisting of non-space characters only.

---

## Approach: Trim + LastIndexOf

### Key Steps
1. **Trim** trailing (and leading) whitespace with `.trim()`
2. **Find** the last space in the trimmed string with `.lastIndexOf(' ')`
3. **Substring** from that position + 1 to end, return its `.length()`

### Edge Case Handled
If there are **no spaces** in the original string, the whole string is one word — return `s.length()` directly.

> ⚠️ Note: The no-space check uses the **original** `s`, but `s.length()` would give the same result as trimming first since a no-space string has no whitespace anyway. Still, be mindful of *which* variable you operate on at each step.

---

## Walkthrough

### Example 1 — Trailing spaces
```
s = "Hello World   "

s2 = s.trim()         → "Hello World"
lastIndexOf(' ')      → 5
substring(6)          → "World"
return 5              ✓
```

### Example 2 — No spaces
```
s = "Hello"

s.contains(" ")       → false
return s.length()     → 5  ✓
```

### Example 3 — Multiple spaces between words
```
s = "fly me   to  the moon"

s2 = s.trim()         → "fly me   to  the moon"
lastIndexOf(' ')      → 17
substring(18)         → "moon"
return 4              ✓
```

---

## Key String Methods Used

| Method | What it does |
|--------|-------------|
| `.trim()` | Removes leading and trailing whitespace |
| `.contains(" ")` | Checks if any space exists in the string |
| `.lastIndexOf(' ')` | Returns index of the **last** occurrence of a space |
| `.substring(i)` | Returns everything from index `i` to end |
| `.length()` | Returns the number of characters |

---

## Complexity

| | Value |
|---|---|
| **Time** | O(n) — trim and lastIndexOf both scan the string |
| **Space** | O(n) — substring creates a new string object |

---

## Alternative One-Liner
```java
return s.trim().substring(s.trim().lastIndexOf(' ') + 1).length();
```
Works cleanly when there's at least one space. The `lastIndexOf` returns `-1` if no space is found, so `substring(0)` returns the whole trimmed string — meaning the explicit no-space check isn't strictly necessary after trimming, but makes intent clearer.

---

## Common Pitfalls

- Not trimming before `lastIndexOf` — trailing spaces would make it return 0
- Using `indexOf` instead of `lastIndexOf` — finds the **first** space, not the last
- Operating on untrimmed string for the substring — can return empty string `""`