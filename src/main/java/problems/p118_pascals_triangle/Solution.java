package problems.p118_pascals_triangle;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> x = new ArrayList<>();
            for(int i=0;i<numRows;i++){
                List<Integer> y = new ArrayList<>();
                for(int j=0;j<=i;j++){
                    if(j==0||j==i){
                        y.add(1);
                    }else{
                        y.add(x.get(i-1).get(j)+(x.get(i-1)).get(j-1));
                    }
                }
                x.add(y);
            }
            return x;
    }
}