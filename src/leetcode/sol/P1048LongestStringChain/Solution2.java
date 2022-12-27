package leetcode.sol.P1048LongestStringChain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution2 {
    public int longestStrChain(String[] words) {
        Map<String, Integer> dp = new HashMap<>();

        // Sorting the list in terms of the word length
        Arrays.sort(words, (a, b) -> a.length() - b.length()); // O(NlogN)

        int longestWordSequenceLength = 1;

        for (String word : words) { // O(N)
            int presentLength = 1;
            // find all possible predecessors for the current word by removing one letter at a time
            for (int i = 0; i < word.length(); i++) { // O(L^2) in worst case
                StringBuilder temp = new StringBuilder(word);
                temp.deleteCharAt(i);
                String predecessor = temp.toString();
                int previousLength = dp.getOrDefault(predecessor, 0);
                presentLength = Math.max(presentLength, previousLength + 1);
            }
            dp.put(word, presentLength);
            longestWordSequenceLength = Math.max(longestWordSequenceLength, presentLength);
        }
        return longestWordSequenceLength;
    }
}
