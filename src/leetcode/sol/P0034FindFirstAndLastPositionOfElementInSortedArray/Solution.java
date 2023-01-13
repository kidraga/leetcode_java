package leetcode.sol.P0034FindFirstAndLastPositionOfElementInSortedArray;

/**
 * https://labuladong.github.io/algo/2/20/29/
 */
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int left = left_bound(nums, target);
        if (left == -1) return new int[]{-1, -1};
        int right = right_bound(nums, target);
        return new int[]{left, right};
    }

    private int left_bound(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                // 找左边界,所以nums[mid] == target的时候把right放过来,左边界一定还在左边
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }
        }
        // 如果target比所有数都大,最后一次区间就是[n-1,n-1],最后left==n
        // nums[left]是越界的,所以优先查这种edge case
        if (left == nums.length) return -1;
        // 如果target不在nums里,最后会停在left==right+1,但nums[left] != target
        return nums[left] == target ? left : -1;
    }

    private int right_bound(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                // 找右边界,所以相等的时候把left拉过来,右边界一定还在右边
                left = mid + 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }
        }

        // 如果target比所有数都小,left全程都不会动,还停在0.
        // nums[left - 1]是越界的,所以优先查这种edge case
        if (left - 1 < 0) return -1;
        // 如果target不在nums里
        return nums[left - 1] == target ? left - 1 : -1;
    }
}
