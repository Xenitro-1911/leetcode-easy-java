# 028 - Find the Index of the First Occurrence in a String

## Problem Summary
Given two strings `needle` and `haystack`, return the index of the first occurrence of `needle` in `haystack`, or `-1` if not found.

## Approach: Built-in Java String Methods

### Key Insight
Java's `String` class provides `indexOf(String)` which does exactly what the problem asks:
- Returns the index of the first occurrence of the substring
- Returns `-1` if the substring is not found

### The `contains()` check is redundant
`indexOf()` already returns `-1` when the needle isn't found, so the `contains()` guard is unnecessary. The solution can be simplified to a single line.

### Final Solution
```java
public int strStr(String haystack, String needle) {
    return haystack.indexOf(needle);
}
```

Or the submitted version with the explicit guard:
```java
public int strStr(String haystack, String needle) {
    if (!haystack.contains(needle)) {
        return -1;
    } else {
        return haystack.indexOf(needle);
    }
}
```

Both are functionally identical — `indexOf` returns `-1` on no match, so `contains` adds no extra value.

## Complexity
| | |
|---|---|
| Time | O(n * m) — where n = haystack length, m = needle length |
| Space | O(1) — no extra data structures |

## Edge Cases to Remember
- `needle` longer than `haystack` → returns `-1`
- `needle` is empty string → `indexOf` returns `0` (standard Java behaviour)
- `needle` appears multiple times → only the **first** index is returned

## Takeaway
When Java's standard library has a method that maps directly to the problem, it's valid to use it. In interviews, it's worth mentioning you know it internally uses an O(n*m) sliding window or KMP-style search, showing deeper understanding.