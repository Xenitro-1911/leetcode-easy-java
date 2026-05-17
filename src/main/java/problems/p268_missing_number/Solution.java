package problems.p268_missing_number;

import java.util.Arrays;

public class Solution {
    public int missingNumber(int[] nums) {
        int sum=0;
        int l = nums.length;
        for(int i=0;i<l;i++){
            sum = sum + nums[i];
        }
        return ((l*(l+1)/2)) - sum;
    }
}