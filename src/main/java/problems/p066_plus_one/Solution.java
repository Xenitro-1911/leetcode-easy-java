package problems.p066_plus_one;

public class Solution {
    public int[] plusOne(int[] digits) {
        int l = digits.length;
        boolean z=true;
        int sum=1;
        for(int i=0;i<l;i++){
            if(digits[i]!=9){
                z=false;
            }
        }
        if(z==true){
            int x[]=new int[l+1];
            x[0]=1;
            for(int i=1;i<l+1;i++){
                x[i]=0;
            }
            return x;
        }else{
            for(int i=l-1;i>=0;i--){
                if(digits[i]==9){
                    digits[i]=0;
                }else{
                    int temp = digits[i];
                    digits[i]=temp+sum;
                    break;
                }
            }
            return digits;
        }
    }
}