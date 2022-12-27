package leetcode.sol.P0493ReversePairs;

class Solution {
    private int[] temp;
    private int count = 0;
    public int reversePairs(int[] nums) {
        sort(nums);
        return count;
    }

    private void sort(int[] nums) {
        temp = new int[nums.length];
        sort(nums, 0, nums.length - 1);
    }

    private void sort(int[] nums, int lo, int hi) {
        if (lo == hi) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(nums, lo, mid);
        sort(nums, mid + 1, hi);
        merge(nums, lo, mid, hi);
    }

    private void merge(int[] nums, int lo, int mid, int hi) {
        for (int i = lo; i <= hi; i++) {
            temp[i] = nums[i];
        }

        // 在合并有序数组之前，做一下对比
        for (int i = lo; i <= mid; i++) {
            // 对于左半边的每个nums[i], 都去右半边寻找符合条件的元素
            for (int j = mid + 1; i <= hi; i++) {
                // nums中的元素可能比较大，乘2可能溢出，所以转化成long
                if ((long) nums[i] > (long) nums[j] * 2) {
                    count++;
                }
            }
        }

        // 数组双指针，合并两个有序数组
        int i = lo, j = mid + 1;
        for (int p = lo; p <= hi; p++) {
            if (i == mid + 1) {
                nums[p] = temp[j];
                j++;
            } else if (j == hi + 1) {
                nums[p] = temp[i];
                i++;
            } else if (temp[i] > temp[j]) {
                nums[p] = temp[j];
                j++;
            } else {
                nums[p] = temp[i];
                i++;
            }
        }
    }
}
