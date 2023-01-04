package leetcode.sol.P1004MaxConsecutiveOnesIII;

class Solution {
    public int longestOnes(int[] nums, int k) {
        int left = 0;
        int right = 0;
        int len = 0;

        while (right < nums.length) {
            int num = nums[right];
            if (num == 0) {
                k--;
                // when k is used up, move left pointer to shrink the window
                while (k < 0) {
                    len = Math.max(len, right - left);
                    if (nums[left] == 0) {
                        k++;
                    }
                    left++;
                }
            }
            right++;
        }
        // This will handle the case that there're less than k zeros in the array
        // The right pointer will go to the end and len never gets updated.
        return Math.max(len, right - left);
    }
}
