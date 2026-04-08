# Add Binary - Notes

## Problem
Given two binary strings `a` and `b`, return their sum as a binary string.

---

## Key Line Explained: `sum += a.charAt(i--) - '0'`

This one line does **three things at once**. Let's break it down piece by piece:

### 1. `a.charAt(i)`
- Gets the **character** at index `i` in string `a`
- Returns a `char`, for example `'1'` or `'0'`
- Important: `'1'` is NOT the same as `1` in Java
    - `'1'` is a character with ASCII value **49**
    - `'0'` is a character with ASCII value **48**
    - `1` is just the integer one

### 2. `- '0'`
- This converts the **character to an integer**
- `'1' - '0'` = 49 - 48 = **1**
- `'0' - '0'` = 48 - 48 = **0**
- This is the standard Java trick to convert a digit character to its integer value

### 3. `i--`
- This is **post-decrement** — it uses the current value of `i` first, THEN decrements it
- So `a.charAt(i--)` reads the character at the current `i`, then moves `i` one step left
- This means we don't need a separate `i--` line after

### Putting it all together
```
sum += a.charAt(i--) - '0'
```
In plain English: *"Get the character at position i, convert it to an integer (0 or 1), add it to sum, then move i one position to the left."*

---

## How the Full Solution Works

```java
while (i >= 0 || j >= 0 || carry > 0) {
    int sum = carry;
    if (i >= 0) sum += a.charAt(i--) - '0';
    if (j >= 0) sum += b.charAt(j--) - '0';
    sb.append(sum % 2);
    carry = sum / 2;
}
```

### The 4 possible cases for `sum`:

| bitA | bitB | carry | sum | append (sum%2) | new carry (sum/2) |
|------|------|-------|-----|----------------|-------------------|
| 0    | 0    | 0     | 0   | 0              | 0                 |
| 1    | 0    | 0     | 1   | 1              | 0                 |
| 1    | 1    | 0     | 2   | 0              | 1                 |
| 1    | 1    | 1     | 3   | 1              | 1                 |

### Why `sum % 2` and `sum / 2`?
- `sum % 2` gives the **remainder** when dividing by 2 → the digit to write down (0 or 1)
- `sum / 2` gives the **quotient** → the carry (1 if sum was 2 or 3, else 0)
- This is exactly how binary addition works!

### Why `carry > 0` in the while condition?
- After both strings are exhausted, there might still be a leftover carry
- Example: `"1" + "1"` = `"10"` — the `1` at the front comes from the carry
- The loop handles this automatically instead of needing an `if (carry) append(1)` at the end

---

## Complexity
- **Time:** O(max(n, m)) — we visit every digit once
- **Space:** O(max(n, m)) — for the result string

---

## Example Walkthrough: "11" + "1"

| i | j | sum | append | carry |
|---|---|-----|--------|-------|
| 1 | 0 | 0+1+1=2 | 0 | 1 |
| 0 | -1 | 1+1=2 | 0 | 1 |
| -1 | -1 | 1 | 1 | 0 |

Result (reversed): `"100"` ✅