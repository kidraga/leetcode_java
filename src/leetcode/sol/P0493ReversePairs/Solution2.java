package leetcode.sol.P0493ReversePairs;

class Solution2 {
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

        // 进行效率优化，维护左闭右开区间[mid+1, end)中的元素乘2小于nums[i]
        // 为什么end是开区间？因为这样的话可以保证初始区间[mid+1, mid+1)是一个空区间
        int end = mid + 1;
        for (int i = lo; i <= mid; i++) {
            // nums中的元素可能较大，乘2可能溢出，所以转化成long
            while (end <= hi && (long)nums[i] > (long)nums[end] * 2) {
                end++;
            }
            count += end - (mid + 1);
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
