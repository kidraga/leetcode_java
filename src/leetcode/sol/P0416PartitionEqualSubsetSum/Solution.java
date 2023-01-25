package leetcode.sol.P0416PartitionEqualSubsetSum;

/**
 * 目标是从nums里找到几个数, 总和是sum(nums)的一半就行, 剩下的自然是另一半
 *
 * dp[i]: nums[0:i]是否能找到几个数的和是half sum
 * 对于每个数, 我们都可以选择使用或者不使用.
 * 如果使用, 那我们要求的就是: nums[0:i-1]里取一些数, 加上nums[i]的和, 正好是half sum
 * 也就是说halfSum - nums[i]是新的target. dp[i]with target==halfSum取决于dp[i-1]with target==halfSum-nums[i]
 * 可以看出其实target也是状态变量之一. 那么这个dp应该是二维的
 * dp[i][target]: nums[0:i]是否能找到几个数的和是target
 * dp[nums.length-1][halfSum]就是答案
 * dp[i][target]:
 * option 1: 使用nums[i]. dp[i][target] = dp[i-1][target - nums[i]]
 * option 2: 不使用nums[i]. dp[i][target] = dp[i-1][target]
 */
class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) return false;

        int halfSum = sum / 2;
        int n = nums.length;
        boolean[][] dp = new boolean[n + 1][halfSum + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i <= n; i++) { // i从1开始, i=0表示什么都不选
            for (int j = 1; j <= halfSum; j++) {
                if (j - nums[i - 1] < 0) {
                    // target 比当前值还要小, 不选
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 两种option, 选或不选, 有一个是true就行
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[n][halfSum];
    }
}
