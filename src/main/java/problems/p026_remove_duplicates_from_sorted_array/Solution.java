package problems.p026_remove_duplicates_from_sorted_array;

public class Solution {

    public int removeDuplicates(int[] nums) {
        int f=1;
        int x=nums[0];
        for(int i=1;i<nums.length;i++){
            if(x!=nums[i]){
                nums[f]=nums[i];
                f++;
            }
            x=nums[i];
        }
        return f;
    }
}