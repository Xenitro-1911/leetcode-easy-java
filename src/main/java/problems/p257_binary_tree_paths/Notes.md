# 257 - Binary Tree Paths

## Problem Statement
Given the root of a binary tree, return all root-to-leaf paths in any order. A leaf is a node with no children.

## Approach: DFS with StringBuilder Backtracking

### Key Insight
Use depth-first recursion, building the path string as you go deeper. When a leaf is reached, add the completed path to the result. After returning from a node, **backtrack** the StringBuilder to its previous state so sibling branches start with a clean path.

### Why StringBuilder over String?
`String` concatenation creates a new object at every node — O(n) allocations per path, O(n²) total for a skewed tree. A single shared `StringBuilder` is mutated in place and backtracked after each node, giving O(n) total work with far fewer allocations.

### Backtracking Explained
Before appending the current node, save `len = path.length()`. After processing the node and its subtrees, call `path.setLength(len)` to restore the StringBuilder to exactly where it was before this node. This undoes the append so the parent's path is intact for the next sibling.

```
Tree: [1, 2, 3, null, 5]

helper(1, "")
  len=0, path="1"
  helper(2, "1")
    len=1, path="1->2"
    helper(null) → return
    helper(5, "1->2")
      len=4, path="1->2->5"  ← leaf → add "1->2->5"
      setLength(4) → path="1->2"
    setLength(1) → path="1"
  helper(3, "1")
    len=1, path="1->3"  ← leaf → add "1->3"
    setLength(1) → path="1"
```

### Why `if (len > 0)` before appending `->`?
The root node has no predecessor, so no `->` should be prepended. Every subsequent node has a parent, so `->` is added first. This avoids a leading `->` in the path string.

```java
public void helper(TreeNode root, StringBuilder path, List<String> result) {
    if (root == null) return;

    int len = path.length();
    if (len > 0) path.append("->");
    path.append(root.val);

    if (root.left == null && root.right == null) {
        result.add(path.toString());
    } else {
        helper(root.left, path, result);
        helper(root.right, path, result);
    }

    path.setLength(len);  // backtrack
}
```

## Complexity
| | Value |
|---|---|
| Time | O(n) — every node visited exactly once |
| Space | O(h) — recursion stack depth equals tree height; O(log n) balanced, O(n) skewed |

## Common Pitfalls
- **`String.concat()` or `+=` not assigned** — strings are immutable; `path.concat(x)` returns a new string but the original is unchanged. Always assign: `path = path + x`.
- **Passing path by value with String but forgetting it's immutable** — with `String`, each call gets its own copy automatically (safe). With `StringBuilder`, all calls share one object — backtracking is mandatory.
- **Forgetting `setLength()` with StringBuilder** — without backtracking, sibling branches inherit path segments from previously visited branches, producing corrupted paths.
- **Not handling null root** — calling `helper` on a null root without a null check causes NullPointerException.
- **Adding `->` before the root** — the `if (len > 0)` guard prevents a leading `->` on the first node.

## Alternative: String Concatenation (Simpler, Slightly Slower)
```java
public void helper(TreeNode root, String path, List<String> result) {
    if (root == null) return;
    path = path.isEmpty() ? "" + root.val : path + "->" + root.val;
    if (root.left == null && root.right == null) {
        result.add(path);
        return;
    }
    helper(root.left, path, result);
    helper(root.right, path, result);
}
```
No backtracking needed since strings are immutable — each call gets its own copy. Simpler to reason about but creates more temporary objects.