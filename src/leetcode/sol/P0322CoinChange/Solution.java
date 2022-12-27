package leetcode.sol.P0322CoinChange;

import java.util.HashMap;
import java.util.Map;

class Solution {
    private int[] coins;
    private Map<Integer, Integer> memo;
    public int coinChange(int[] coins, int amount) {
        this.coins = coins;
        this.memo = new HashMap<>();

        return dp(amount);
    }

    private int dp(int target) {
        // base
        if (target == 0) return 0;
        if (target < 0) return -1;
        if (memo.containsKey(target)) return memo.get(target);

        // dp(target) min num of coins to get target
        int minNumberOfCoins = -1;
        for (int coin : coins) {
            int newTarget = target - coin;
            int coinsNeededForRemaining = dp(newTarget);
            if (coinsNeededForRemaining >= 0) {
                if (minNumberOfCoins == -1) {
                    minNumberOfCoins = coinsNeededForRemaining + 1;
                } else {
                    minNumberOfCoins = Math.min(minNumberOfCoins, coinsNeededForRemaining + 1);
                }
            }
        }

        memo.put(target, minNumberOfCoins);
        return minNumberOfCoins;
    }
}
