package leetcode.sol.P1143LongestCommonSubsequence;

public class Solution {
    private String text1;
    private String text2;
    private int[][] memo;
    public int longestCommonSubsequence(String text1, String text2) {
        this.text1 = text1;
        this.text2 = text2;
        this.memo = new int[text1.length()][text2.length()];

        return dp(text1.length() - 1, text2.length() - 1);
    }

    private int dp(int text1Index, int text2Index) {

        if (text1Index < 0 || text2Index < 0) {
            return 0;
        }

        if (memo[text1Index][text2Index] > 0) {
            return memo[text1Index][text2Index];
        }

        int result = 0;
        if (text1.charAt(text1Index) == text2.charAt(text2Index)) {
            result = 1 + dp(text1Index - 1, text2Index - 1);
            memo[text1Index][text2Index] = result;
            return result;
        }
        result =  Math.max(dp(text1Index - 1, text2Index), dp(text1Index, text2Index - 1));
        memo[text1Index][text2Index] = result;
        return result;
    }
}
