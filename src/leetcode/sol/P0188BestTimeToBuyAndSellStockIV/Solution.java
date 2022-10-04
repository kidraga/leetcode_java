package leetcode.sol.P0188BestTimeToBuyAndSellStockIV;

public class Solution {
    private int[] prices;
    private int[][][] memo;
    public int maxProfit(int k ,int[] prices) {
        this.prices = prices;
        this.memo = new int[prices.length][k + 1][2];
        return dp(0, k, 0);
    }

    private int dp(int i, int transactionsRemaining, int holding) {
        // base
        if (transactionsRemaining == 0 || i == prices.length) {
            return 0;
        }

        if (memo[i][transactionsRemaining][holding] == 0) {
            // option 1: do nothing
            int doNothing = dp(i + 1, transactionsRemaining, holding);
            // option 2: do something, if we are holding, then sell. if we are not holding, then buy
            int doSomething;

            if (holding == 1) {
                // sell
                doSomething = prices[i] + dp(i + 1, transactionsRemaining - 1, 0);
            } else {
                // buy
                doSomething = - prices[i] + dp(i + 1, transactionsRemaining, 1);
            }

            // recurrence relation. Choose the most profitable option.
            memo[i][transactionsRemaining][holding] = Math.max(doNothing, doSomething);
        }
        return memo[i][transactionsRemaining][holding];
    }
}
