package leetcode.sol.P0518CoinChangeII;

/**
 * Wrong answer
 * 不能保证组合是唯一的，例如[1,2], 3,那么1+2和2+1都会被算在结果里
 */
public class Solution {
    public int change(int amount, int[] coins) {
        /* dp(i) = total number of combinations to make up amount i
           At each state, we have some options: we can choose the coin that <= current target amount i
           dp(i) = sum(dp(i - valid coins)), where valid coin is (coin <= i)
           dp(0 or negative) = 0, base case
         */

        if (amount < 0) return 0;
        if (amount == 0) return 1;

        int totalCombinations = 0;
        for (int coin : coins) {
            int combinationsForOneChoice = change(amount - coin, coins);
            totalCombinations += combinationsForOneChoice;
        }
        return totalCombinations;
    }
}
