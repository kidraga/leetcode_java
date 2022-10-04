package leetcode.sol.P0075SortColors;

public class Solution {
    public void sortColors(int[] nums) {
        int zeroEnd = 0; // index of first number that != 0
        int twoStart = nums.length - 1; // index of number before 2
        int i = zeroEnd;
        while (i <= twoStart) {
            if (nums[i] == 0) {
                swap(nums, i, zeroEnd);
                zeroEnd++;
                i++;
            } else if (nums[i] == 2) {
                swap(nums, i, twoStart);
                twoStart--;
                // note: you shouldn't move i in this case
            } else {
                i++;
            }
        }
    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }

}
