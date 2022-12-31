package leetcode.sol.P1696JumpGameVI;

import java.util.PriorityQueue;

class Solution {
    public int maxResult(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        pq.add(new int[]{nums[0], 0}); // {score[i], i}

        for (int i = 1; i < n; i++) {
            // if there is higher scores but it's index is even ealier than i - k
            // remove them.
            // So the max score must be within i - k
            while (pq.peek()[1] < i - k) {
                pq.remove();
            }
            dp[i] = nums[i] + dp[pq.peek()[1]];
            pq.offer(new int[]{dp[i], i});
        }
        return dp[n - 1];
    }
}
