package problems.p035_search_insert_position;

public class Solution {

    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid; // Found it!
            } else if (nums[mid] < target) {
                left = mid + 1; // Target is bigger, look right
            } else {
                right = mid - 1; // Target is smaller, look left
            }
        }
        // If we don't find it, 'left' is exactly where it should be inserted
        return left;
    }
}
