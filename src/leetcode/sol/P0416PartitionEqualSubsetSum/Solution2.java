
package leetcode.sol.P0416PartitionEqualSubsetSum;

/**
 * 从solution1观察到, dp[i][j]都是通过上一行dp[i-1][...]得来的, 之前的数据都不会再使用了
 * 所以可以压缩dp的表格, 只保留一维
 */
class Solution2 {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) return false;

        int halfSum = sum / 2;
        int n = nums.length;
        boolean[] dp = new boolean[halfSum + 1];
        dp[0] = true;

        for (int i = 0; i < n; i++) {
            for (int j = halfSum; j >= 0; j--) {
                if (j - nums[i] >= 0) {
                    dp[j] = dp[j] || dp[j - nums[i]];
                }
            }
        }
        return dp[halfSum];
    }
}
