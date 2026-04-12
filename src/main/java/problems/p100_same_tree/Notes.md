# 100. Same Tree

## Problem
Given the roots of two binary trees `p` and `q`, return `true` if they are structurally identical and all corresponding nodes have the same value.

## Approach: Recursive DFS

Handle three base cases in order, then recurse:

1. Both nodes null → same branch end → `return true`
2. Exactly one null → structural mismatch → `return false`
3. Values differ → `return false`
4. Recurse: both left AND both right subtrees must match

```java
public boolean isSameTree(TreeNode p, TreeNode q) {
    if (p == null && q == null) return true;
    if (p == null || q == null) return false;
    if (p.val != q.val) return false;
    return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
}
```

## Complexity
- **Time:** O(n) — every node visited once
- **Space:** O(h) — call stack depth = tree height; O(log n) balanced, O(n) worst case

## Key Learnings

### 1. Null checks must come before value access
Checking `p.val` before guarding against `p == null` causes a NullPointerException. Always guard first.

### 2. Primitives are passed by value in Java
Passing `int f` into a helper and setting `f = 1` inside does not affect the caller's copy. Options: use a return value, use an `Integer[]` wrapper, or use an instance field. A boolean return value is the cleanest solution here.

### 3. Recursive return values must be used
Calling `helper(p.left, q.left)` without capturing or returning the result discards the answer entirely. A `false` deep in the tree will never surface.

### 4. `&&` short-circuits — use it for combining subtree results
`return left && right` means: if the left subtree already returns `false`, the right subtree is never evaluated. Correct and efficient.

### 5. No separate helper needed
When the recursive signature matches the problem signature, the method can call itself directly.# Notes.md - Notes
