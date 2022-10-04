package leetcode.sol.P0746MinCostClimbingStairs;

public class Solution {
    public int minCostClimbingStairs(int[] cost) {
        // f(n) = min(f(n-1) + cost[n-1], f(n-2) + cost[n-2])
        int n = cost.length;
        int[] minPay = new int[n + 1];
        minPay[0] = 0;
        minPay[1] = 0;

        for (int i = 2; i <= n; i++) {
            minPay[i] = Math.min(minPay[i-1] + cost[i-1],
                                minPay[i-2] + cost[i-2]);
        }
        return minPay[n];
    }
}
