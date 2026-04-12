# 101. Symmetric Tree

## Problem
Given the root of a binary tree, check whether it is a mirror of itself (symmetric around its center).

## Approach — Recursive Mirror Check

Decompose the problem into comparing two subtrees: the left and right children of the root.
A tree is symmetric if its left subtree is a mirror of its right subtree.

Two nodes mirror each other if:
1. Both are null → symmetric
2. One is null, the other isn't → not symmetric
3. Their values differ → not symmetric
4. Their values match AND:
    - `left.left` mirrors `right.right`
    - `left.right` mirrors `right.left`

### Key insight — mirroring direction
Mirror comparison is **not** same-side (left↔left, right↔right).
It is **cross-side**: outer children mirror each other, inner children mirror each other.

```
        1
       / \
      2   2
     / \ / \
    3  4 4  3

left.left (3)  ↔  right.right (3)   ← outer
left.right (4) ↔  right.left (4)    ← inner
```

## Solution

```java
public class Solution {
    public boolean isSymmetric(TreeNode root) {
        return helper(root.left, root.right);
    }

    public boolean helper(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        if (left.val != right.val) return false;
        return helper(left.left, right.right) && helper(left.right, right.left);
    }
}
```

## Complexity
- **Time:** O(n) — every node is visited exactly once
- **Space:** O(h) — recursive call stack, where h is tree height; O(log n) for balanced, O(n) worst case (skewed tree)

## Bugs Fixed During Development
| Bug | Effect | Fix |
|-----|--------|-----|
| `if (left.val == right.val) return true` | Ignored subtrees entirely; missed mismatches deeper in the tree | Remove early return; only return `false` on mismatch |
| `helper(left.left, right.left)` | Compared same-side children instead of mirror-side | Changed to `helper(left.left, right.right)` and `helper(left.right, right.left)` |
| Recursive results discarded | `return true` always regardless of subtree results | Combined results with `&&` |

## Key Takeaways
- **Symmetry requires both structure and values** to mirror — a structurally balanced tree with different values is not symmetric.
- **Mirror traversal crosses sides** — always pair outer with outer and inner with inner.
- **Discard no boolean return values** — if a recursive call returns `false`, it must propagate up.# Notes.md - Notes
