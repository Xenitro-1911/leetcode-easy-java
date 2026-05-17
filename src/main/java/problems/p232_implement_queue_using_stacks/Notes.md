# 232 - Implement Queue using Stacks

## Problem Statement
Implement a first-in-first-out (FIFO) queue using only stack operations (`push`, `pop`, `peek`, `isEmpty`). The queue must support `push`, `pop`, `peek`, and `empty`.

## Approach: Two Stacks with Lazy Transfer

### Key Insight
A stack is LIFO — the newest element comes out first. A queue is FIFO — the oldest element comes out first. Two stacks can simulate a queue: one stack (`s`) receives all new elements, the other (`s2`) serves all reads. When `s2` is empty and a read is needed, reverse `s` into `s2` — this flips the order so the oldest element is now on top.

### Why only transfer when `s2` is empty?
If `s2` still has elements, they are already in the correct FIFO order (oldest on top). Transferring again would corrupt that order. Only when `s2` is fully drained can a fresh transfer from `s` be done safely.

### Lazy Transfer Trace:
```
push(1): s=[1]         s2=[]
push(2): s=[1,2]       s2=[]
push(3): s=[1,2,3]     s2=[]

pop():
  s2 is empty → transfer:
  s=[1,2,3] → s2=[3,2,1]  (1 is on top)
  s2.pop() → return 1
  s=[],  s2=[3,2]

push(4): s=[4]         s2=[3,2]

pop():
  s2 not empty → skip transfer
  s2.pop() → return 2
  s=[4],  s2=[3]

pop():
  s2 not empty → skip transfer
  s2.pop() → return 3
  s=[4],  s2=[]

pop():
  s2 is empty → transfer:
  s=[4] → s2=[4]
  s2.pop() → return 4  ✅
```

### All four operations:
```java
public void push(int x)  { s.push(x); }

public int pop() {
    if (s2.isEmpty()) while (!s.isEmpty()) s2.push(s.pop());
    return s2.pop();
}

public int peek() {
    if (s2.isEmpty()) while (!s.isEmpty()) s2.push(s.pop());
    return s2.peek();
}

public boolean empty() { return s.isEmpty() && s2.isEmpty(); }
```

## Complexity
| Operation | Amortised Time | Worst Case |
|---|---|---|
| push | O(1) | O(1) |
| pop | O(1) amortised | O(n) |
| peek | O(1) amortised | O(n) |
| empty | O(1) | O(1) |

Each element is moved from `s` to `s2` at most **once** across its lifetime, so the amortised cost per operation is O(1) even though a single call can take O(n).

## Why `empty()` doesn't need a transfer
The queue is empty only when both stacks are empty — regardless of which stack holds the elements. No transfer is needed to answer this question.

## Comparison with p225 (Stack using Queue)
| | p225 Stack via Queue | p232 Queue via Stacks |
|---|---|---|
| Costly operation | push O(n) | pop/peek O(n) worst |
| Cheap operation | pop/peek O(1) | push O(1) |
| Strategy | Rotate on every push | Lazy transfer only when needed |

## Common Pitfalls
- **Transferring every time** — transferring on every `pop`/`peek` works but is inefficient and breaks ordering if new pushes happen between reads.
- **Transferring when `s2` is not empty** — corrupts the FIFO order of elements already in `s2`.
- **`empty()` with transfer** — unnecessary; both stacks being empty is sufficient.
- **Not handling interleaved push/pop** — the lazy transfer approach handles this naturally; eager transfer does not.