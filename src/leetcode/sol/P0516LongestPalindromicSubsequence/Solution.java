package leetcode.sol.P0516LongestPalindromicSubsequence;

/**
 * dp[i][j]: 以ij为首尾的最长palindrome subsequence
 * if (s[i] == s[j]), dp[i][j] = dp[i+1][j-1] + 2
 * else, dp[i][j] = max(dp[i+1][j], dp[i][j-1])
 *
 * base: dp[i][i] = 1
 */
class Solution {
    public int longestPalindromeSubseq(String s) {
        int[][] dp = new int[s.length()][s.length()];

        for (int i = s.length() - 1; i >= 0; i--) {
            dp[i][i] = 1;

            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][s.length() - 1];
    }
}
