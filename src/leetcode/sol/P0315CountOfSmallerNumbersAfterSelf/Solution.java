package leetcode.sol.P0315CountOfSmallerNumbersAfterSelf;

import java.util.LinkedList;
import java.util.List;

/**
 * https://labuladong.github.io/algo/2/21/41/
 */
class Solution {
    private class Pair {
        int val;
        int id;
        public Pair(int val, int id) {
            // 记录数组的元素值
            this.val = val;
            // 记录元素在数组中的原始索引
            this.id = id;
        }
    }

    // merge sort所用的辅助数组
    private Pair[] temp;
    // 记录每个元素后面比自己小的元素个数
    private int[] count;

    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        count = new int[n];
        temp = new Pair[n];
        Pair[] arr = new Pair[n];
        // 记录元素原始的index位置，以便在count数组中更新结果
        for (int i = 0; i < n; i++) {
            arr[i] = new Pair(nums[i], i);
        }
        // merge sort, 记录结果到count中
        sort(arr, 0, n - 1);

        List<Integer> res = new LinkedList<>();
        for (int c : count) res.add(c);
        return res;
    }

    private void sort(Pair[] arr, int lo, int hi) {
        if (lo == hi) return;
        int mid = lo + (hi - lo) / 2;
        sort(arr, lo, mid);
        sort(arr, mid + 1, hi);
        merge(arr, lo, mid, hi);
    }

    private void merge(Pair[] arr, int lo, int mid, int hi) {
        for (int i = lo; i <= hi; i++) {
            temp[i] = arr[i];
        }

        int i = lo, j = mid + 1;
        for (int p = lo; p <= hi; p++) {
            if (i == mid + 1) {
                arr[p] = temp[j];
                j++;
            } else if (j == hi + 1) {
                arr[p] = temp[i];
                i++;
                // 更新count数组
                count[arr[p].id] += j - mid - 1;
            } else if (temp[i].val > temp[j].val) {
                arr[p] = temp[j];
                j++;
            } else {
                arr[p] = temp[i];
                i++;
                // 更新count数组
                count[arr[p].id] += j - mid - 1;
            }
        }
    }
}
