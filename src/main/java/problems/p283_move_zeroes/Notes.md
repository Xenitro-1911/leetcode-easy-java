# 283 - Move Zeroes

## Problem Statement
Given an integer array `nums`, move all `0`s to the end while maintaining the relative order of the non-zero elements. Must be done in-place.

## Approach: Two-Pointer Write Head

### Key Insight
Use a write pointer `j` that tracks where the next non-zero element should be placed. Scan through the array with `i` — whenever a non-zero is found, write it to position `j` and advance `j`. After the scan, fill everything from `j` to the end with zeros.

### Algorithm
1. `j = 0` — write head starts at the beginning.
2. For each element `nums[i]`:
    - If non-zero → write to `nums[j]`, increment `j`.
    - If zero → skip.
3. After the loop, fill `nums[j]` to `nums[n-1]` with zeros.

```java
int j = 0;

for (int i = 0; i < nums.length; i++) {
    if (nums[i] != 0) {
        nums[j] = nums[i];
        j++;
    }
}

while (j < nums.length) {
    nums[j++] = 0;
}
```

### Trace on `[0, 1, 0, 3, 12]`:
```
i=0: nums[0]=0  → skip,       j=0
i=1: nums[1]=1  → nums[0]=1,  j=1
i=2: nums[2]=0  → skip,       j=1
i=3: nums[3]=3  → nums[1]=3,  j=2
i=4: nums[4]=12 → nums[2]=12, j=3

Fill zeros: nums[3]=0, nums[4]=0
Result: [1, 3, 12, 0, 0] ✅
```

### Why fill zeros at the end instead of swapping?
Swapping would work but requires more writes. The write-head approach overwrites positions directly — non-zeros are written once to their final position, then zeros are written once to fill the tail. Each element is touched at most twice.

## Complexity
| | Value |
|---|---|
| Time | O(n) — one pass to place non-zeros, one pass to fill zeros |
| Space | O(1) — in-place, only one extra pointer |

## Common Pitfalls
- **Bubble-style swapping** — repeatedly swapping adjacent elements is O(n²) and complex to implement correctly; the two-pointer approach is strictly better.
- **Modifying `i` inside the loop** — advancing `i` inside a nested while loop causes elements to be skipped, especially consecutive zeros.
- **Checking `nums[i+1]` before bounds check** — always guard with `i < nums.length - 1` before accessing `nums[i+1]`.
- **Forgetting to fill zeros** — after writing all non-zeros to the front, the tail still contains old values. The second pass overwrites them with zeros.
- **Not preserving relative order** — any approach that moves zeros to the end by swapping with arbitrary elements risks reordering non-zeros. The write-head approach preserves order naturally since `i` always moves forward.