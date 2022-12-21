package leetcode.sol.P0239SlidingWindowMaximum;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/sliding-window-maximum/description/
 */
class Solution {
    class MonotonicQueue {
        // 双链表，支持头部和尾部增删元素
        // 维护其中的元素自尾部到头部单调递增
        private LinkedList<Integer> maxq = new LinkedList<>();

        // 在尾部添加一个元素n，维护maxq的单调性质
        public void push(int n) {
            // 将前面小于自己的元素都删除
            while (!maxq.isEmpty() && maxq.getLast() < n) {
                maxq.pollLast();
            }
            maxq.addLast(n);
        };
        public int max() {
            // 队头的元素肯定是最大的
            return maxq.getFirst();
        };
        public void pop(int n) {
            if (n == maxq.getFirst()) {
                maxq.pollFirst();
            }
        };
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        MonotonicQueue window = new MonotonicQueue();
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (i < k - 1) {
                // 先把窗口的前k-1填满
                window.push(nums[i]);
            } else {
                // 窗口开始向前滑动
                // 移入新元素
                window.push(nums[i]);
                // 将当前窗口中的最大元素记入结果
                res.add(window.max());
                // 移出最后的元素
                window.pop(nums[i - k + 1]);
            }
        }
        // 将List转化成int[]
        int[] arr = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            arr[i] = res.get(i);
        }
        return arr;
    }
}
