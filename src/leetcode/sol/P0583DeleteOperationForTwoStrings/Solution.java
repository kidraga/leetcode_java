package leetcode.sol.P0583DeleteOperationForTwoStrings;

/**
 * dp[i][j] := word1[0:i]和word2[0:j]需要删几个char才能相同
 * dp[i][j]取决于dp[i-1][j-1]
 * if word1[i] == word2[j], dp[i][j] = dp[i-1][j-1]
 * if word1[i] != word2[j], dp[i][j] = min(dp[i-1][j], dp[i][j-1]) + 1
 * 我们可以选择删除word1[i], 那么答案就是1 + dp[i-1][j]
 * 同理也可以选择删除word2[j], 对应答案就是1 + dp[i][j-1]
 * 综上, 最终答案就是上面二者取最小
 *
 * base case: dp[0][0]
 */
class Solution {
    private String word1;
    private String word2;
    private int[][] memo;
    public int minDistance(String word1, String word2) {
        this.word1 = word1;
        this.word2 = word2;
        memo = new int[word1.length()][word2.length()];

        return dp(word1.length() - 1, word2.length() - 1);
    }

    private int dp(int p1, int p2) {
        // base case: dp[0][0]
        // if word1[0] == word2[0], then result = 0, 不需要删除
        // if not, 选择删除一个, 例如word1[0], 然后答案就是dp[-1][0]
        // 也就是word1是空string, 那么此时word2有几个字符就需要删几个, 就是说答案就是word2此时的长度
        // 同理, 如果选择删除word2[0], 答案就是word1的长度
        if (p1 < 0) {
            return p2 < 0 ? 0 : p2 + 1;
        }
        if (p2 < 0) {
            return p1 < 0 ? 0 : p1 + 1;
        }
        if (memo[p1][p2] != 0) return memo[p1][p2];

        int result = 0;
        if (word1.charAt(p1) == word2.charAt(p2)) {
            result = dp(p1 - 1, p2 - 1);
        } else {
            result = Math.min(dp(p1 - 1, p2), dp(p1, p2 - 1)) + 1;
        }
        memo[p1][p2] = result;
        return result;
    }
}
