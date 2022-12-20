package leetcode.sol.P0496NextGreaterElementI;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * https://labuladong.github.io/algo/2/23/63/
 */
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        // 记录nums2中每个元素的下一个更大元素
        int[] greater = nextGreaterElement(nums2);
        // 转化成映射：元素x -> x的下一个更大元素
        Map<Integer, Integer> valueToGreater = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            valueToGreater.put(nums2[i], greater[i]);
        }

        // nums1是nums2的子集，所以根据valueToGreater可以得到结果
        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            res[i] = valueToGreater.get(nums1[i]);
        }
        return res;
    }

    private int[] nextGreaterElement(int[] nums) {
        int[] res = new int[nums.length];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums[i]) {
                stack.pop();
            }
            res[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[i]);
        }
        return res;
    }
}
