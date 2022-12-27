package leetcode.sol.P0309BestTimeToBuyAndSellStockWithCooldown;

class Solution {
    private int[] prices;
    private int[][][] memo;
    public int maxProfit(int[] prices) {
        this.prices = prices;
        this.memo = new int[prices.length][2][2];

        return dp(0, 0, 0);
    }

    private int dp(int i, int holding, int cooldown) {
        if (i == prices.length) {
            return 0;
        }

        if (memo[i][holding][cooldown] == 0) {
            // option 1: do nothing
            int doNothing = dp(i + 1, holding, 0);

            // option 2: buy or sell
            int doSomething;
            if (holding == 1) {
                // no need to check cooldown since it's only after selling
                // sell
                doSomething = prices[i] + dp(i + 1, 0, 1);
            } else {
                if (cooldown == 1) {
                    doSomething = dp(i + 1, holding, 0);
                } else {
                    // buy
                    doSomething = -prices[i] + dp(i + 1, 1, 0);
                }
            }
            memo[i][holding][cooldown] = Math.max(doNothing, doSomething);
        }
        return memo[i][holding][cooldown];
    }
}
