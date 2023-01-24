package leetcode.sol.P0712MinimumASCIIDeleteSumForTwoStrings;

/**
 * dp[i][j]
 * if s1[i] == s2[j], 那么这两个字符不用删, dp[i][j] = min(dp[i-1][j], dp[i][j-1])
 * if s1[i] != s2[j], 我们可以选择删s1[i], 或者s2[j], 或者都删, 对应的dp:
 * 删s1[i]: dp[i-1][j] + ascii(s1[i])
 * 删s2[j]: dp[i][j-1] + ascii(s2[j])
 * 两个都删: dp[i-1][j-1] + ascii(s1[i]) + ascii(s2[j])
 * 上面几种情况取最小就行
 *
 * base case: dp[0][0]
 */
class Solution {
    private String s1;
    private String s2;
    private int[][] memo;
    public int minimumDeleteSum(String s1, String s2) {
        this.s1 = s1;
        this.s2 = s2;
        this.memo = new int[s1.length()][s2.length()];

        return dp(s1.length() - 1, s2.length() - 1);
    }

    private int dp(int p1, int p2) {
        if (p1 < 0) {
            return p2 < 0 ? 0 : asciiSum(s2, 0, p2);
        }
        if (p2 < 0) {
            return p1 < 0 ? 0 : asciiSum(s1, 0, p1);
        }

        if (memo[p1][p2] != 0) return memo[p1][p2];

        int result = 0;
        if (s1.charAt(p1) == s2.charAt(p2)) {
            result = dp(p1 - 1, p2 - 1);
        } else {
            int option1 = dp(p1 - 1, p2) + ascii(s1.charAt(p1));
            int option2 = dp(p1, p2 - 1) + ascii(s2.charAt(p2));
            int option3 = dp(p1 - 1, p2 - 1) + ascii(s1.charAt(p1)) + ascii(s2.charAt(p2));
            result = Math.min(option1, Math.min(option2, option3));
        }
        memo[p1][p2] = result;
        return result;
    }

    private int asciiSum(String word, int start, int end) {
        int result = 0;
        for (int i = start; i <= end; i++) {
            result += ascii(word.charAt(i));
        }
        return result;
    }

    private int ascii(char c) {
        return (int) c;
    }
}
