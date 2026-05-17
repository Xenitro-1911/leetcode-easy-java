package problems.p258_add_digits;

public class Solution {
    public int addDigits(int num) {

        if (num == 0) return 0;
        return 1 + (num - 1) % 9;
    }
}