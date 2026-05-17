# 169 - Majority Element

## Problem Statement
Given an array `nums` of size `n`, return the majority element — the element that appears **more than ⌊n/2⌋ times**. You may assume the majority element always exists.

## Approach: Boyer-Moore Voting Algorithm

The key insight: if we pair up every non-majority element with one majority element and cancel them out, the majority element always survives — because it appears more than half the time.

### Algorithm
Maintain two variables:
- `candidate` — the current candidate for majority element
- `count` — the "net votes" for that candidate

Walk the array:
- If `count == 0`, adopt the current element as the new `candidate`.
- If the current element equals `candidate`, increment `count`.
- Otherwise, decrement `count` (cancellation).

At the end, `candidate` holds the majority element.

### Why does this work?
The majority element appears > n/2 times. Every time a non-majority element cancels a majority vote, the majority element still has more remaining votes. No matter what order the elements appear in, the majority element outlasts all others.

```java
public int majorityElement(int[] nums) {
    int candidate = 0;
    int count = 0;
    for (int num : nums) {
        if (count == 0) {
            candidate = num;
        }
        count += (num == candidate) ? 1 : -1;
    }
    return candidate;
}
```

## Complexity
| | Value |
|---|---|
| Time | O(n) — single pass |
| Space | O(1) — two variables only |

## Common Pitfalls
- **Assuming the candidate is set only once** — it can change multiple times as `count` hits 0. That's fine; the true majority always wins in the end.
- **Thinking `count` represents the frequency** — it doesn't. It's a net difference. The actual count of `candidate` in the full array could be much higher.
- **Forgetting the problem guarantee** — Boyer-Moore only guarantees the correct answer when a majority element is guaranteed to exist. Without that guarantee, a second pass to verify would be required.

## Alternatives
| Approach | Time | Space | Notes |
|---|---|---|---|
| HashMap frequency count | O(n) | O(n) | Simple but uses extra space |
| Sort + return middle | O(n log n) | O(1) or O(n) | Middle index always holds majority element after sorting |
| Boyer-Moore Voting | O(n) | O(1) | Optimal — single pass, no extra space |
| Bit manipulation | O(32n) | O(1) | Determine each bit of result independently |