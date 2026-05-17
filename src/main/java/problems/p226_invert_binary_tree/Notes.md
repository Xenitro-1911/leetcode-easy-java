# 226 - Invert Binary Tree

## Problem Statement
Given the root of a binary tree, invert the tree (mirror it), and return its root.

## Approach: Recursive In-Place Swap

### Key Insight
Inverting a binary tree means every node's left and right children are swapped. You don't need to build a new tree — swap the children in place at each node, then recursively invert both subtrees.

### Algorithm
1. Base case: if `root` is null, return null.
2. Swap `root.left` and `root.right` using a temp variable.
3. Recurse into the (now swapped) left and right subtrees.
4. Return `root`.

```java
public TreeNode invertTree(TreeNode root) {
    if (root == null) return root;

    TreeNode temp = root.left;
    root.left = root.right;
    root.right = temp;

    invertTree(root.left);
    invertTree(root.right);
    return root;
}
```

### Why swap before recursing?
It doesn't matter — swapping before or after recursing both produce the correct result. The swap at each node is independent of what happens in the subtrees. Swapping first (pre-order) is the most intuitive.

### Recursion trace on [4,2,7,1,3,6,9]:
```
invertTree(4)
  swap: left=7, right=2
  invertTree(7)
    swap: left=9, right=6
    invertTree(9) → leaf, return
    invertTree(6) → leaf, return
  invertTree(2)
    swap: left=3, right=1
    invertTree(3) → leaf, return
    invertTree(1) → leaf, return
return root → [4,7,2,9,6,3,1] ✅
```

## Complexity
| | Value |
|---|---|
| Time | O(n) — every node is visited exactly once |
| Space | O(h) — recursion stack depth equals tree height; O(log n) for balanced, O(n) worst case |

## Alternative: Iterative with Queue (BFS)
```java
public TreeNode invertTree(TreeNode root) {
    if (root == null) return root;
    Queue<TreeNode> q = new LinkedList<>();
    q.offer(root);
    while (!q.isEmpty()) {
        TreeNode node = q.poll();
        TreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;
        if (node.left != null) q.offer(node.left);
        if (node.right != null) q.offer(node.right);
    }
    return root;
}
```
Level-order traversal, swapping children at each node. Same O(n) time, O(n) space for the queue. Avoids recursion stack overflow on very deep trees but is more verbose.

## Common Pitfalls
- **Building a new tree** — unnecessary; the swap is done in place on the existing nodes.
- **Forgetting the null base case** — without it, `root.left` and `root.right` throw a NullPointerException on leaf nodes.
- **Not returning root** — the function must return the (now inverted) root so the caller has a reference to the tree.
- **Trying to return both recursive calls** — `return invertTree(root.left) invertTree(root.right)` is a syntax error; the recursive calls are side-effect operations and only `root` needs to be returned.