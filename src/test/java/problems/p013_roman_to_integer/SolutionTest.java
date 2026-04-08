package problems.p013_roman_to_integer;

public class SolutionTest {

    public int romanToInt(String s) {
        int[] romanArr = new int[128]; // Size 128 covers all uppercase ASCII letters

        romanArr['I'] = 1;
        romanArr['V'] = 5;
        romanArr['X'] = 10;
        romanArr['L'] = 50;
        romanArr['C'] = 100;
        romanArr['D'] = 500;
        romanArr['M'] = 1000;

        int num = 0;

        for (int j = 0; j < s.length(); j++) {
            if (j == s.length() - 1) {
                num += romanArr[s.charAt(j)];
            } else if (romanArr[s.charAt(j)] >= romanArr[s.charAt(j + 1)]) {
                num += romanArr[s.charAt(j)];
            } else {
                num -= romanArr[s.charAt(j)];
            }
        }

        return num;
    }

    // -------- TESTS --------
    public static void main(String[] args) {
        SolutionTest sol = new SolutionTest();
        int passed = 0;
        int failed = 0;

        // Test cases: {input, expected}
        Object[][] tests = {
                {"III",        3},
                {"IV",         4},
                {"IX",         9},
                {"LVIII",      58},
                {"MCMXCIV",    1994},
                {"XLII",       42},
                {"CDXLIV",     444},
                {"MMMCMXCIX",  3999},
                {"I",          1},
                {"M",          1000},
                {"CM",         900},
                {"XL",         40},
                {"DCCC",       800},
        };

        for (Object[] test : tests) {
            String input  = (String) test[0];
            int expected  = (int)    test[1];
            int result    = sol.romanToInt(input);
            boolean ok    = result == expected;

            System.out.printf("%-15s expected: %-6d got: %-6d %s%n",
                    input, expected, result, ok ? "✅" : "❌ FAIL");

            if (ok) passed++; else failed++;
        }

        System.out.println("\n--- Results ---");
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);
    }
}