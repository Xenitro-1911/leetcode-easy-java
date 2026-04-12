package problems.p121_best_time_to_buy_and_sell_stock;

class Solution {
    public int maxProfit(int[] prices) {
        // Initial check for empty or small arrays
        if (prices == null || prices.length < 2) return 0;

        int minPrice = prices[0];
        int maxProfit = 0;

        for (int i = 1; i < prices.length; i++) {
            // Using a simple if-else is often faster than Math.min/max
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
    }
}
