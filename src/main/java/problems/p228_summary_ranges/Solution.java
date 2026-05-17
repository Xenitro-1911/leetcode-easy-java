package problems.p228_summary_ranges;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        int len = nums.length;

        if (len == 0) return result;

        // Single-pass sliding pointer strategy
        for (int i = 0; i < len; i++) {
            int start = nums[i];

            // Move forward as long as consecutive numbers increase by exactly 1
            // i + 1 < len prevents index out of bounds
            // nums[i + 1] == nums[i] + 1 prevents integer overflow complications
            while (i + 1 < len && nums[i + 1] == nums[i] + 1) {
                i++;
            }

            // Build the string dynamically using StringBuilder
            StringBuilder sb = new StringBuilder();
            if (start == nums[i]) {
                sb.append(start);
            } else {
                sb.append(start).append("->").append(nums[i]);
            }

            result.add(sb.toString());
        }

        return result;
    }
}