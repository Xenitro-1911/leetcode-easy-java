# 108. Convert Sorted Array to Binary Search Tree

## Problem
Given a sorted integer array, convert it to a height-balanced BST.

## Approach — Recursive midpoint

Pick the middle element of the current subarray as the root. Recurse on the left half for the left subtree and the right half for the right subtree. Because the array is sorted, the midpoint always produces a balanced split.

```java
public TreeNode sortedArrayToBST(int[] nums) {
    return helper(nums, 0, nums.length - 1);
}

public TreeNode helper(int[] nums, int low, int high) {
    if (low > high) return null;
    int mid = (low + high) / 2;
    TreeNode root = new TreeNode(nums[mid]);
    root.left  = helper(nums, low, mid - 1);
    root.right = helper(nums, mid + 1, high);
    return root;
}
```

## Complexity
- **Time**: O(n) — every element becomes exactly one node
- **Space**: O(log n) — recursion depth equals tree height (balanced)

## Key lessons

### 1. void helper with a node parameter doesn't work
Passing `null` into a void method and assigning inside it only modifies a local variable. The caller's reference is never updated. The fix: return the created node so the caller can attach it.

### 2. The helper and the main function do the same job
`sortedArrayToBST` doesn't need its own logic — it's just `return helper(nums, 0, nums.length - 1)`. Avoid duplicating the "pick mid, build node" logic in both methods.

### 3. Base case: low > high, not i == x - 1
When the subarray is empty (low exceeds high), return null. This naturally handles leaf nodes whose children should be null.

### 4. Each recursive call creates exactly one node
The tree is built bottom-up as stack frames return. The parent receives a fully built subtree from each recursive call and attaches it with `root.left = ...` and `root.right = ...`.

### 5. Mid formula
`(low + high) / 2` — integer division floors toward the left element when the subarray has an even number of elements. Both `(low + high) / 2` and `(low + high + 1) / 2` produce valid height-balanced results on LeetCode.