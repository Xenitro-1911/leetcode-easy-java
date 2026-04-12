# 144. Binary Tree Preorder Traversal

## Problem
Given the root of a binary tree, return the preorder traversal of its nodes' values.

## Approach — Recursive DFS

**Preorder order: Root → Left → Right**

1. Base case: if `root == null`, return an empty list.
2. Call `helper(root, list)` to populate the list recursively.
3. In the helper:
    - Add `root.val` first (root)
    - Recurse on `root.left` (left subtree)
    - Recurse on `root.right` (right subtree)

## Solution

```java
public class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> x = new ArrayList<>();
        if (root == null) {
            return x;
        }
        helper(root, x);
        return x;
    }

    public void helper(TreeNode root, List<Integer> x) {
        if (root == null) {
            return;
        }
        x.add(root.val);
        helper(root.left, x);
        helper(root.right, x);
    }
}
```

## Complexity
- **Time:** O(n) — every node is visited exactly once
- **Space:** O(h) — call stack depth equals tree height h; O(log n) balanced, O(n) worst case (skewed)

## Key Takeaways
- Preorder = **root first**, then left, then right — the add comes *before* the recursive calls.
- The void helper mutates the shared `List` directly — no return value needed because `List` is passed by reference.
- Contrast with:
    - **Inorder** (LeetCode 94): left → root → right
    - **Postorder** (LeetCode 145): left → right → root