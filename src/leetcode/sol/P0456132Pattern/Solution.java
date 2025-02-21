package leetcode.sol.P0456132Pattern;

import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public boolean find132pattern(int[] nums) {
        int length = nums.length;
        if (length < 3) return false;

        Deque<Integer> stack = new ArrayDeque<>(length);
        int third = Integer.MIN_VALUE;

        for (int i = length - 1; i >= 0; i--) {
            if (third > nums[i]) {
                return true;
            }

            while (!stack.isEmpty() && stack.peek() < nums[i]) {
                third = stack.pop();
            }

            stack.push(nums[i]);
        }
        return false;
    }
}
