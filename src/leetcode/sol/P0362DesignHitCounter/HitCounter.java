package leetcode.sol.P0362DesignHitCounter;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 题目描述非常糟糕, 搞清楚题目描述之后是道很简单的题
 */
class HitCounter {

    private Queue<Integer> hits;

    public HitCounter() {
        hits = new LinkedList<>();
    }

    // O(1)
    public void hit(int timestamp) {
        hits.add(timestamp);
    }

    // O(n)
    public int getHits(int timestamp) {
        while (!hits.isEmpty()) {
            int diff = timestamp - hits.peek();
            if (diff >= 300) hits.remove();
            else break;
        }
        return hits.size();
    }
}
