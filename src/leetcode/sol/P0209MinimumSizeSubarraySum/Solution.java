package leetcode.sol.P0209MinimumSizeSubarraySum;

/**
 * two pointer同向移动
 */
public class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int ans = Integer.MAX_VALUE;
        int sum = 0;
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            while (sum >= target) {
                ans = Math.min(ans, right + 1 - left);
                sum -= nums[left];
                left++;
            }
        }
        return ans != Integer.MAX_VALUE ? ans : 0;
    }
}
