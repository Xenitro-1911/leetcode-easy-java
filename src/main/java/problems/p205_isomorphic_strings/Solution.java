package problems.p205_isomorphic_strings;

class Solution {
    public boolean isIsomorphic(String s, String t) {
        // Size 256 covers all standard ASCII characters
        int[] mapS = new int[256];
        int[] mapT = new int[256];

        int len = s.length();

        for (int i = 0; i < len; i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);

            // If the last recorded positions don't match, they aren't isomorphic
            if (mapS[c1] != mapT[c2]) {
                return false;
            }

            // Store the next position (i + 1) so that the default 0 
            // properly represents an unvisited character
            mapS[c1] = i + 1;
            mapT[c2] = i + 1;
        }

        return true;
    }
}
