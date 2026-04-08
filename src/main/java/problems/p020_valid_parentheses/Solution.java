package problems.p020_valid_parentheses;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
    public boolean isValid(String s) {
        // 1. Quick check: odd length strings can never be valid
        if (s.length() % 2 != 0) return false;

        Deque<Character> stack = new ArrayDeque<>();

        // 2. Use a char array to avoid repeated .charAt() method calls
        for (char c : s.toCharArray()) {
            // 3. Logic Optimization: Push the EXPECTED closing bracket
            // This removes the need for a Map and simplifies the "else" check
            if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            } else if (c == '[') {
                stack.push(']');
            }
            // 4. If it's a closing bracket, it MUST match the top of the stack
            else if (stack.isEmpty() || stack.pop() != c) {
                return false;
            }
        }

        return stack.isEmpty();
    }
}
