package problems.p171_excel_sheet_column_number;

class Solution {
    public int titleToNumber(String columnTitle) {
        int result = 0;
        for (int i = 0; i < columnTitle.length(); i++) {
            // Standard base-conversion: result = result * base + current_digit
            // 'A' is 1, so (char - 'A' + 1) gives us the value
            result = result * 26 + (columnTitle.charAt(i) - 'A' + 1);
        }
        return result;
    }
}
