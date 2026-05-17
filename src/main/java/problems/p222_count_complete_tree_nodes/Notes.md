# 222 - Count Complete Tree Nodes

## Problem Statement
Given the root of a complete binary tree, return the number of nodes. The algorithm must run in less than O(n) time.

## Approach: Perfect Subtree Detection + Recursion

### Key Insight
In a complete binary tree, if you measure the height by always going **left** and always going **right** from any node:
- If both heights are **equal** → that subtree is a **perfect** binary tree → use the formula `2^h - 1` instantly
- If they **differ** → recurse on both subtrees

At every recursive call, one of the two subtrees is always guaranteed to be perfect (because the tree is complete), so it short-circuits immediately. Only one path truly recurses — giving O(log² n) overall.

### Algorithm
```java
public int countNodes(TreeNode root) {
    if (root == null) return 0;

    int leftH = 0, rightH = 0;
    TreeNode l = root, r = root;

    while (l != null) { leftH++; l = l.left; }
    while (r != null) { rightH++; r = r.right; }

    if (leftH == rightH) return (1 << leftH) - 1;

    return 1 + countNodes(root.left) + countNodes(root.right);
}
```

### Step-by-step on example [1,2,3,4,5,6]:
```
At root (1):
  leftH  = 3 (1→2→4)
  rightH = 3 (1→3→6)  ← wait, right path goes 1→3→6? No — 3 has no right child
  rightH = 2 (1→3)
  leftH != rightH → recurse

At root.left (2):
  leftH  = 2 (2→4)
  rightH = 2 (2→5)
  equal → perfect! return (1<<2)-1 = 3 ✅

At root.right (3):
  leftH  = 2 (3→6)
  rightH = 1 (3→ null right)
  differ → recurse

At node 6 and null:
  countNodes(6) → leftH=1, rightH=1 → return 1
  countNodes(null) → return 0

Total: 1 + 3 + (1 + 1 + 0) = 6 ✅
```

## Bit Manipulation: `(1 << leftH) - 1`

### What is a left bit shift?
The `<<` operator shifts all bits of a number to the left by a given number of positions. Each left shift multiplies the value by 2.

```
1 in binary:       0 0 0 0 0 0 0 1

1 << 1:            0 0 0 0 0 0 1 0  =  2
1 << 2:            0 0 0 0 0 1 0 0  =  4
1 << 3:            0 0 0 0 1 0 0 0  =  8
1 << 4:            0 0 0 1 0 0 0 0  =  16
```

So `1 << h` is exactly `2^h`, computed with a single CPU instruction rather than a loop or `Math.pow()`.

### Why `(1 << leftH) - 1`?
A perfect binary tree of height `h` (counting nodes on the path, not edges) has:
- Level 1: 1 node
- Level 2: 2 nodes
- Level 3: 4 nodes
- ...
- Level h: 2^(h-1) nodes
- **Total: 2^h - 1 nodes**

So `(1 << leftH) - 1` = `2^leftH - 1` = total nodes in a perfect tree of height `leftH`.

Example with `leftH = 3`:
```
(1 << 3) - 1
= 8 - 1
= 7
```
A perfect tree with 3 levels has 7 nodes. ✅

### Why not `Math.pow(2, leftH)`?
`Math.pow()` returns a `double`, requiring a cast back to `int` and involving floating point arithmetic. `1 << leftH` is an integer operation — faster, cleaner, and exact.

## Complexity
| | Value |
|---|---|
| Time | O(log² n) — at each level, two O(log n) height traversals, over O(log n) levels |
| Space | O(log n) — recursion depth equals tree height |

## Common Pitfalls
- **Confusing height with levels** — if you count edges, height of a single node is 0 and formula becomes `(1 << leftH) - 1` with `leftH` being edge-count + 1. Be consistent.
- **Using O(n) recursion** — a naive DFS that visits every node works but violates the problem constraint.
- **`Math.pow()` instead of bit shift** — returns `double`, risks floating point precision issues on large inputs.
- **Measuring height wrong** — left height must always go left, right height must always go right. Mixing directions gives wrong results.