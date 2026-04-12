# 111. Minimum Depth of Binary Tree

## Problem
Given a binary tree, find its minimum depth — the number of nodes along the shortest path from the root down to the nearest leaf node.
A leaf is a node with no children.

---

## Approach 1: DFS Recursive — O(n) time / O(n) space

Recurse through the tree. Key insight: when a node has only one child, the null side must not be considered as a valid path — override it with `Integer.MAX_VALUE` so it never wins `Math.min`.

```java
public int minDepth(TreeNode root) {
    if (root == null) return 0;
    if (root.left == null && root.right == null) return 1;

    int left = minDepth(root.left);
    int right = minDepth(root.right);

    if (root.left == null) left = Integer.MAX_VALUE;
    if (root.right == null) right = Integer.MAX_VALUE;

    return Math.min(left, right) + 1;
}
```

**Why `Integer.MAX_VALUE`?** A null child means no valid path exists in that direction. Setting it to `MAX_VALUE` ensures `Math.min` always picks the side that actually has a leaf.

---

## Approach 2: BFS Iterative — O(n) time / O(n) space ✅

Process the tree level by level. The first leaf node encountered is guaranteed to be at the minimum depth — return immediately without visiting the rest of the tree.

```java
public int minDepth(TreeNode root) {
    if (root == null) return 0;

    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    int depth = 1;

    while (!queue.isEmpty()) {
        int size = queue.size();

        for (int i = 0; i < size; i++) {
            TreeNode node = queue.poll();

            if (node.left == null && node.right == null) return depth;

            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }
        depth++;
    }
    return depth;
}
```

---

## DFS vs BFS for this problem

| | DFS | BFS |
|---|---|---|
| Time | O(n) worst case | O(n) worst case |
| Space | O(n) call stack | O(n) queue |
| Early exit | No — visits whole tree | Yes — stops at first leaf |

BFS is the better fit here. In a skewed tree where the minimum depth leaf is near the root, BFS exits early while DFS must traverse the entire tree regardless.

---

## Key Learnings

- **Null child ≠ valid path** — when a node has one null child, you must not count the null side as a candidate for minimum. Override it to `Integer.MAX_VALUE` in DFS, or simply don't enqueue it in BFS.
- **BFS finds shortest path faster** — whenever the goal is the *nearest* something (nearest leaf, shortest path), BFS is the natural fit because it explores level by level and can exit as soon as the target is found.
- **Leaf check is the termination condition** — `node.left == null && node.right == null` is what defines a leaf. Both approaches hinge on this check.
- **`depth` starts at 1** — the root itself counts as one node in the path, so initialise depth to 1, not 0.

---

## Complexity
| | Time | Space |
|---|---|---|
| DFS | O(n) | O(n) |
| BFS | O(n) worst, better in practice | O(n) |