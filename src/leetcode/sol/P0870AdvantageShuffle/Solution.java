package leetcode.sol.P0870AdvantageShuffle;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution {
    public int[] advantageCount(int[] nums1, int[] nums2) {
        int n = nums1.length;
        // 给nums2降序排序
        PriorityQueue<int[]> maxpq = new PriorityQueue<>(
                (int[] pair1, int[] pair2) -> { // int[] = {index, val}
                    return pair2[1] - pair1[1];
                }
        );
        for (int i = 0; i < n; i++) {
            maxpq.offer(new int[]{i, nums2[i]});
        }
        // 给nums1升序排序
        Arrays.sort(nums1);

        // nums1[left]是最小值, nums[right]是最大值
        int left = 0, right = n - 1;
        int[] res = new int[n];

        while (!maxpq.isEmpty()) {
            int[] pair = maxpq.poll(); // {index, val}
            // maxval是num2中的最大值，i是对应的index
            int i = pair[0], maxval = pair[1];
            if (maxval < nums1[right]) {
                // 如果nums1[right]能比得过maxval，就用这个值
                res[i] = nums1[right];
                right--;
            } else {
                // 如果比不过，就用最小的值来消耗掉maxval
                res[i] = nums1[left];
                left++;
            }
        }
        return res;

    }
}
