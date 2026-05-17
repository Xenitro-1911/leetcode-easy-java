# 228 - Summary Ranges

## Problem Statement
Given a sorted unique integer array `nums`, return the smallest sorted list of ranges that cover all numbers exactly. A range `[a,b]` is represented as `"a->b"` if `a != b`, or `"a"` if `a == b`.

## Approach: Single-Pass Sliding Pointer

### Key Insight
Since the array is sorted and all elements are unique, consecutive elements in the same range differ by exactly 1. Use `i` as the outer loop variable and advance it with an inner `while` loop as long as the sequence stays consecutive. When the run ends, record the range from `start` to `nums[i]`.

### Algorithm
1. For each `i`, record `start = nums[i]`.
2. Advance `i` while `nums[i+1] == nums[i] + 1` (consecutive).
3. When the run breaks, build the range string:
    - `start == nums[i]` → `"start"` (single element)
    - otherwise → `"start->nums[i]"`
4. Add to result. The outer `for` loop then increments `i` past the end of this range.

```java
for (int i = 0; i < len; i++) {
    int start = nums[i];

    while (i + 1 < len && nums[i + 1] == nums[i] + 1) {
        i++;
    }

    StringBuilder sb = new StringBuilder();
    if (start == nums[i]) {
        sb.append(start);
    } else {
        sb.append(start).append("->").append(nums[i]);
    }
    result.add(sb.toString());
}
```

### Why `i + 1 < len` before `nums[i+1]`?
Short-circuit evaluation — if `i + 1 < len` is false, Java never evaluates `nums[i+1]`, preventing an ArrayIndexOutOfBoundsException. Order matters here.

### Why `nums[i+1] == nums[i] + 1` instead of `nums[i+1] - nums[i] == 1`?
Subtraction can overflow for extreme integer values (e.g. `Integer.MAX_VALUE - (-1)`). Addition `nums[i] + 1` is safe because the array is sorted and unique, so `nums[i]` is never `Integer.MAX_VALUE` when there's a next element equal to it plus one.

### Why `StringBuilder` over String concatenation?
String concatenation with `+` creates a new `String` object each time. `StringBuilder` builds the string in a mutable buffer and converts once at the end — more efficient, especially relevant here since the string is built in up to two appends.

## Complexity
| | Value |
|---|---|
| Time | O(n) — each element is visited exactly once across both loops |
| Space | O(1) extra — output list aside, only a few variables are used |

## Common Pitfalls
- **Accessing `nums[i+1]` without bounds check** — always guard with `i + 1 < len` first.
- **Not handling the last range** — a common mistake with a pure `for` loop approach is forgetting to add the final range after the loop ends. The inner `while` approach handles this naturally since the range is always added before `i` advances.
- **Always appending `->`** — single-element ranges must be formatted as just `"a"`, not `"a->a"`. The `start == nums[i]` check handles this.
- **Mutating `i` inside a for loop** — advancing `i` inside the `while` is intentional here. The outer `for` loop's `i++` then moves past the range end to the next range start. This is a valid pattern but easy to misread.

## Alternative: Two-Pointer with Separate End Variable
```java
for (int i = 0; i < len; i++) {
    int start = nums[i];
    while (i + 1 < len && nums[i + 1] == nums[i] + 1) i++;
    int end = nums[i];
    result.add(start == end ? String.valueOf(start) : start + "->" + end);
}
```
Identical logic, stores `end` explicitly for clarity instead of re-reading `nums[i]` after the while loop.