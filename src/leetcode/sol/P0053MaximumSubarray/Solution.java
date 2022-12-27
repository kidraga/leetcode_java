package leetcode.sol.P0053MaximumSubarray;

class Solution {
    public int maxSubArray(int[] nums) {
        int best = Integer.MIN_VALUE;
        int current = 0;
        for (int num : nums) {
            current = Math.max(current + num, num);
            best = Math.max(best, current);
        }
        return best;
    }
}
