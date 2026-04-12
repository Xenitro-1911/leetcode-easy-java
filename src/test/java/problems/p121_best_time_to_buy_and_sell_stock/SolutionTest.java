package problems.p121_best_time_to_buy_and_sell_stock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    private Solution solution;

    @BeforeEach
    void setUp() {
        solution = new Solution();
    }

    // --- Basic cases ---

    @Test
    void testExample1() {
        // Buy on day 1 (price=1), sell on day 4 (price=6) → profit = 5
        assertEquals(5, solution.maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
    }

    @Test
    void testExample2() {
        // Prices only decrease → no profit possible
        assertEquals(0, solution.maxProfit(new int[]{7, 6, 4, 3, 1}));
    }

    @Test
    void testAllSamePrices() {
        // No movement → no profit
        assertEquals(0, solution.maxProfit(new int[]{5, 5, 5, 5}));
    }

    // --- Edge cases ---

    @Test
    void testTwoDaysProfit() {
        assertEquals(3, solution.maxProfit(new int[]{1, 4}));
    }

    @Test
    void testTwoDaysNoProfit() {
        assertEquals(0, solution.maxProfit(new int[]{4, 1}));
    }

    @Test
    void testSingleElement() {
        assertEquals(0, solution.maxProfit(new int[]{5}));
    }

    @Test
    void testNullInput() {
        assertEquals(0, solution.maxProfit(null));
    }

    // --- Tricky cases ---

    @Test
    void testMinAfterBetterSell() {
        // Global min is at end — best trade is (1→6), not using the final 0
        assertEquals(5, solution.maxProfit(new int[]{3, 1, 6, 2, 0}));
    }

    @Test
    void testProfitNotFromGlobalMin() {
        // Global min is 1 (day 4), but best profit is 5-2=3 (days 1→2)
        assertEquals(3, solution.maxProfit(new int[]{3, 2, 5, 1, 2}));
    }

    @Test
    void testDipThenRise() {
        // Must not lose maxProfit when price dips after a peak
        assertEquals(4, solution.maxProfit(new int[]{1, 5, 2, 3}));
    }

    @Test
    void testLargePriceJump() {
        assertEquals(999, solution.maxProfit(new int[]{1, 1000}));
    }

    @Test
    void testPeakAtStart() {
        // Best price is day 0 — can only go down from there
        assertEquals(0, solution.maxProfit(new int[]{100, 50, 25, 10}));
    }

    @Test
    void testPeakAtEnd() {
        // Best sell is the last day
        assertEquals(8, solution.maxProfit(new int[]{1, 2, 3, 4, 9}));
    }
}