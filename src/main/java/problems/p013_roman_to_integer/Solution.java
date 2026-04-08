package problems.p013_roman_to_integer;

public class Solution {

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
            } else if (romanArr[s.charAt(j)] >= romanArr[s.charAt(j+1)]) {
                num += romanArr[s.charAt(j)];
            } else {
                num -= romanArr[s.charAt(j)];
            }
        }

        return num;
    }
}
