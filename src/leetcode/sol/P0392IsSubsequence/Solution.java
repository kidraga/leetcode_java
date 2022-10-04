package leetcode.sol.P0392IsSubsequence;

public class Solution {
    public boolean isSubsequence(String s, String t) {
        if (s.isEmpty()) return true;
        int posS = 0;
        int posT = 0;
        while (posS < s.length() && posT < t.length()) {
            char charFromS = s.charAt(posS);
            char charFromT = t.charAt(posT);

            if (charFromS == charFromT) {
                if (posS == s.length() - 1) {
                    return true;
                }
                posS++;
            }
            posT++;
        }
        return false;

    }
}
