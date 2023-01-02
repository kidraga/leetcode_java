package leetcode.sol.P1438LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit;

import java.util.Deque;
import java.util.LinkedList;

class Solution2 {
    public int longestSubarray(int[] nums, int limit) {
        int left = 0;
        int len = 0;

        Deque<Integer> maxQueue = new LinkedList<>();
        Deque<Integer> minQueue = new LinkedList<>();

        for (int right = 0; right < nums.length; right++) {

            // update maxDeque with new right pointer
            while (!maxQueue.isEmpty() && maxQueue.peekLast() < nums[right]) {
                maxQueue.removeLast();
            }
            maxQueue.addLast(nums[right]);

            // update minDeque with new right pointer
            while (!minQueue.isEmpty() && minQueue.peekLast() > nums[right]) {
                minQueue.removeLast();
            }
            minQueue.addLast(nums[right]);

            while (maxQueue.peekFirst() - minQueue.peekFirst() > limit) {
                if (maxQueue.peekFirst() == nums[left]) maxQueue.pollFirst();
                if (minQueue.peekFirst() == nums[left]) minQueue.pollFirst();
                left++;
            }

            len = Math.max(len, right - left + 1);
        }
        return len;
    }
}
