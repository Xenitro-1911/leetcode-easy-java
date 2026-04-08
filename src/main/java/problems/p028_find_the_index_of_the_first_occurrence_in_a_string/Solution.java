package problems.p028_find_the_index_of_the_first_occurrence_in_a_string;

public class Solution {

    public int strStr(String haystack, String needle) {
        if(!haystack.contains(needle)){
            return -1;
        }
        else{
            return haystack.indexOf(needle);
        }
    }
}