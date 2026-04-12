# 94. Binary Tree Inorder Traversal

## Problem
Given the root of a binary tree, return the inorder traversal of its nodes' values.

## Approach — Recursive DFS

Inorder traversal visits nodes in **Left → Root → Right** order.

The recursion is built on one key insight: you don't need to manually "go back" to the root after reaching the leftmost node — the **call stack unwinds automatically**, returning to the next line after each recursive call finishes.

### Base Case
If the current node is `null` (i.e., we've fallen off the tree), return immediately. This handles leaf children cleanly without any look-ahead checks.

### Recursive Step
1. Recurse left
2. Add current value
3. Recurse right

### Key Design Decision
The result list is declared **inside** `inorderTraversal` and passed into the helper — not as an instance variable. This avoids stale state if the same `Solution` object is reused across test cases (common on LeetCode).

## Complexity
- **Time:** O(n) — every node is visited exactly once
- **Space:** O(n) — result list + O(h) call stack where h is tree height (O(log n) balanced, O(n) worst case skewed)

## Mistakes Made
- Initially checked `root.left == null` instead of `root == null` as the base case — this looks ahead one level unnecessarily and breaks on nodes with no left child
- Initially used an instance-level `ArrayList`, which risks carrying over values between test cases

## Key Learnings
- **Recursion unwinds the stack for you** — you never need to manually navigate back up the tree
- **Base case = check the current node, not its children** — keep it simple
- **Pass mutable collections as parameters** rather than using instance fields for stateless, reusable code# Notes.md - Notes
