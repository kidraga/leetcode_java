package leetcode.sol.P0487MaxConsecutiveOnesII;

class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int left = 0;
        int right = 0;
        boolean hasZero = false;
        int prevZeroIndex = -1;
        int len = 0;

        while (right < nums.length) {
            int num = nums[right];
            if (num == 0) {
                if (hasZero) {
                    len = Math.max(len, right - left);
                    left = prevZeroIndex + 1;
                    prevZeroIndex = right;
                } else {
                    hasZero = true;
                    prevZeroIndex = right;
                }
            }
            right++;
        }
        return Math.max(len, right - left);
    }
}
