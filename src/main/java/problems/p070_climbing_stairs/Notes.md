# 🧗 Climbing Stairs — LeetCode #70

## The Problem
You can climb either **1 step** or **2 steps** at a time.  
How many **distinct ways** can you reach the top of `n` stairs?

---

## 🔑 The Key Insight

To arrive at step `n`, you **must** have come from one of two places:
- **Step `n-1`** → you took 1 step up
- **Step `n-2`** → you took 2 steps up

**That's it. No other way exists.**

So:
```
ways(n) = ways(n-1) + ways(n-2)
```

### Concrete Example — Step 5:
```
ways(5) = ways(4) + ways(3)
        = 5      + 3
        = 8
```
Because:
- All the ways to reach step 4, then take **1 step** → still valid paths to step 5
- All the ways to reach step 3, then take **2 steps** → still valid paths to step 5

---

## 📊 Building the Table

Start from what you **know for certain**:

| Step | Ways | How |
|------|------|-----|
| 1    | 1    | base case |
| 2    | 2    | base case |
| 3    | 3    | = ways(2) + ways(1) = 2 + 1 |
| 4    | 5    | = ways(3) + ways(2) = 3 + 2 |
| 5    | 8    | = ways(4) + ways(3) = 5 + 3 |
| 6    | 13   | = ways(5) + ways(4) = 8 + 5 |

> 💡 You might notice this is the **Fibonacci sequence**!

---

## 🪟 The Sliding Window Approach

We only ever need the **last two values** — so we use two variables:

```
first  = ways(n-2)   ← two steps back
second = ways(n-1)   ← one step back
third  = first + second  ← current step ✅
```

Then we **slide forward**:
```
first  ← second
second ← third
```

Visual for n = 5:

```
[first=1, second=2]  →  third=3
[first=2, second=3]  →  third=5
[first=3, second=5]  →  third=8   ← answer!
```

---

## ✅ The Solution

```java
public class Solution {
    public int climbStairs(int n) {
        if (n <= 2) return n;

        int first = 1;  // Ways to reach step 1
        int second = 2; // Ways to reach step 2

        for (int i = 3; i <= n; i++) {
            int third = first + second;
            first = second;
            second = third;
        }

        return second;
    }
}
```

---

## 📌 Complexity

| | |
|---|---|
| **Time** | O(n) — one pass from 3 to n |
| **Space** | O(1) — only two variables used |

---

## 🧠 Pattern to Remember

> Whenever a problem says *"how many ways to reach X, given you can take A or B steps"*,  
> think **Fibonacci-style DP**: `ways(n) = ways(n-1) + ways(n-2)`