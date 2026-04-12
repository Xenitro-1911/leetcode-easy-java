# Problem 104 — Maximum Depth of Binary Tree

## Problem Summary
Given the root of a binary tree, return its maximum depth — the number of nodes along the longest path from root to the farthest leaf.

---

## Approach: Recursive DFS (Post-order)

The solution is a clean recursive decomposition:

```java
public int maxDepth(TreeNode root) {
    if (root == null) return 0;
    return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
}
```

### Reasoning
- **Base case**: A null node contributes 0 depth — there is nothing here.
- **Recursive case**: The depth at any node is the greater of its two subtree depths, plus 1 for the node itself.
- No helper method, no global counter, no extra state needed.

---

## Complexity
| | |
|---|---|
| Time | O(n) — every node is visited exactly once |
| Space | O(h) — call stack depth equals tree height; O(log n) balanced, O(n) worst case (skewed tree) |

---

## Key Mistakes Made Along the Way

### 1. Global counter `x`
Using a shared instance variable to accumulate depth fails because recursion explores multiple paths. The counter picks up increments from all branches and never resets between paths.

### 2. Trying to "balance" with decrements
Adding `x--` on null nodes doesn't fix the global counter — null is reached twice per leaf (left and right child), causing double-decrements that corrupt the count.

### 3. Counting nodes instead of depth
Incrementing once per non-null node counts total nodes visited, not the length of the longest path.

### 4. Passing `Integer` to a void helper
`Integer` is immutable and passed by value in Java. Modifying it inside a method has no effect on the caller's variable.

---

## Core Insight
> Depth is not something you *accumulate* globally — it is something each node *computes and returns* based on its children.

Once `maxDepth` returns a value instead of modifying state, the solution becomes two lines.

---

## Trace Example
```
    1
   / \
  2   3
 /
4
```
- `maxDepth(4)` → max(0, 0) + 1 = **1**
- `maxDepth(2)` → max(1, 0) + 1 = **2**
- `maxDepth(3)` → max(0, 0) + 1 = **1**
- `maxDepth(1)` → max(2, 1) + 1 = **3** ✓# Notes.md - Notes
