package problems.p118_pascals_triangle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    private Solution solution;

    @BeforeEach
    void setUp() {
        solution = new Solution();
    }

    // --- Base cases ---

    @Test
    void testOneRow() {
        List<List<Integer>> result = solution.generate(1);
        assertEquals(1, result.size());
        assertEquals(List.of(1), result.get(0));
    }

    @Test
    void testTwoRows() {
        List<List<Integer>> result = solution.generate(2);
        assertEquals(2, result.size());
        assertEquals(List.of(1), result.get(0));
        assertEquals(List.of(1, 1), result.get(1));
    }

    @Test
    void testThreeRows() {
        List<List<Integer>> result = solution.generate(3);
        assertEquals(3, result.size());
        assertEquals(List.of(1, 2, 1), result.get(2));
    }

    // --- Standard cases ---

    @Test
    void testFiveRows() {
        List<List<Integer>> result = solution.generate(5);
        assertEquals(5, result.size());
        assertEquals(List.of(1, 4, 6, 4, 1), result.get(4));
    }

    @Test
    void testRowCount() {
        for (int n = 1; n <= 10; n++) {
            assertEquals(n, solution.generate(n).size());
        }
    }

    @Test
    void testEachRowLength() {
        List<List<Integer>> result = solution.generate(6);
        for (int i = 0; i < 6; i++) {
            assertEquals(i + 1, result.get(i).size());
        }
    }

    // --- Structural invariants ---

    @Test
    void testFirstElementAlwaysOne() {
        List<List<Integer>> result = solution.generate(10);
        for (List<Integer> row : result) {
            assertEquals(1, row.get(0));
        }
    }

    @Test
    void testLastElementAlwaysOne() {
        List<List<Integer>> result = solution.generate(10);
        for (List<Integer> row : result) {
            assertEquals(1, row.get(row.size() - 1));
        }
    }

    @Test
    void testSymmetry() {
        List<List<Integer>> result = solution.generate(8);
        for (List<Integer> row : result) {
            for (int j = 0; j < row.size() / 2; j++) {
                assertEquals(row.get(j), row.get(row.size() - 1 - j));
            }
        }
    }

    @Test
    void testInteriorSumProperty() {
        // Each interior element equals sum of two above it
        List<List<Integer>> result = solution.generate(7);
        for (int i = 1; i < result.size(); i++) {
            List<Integer> prev = result.get(i - 1);
            List<Integer> curr = result.get(i);
            for (int j = 1; j < curr.size() - 1; j++) {
                assertEquals((int) prev.get(j - 1) + prev.get(j), (int) curr.get(j));
            }
        }
    }

    // --- Edge / constraint cases ---

    @Test
    void testMaxRows() {
        List<List<Integer>> result = solution.generate(30);
        assertEquals(30, result.size());
        assertEquals(1, result.get(29).get(0));
        assertEquals(1, result.get(29).get(29));
    }

    @Test
    void testRowSumsArePowersOfTwo() {
        // Sum of row i == 2^i
        List<List<Integer>> result = solution.generate(10);
        for (int i = 0; i < result.size(); i++) {
            int sum = result.get(i).stream().mapToInt(Integer::intValue).sum();
            assertEquals((int) Math.pow(2, i), sum);
        }
    }
}