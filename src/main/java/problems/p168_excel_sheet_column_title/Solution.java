package problems.p168_excel_sheet_column_title;

public class Solution {
    public String convertToTitle(int columnNumber) {
        StringBuilder s = new StringBuilder();
        if(columnNumber==0){
            return "";
        }

        while(columnNumber!=0){

            int x = columnNumber%26;
            if (x == 0) x = 26;// Use 26.0 to ensure double division
            s.append((char)(x+64)); // Correct casting syntax
            columnNumber = (columnNumber-1)/26;
        }
        return s.reverse().toString();
    }
}