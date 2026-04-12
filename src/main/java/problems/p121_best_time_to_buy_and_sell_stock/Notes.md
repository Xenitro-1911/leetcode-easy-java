# 121. Best Time to Buy and Sell Stock

**Difficulty:** Easy
**Topic:** Arrays, Greedy

---

## Problem Summary

Given an array `prices` where `prices[i]` is the stock price on day `i`, find the maximum profit from a single buy-sell transaction. You must buy before you sell. Return `0` if no profit is possible.

---

## Algorithm

**Single-pass greedy with running minimum.**

Track two values as you scan left to right:
1. `minPrice` — the cheapest buy day seen so far
2. `maxProfit` — the best profit seen so far

At each day `i`, you either:
- Found a new minimum → update `minPrice` (can't sell today at a profit)
- Or the price is higher → compute profit against current `minPrice`, update `maxProfit` if better

The `else` branch is valid because if `prices[i] < minPrice`, selling today is never profitable, so no profit update is needed.

```java
if (prices == null || prices.length < 2) return 0;
int minPrice = prices[0];
int maxProfit = 0;
for (int i = 1; i < prices.length; i++) {
    if (prices[i] < minPrice) {
        minPrice = prices[i];
    } else {
        int currentProfit = prices[i] - minPrice;
        if (currentProfit > maxProfit) {
            maxProfit = currentProfit;
        }
    }
}
return maxProfit;
```

---

## Complexity

| | |
|---|---|
| **Time** | O(n) — single pass |
| **Space** | O(1) — two variables |

Cannot do better than O(n): every price must be examined at least once.

---

## Key Insights

- **Running min, not global min.** You only care about the cheapest price *before* the current day, not across the whole array. The global min might come *after* a better sell opportunity.
- **if-else over Math.min/Math.max.** When a new minimum is found, a profitable sale that day is impossible, so the else branch safely skips the profit check. It also avoids two comparisons per iteration.
- **Never update minPrice and maxProfit on the same day.** You can't buy and sell on the same day and earn profit.

---

## Common Pitfalls

| Mistake | Why it fails |
|---|---|
| Fixing buy index at 0 | Misses cheaper buy days later |
| Incrementing buy index each step | Forces adjacent-day trades only |
| Finding global min first, then scanning | The best sell might come before the global min |
| Overwriting `maxProfit` without `Math.max` | Loses previously seen profits on a dip day |

---

## Alternatives

- **Kadane's variant:** Convert to daily deltas (`prices[i] - prices[i-1]`), then find max subarray sum. Equivalent logic, slightly less intuitive.
- **Brute force:** O(n²) — check every buy/sell pair. Too slow for large inputs.