package leetcode.sol.P0347TopKFrequentElements;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * The problem guaranteed there's a unique answer, so this solution works.
 */
class Solution {

    public int[] topKFrequent(int[] nums, int k) {

        // 统计freq
        Map<Integer, Integer> valueToFrequency = new HashMap<>();
        for (int num : nums) {
            valueToFrequency.put(num, valueToFrequency.getOrDefault(num, 0) + 1);
        }

        // [value, freq]全部放入pq里排序
        // pq size = k. 排完剩下的就是答案
        PriorityQueue<int[]> valueToFrequencyQueue = new PriorityQueue<>(k, (a, b) -> (b[1] - a[1]));
        valueToFrequency.entrySet().stream()
            .forEach(entry -> {
                int[] pair = new int[]{entry.getKey(), entry.getValue()};
                valueToFrequencyQueue.add(pair);
            });


        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = valueToFrequencyQueue.poll()[0];
        }

        return ans;
    }
}
