package leetcode.sol.P0698PartitionToKEqualSumSubsets;

public class Solution {

    private int[] nums;
    private int k;
    private int targetSum;
    private int[] sums;

    /**
     * Corrent. But TLE
     */
    public boolean canPartitionKSubsets(int[] nums, int k) {

        int total = 0;
        for (int num : nums) {
            total += num;
        }
        // check if it can be divide to k parts at all
        if (total % k != 0) return false;

        this.nums = nums;
        this.k = k;
        this.targetSum = total / k;
        this.sums = new int[k];

        return backtrack(0);
    }

    private boolean backtrack(int position) {
        // base
        // 注意这里不是position == this.nums.length - 1
        // 不然最后一次进来的时候，最后一个element还没有被加到sums里就要判断allSumsAreEqual了
        if (position == this.nums.length) {
            return allSumsAreEqual() ? true : false;
        }


        int element = this.nums[position];
        for (int i = 0; i < this.k; i++) {
            int sum = this.sums[i];
            if (sum + element > targetSum) {
                continue;
            }

            // put element to each bucket
            sums[i] += element;

            if (backtrack(position + 1)) return true;

            sums[i] -= element;
        }
        return false;
    }

    private boolean allSumsAreEqual() {
        for (int sum : this.sums) {
            if (sum != targetSum) {
                return false;
            }
        }
        return true;
    }
}
