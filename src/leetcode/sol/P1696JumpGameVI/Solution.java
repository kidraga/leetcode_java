package leetcode.sol.P1696JumpGameVI;

import java.util.PriorityQueue;

/**
 * DP
 * dp[i]: 到达i位置时的max score
 * dp[i] = max(dp[i-k], ..., dp[i-1]) + nums[i]
 * base case: dp[0] = nums[0]
 * ans = dp[n-1]
 *
 * Time limit exceed
 */
class Solution {
    public int maxResult(int[] nums, int k) {
        int[] dp = new int[nums.length + k];
        // 这一步不能少
        // nums[0]是不可避免算成score的，如果没有这一步，则nums[0]之前的最高分都是0
        // 但如果nums[0]是负数，则nums[0]的分数也是负数
        for (int i = 0; i < k + 1; i++) {
            dp[i] = nums[0];
        }
        for (int i = 1; i < nums.length; i++) { // 同上，从nums[1]开始是因为nums[0]是不可避免的
            PriorityQueue<Integer> pq = new PriorityQueue<>(k, (a, b) -> b - a);
            for (int j = 0; j < k; j++) {
                pq.offer(dp[i + k - j - 1]);
            }
            int prevMaxScore = pq.poll();
            dp[i + k] = nums[i] + prevMaxScore;
            pq.offer(dp[i + k]);
        }

        return dp[dp.length - 1];
    }
}
