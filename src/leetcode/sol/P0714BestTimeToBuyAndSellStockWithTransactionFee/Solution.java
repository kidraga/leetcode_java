package leetcode.sol.P0714BestTimeToBuyAndSellStockWithTransactionFee;

class Solution {
    private int[] prices;
    private int fee;
    private int[][] memo;
    public int maxProfit(int[] prices, int fee) {
        /**
         * dp(i, holding) = max profit at ith day
         * option 1: sell, only if holding is true. dp(i-1, 1) + prices[i] - fee
         * option 2: buy, only if not holding. dp(i-1, 0) - prices[i]
         * option 3: do nothing. dp(i-1, holding)
         * dp(i, holding) = max(option1, option2, option3)
         *
         * dp(0, 0) = 0
         * dp(0, 1) = -prices[0]
         */

        int n = prices.length;
        memo = new int[n][2];
        this.prices = prices;
        this.fee = fee;

        return dp(0, 0);
    }

    private int dp(int i, int holding) {
        if (i == this.prices.length) {
            return 0;
        }

        if (memo[i][holding] == 0) {
            // option 1: do nothing
            int doNothing = dp(i + 1, holding);

            // option 2: buy or sell
            int doSomething;
            if (holding == 1) {
                doSomething = prices[i] - fee + dp(i + 1, 0);
            } else {
                doSomething = -prices[i] + dp(i + 1, 1);
            }
            memo[i][holding] = Math.max(doNothing, doSomething);
        }
        return memo[i][holding];
    }
}
