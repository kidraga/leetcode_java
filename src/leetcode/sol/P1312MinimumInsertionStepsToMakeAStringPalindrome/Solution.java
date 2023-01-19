package leetcode.sol.P1312MinimumInsertionStepsToMakeAStringPalindrome;

/**
 * 类似516的思路
 * 假如我已经知道ij间的最少插入数是x, 那么在ij两边各加一个字符, 然后分情况讨论, 就可以求出i-1,j+1的结果
 * 反过来就是dp的定义:
 * 假如s[i] == s[j], dp[i][j] = dp[i + 1][j - 1];
 * 假如s[i] != s[j], 我们可以在左边插入s[j],或右边插入s[i]
 * 或者说: dp[i][j] = min(dp[i+1][j] + 1, dp[i][j-1] + 1)
 *
 * base: dp[i][i] = 0. 一个字符已经是palindrome了,不需要插入
 */
class Solution {
    public int minInsertions(String s) {
        int[][] dp = new int[s.length() + 1][s.length() + 1];

        for (int i = s.length() - 1; i >= 0; i--) {
            dp[i][i] = 0;

            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
                }
            }
        }
        return dp[0][s.length() - 1];
    }
}
