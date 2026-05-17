package problems.p202_happy_number;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public boolean isHappy(int n) {
        int x = 0;
        List<Integer> num = new ArrayList<>();
        while(true){
            x=0;
            while(n!=0){
                x = x + (n%10)*(n%10);
                n=n/10;
            }
            if(x==1){
                return true;
            }else if(num.contains(x)){
                return false;
            }else{
                n=x;
                num.add(x);
            }
        }
    }
}