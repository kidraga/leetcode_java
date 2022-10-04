package leetcode.sol.P0053MaximumSubarray;

public class Solution2 {
    public int maxSubArray(int[] nums) {
        /** dp(i) = maxSubArray until nums[i], inclusive
         *  dp(i) has to do with dp(i-1) and nums[i]
         *  [-1,-1,10000,-1,-1,1]
         */
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        int max = dp[0];
        for (int i =1; i < n; i++) {
            dp[i] = nums[i] + (dp[i-1] > 0 ? dp[i-1] : 0);
            max = Math.max(max, dp[i]);
        }
        return max;

    }
}
