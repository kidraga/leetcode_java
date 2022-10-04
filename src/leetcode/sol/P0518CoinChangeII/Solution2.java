package leetcode.sol.P0518CoinChangeII;

/**
 * https://leetcode.com/problems/coin-change-ii/discuss/99212/Knapsack-problem-Java-solution-with-thinking-process-O(nm)-Time-and-O(m)-Space
 */
public class Solution2 {
    private int[] coins;
    public int change(int amount, int[] coins) {
        /**
         * dp(i, amount) = total number of combinations to make amount using ONLY coins before coins[i]
         * dp(0, amount) = using no coin
         * dp(0, 0) = 1, dp(0, amount) = 0;
         * dp(i, amount) = dp(i-1, amount) + dp(i, amount - coins[i])
         */
        int[][] dp = new int[coins.length + 1][amount + 1]; // +1 since both num coins or amount can be 0
        dp[0][0] = 1;

        for (int i = 1; i <= coins.length; i++) {
            dp[i][0] = 1;
            for (int target = 1; target <= amount; target++) {
                dp[i][target] = dp[i-1][target] + (target - coins[i-1] >= 0 ? dp[i][target - coins[i-1]] : 0);
            }
        }
        return dp[coins.length][amount];
    }
}
