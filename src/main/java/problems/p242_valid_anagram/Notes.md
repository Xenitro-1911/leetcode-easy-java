# 242 - Valid Anagram

## Problem Statement
Given two strings `s` and `t`, return `true` if `t` is an anagram of `s`, and `false` otherwise. An anagram uses all original letters exactly once in a different arrangement.

## Approach: Frequency Array

### Key Insight
Two strings are anagrams if and only if every character appears the same number of times in both. Use a fixed-size array of 26 integers (one per lowercase letter) — increment for each character in `s` and decrement for each character in `t`. If every frequency is 0 at the end, they are anagrams.

### Why `char - 'a'`?
Characters in Java are stored as their ASCII value. `'a'` = 97, `'b'` = 98, etc. Subtracting `'a'` maps each letter to an index 0–25:
```
'a' - 'a' = 0
'b' - 'a' = 1
'z' - 'a' = 25
```

### Single-pass counting
Both strings are processed in one loop — increment for `s`, decrement for `t`:
```java
for (int i = 0; i < sChars.length; i++) {
    freq[sChars[i] - 'a']++;
    freq[tChars[i] - 'a']--;
}
```
If `s` and `t` are anagrams, every character's increments cancel its decrements → all zeros.

### Why `toCharArray()` instead of `charAt()`?
`toCharArray()` converts the string to a raw `char[]` once upfront. Array indexing is a direct memory lookup with no bounds checking overhead on each access, whereas `charAt()` performs internal bounds checking on every call. Minor optimisation for tight loops.

```java
char[] sChars = s.toCharArray();
char[] tChars = t.toCharArray();
int[] freq = new int[26];

for (int i = 0; i < sChars.length; i++) {
    freq[sChars[i] - 'a']++;
    freq[tChars[i] - 'a']--;
}

for (int f : freq) {
    if (f != 0) return false;
}
return true;
```

## Complexity
| | Value |
|---|---|
| Time | O(n) — one pass through both strings, one pass through fixed 26-element array |
| Space | O(1) — `freq` array is always size 26 regardless of input |

## Why Not O(n²) Nested Loop?
The naive approach (for each character in `s`, scan `t` for a match) is O(n²). It also requires tracking which indices in `t` have been used, adding complexity. The frequency array reduces this to two linear scans.

## Common Pitfalls
- **Skipping the length check** — without it, `s = "ab"` and `t = "abc"` could pass if frequencies happen to cancel (they won't here, but it's a necessary guard).
- **Using `HashMap<Character, Integer>`** — works correctly but has boxing overhead (`char` → `Character`) and hashing cost. The `int[26]` array is faster and simpler for lowercase letters.
- **Only counting `s`, not decrementing for `t`** — you need both directions; just counting `s` and checking `t` separately requires two arrays.
- **Setting `x = false` in an else inside the inner loop** — a common mistake when taking the manual matching approach; the flag gets overwritten by later non-matching characters even after a valid match was found.

## Alternative: Sort and Compare
```java
char[] sc = s.toCharArray();
char[] tc = t.toCharArray();
Arrays.sort(sc);
Arrays.sort(tc);
return Arrays.equals(sc, tc);
```
Sorting both strings and comparing is O(n log n) time, O(n) space. Correct but strictly worse than the frequency array approach.