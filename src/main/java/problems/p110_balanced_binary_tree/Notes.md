# 110. Balanced Binary Tree

## Problem
Given a binary tree, determine if it is height-balanced.
A height-balanced binary tree is one where the depth of the two subtrees of **every node** never differs by more than one.

## Approach 1: Naive Recursive — O(n²) time / O(n) space

Call `isBalanced` recursively on left and right subtrees, using a separate `helper` to compute heights.

```java
public boolean isBalanced(TreeNode root) {
    if (root == null) return true;
    int left = helper(root.left);
    int right = helper(root.right);
    if (Math.abs(left - right) > 1) return false;
    return isBalanced(root.left) && isBalanced(root.right);
}

public int helper(TreeNode x) {
    if (x == null) return 0;
    return Math.max(helper(x.left), helper(x.right)) + 1;
}
```

**Why O(n²)?** At each node, `helper` is called again on the subtree below it — nodes near the root get visited many times.

---

## Approach 2: Optimised Single Pass — O(n) time / O(n) space ✅

Encode balance information directly into the return value of `helper`:
- Return `-1` if any imbalance is detected anywhere in the subtree
- Return the actual height otherwise

This way every node is visited exactly once.

```java
public boolean isBalanced(TreeNode root) {
    return helper(root) != -1;
}

public int helper(TreeNode x) {
    if (x == null) return 0;

    int left = helper(x.left);
    int right = helper(x.right);

    if (left == -1 || right == -1) return -1;       // propagate imbalance up
    if (Math.abs(left - right) > 1) return -1;       // this node is unbalanced

    return Math.max(left, right) + 1;                // return actual height
}
```

---

## Key Learnings

- **"Every node" matters** — the definition requires balance at every node, not just the root. A root-only check will miss imbalances deeper in the tree.
- **Sentinel value trick** — when a recursive function needs to return both a value *and* a status, a sentinel (here `-1`) can encode both in a single return type, avoiding extra fields or wrapper objects.
- **Reuse computed results** — always use already-computed values (`left`, `right`) instead of re-calling the function. Re-calling defeats the purpose of memoising the traversal.
- **Null node = balanced + height 0** — the base case returning `0` for null correctly represents an empty subtree as balanced with height zero.

---

## Complexity
| | Time | Space |
|---|---|---|
| Naive | O(n²) | O(n) |
| Optimised | O(n) | O(n) |

Space is O(n) in both cases due to the call stack (O(h) where h = tree height, worst case O(n) for a skewed tree).