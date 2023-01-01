package leetcode.sol.P0992SubarraysWithKDifferentIntegers;

import java.util.HashMap;
import java.util.Map;

/**
 * Brute force
 * Time limit exceeded
 */
class Solution {
    public int subarraysWithKDistinct(int[] nums, int k) {
        int n = nums.length;
        int res = 0;

        for (int i = 0; i < n; i++) {
            int left = i;
            int right = left;
            Map<Integer, Integer> window = new HashMap<>();
            while (right < n && window.size() <= k) {
                int num = nums[right];
                window.put(num, window.getOrDefault(num, 0) + 1);
                if (window.size() == k) {
                    res++;
                }
                right++;
            }
        }
        return res;
    }
}
