# 066 - Plus One

## Problem Summary
Given an array of digits representing a large integer, increment it by one and return the result as an array.

---

## Approach

### Key Insight
Add 1 starting from the **rightmost digit** and handle carrying — just like manual addition.

### Two Cases to Handle

**Case 1 — All 9s (e.g. `[9,9,9]`)**
- Result needs a bigger array: `[1,0,0,0]`
- Detected by checking if every digit equals `9`
- Create a new array of size `l+1`, set index `0` to `1`, rest to `0`

**Case 2 — Not all 9s (e.g. `[1,9,9]`)**
- Traverse from right to left
- If digit is `9` → set to `0` (carry propagates)
- If digit is not `9` → increment by 1 and `break`
- Return the modified original array

---

## Your Solution

```java
public int[] plusOne(int[] digits) {
    int l = digits.length;
    boolean z = true;

    for (int i = 0; i < l; i++) {
        if (digits[i] != 9) {
            z = false;
        }
    }

    if (z == true) {
        int x[] = new int[l + 1];
        x[0] = 1;
        for (int i = 1; i < l + 1; i++) {
            x[i] = 0;
        }
        return x;
    } else {
        for (int i = l - 1; i >= 0; i--) {
            if (digits[i] == 9) {
                digits[i] = 0;
            } else {
                digits[i] = digits[i] + 1;
                break;
            }
        }
        return digits;
    }
}
```

---

## Complexity
| | |
|---|---|
| **Time** | O(n) — single pass to check all-9s, single pass to add |
| **Space** | O(1) in most cases, O(n) only when all digits are 9 |

---

## Common Pitfalls
- Using `if(true)` instead of `if(z)` — always double-check your condition variables
- Forgetting to set the `9`s to `0` when carrying (instead of just skipping them)
- Not handling the all-9s edge case (requires a larger output array)

---

## Pattern
> **Array + Carry Propagation** — traverse from right, zero out 9s, increment the first non-9.