package leetcode.sol.P0992SubarraysWithKDifferentIntegers;

import java.util.HashMap;
import java.util.Map;

class Solution2 {
    public int subarraysWithKDistinct(int[] nums, int k) {
        return atMost(nums, k) - atMost(nums, k - 1);
    }

    private int atMost(int[] nums, int k) {
        int res = 0, left = 0;
        Map<Integer, Integer> window = new HashMap<>();
        for (int right = 0; right < nums.length; right++) {
            window.put(nums[right], window.getOrDefault(nums[right], 0) + 1);

            while (window.size() > k) {
                if (window.get(nums[left]) == 1) {
                    window.remove(nums[left]);
                } else {
                    window.put(nums[left], window.get(nums[left]) - 1);
                }
                left++;
            }
            res += right - left + 1;
        }
        return res;
    }
}
