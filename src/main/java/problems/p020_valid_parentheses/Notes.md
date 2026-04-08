# Problem 20 - Valid Parentheses

## Problem Summary
Given a string containing only `(`, `)`, `{`, `}`, `[`, `]`, determine if the input string is valid.
A string is valid if:
- Every opening bracket has a corresponding closing bracket
- Brackets are closed in the correct order

---

## Approach: Stack (Optimal)

### Key Insight
Instead of pushing the **opening** bracket and later checking if the closing bracket matches,
we push the **expected closing bracket** directly onto the stack.
This eliminates the need for a HashMap and simplifies the matching check.

### Algorithm
1. **Odd length early return** — an odd length string can never be balanced, return false immediately
2. **Loop through characters:**
    - If opening bracket → push the **expected closing bracket** onto the stack
    - If closing bracket → check if stack is empty OR top of stack doesn't match → return false
3. **After loop** → return `stack.isEmpty()` (unmatched opening brackets would remain)

---

## Complexity
| | Complexity |
|---|---|
| Time | O(n) — single pass through the string |
| Space | O(n) — stack holds at most n/2 characters |

> O(n) is the **theoretical minimum** for this problem — you must read every character at least once.

---

## Key Tricks & Optimisations

| Trick | Why |
|---|---|
| `s.length() % 2 != 0` early return | Odd length can never be valid — saves work |
| `s.toCharArray()` | Avoids repeated `.charAt()` method call overhead |
| Push expected closing bracket | Eliminates HashMap lookup, cleaner matching logic |
| `stack.isEmpty() \|\| stack.pop() != c` | Handles empty stack guard and match check in one line |

---

## Common Mistakes
- Calling `stack.peek()` on an empty stack → `NullPointerException` (always guard first!)
- Using `||` instead of `&&` when checking null + match (wrong short-circuit behaviour)
- Forgetting to check `stack.isEmpty()` at the end (unmatched opening brackets)
- Assuming count-based approach works — `([)]` has balanced counts but is **invalid**

---

## Solution

```java
import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
    public boolean isValid(String s) {
        if (s.length() % 2 != 0) return false;

        Deque<Character> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            } else if (c == '[') {
                stack.push(']');
            } else if (stack.isEmpty() || stack.pop() != c) {
                return false;
            }
        }

        return stack.isEmpty();
    }
}
```