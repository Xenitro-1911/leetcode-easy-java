package problems.p217_contains_duplicate;

import java.util.HashSet;

public class Solution {
    public boolean containsDuplicate(int[] nums) {
        // Provide an initial capacity to reduce internal map resizing overhead
        HashSet<Integer> x = new HashSet<>(nums.length);

        for (int num : nums) {
            // .add() returns false if the element was already present
            if (!x.add(num)) {
                return true;
            }
        }
        return false;
    }
}
