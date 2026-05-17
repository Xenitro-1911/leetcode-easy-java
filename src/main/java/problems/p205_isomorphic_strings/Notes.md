# 205 - Isomorphic Strings

## Problem Statement
Given two strings `s` and `t`, determine if they are isomorphic. Two strings are isomorphic if the characters in `s` can be replaced to get `t`, such that:
- Every character in `s` maps to exactly one character in `t`
- No two characters in `s` map to the same character in `t`
- A character may map to itself

## Approach: Parallel Last-Seen Index Tracking (ASCII Array)

### Key Insight
Two strings are isomorphic if and only if, at every index `i`, the character `s[i]` and `t[i]` were **last seen at the same index**. If at any point their last-seen positions differ, the mapping is broken.

### Why store `i + 1` instead of `i`?
Arrays are zero-initialized, so index `0` is the default. If we stored `i` directly, a character seen at position `0` would be indistinguishable from an unvisited character (both equal `0`). Storing `i + 1` shifts all valid positions to `1`-based, leaving `0` unambiguously meaning "never seen".

### Algorithm
1. Use two `int[256]` arrays (one per string) to track the last-seen position of each ASCII character.
2. At each index `i`, compare `mapS[c1]` and `mapT[c2]`.
    - If they differ → not isomorphic, return `false`.
    - If they match → update both arrays to `i + 1`.
3. If the loop completes → return `true`.

```java
int[] mapS = new int[256];
int[] mapT = new int[256];

for (int i = 0; i < len; i++) {
    char c1 = s.charAt(i);
    char c2 = t.charAt(i);

    if (mapS[c1] != mapT[c2]) return false;

    mapS[c1] = i + 1;
    mapT[c2] = i + 1;
}
return true;
```

## Complexity
| | Value |
|---|---|
| Time | O(n) — single pass through both strings |
| Space | O(1) — two fixed-size arrays of 256 integers |

## Why Not HashMap?
A `HashMap<Character, Integer>` works correctly but carries overhead — hashing, boxing `char` to `Character`, and dynamic allocation. Since the character set is bounded (ASCII = 256), a plain `int[]` array is faster and cleaner.

## Why Not Frequency Counting?
Counting how often each character appears and comparing frequency sets fails for cases like:
- `s = "ab"`, `t = "cc"` → `s` has two distinct chars, `t` has one — caught correctly
- `s = "aab"`, `t = "xyx"` → frequencies might align accidentally even though the positional mapping is inconsistent

Frequency only tells you *how many times* a character appears — not *where*, which is what isomorphism actually depends on.

## Common Pitfalls
- **Storing `i` instead of `i + 1`** — causes false positives when a character first appears at index `0`, since `0` is the array default.
- **Checking only one direction** — mapping `s → t` alone isn't enough. `s = "ab"`, `t = "aa"` would pass a one-way check but is not isomorphic because two different `s` characters map to the same `t` character. The parallel array approach handles both directions simultaneously.
- **Comparing value sets at the end** — doesn't work because it loses positional information. Two mappings can have identical value sets but inconsistent structure.