package leetcode.sol.P0503NextGreaterElementII;

import java.util.Stack;

/**
 * https://labuladong.github.io/algo/2/23/63/
 */
class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Stack<Integer> s = new Stack<>();
        // 数组长度加倍模拟环形数组
        for (int i = 2 * n - 1; i >= 0; i--) {
            // i要求模
            while (!s.isEmpty() && s.peek() <= nums[i % n]) {
                s.pop();
            }

            res[i % n] = s.isEmpty() ? -1 : s.peek();
            s.push(nums[i % n]);
        }
        return res;
    }
}
