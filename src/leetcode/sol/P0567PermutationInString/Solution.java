package leetcode.sol.P0567PermutationInString;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public boolean checkInclusion(String s1, String s2) {
        Map<Character, Integer> charToCount = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        for (char c : s1.toCharArray()) {
            charToCount.put(c, charToCount.getOrDefault(c, 0) + 1);
        }

        int left = 0;
        int right = 0;
        int valid = 0;
        while (right < s2.length()) {
            char c = s2.charAt(right);
            right++;

            // 进行窗口内数据的更新
            if (charToCount.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(charToCount.get(c))) {
                    valid++;
                }
            }

            // 判断左侧窗口是否需要收缩
            while (right - left >= s1.length()) {
                // 判断是否找到了合法的子串
                if (valid == charToCount.size()) {
                    return true;
                }

                char d = s2.charAt(left);
                left++;
                // 进行窗口内的数据更新
                if (charToCount.containsKey(d)) {
                    if (window.get(d).equals(charToCount.get(d))) {
                        valid--;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }
        }
        return false;
    }
}
