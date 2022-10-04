package leetcode.sol.P0740DeleteAndEarn;

import java.util.HashMap;
import java.util.Map;

/**
 * Top-down DP
 * Time: O(N + k). N: nums.length. k: max(nums)
 */
public class Solution {

    private Map<Integer, Integer> points = new HashMap<>();
    private Map<Integer, Integer> cache = new HashMap<>();

    public int deleteAndEarn(int[] nums) {
        int maxNumber = 0;

        // precompute how many points we gain from taking an element
        for (int num : nums) {
            points.put(num, points.getOrDefault(num, 0) + num);
            maxNumber = Math.max(maxNumber, num);
        }
        return maxPoints(maxNumber);
    }

    private int maxPoints(int num) {
        // base
        if (num == 0) {
            return 0;
        }
        if (num == 1) {
            return points.getOrDefault(1, 0);
        }

        if (cache.containsKey(num)) {
            return cache.get(num);
        }

        int gain = points.getOrDefault(num, 0);
        cache.put(num, Math.max(maxPoints(num - 1), maxPoints(num - 2) + gain));
        return cache.get(num);
    }
}
