package leetcode.sol.P0516LongestPalindromicSubsequence;

import java.util.Arrays;

/**
 * Top bottom
 */
class Solution2 {
    private int[][] memo;
    public int longestPalindromeSubseq(String s) {
        memo = new int[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            Arrays.fill(memo[i], -1);
        }
        return dp(s, 0, s.length() - 1);
    }

    private int dp(String s, int i, int j) {
        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        if (i > j) return 0;
        if (i == j) return 1;

        if (s.charAt(i) == s.charAt(j)) {
            memo[i][j] = dp(s, i + 1, j - 1) + 2;
        } else {
            memo[i][j] = Math.max(dp(s, i + 1, j), dp(s, i, j - 1));
        }
        return memo[i][j];
    }
}
