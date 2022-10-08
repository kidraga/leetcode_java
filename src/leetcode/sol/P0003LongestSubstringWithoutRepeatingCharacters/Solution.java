package leetcode.sol.P0003LongestSubstringWithoutRepeatingCharacters;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> window = new HashMap<>();

        int left = 0;
        int right = 0;
        int res = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;

            // 更新窗口的数据
            window.put(c, window.getOrDefault(c, 0) + 1);

            // 判断窗口左侧是否需要收缩
            while (window.get(c) > 1) {
                char d = s.charAt(left);
                left++;
                // 更新窗口的数据
                window.put(d, window.get(d) - 1);
            }
            // 更新res
            res = Math.max(res, right - left);
        }
        return res;
    }
}
