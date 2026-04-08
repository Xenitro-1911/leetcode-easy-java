package problems.p058_length_of_last_word;

public class Solution {

    public int lengthOfLastWord(String s) {
        String s2 = s.trim();
        if(!s.contains(" ")){
            return s.length();
        }
        return(s2.substring(s2.lastIndexOf(' ')+1)).length();
    }
}