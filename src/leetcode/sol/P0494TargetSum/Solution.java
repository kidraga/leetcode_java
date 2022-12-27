package leetcode.sol.P0494TargetSum;

class Solution {
    private int result = 0;
    public int findTargetSumWays(int[] nums, int target) {
        backtrack(nums, target, 0, 0);
        return result;
    }

    private void backtrack(int[] nums, int target, int startIndex, int currSum) {
        if (startIndex == nums.length) {
            if (currSum == target) {
                result++;
            }
            return;
        }

        // try +
        int newSum = currSum + nums[startIndex];
        backtrack(nums, target, startIndex + 1, newSum);
        // try -
        newSum = currSum - nums[startIndex];
        backtrack(nums,target, startIndex + 1, newSum);
    }
}
