package leetcode.sol.P02593SumSmaller;

import java.util.Arrays;

class Solution {
    public int threeSumSmaller(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);
        int result = 0;
        for (int i = 0; i < n - 2; i++) {
            int upperBound = target - nums[i];
            int small = i + 1;
            int large = n - 1;
            while (small < large) {
                // nums[small] + nums[large] should be smaller than upper bound
                if (nums[small] + nums[large] < upperBound) {
                    // if it's true, if we fix small, and move large to left, apparently it's still true
                    // any index between small and large is true
                    result += large - small;
                    small++;
                } else {
                    // too large, move right pointer to the left
                    large--;
                }
            }

        }
        return result;
    }
}
