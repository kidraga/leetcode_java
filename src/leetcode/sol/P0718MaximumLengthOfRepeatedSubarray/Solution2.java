package leetcode.sol.P0718MaximumLengthOfRepeatedSubarray;

/**
 * DP
 * dp[i][j]定义：以nums1[i]和nums2[j]开头的重合subarray最长是多少。
 * 如果nums1[i] == nums2[j]，则dp[i][j] = dp[i+1][j+1] + 1
 * answer = max(dp[i][j])
 *
 * Time: O(m*n)
 * Space: O(m*n)
 */
class Solution {
    public int findLength(int[] nums1, int[] nums2) {
        int ans = 0;
        int[][] memo = new int[nums1.length + 1][nums2.length + 1];
        for (int i = nums1.length - 1; i >= 0; i--) {
            for (int j = nums2.length - 1; j >= 0; j--) {
                if (nums1[i] == nums2[j]) {
                    memo[i][j] = memo[i+1][j+1] + 1;
                    ans = Math.max(ans, memo[i][j]);
                }
            }
        }
        return ans;
    }
}
