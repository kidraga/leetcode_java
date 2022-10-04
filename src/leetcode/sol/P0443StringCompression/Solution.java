package leetcode.sol.P0443StringCompression;

public class Solution {
    public int compress(char[] chars) {
        int count = 0;
        int start = 0;
        int end = start;
        char currChar = chars[start];
        while (end < chars.length) {
            while (end < chars.length && currChar == chars[end]) {
                count++;
                end++;
            }

            if (count == 1) {
                chars[start] = currChar;
                start++;
                count = 0;
                if (end < chars.length) {
                    currChar = chars[end];
                }
            } else {
                chars[start] = currChar;
                start++;
                String countStr = String.valueOf(count);
                for (char digit : countStr.toCharArray()) {
                    chars[start] = digit;
                    start++;
                }
                count = 0;
                if (end < chars.length) {
                    currChar = chars[end];
                }
            }
        }
        return start;
    }
}
