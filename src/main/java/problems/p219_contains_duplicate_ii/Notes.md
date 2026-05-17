# 219 - Contains Duplicate II

## Problem Statement
Given an integer array `nums` and an integer `k`, return `true` if there exist two distinct indices `i` and `j` such that `nums[i] == nums[j]` and `abs(i - j) <= k`.

## Approach: Sliding Window with HashSet

### Key Insight
We need to detect duplicates within a window of size `k`. Instead of storing indices and doing arithmetic, we maintain a `HashSet` that holds exactly the last `k` elements. If a new element is already in the set, it must have appeared within the last `k` steps — that's all we need to know.

### Sliding Window Explained

Think of a physical window of width `k` sliding across the array one step at a time:

```
nums = [1, 2, 3, 1],  k = 3

i=0: window = {1}          — add 1, no duplicate
i=1: window = {1, 2}       — add 2, no duplicate
i=2: window = {1, 2, 3}    — add 3, no duplicate
i=3: window = {1, 2, 3}    — try add 1 → already present! return true ✅
```

Now a case where the window slides and evicts an element:

```
nums = [1, 2, 3, 4, 1],  k = 3

i=0: window = {1}             — add 1
i=1: window = {1, 2}          — add 2
i=2: window = {1, 2, 3}       — add 3, window now full (size = k)
i=3: window = {2, 3, 4}       — add 4, evict nums[i-k] = nums[0] = 1
i=4: window = {3, 4, 1}  — try add 1 → NOT in set (was evicted), add it
→ return false ✅
```

The eviction `window.remove(nums[i - k])` is what makes the window "slide" — it removes the element that is now more than `k` steps behind.

### Why `i >= k` for eviction?
The window only reaches full capacity at index `k`. Before that, no element is out of range yet so nothing needs to be removed.

### Why `add()` instead of `contains()`?
`HashSet.add()` returns `false` if the element already exists — combining the duplicate check and insertion into one operation.

### Capacity Optimisation
```java
int capacity = Math.min(nums.length, k);
new HashSet<>((int)(capacity / 0.75f) + 1);
```
The default load factor is `0.75` — the HashSet resizes when 75% full. By pre-computing the exact initial capacity needed, we prevent any internal resizing as elements are added.

### Early Exit
```java
if (k == 0 || nums == null || nums.length <= 1) return false;
```
- `k == 0` means indices must be identical, which is impossible for two distinct indices.
- Arrays of length 0 or 1 can't have two distinct indices.

## Complexity
| | Value |
|---|---|
| Time | O(n) — single pass, O(1) add/remove/lookup per step |
| Space | O(min(n, k)) — window never exceeds k elements |

## Alternative: HashMap of Last-Seen Index
```java
HashMap<Integer, Integer> map = new HashMap<>();
for (int i = 0; i < nums.length; i++) {
    if (map.containsKey(nums[i]) && i - map.get(nums[i]) <= k) {
        return true;
    }
    map.put(nums[i], i);
}
return false;
```
Stores the most recent index of each value. When a duplicate is found, subtracts the stored index from the current index to check distance. Equally O(n) time but uses O(n) space in the worst case — the HashSet sliding window is strictly better in space when `k << n`.

## Common Pitfalls
- **Sorting the array first** — destroys original indices, making index distance checks meaningless.
- **Nested loops** — O(n²) brute force works but fails on large inputs.
- **Not updating the map/set** — after a failed duplicate check you must still update the stored index to the current one, so future lookups use the most recent position.
- **Off-by-one on eviction** — evicting at `i > k` instead of `i >= k` leaves one extra element in the window, potentially missing a valid early eviction.