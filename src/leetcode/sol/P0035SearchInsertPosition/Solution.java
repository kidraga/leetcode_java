package leetcode.sol.P0035SearchInsertPosition;

public class Solution {
    /**
    Edge cases:
    1. target is smaller than all values
    2. target is larger than all values
    3. there's only 1 value in array (covered by 1 and 2)
    4. there's no value in array (impossible, defined in constraints)
    */
    public int searchInsert(int[] nums, int target) {
        
        int left = 0;
        int right = nums.length - 1;
        if (nums[left] > target) {
            return 0;
        } else if (nums[right] < target) {
            return nums.length;
        }
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}
