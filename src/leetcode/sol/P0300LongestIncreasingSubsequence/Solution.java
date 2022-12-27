package leetcode.sol.P0300LongestIncreasingSubsequence;

import java.util.Arrays;

class Solution {
    public int lengthOfLIS(int[] nums) {
        // dp[i] max length of increasing array that ends with nums[i]
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int longest = 0;
        for (int length : dp) {
            longest = Math.max(longest, length);
        }
        return longest;
    }
}
