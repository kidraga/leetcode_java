package leetcode.sol.P0295FindMedianFromDataStream;

import java.util.PriorityQueue;

/**
 * https://mp.weixin.qq.com/s/oklQN_xjYy--_fbFkd9wMg
 */
class MedianFinder {

    private PriorityQueue<Integer> small;
    private PriorityQueue<Integer> large;

    public MedianFinder() {
        // 小堆顶
        small = new PriorityQueue<>();
        // 大堆顶
        large = new PriorityQueue<>((a, b) -> b - a);
    }

    public void addNumber(int num) {
        if (small.size() >= large.size()) {
            small.offer(num);
            large.offer(small.poll());
        } else {
            large.offer(num);
            small.offer(large.poll());
        }
    }

    public double findMedian() {
        // 如果小堆顶数量大于大堆顶
        if (small.size() > large.size()) {
            return small.peek();
        } else if (large.size() > small.size()) {
            // 如果大堆顶数量大于小堆顶
            return large.peek();
        }

        // 两个堆的数量一致
        return (small.peek() + large.peek()) / 2.0;
    }

}
