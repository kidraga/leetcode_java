package leetcode.sol.P0198HouseRobber;

import java.util.HashMap;

/**
 * Top-down approach
 * Time: O(N)
 * Space: O(N)
 */
class Solution {
    private HashMap<Integer, Integer> memo = new HashMap<>();
    private int[] nums;
    public int rob(int[] nums) {
        // dp[i] max money you can get when you get to house i
        // dp[0] = num[0]
        // dp[1] = max(num[0], num[1])
        // we have two options at ith house:
        // 1. we don't rob ith house, then we can get dp(i-1) money
        // 2. we rob ith house, we get nums[i] money + what we robbed before.
        // We can only get this if we didn't rob the previous house,
        // so what we get at this house is nums[i] + dp[i - 2]
        // so dp[i] = max(dp(i-1), nums[i] + dp[i-2])
        this.nums = nums;
        return dp(nums.length - 1);
    }

    private int dp(int i) {
        // base case
        if (i == 0) return nums[0];
        if (i == 1) return Math.max(nums[0], nums[1]);

        if (!memo.containsKey(i)) {
            memo.put(i, Math.max(dp(i - 1), dp(i - 2) + nums[i]));
        }
        return memo.get(i);
    }
}
