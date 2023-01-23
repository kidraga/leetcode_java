package leetcode.sol.P1143LongestCommonSubsequence;

/**
 * dp定义:
 * dp[i][j] = text1[0:i]和text2[0:j]的最长公共子序列LCS
 * dp[text1.length-1][text2.length-1]就是答案
 *
 * dp[i][j]分几种情况:
 * 如果text1[i]和text2[j]一样, 那么LCS长度应该加1, dp[i][j] = 1 + dp[i-1][j-1]
 * 如果不一样, 那么text1[i]还是有可能和text2前面的部分中的字符一样的, 即text2[0:j-1]的部分一样. 同理text2[j]也可能在text[0:i-1]出现
 * 所以答案就是上面两种情况取最大, dp[i][j] = max(dp[i][j-1], dp[i-1][j])
 *
 * base case: dp[0][0]. 这个可以单独算一下. 不过代码里其实也包括了这种情况
 *
 * 过程中需要考虑一下subproblem是否已经解开了, 即计算dp[i][j]的时候需要的dp表里的数据是否都存在.
 * 通过代码很容易看出这是一个top-down的dp, 在recursive call的时候ij都是不停变小的, 所以subproblem都已经解完了.
 */
class Solution {
    private String text1;
    private String text2;
    private int[][] memo;
    public int longestCommonSubsequence(String text1, String text2) {
        this.text1 = text1;
        this.text2 = text2;
        this.memo = new int[text1.length()][text2.length()];

        return dp(text1.length() - 1, text2.length() - 1);
    }

    private int dp(int p1, int p2) {
        // base case是dp[0][0], 这种情况包括在代码里了
        // 如果text1[0] == text2[0], 则结果是1+dp[-1][-1] = 1
        // 如果不相同, 则等于0
        if (p1 < 0 || p2 < 0) {
            return 0;
        }

        if (memo[p1][p2] > 0) {
            return memo[p1][p2];
        }

        int result = 0;
        if (text1.charAt(p1) == text2.charAt(p2)) {
            result = 1 + dp(p1 - 1, p2 - 1);
            memo[p1][p2] = result;
            return result;
        }
        result =  Math.max(dp(p1 - 1, p2), dp(p1, p2 - 1));
        memo[p1][p2] = result;
        return result;
    }
}
