package problems.p278_first_bad_version;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Minimal VersionControl stub for local testing
abstract class VersionControl {
    protected int firstBad;
    public boolean isBadVersion(int version) {
        return version >= firstBad;
    }
}

class SolutionTest {

    private Solution solution;

    // Helper: set which version is the first bad one
    private void setBad(int firstBad) {
        solution = new Solution();
        solution.firstBad = firstBad;
    }

    @BeforeEach
    void setUp() {
        solution = new Solution();
    }

    // --- LeetCode examples ---

    @Test
    void testLeetcodeExample1() {
        // n=5, bad=4 → 4
        solution.firstBad = 4;
        assertEquals(4, solution.firstBadVersion(5));
    }

    @Test
    void testLeetcodeExample2() {
        // n=1, bad=1 → 1
        solution.firstBad = 1;
        assertEquals(1, solution.firstBadVersion(1));
    }

    // --- First version is bad ---

    @Test
    void testFirstVersionBad_smallN() {
        setBad(1);
        assertEquals(1, solution.firstBadVersion(5));
    }

    @Test
    void testFirstVersionBad_largeN() {
        setBad(1);
        assertEquals(1, solution.firstBadVersion(1000));
    }

    // --- Last version is bad ---

    @Test
    void testLastVersionBad_smallN() {
        setBad(5);
        assertEquals(5, solution.firstBadVersion(5));
    }

    @Test
    void testLastVersionBad_largeN() {
        setBad(1000);
        assertEquals(1000, solution.firstBadVersion(1000));
    }

    // --- Middle versions ---

    @Test
    void testMiddleBad_even() {
        setBad(3);
        assertEquals(3, solution.firstBadVersion(6));
    }

    @Test
    void testMiddleBad_odd() {
        setBad(4);
        assertEquals(4, solution.firstBadVersion(7));
    }

    // --- Single version ---

    @Test
    void testSingleVersion_isBad() {
        setBad(1);
        assertEquals(1, solution.firstBadVersion(1));
    }

    // --- Two versions ---

    @Test
    void testTwoVersions_firstBad() {
        setBad(1);
        assertEquals(1, solution.firstBadVersion(2));
    }

    @Test
    void testTwoVersions_secondBad() {
        setBad(2);
        assertEquals(2, solution.firstBadVersion(2));
    }

    // --- Large n overflow safety ---

    @Test
    void testLargeN_firstBadNearMiddle() {
        // Tests safe mid calculation with large values
        int n = Integer.MAX_VALUE / 2;
        setBad(n / 2);
        assertEquals(n / 2, solution.firstBadVersion(n));
    }

    @Test
    void testLargeN_firstBadAtStart() {
        int n = 100000;
        setBad(1);
        assertEquals(1, solution.firstBadVersion(n));
    }

    @Test
    void testLargeN_firstBadAtEnd() {
        int n = 100000;
        setBad(n);
        assertEquals(n, solution.firstBadVersion(n));
    }

    // --- Convergence: left == right ---

    @Test
    void testConvergence_badIsSecondToLast() {
        setBad(4);
        assertEquals(4, solution.firstBadVersion(5));
    }

    @Test
    void testConvergence_badIsSecond() {
        setBad(2);
        assertEquals(2, solution.firstBadVersion(5));
    }
}