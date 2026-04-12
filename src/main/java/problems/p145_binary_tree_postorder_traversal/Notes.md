# 145. Binary Tree Postorder Traversal

## Problem
Given the root of a binary tree, return the postorder traversal of its nodes' values.

## Approach — Recursive DFS

**Postorder order: Left → Right → Root**

1. Base case: if `root == null`, return an empty list.
2. Call `helper(root, list)` to populate the list recursively.
3. In the helper:
    - Recurse on `root.left` (left subtree)
    - Recurse on `root.right` (right subtree)
    - Add `root.val` last (root)

## Solution

```java
public class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
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
        helper(root.left, x);
        helper(root.right, x);
        x.add(root.val);
    }
}
```

## Complexity
- **Time:** O(n) — every node is visited exactly once
- **Space:** O(h) — call stack depth equals tree height h; O(log n) balanced, O(n) worst case (skewed)

## Key Takeaways
- Postorder = **root last** — the `add` comes *after* both recursive calls.
- Classic use case: deleting/freeing a tree (children before parent), or evaluating expression trees.
- The void helper mutates the shared `List` directly — works because `List` is passed by reference.
- Traversal family comparison:
    - **Preorder** (LeetCode 144): root → left → right
    - **Inorder** (LeetCode 94): left → root → right
    - **Postorder** (LeetCode 145): left → right → root