# 112. Path Sum

**Difficulty:** Easy
**Topic:** Binary Trees, DFS, Recursion

---

## Problem Summary

Given the root of a binary tree and an integer `targetSum`, return `true` if any
root-to-leaf path exists where the sum of all node values equals `targetSum`.
A leaf is a node with no children.

---

## Approach: DFS with Running Sum

Traverse the tree recursively, accumulating a running sum as we go deeper.
At each leaf, check if the accumulated sum equals `targetSum`.

### Algorithm

1. Base case: if `root == null`, return `false`
2. Add the current node's value to `sum`
3. If it's a leaf (`left == null && right == null`), check `sum == targetSum`
4. Otherwise, recurse into left and right subtrees with `||` — either path succeeding is enough

### Key Insight

Use `||` (not `&&`) when combining recursive calls — we want **any** valid path,
not **all** paths to be valid.

---

## Solution

```java
public class Solution {

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        return helper(0, root, targetSum);
    }

    public boolean helper(int sum, TreeNode root, int targetSum) {
        if (root == null) return false;

        sum = sum + root.val;

        if (root.left == null && root.right == null) {
            return sum == targetSum;
        }

        return helper(sum, root.left, targetSum) || helper(sum, root.right, targetSum);
    }
}
```

### Cleaner Alternative (no helper needed)

Subtract from `targetSum` as you go down, so at the leaf you just check if the
remaining target equals the leaf's value:

```java
public boolean hasPathSum(TreeNode root, int targetSum) {
    if (root == null) return false;
    if (root.left == null && root.right == null) return root.val == targetSum;
    return hasPathSum(root.left, targetSum - root.val)
        || hasPathSum(root.right, targetSum - root.val);
}
```

---

## Complexity

| | |
|---|---|
| **Time** | O(n) — every node is visited once |
| **Space** | O(h) — call stack depth equals tree height; O(log n) balanced, O(n) worst case (skewed tree) |

---

## Pitfalls

- **`&&` vs `||`** on recursive calls: `&&` would require *all* paths to succeed — wrong.
- **Null check in helper**: Without it, calling `helper(null)` causes a NullPointerException
  since the first line accesses `root.val`.
- **Double-adding at leaf**: If the leaf case falls through to the recursive call,
  `root.val` gets added twice. Always `return` explicitly from the leaf block.
- **Falling through leaf on mismatch**: If `sum != targetSum` at a leaf, you must
  `return false` — don't let execution continue to the recursive calls below.

---

## Alternatives

- **Iterative DFS** using a stack of `(node, currentSum)` pairs
- **BFS** with a queue — same idea, level-order instead of depth-first