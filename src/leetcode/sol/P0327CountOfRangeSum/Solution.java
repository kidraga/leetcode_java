package leetcode.sol.P0327CountOfRangeSum;

/**
 * https://labuladong.github.io/algo/2/21/41/
 */
class Solution {
    private int lower;
    private int upper;
    private int count = 0;
    private long[] temp;
    public int countRangeSum(int[] nums, int lower, int upper) {
        this.lower = lower;
        this.upper = upper;
        // 构建preSum, int可能溢出，所以用long存储
        long[] preSum = new long[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            preSum[i + 1] = (long) nums[i] + preSum[i];
        }
        // 对preSum进行归并排序
        sort(preSum);
        return count;
    }

    private void sort(long[] nums) {
        temp = new long[nums.length];
        sort(nums, 0, nums.length - 1);
    }

    private void sort(long[] nums, int lo, int hi) {
        if (lo == hi) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(nums, lo, mid);
        sort(nums, mid + 1, hi);
        merge(nums, lo, mid, hi);
    }

    private void merge(long[] nums, int lo, int mid, int hi) {
        for (int i = lo; i <= hi; i++) {
            temp[i] = nums[i];
        }

        // 在合并有序数组之前做些计算（这段代码会超时）
//        for (int i = lo; i <= mid; i++) {
//            for (int j = mid + 1; j <= hi; j++) {
//                // 寻找符合条件的nums[j]
//                long delta = nums[j] - nums[i];
//                if (delta <= upper && delta >= lower) {
//                    count++;
//                }
//            }
//        }

        // 优化效率
        // 维护左闭右开区间[start, end)中的元素和nums[i]的差在[lower, upper]中
        int start = mid + 1, end = mid + 1;
        for (int i = lo; i <= mid; i++) {
            // 如果nums[i]对应的区间是[start, end),
            // 那么nums[i+1]对应的区间一定会整体右移，类似滑动窗口
            while (start <= hi && nums[start] - nums[i] < lower) {
                start++;
            }
            while (end <= hi && nums[end] - nums[i] <= upper) {
                end++;
            }
            count += end - start;
        }

        // 双指针，合并两个有序数组
        int i = lo, j = mid + 1;
        for (int p = lo; p <= hi; p++) {
            if (i == mid + 1) {
                nums[p] = temp[j++];
            } else if (j == hi + 1) {
                nums[p] = temp[i++];
            } else if (temp[i] > temp[j]) {
                nums[p] = temp[j++];
            } else {
                nums[p] = temp[i++];
            }
        }
    }
}
