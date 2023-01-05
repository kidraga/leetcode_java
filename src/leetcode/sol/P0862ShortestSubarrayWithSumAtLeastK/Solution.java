package leetcode.sol.P0862ShortestSubarrayWithSumAtLeastK;

import java.util.Deque;
import java.util.LinkedList;

class Solution {
    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        long[] prefixSum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i+1] = prefixSum[i] + (long) nums[i];
        }

        // we want smallest y-x such that prefixSum[y] - prefixSum[x] >= k
        int ans = n + 1;
        Deque<Integer> monoQueue = new LinkedList<>();

        for (int right = 0; right < prefixSum.length; right++) {
            while (!monoQueue.isEmpty() && prefixSum[right] <= prefixSum[monoQueue.getLast()]) {
                monoQueue.removeLast();
            }
            while (!monoQueue.isEmpty() && prefixSum[right] >= prefixSum[monoQueue.getFirst()] + k) {
                ans = Math.min(ans, right - monoQueue.removeFirst());
            }

            monoQueue.addLast(right);
        }

        return ans < n + 1 ? ans : -1;
    }
}
