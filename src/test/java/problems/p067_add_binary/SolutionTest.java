package problems.p067_add_binary;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Solution {
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;

        while (i >= 0 || j >= 0 || carry > 0) {
            int sum = carry;
            if (i >= 0) sum += a.charAt(i--) - '0';
            if (j >= 0) sum += b.charAt(j--) - '0';
            sb.append(sum % 2);
            carry = sum / 2;
        }

        return sb.reverse().toString();
    }
}

public class SolutionTest {

    private final Solution solution = new Solution();

    @Test
    void testExample1() {
        // "11" + "1" = "100" (3 + 1 = 4)
        assertEquals("100", solution.addBinary("11", "1"));
    }

    @Test
    void testExample2() {
        // "1010" + "1011" = "10101" (10 + 11 = 21)
        assertEquals("10101", solution.addBinary("1010", "1011"));
    }

    @Test
    void testBothZero() {
        assertEquals("0", solution.addBinary("0", "0"));
    }

    @Test
    void testOneZero() {
        assertEquals("1", solution.addBinary("1", "0"));
        assertEquals("1", solution.addBinary("0", "1"));
    }

    @Test
    void testCarryChain() {
        // "111" + "1" = "1000" (7 + 1 = 8)
        assertEquals("1000", solution.addBinary("111", "1"));
    }

    @Test
    void testDifferentLengths() {
        // "110010" + "10111" = "1001001" (50 + 23 = 73)
        assertEquals("1001001", solution.addBinary("110010", "10111"));
    }

    @Test
    void testSingleBits() {
        assertEquals("10", solution.addBinary("1", "1"));
    }

    @Test
    void testLargeNumbers() {
        // "1111111" + "1" = "10000000" (127 + 1 = 128)
        assertEquals("10000000", solution.addBinary("1111111", "1"));
    }
}