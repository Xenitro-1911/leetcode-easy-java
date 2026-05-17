# 225 - Implement Stack using Queues

## Problem Statement
Implement a last-in-first-out (LIFO) stack using only queue operations (`offer`, `poll`, `peek`, `isEmpty`). The stack must support `push`, `pop`, `top`, and `empty`.

## Approach: Single Queue with Rotation on Push

### Key Insight
A queue is FIFO — elements come out in the order they went in. A stack is LIFO — the most recently added element comes out first. To bridge this gap, after every `push`, rotate all older elements to the back so the new element ends up at the front. This makes `pop` and `top` trivially O(1).

### Push Rotation Explained
```
Initial queue (top of stack = front of queue):
front → [3, 2, 1] ← back

Push 4:
1. offer(4):   [3, 2, 1, 4]
2. Rotate size-1 = 3 times:
   poll 3, offer 3: [2, 1, 4, 3]
   poll 2, offer 2: [1, 4, 3, 2]
   poll 1, offer 1: [4, 3, 2, 1]
front → [4, 3, 2, 1] ← back  ✅
```

The newest element is always at the front after push completes.

### Why `size - 1` rotations?
After `offer(x)`, the queue has `size` elements. We rotate every element **except** `x` (which is already at the back) to the back — that's `size - 1` rotations. After that, `x` has wrapped around to the front.

### All four operations:
```java
public void push(int x) {
    q.add(x);
    int size = q.size();
    for (int i = 0; i < size - 1; i++) {
        q.offer(q.poll());
    }
}

public int pop()   { return q.poll();    }  // remove front
public int top()   { return q.peek();    }  // read front
public boolean empty() { return q.isEmpty(); }
```

## Complexity
| Operation | Time | Space |
|---|---|---|
| push | O(n) — rotates all existing elements | O(1) extra |
| pop | O(1) | O(1) |
| top | O(1) | O(1) |
| empty | O(1) | O(1) |
| overall space | — | O(n) |

## Why Not Make Pop O(n) Instead?
An alternative is to do nothing on push (just `offer`) and rotate on `pop`/`top` instead. This makes push O(1) but pop/top O(n). Since stacks are typically read more than written, the push-rotation approach (O(n) push, O(1) pop) is generally preferred — but both are valid.

## Common Pitfalls
- **Naming collision** — naming both the queue field and the push parameter `x` causes the parameter to shadow the field, making the queue inaccessible inside `push`.
- **Rotating `size` times instead of `size - 1`** — rotating all elements brings the queue back to its original order, accomplishing nothing.
- **Using two queues unnecessarily** — a single queue with rotation is sufficient; two queues add complexity with no benefit.
- **Using a `Deque` as a deque** — `ArrayDeque` implements `Deque` which has direct stack operations (`push`, `pop`). The problem requires using only queue operations (`offer`, `poll`, `peek`), so avoid calling deque-specific methods.