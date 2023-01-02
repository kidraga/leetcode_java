package leetcode.sol.P1438LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit;

import java.util.PriorityQueue;

/**
 * My solution. There should be a way to avoid using PriorityQueue
 * so it can be faster
 * Maybe monotonic queue
 */
class Solution {
    public int longestSubarray(int[] nums, int limit) {
        int left = 0;
        int maxValue = nums[0];
        int minValue = nums[0];
        int right = 0;
        int len = 0;

        PriorityQueue<Integer> increaseQueue = new PriorityQueue<>(); // min
        PriorityQueue<Integer> decreaseQueue = new PriorityQueue<>((a, b) -> b - a); // max

        while (right < nums.length) {
            maxValue = Math.max(maxValue, nums[right]);
            minValue = Math.min(minValue, nums[right]);
            increaseQueue.offer(nums[right]);
            decreaseQueue.offer(nums[right]);

            while (Math.abs(maxValue - minValue) > limit) {
                int value = nums[left];
                left++;
                increaseQueue.remove(value);
                decreaseQueue.remove(value);
                maxValue = decreaseQueue.peek();
                minValue = increaseQueue.peek();
            }
            len = Math.max(len, right - left + 1);
            right++;
        }
        return len;
    }
}
