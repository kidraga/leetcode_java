package leetcode.sol.P0076MinimumWindowSubstring;

import java.util.HashMap;
import java.util.Map;

/**
 * https://labuladong.github.io/algo/2/20/27/
 */
public class Solution {
    public String minWindow(String s, String t) {
        // 统计t里的char以及数量，char to count in t
        Map<Character, Integer> need = new HashMap<>();
        // 统计window里的char以及数量，char to count in window
        Map<Character, Integer> window = new HashMap<>();

        // go through t and update need
        for (char c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        int left = 0;
        int right = 0;
        // 记录window里有多少有效char。char出现在window里并且数量和need对上就算有效
        int valid = 0;

        int start = 0; // starting index of the return sub string
        int len = Integer.MAX_VALUE; // length of the final sub string
        while (right < s.length()) {
            // c是准备放入window的char
            char c = s.charAt(right);
            right++; // 增大window

            // 更新window内的信息
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                // 如果window里c的数量已经够了，valid就+1
                if (window.get(c).equals(need.get(c))) {
                    valid++;
                }
            }

            // 判断左侧窗口是否要收缩
            // 只有window里已经包含了所有的char时，我们才考虑移动左侧收缩窗口
            // 包含所有char的时候,valid == need.size
            while (valid == need.size()) {
                // 先更新当前的长度以及起点
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }

                // d是左侧准备拿出window的char
                char d = s.charAt(left);
                // 缩小窗口
                left++;
                // 如果拿出的char是一个目标char，需要更新window的统计信息
                if (need.containsKey(d)) {
                    if (window.get(d).equals(need.get(d))) {
                        valid--;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }
}
