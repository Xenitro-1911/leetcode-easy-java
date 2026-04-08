package problems.p067_add_binary;

class Solution {
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;

        // One loop to rule them all
        while (i >= 0 || j >= 0 || carry > 0) {
            int sum = carry; // Start with the carry from the previous step

            if (i >= 0) sum += a.charAt(i--) - '0'; // Convert char '1' or '0' to int
            if (j >= 0) sum += b.charAt(j--) - '0';

            sb.append(sum % 2); // Result digit (0 or 1)
            carry = sum / 2;    // New carry (1 if sum was 2 or 3, else 0)
        }

        return sb.reverse().toString();
    }
}
