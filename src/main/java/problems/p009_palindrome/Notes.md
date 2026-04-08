# p009 — Palindrome Number

## Problem
Determine whether an integer is a palindrome without converting it to a string.

## Approach — Half Reversal
Instead of reversing the entire number, reverse only the second half and compare it to the first half.

**Key insight:** Stop the loop when `rev >= x`. At that point:
- `x` holds the left half
- `rev` holds the reversed right half

## Early Exit Conditions
| Condition | Reason |
|---|---|
| `x < 0` | Negative numbers are never palindromes (due to `-` sign) |
| `x < 10` | Single digits are always palindromes |
| `x % 10 == 0` | Any number ending in 0 can't be a palindrome (except 0 itself, caught by `x < 10`) |

## Complexity
| | Value |
|---|---|
| Time | O(log n) — we only process half the digits |
| Space | O(1) — no extra data structures |

## Solution
```java
public boolean isPalindrome(int x) {
    if (x < 0 || x % 10 == 0) return false;
    if (x < 10) return true;

    int rev = 0;
    while (x > rev) {
        rev = rev * 10 + x % 10;
        x /= 10;
    }
    return x == rev || x == rev / 10;
}
```

## Why `x == rev / 10`?
For odd-digit numbers (e.g. `121`), the middle digit ends up in `rev`:
- After loop: `x = 1`, `rev = 12`
- `rev / 10 = 1` → matches `x` ✅

## Alternatives Considered
- **String conversion** — Simple but uses O(n) space and doesn't meet the follow-up constraint
- **Full reversal** — Works but does unnecessary work on the left half; also risks integer overflow

## Bugs Fixed Along the Way
- Order of `if` checks matters — `x < 10` before `x < 0` caused `-121` to return `true`
- Comparing `rev == x` inside the loop: at the halfway point `x` has already been halved, so comparing original `x2` to `rev` was wrong
- Numbers ending in `0` (e.g. `10`) need an explicit early exit — otherwise `0 == rev/10` evaluates to `true`

## What I Learned
- Half-reversal is a common trick to avoid overflow and reduce work
- Always trace through edge cases manually (negatives, single digits, trailing zeros, odd/even length)
- LeetCode runtime can fluctuate — algorithmic complexity matters more than ms differences