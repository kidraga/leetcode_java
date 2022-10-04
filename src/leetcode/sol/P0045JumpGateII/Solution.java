package leetcode.sol.P0045JumpGateII;

public class Solution {
    /**
     * Brute force
     * Time: O(2^n)
     */
    public int jump(int[] nums) {
        return jump(0, nums);
    }

    /**
     * Calculate minimum steps needed from give position.
     */
    private int jump(int pos, int[] nums) {
        int n = nums.length;

        // base case: if position is already at or beyond last index, we need only 0 step.
        if (pos >= n - 1) {
            return 0;
        }

        int minSteps = n; // worst case. technically worst case is n-1 steps.
        // try all steps, from 1 to nums[pos]
        for (int i = 1; i <= nums[pos]; i++) {
            minSteps = Math.min(minSteps, 1 + jump(pos + i, nums));
        }

        return minSteps;
    }
}