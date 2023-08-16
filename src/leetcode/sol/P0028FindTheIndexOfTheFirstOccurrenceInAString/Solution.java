package leetcode.sol.P0028FindTheIndexOfTheFirstOccurrenceInAString;

class Solution {
    public int strStr(String haystack, String needle) {
        if (needle == null) return 0;
        if (haystack == null) return -1;
        if (haystack.length() < needle.length()) return -1;

        for (int i = 0; i < haystack.length() - needle.length() + 1; i++) {

            if (haystack.charAt(i) == needle.charAt(0)) {
                // found potential
                int p1 = i;
                int p2 = 0;
                while (p2 < needle.length() && haystack.charAt(p1) == needle.charAt(p2)) {
                    p1++;
                    p2++;
                }
                if (p2 == needle.length()) {
                    // found
                    return i;
                }
            }
        }

        return -1;
    }
}
