# Q21 - Merge Two Sorted Lists

## Key Concept: Linked Lists
- A linked list is a chain of nodes
- Each node holds a **value** and a **pointer to the next node**
- The last node points to `null`

```
1 -> 3 -> 5 -> null
2 -> 4 -> 6 -> null
```

## The Dummy Node Trick
- Create a fake `dummy` node at the start
- Use a `current` pointer to build the new list
- Return `dummy.next` at the end (skips the fake node)
- **Why?** Avoids special-casing the head of the result list

## Algorithm (Two Pointer)
1. Compare `list1.val` vs `list2.val`
2. Attach the smaller node to `current.next`
3. Advance that list's pointer forward
4. Advance `current` forward
5. When one list is exhausted → attach the remainder of the other

## Why attaching the remainder works
- Both input lists are **already sorted**
- So whatever is left over is guaranteed to be larger than everything already added

## Complexity
| | |
|---|---|
| Time | O(n + m) — visit every node once |
| Space | O(1) — no new nodes created, just re-linked |

## Common Mistakes
- Forgetting to advance `current = current.next` inside the loop
- Returning `dummy` instead of `dummy.next`
- Trying to create new nodes (unnecessary — just re-link existing ones)