# 125. Valid Palindrome

**Difficulty:** Easy
**Topic:** Two Pointers, Strings

---

## Problem Summary

Given a string `s`, return `true` if it is a palindrome after converting all uppercase to lowercase and removing all non-alphanumeric characters. Return `false` otherwise.

---

## Algorithm

**Two-pointer in-place scan — no extra string built.**

Place one pointer at the start (`left`) and one at the end (`right`). Converge inward:

1. If `left` points to a non-alphanumeric character → skip it (`left++`)
2. Else if `right` points to a non-alphanumeric character → skip it (`right--`)
3. Otherwise both are alphanumeric → compare lowercased; return `false` on mismatch, else advance both

If the pointers meet without a mismatch, return `true`.

```java
int left = 0;
int right = s.length() - 1;
while (left < right) {
    char l = s.charAt(left);
    char r = s.charAt(right);
    if (!Character.isLetterOrDigit(l)) {
        left++;
    } else if (!Character.isLetterOrDigit(r)) {
        right--;
    } else {
        if (Character.toLowerCase(l) != Character.toLowerCase(r)) {
            return false;
        }
        left++;
        right--;
    }
}
return true;
```

---

## Complexity

| | |
|---|---|
| **Time** | O(n) — each character visited at most once |
| **Space** | O(1) — no auxiliary string or buffer built |

---

## Key Insights

- **Skip before compare.** Handle non-alphanumeric characters first in the if-else chain so you never compare junk characters.
- **else-if structure matters.** Only advance both pointers after a valid comparison. If you advanced both unconditionally you could skip characters.
- **toLowerCase at compare time.** No need to preprocess the whole string — just lowercase at the moment of comparison.
- **Two-pointer beats clean-then-reverse.** The clean-then-reverse approach (StringBuilder) is O(n) time but also O(n) space. Two pointers on the original string achieve O(1) space.

---

## Common Pitfalls

| Mistake | Why it fails |
|---|---|
| Forgetting `toLowerCase` | `'A'` and `'a'` don't match with `!=` |
| Using `==` instead of `!=` on chars | Inverted logic — returns false on match |
| Advancing both pointers before checking alphanumeric | Skips characters that should be compared |
| Building a cleaned string but forgetting to lowercase | Mismatches on mixed-case input |
| StringBuilder `y = x` copy attempt | Both variables point to same object; reversing one reverses both |

---

## Alternatives

**Clean-then-compare (O(n) space):**
```java
StringBuilder sb = new StringBuilder();
for (char c : s.toCharArray())
    if (Character.isLetterOrDigit(c))
        sb.append(Character.toLowerCase(c));
String cleaned = sb.toString();
return cleaned.equals(sb.reverse().toString());
// Note: must save toString() BEFORE calling reverse()
```
Simpler to read, but allocates an extra string. Fine for most use cases.