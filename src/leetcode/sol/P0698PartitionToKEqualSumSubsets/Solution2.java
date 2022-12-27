package leetcode.sol.P0698PartitionToKEqualSumSubsets;

import java.util.Arrays;

/**
 * Wrong
 */
class Solution2 {

    private int[] nums;
    private int k;
    private int targetSum;
    private boolean[] selected;

    public boolean canPartitionKSubsets(int[] nums, int k) {
        int total = 0;
        for (int num : nums) {
            total += num;
        }
        // check if it can be divide to k parts at all
        if (total % k != 0) return false;
        Arrays.sort(nums);

        this.nums = nums;
        this.k = k;
        this.targetSum = total / k;
        this.selected = new boolean[nums.length];

        return backtrack(0, 0, 0);
    }

    private boolean backtrack(
            int numberOfCompletedBucket,
            int currSum,
            int startIndex
    ) {

        if (numberOfCompletedBucket == this.k - 1) {
            return true;
        }

        if (currSum > this.targetSum) {
            return false;
        }

        if (currSum == this.targetSum) {
            return backtrack(numberOfCompletedBucket + 1, 0, 0);
        }

        for (int i = startIndex; i < this.nums.length; i++) {
            if (this.selected[i]) {
                continue;
            }

            this.selected[i] = true;
            if (backtrack(numberOfCompletedBucket, currSum + this.nums[i], i + 1)) {
                return true;
            }
            this.selected[i] = false;
        }
        return false;
    }
}
