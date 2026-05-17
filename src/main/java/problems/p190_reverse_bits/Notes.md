# 190. Reverse Bits

## Problem
Reverse the bits of a given 32-bit unsigned integer and return the result.

Example: `n = 00000010100101000001111010011100` → `00111001011110000010100101000000`

---

## Approach — Bit-by-Bit Extraction with Shifting

Process all 32 bits one at a time. At each step:
1. Extract the last (lowest) bit of `n` using `n & 1`
2. Shift `result` left by 1 to make room
3. OR the extracted bit into `result`
4. Unsigned right shift `n` to move to the next bit

**Trace for `n = 13` (`...00001101`):**
| i | n & 1 | result after step |
|---|-------|-------------------|
| 0 | 1     | 1                 |
| 1 | 0     | 10                |
| 2 | 1     | 101               |
| 3 | 1     | 1011              |
| 4–31 | 0  | 10110000...0      |

Result is the bit-reversed 32-bit value.

---

## Key Operators

| Operator | Meaning |
|----------|---------|
| `n & 1`    | Extract lowest bit (0 or 1) |
| `result << 1` | Shift result left, making room for new bit |
| `result \| bit` | Place extracted bit into lowest position |
| `n >>>= 1` | **Unsigned** right shift — does NOT sign-extend for negative `n` |

---

## Why `>>>` and not `>>`?

`>>` is arithmetic right shift — it copies the sign bit into the vacated position. For negative `n`, this would fill with `1`s instead of `0`s, corrupting the remaining bits.

`>>>` always shifts in `0`s regardless of sign — correct for treating `n` as unsigned 32-bit.

---

## Why the String Approach Fails

| Issue | Problem |
|-------|---------|
| `Integer.toBinaryString` strips leading zeros | `1` becomes `"1"` not 32 chars |
| `Integer.parseInt(x, 2)` is signed | Throws for values ≥ 2^31 |
| `n > 0` loop condition | Breaks entirely for negative inputs |

---

## Complexity

| | Value |
|---|---|
| Time  | O(1) — always exactly 32 iterations |
| Space | O(1) — no extra data structures |

---

## Solution

```java
public class Solution {
    public int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result = (result << 1) | (n & 1);
            n >>>= 1;
        }
        return result;
    }
}
```