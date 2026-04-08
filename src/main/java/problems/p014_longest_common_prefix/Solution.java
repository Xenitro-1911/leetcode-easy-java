package problems.p014_longest_common_prefix;

public class Solution {
    public String longestCommonPrefix(String[] strs) {

        // Can you fill in the blanks?
        String shortest = strs[0];
        for(String s : strs){
            if(s.length()<shortest.length()){
                shortest = s;
            }
        }

        int l,j=0;
        l=shortest.length();
        while (j < l) {
            char c = shortest.charAt(j);
            for (int i = 0; i < strs.length; i++) {
                if(strs[i] == shortest) continue;
                if(strs[i].charAt(j) != c){
                    return shortest.substring(0, j);
                }
            }
            j++;
        }
        return shortest.substring(0, j);
    }
}