package problems.p119_pascals_triangle_ii;

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
    void testRowZero() {
        assertEquals(List.of(1), solution.getRow(0));
    }

    @Test
    void testRowOne() {
        assertEquals(List.of(1, 1), solution.getRow(1));
    }

    @Test
    void testRowTwo() {
        assertEquals(List.of(1, 2, 1), solution.getRow(2));
    }

    @Test
    void testRowThree() {
        assertEquals(List.of(1, 3, 3, 1), solution.getRow(3));
    }

    @Test
    void testRowFour() {
        assertEquals(List.of(1, 4, 6, 4, 1), solution.getRow(4));
    }

    // --- Structural invariants ---

    @Test
    void testRowLength() {
        for (int i = 0; i <= 10; i++) {
            assertEquals(i + 1, solution.getRow(i).size());
        }
    }

    @Test
    void testFirstElementAlwaysOne() {
        for (int i = 0; i <= 15; i++) {
            assertEquals(1, solution.getRow(i).get(0));
        }
    }

    @Test
    void testLastElementAlwaysOne() {
        for (int i = 0; i <= 15; i++) {
            List<Integer> row = solution.getRow(i);
            assertEquals(1, row.get(row.size() - 1));
        }
    }

    @Test
    void testSymmetry() {
        for (int i = 0; i <= 10; i++) {
            List<Integer> row = solution.getRow(i);
            for (int j = 0; j < row.size() / 2; j++) {
                assertEquals(row.get(j), row.get(row.size() - 1 - j));
            }
        }
    }

    @Test
    void testRowSumIsPowerOfTwo() {
        for (int i = 0; i <= 10; i++) {
            int sum = solution.getRow(i).stream().mapToInt(Integer::intValue).sum();
            assertEquals((int) Math.pow(2, i), sum);
        }
    }

    // --- Overflow / large input ---

    @Test
    void testLargeRowLength() {
        List<Integer> row = solution.getRow(33);
        assertEquals(34, row.size());
    }

    @Test
    void testLargeRowFirstAndLast() {
        List<Integer> row = solution.getRow(33);
        assertEquals(1, row.get(0));
        assertEquals(1, row.get(33));
    }

    @Test
    void testLargeRowSymmetry() {
        List<Integer> row = solution.getRow(33);
        for (int j = 0; j < row.size() / 2; j++) {
            assertEquals(row.get(j), row.get(row.size() - 1 - j));
        }
    }
}