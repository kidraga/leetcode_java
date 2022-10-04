package leetcode.sol.P0518CoinChangeII;

/**
 * Solution2的优化。
 * 从dp的公式可以看出来dp[i][j]只取决于dp[i-1][j],dp[i][j-coins[i]].
 * 就是二维dp表的dp[i][j]正上方的值，和同一行左边的值。所以可以简化为1维dp
 */
public class Solution3 {
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i-coin];
            }
        }
        return dp[amount];
    }
}
