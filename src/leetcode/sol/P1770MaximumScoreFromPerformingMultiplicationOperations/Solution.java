package leetcode.sol.P1770MaximumScoreFromPerformingMultiplicationOperations;

/**
 * Top-down solution
 * Time: O(M^2), M is length of multipliers
 * Space: O(1)
 */
class Solution {

    public int maximumScore(int[] nums, int[] multipliers) {
        return calculateMaxScore(nums, multipliers, 0, nums.length - 1, 0);
    }

    private int calculateMaxScore(
            int[] nums,
            int[] multipliers,
            int leftIndex,
            int rightIndex,
            int multiplierIndex
    ) {
        if (multiplierIndex == multipliers.length) {
            return 0;
        }

        int multiplier = multipliers[multiplierIndex];
        int chooseLeft = multiplier * nums[leftIndex];
        int chooseRight = multiplier * nums[rightIndex];
        int chooseLeftGain = chooseLeft + calculateMaxScore(
                nums,
                multipliers,
                leftIndex + 1,
                rightIndex,
                multiplierIndex + 1);
        int chooseRightGain = chooseRight + calculateMaxScore(
                nums,
                multipliers,
                leftIndex,
                rightIndex - 1,
                multiplierIndex + 1);

        return Math.max(chooseLeftGain, chooseRightGain);
    }
}
