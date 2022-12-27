package leetcode.sol.P1770MaximumScoreFromPerformingMultiplicationOperations;

/**
 * Top-down with memoization
 * Time: O(M^2), M is length of multipliers
 * Space: O(M^2)
 */
class Solution2 {
    private int[][] memo;
    public int maximumScore(int[] nums, int[] multipliers) {
        memo = new int[multipliers.length][multipliers.length];
        return calculateMaxScore(nums, multipliers, 0, 0);
    }

    private int calculateMaxScore(
            int[] nums,
            int[] multipliers,
            int leftIndex,
            int multiplierIndex
    ) {
        if (multiplierIndex == multipliers.length) {
            return 0;
        }

        int rightIndex = nums.length - 1 - (multiplierIndex - leftIndex);
        int multiplier = multipliers[multiplierIndex];
        if (memo[leftIndex][multiplierIndex] != 0) return memo[leftIndex][multiplierIndex];
        int chooseLeft = multiplier * nums[leftIndex];
        int chooseRight = multiplier * nums[rightIndex];
        int chooseLeftGain = chooseLeft + calculateMaxScore(
                nums,
                multipliers,
                leftIndex + 1,
                multiplierIndex + 1);
        int chooseRightGain = chooseRight + calculateMaxScore(
                nums,
                multipliers,
                leftIndex,
                multiplierIndex + 1);

        int bestScore =  Math.max(chooseLeftGain, chooseRightGain);
        memo[leftIndex][multiplierIndex] = bestScore;
        return bestScore;
    }
}
