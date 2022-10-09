package leetcode.sol.P0034FindFirstAndLastPositionOfElementInSortedArray;

/**
 * https://labuladong.github.io/algo/2/20/29/
 */
public class Solution {
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
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }
        }
        if (left == nums.length) return -1;
        return nums[left] == target ? left : -1;
    }

    private int right_bound(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                left = mid + 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }
        }

        if (left - 1 < 0) return -1;
        return nums[left - 1] == target ? left - 1 : -1;
    }
}
