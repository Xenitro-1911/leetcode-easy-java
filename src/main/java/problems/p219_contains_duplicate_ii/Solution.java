package problems.p219_contains_duplicate_ii;

import java.util.HashMap;

public class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {

        HashMap<Integer, Integer> x = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (x.containsKey(nums[i])) {
                if (Math.abs(i - x.get(nums[i])) <= k) {
                    return true;
                }
            }
            x.put(nums[i], i);
        }
        return false;
    }
}