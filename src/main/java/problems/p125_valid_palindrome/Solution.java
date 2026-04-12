package problems.p125_valid_palindrome;

public class Solution {

    public boolean isPalindrome(String s) {
        if(s.isEmpty())return true;

        s=s.toLowerCase();
        StringBuilder x = new StringBuilder();
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(Character.isLetterOrDigit(c)){
                x.append(c);
            }
        }
            int left = 0;
            int right = x.length() - 1;

            while (left < right) {
                if (x.charAt(left) != x.charAt(right)) {
                    return false; // Fast exit on first mismatch
                }
                left++;
                right--;
            }
            return true;
        }
    }