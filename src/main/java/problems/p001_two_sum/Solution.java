package problems.p001_two_sum;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    /**
     * Approach: Hash Map (One Pass)
     * For each number, check if its complement (target - num) already exists in the map.
     * If yes, we found our pair. If no, store the number and its index.
     *
     * Time Complexity:  O(n) — single pass through the array
     * Space Complexity: O(n) — hash map stores up to n elements
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>(); // value -> index

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }

            map.put(nums[i], i);
        }

        return new int[]{}; // guaranteed to never reach here per problem constraints
    }
}