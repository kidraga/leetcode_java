package leetcode.sol.P0131PalindromePartitioning;

import java.util.ArrayList;
import java.util.List;

/**
 * Time: O(N*2^N). N = s.length
 */
class Solution {
    public List<List<String>> partition (String s) {
        List<List<String>> result = new ArrayList<>();
        backtrack(0, result, new ArrayList<String>(), s);
        return result;
    }

    private void backtrack(
            int start,
            List<List<String>> result,
            List<String> currentList,
            String s
    ) {
        if (start >= s.length()) {
            result.add(currentList);
        }

        for (int end = start; end < s.length(); end++) {
            if (isPalindrome(s, start, end)) {
                // add current substring to the currentList
                currentList.add(s.substring(start, end + 1));
                backtrack(end + 1, result, currentList, s);
                currentList.remove(currentList.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String s, int low, int high) {
        while (low < high) {
            if (s.charAt(low + 1) != s.charAt(high - 1)) {
                return false;
            }
        }
        return true;
    }
}
