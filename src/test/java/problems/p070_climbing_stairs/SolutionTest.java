package problems.p070_climbing_stairs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SolutionTest {

    private final Solution solution = new Solution();

    // --- Base Cases ---

    @Test
    public void testOneStep() {
        // Only 1 way: {1}
        assertEquals(1, solution.climbStairs(1));
    }

    @Test
    public void testTwoSteps() {
        // 2 ways: {1+1} or {2}
        assertEquals(2, solution.climbStairs(2));
    }

    @Test
    public void testThreeSteps() {
        // 3 ways: {1+1+1}, {1+2}, {2+1}
        assertEquals(3, solution.climbStairs(3));
    }

    // --- Core Fibonacci Growth ---

    @Test
    public void testFourSteps() {
        // ways(4) = ways(3) + ways(2) = 3 + 2 = 5
        // Paths: {1+1+1+1}, {1+1+2}, {1+2+1}, {2+1+1}, {2+2}
        assertEquals(5, solution.climbStairs(4));
    }

    @Test
    public void testFiveSteps() {
        // ways(5) = ways(4) + ways(3) = 5 + 3 = 8
        // step 5 reached from: step 4 (+1 step) OR step 3 (+2 steps)
        assertEquals(8, solution.climbStairs(5));
    }

    @Test
    public void testSixSteps() {
        // ways(6) = ways(5) + ways(4) = 8 + 5 = 13
        assertEquals(13, solution.climbStairs(6));
    }

    @Test
    public void testSevenSteps() {
        // ways(7) = ways(6) + ways(5) = 13 + 8 = 21
        assertEquals(21, solution.climbStairs(7));
    }

    // --- Larger Values ---

    @Test
    public void testTenSteps() {
        assertEquals(89, solution.climbStairs(10));
    }

    @Test
    public void testTwentySteps() {
        assertEquals(10946, solution.climbStairs(20));
    }

    // --- Constraint Boundary ---

    @Test
    public void testFortyFiveSteps() {
        // Maximum constraint: n = 45
        assertEquals(1836311903, solution.climbStairs(45));
    }

    // --- Sequence Sanity Check ---

    @Test
    public void testFibonacciPattern() {
        // Each result should equal the sum of the previous two
        // ways(n) = ways(n-1) + ways(n-2)
        for (int n = 3; n <= 15; n++) {
            int prev2 = solution.climbStairs(n - 2);
            int prev1 = solution.climbStairs(n - 1);
            int curr  = solution.climbStairs(n);
            assertEquals(prev1 + prev2, curr,
                    "Failed Fibonacci check at n=" + n);
        }
    }
}