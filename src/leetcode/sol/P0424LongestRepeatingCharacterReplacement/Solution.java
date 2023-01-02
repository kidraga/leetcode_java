package leetcode.sol.P0424LongestRepeatingCharacterReplacement;

class Solution {
    public int characterReplacement(String s, int k) {

        int left = 0;
        int maxCount = 0;
        int[] window = new int[26];
        int maxLen = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            window[c - 'A']++;
            maxCount = Math.max(maxCount, window[c - 'A']);

            while (right - left + 1 - maxCount > k) {
                char d = s.charAt(left);
                window[d - 'A']--;
                maxCount = Math.max(maxCount, window[d - 'A']);
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
}
