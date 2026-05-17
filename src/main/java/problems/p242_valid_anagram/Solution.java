package problems.p242_valid_anagram;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length()!=t.length()){
            return false;
        }
        List<Integer> indexes = new ArrayList<>();
        char sc , tc;
        boolean x = true;
        for(int i=0;i<s.length();i++){
            x=false;
            sc=s.charAt(i);
            for(int j=0;j<t.length();j++){
                tc = t.charAt(j);
                if((sc==tc)&&(!indexes.contains(j))){
                    indexes.add(j);
                    x = true;
                    break;
                }
            }
            if(!x){
                return false;
            }
        }
        return true;
    }
}