package leetcode.sol.P1048LongestStringChain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Time: O(N + (L^2 * N)) = O(L^2 * N)
 * Space: O(N)
 */
public class Solution {
    public int longestStrChain(String[] words) {
        Set<String> wordsSet = new HashSet<>(Arrays.asList(words));
        Map<String, Integer> wordToLongestPath = new HashMap<>();

        int ans = 0;
        for (String word : words) { // O(N), N is number of words
            ans = Math.max(ans, dfs(wordsSet, wordToLongestPath, word));
        }
        return ans;
    }

    private int dfs(Set<String> wordsSet, Map<String, Integer> wordToLongestPath, String currentWord) {
        // if we have seen currentWord, return the length of the longest path directly
        if (wordToLongestPath.containsKey(currentWord)) {
            return wordToLongestPath.get(currentWord);
        }

        // starting from currentWord, calculate the longest word sequence
        int maxLength = 1;
        StringBuilder sb = new StringBuilder(currentWord);

        // create all possible strings by taking out one character at a time from currentWord
        for (int i = 0; i < currentWord.length(); i++) { // O(L), L is the max length of word
            sb.deleteCharAt(i);
            String newWord = sb.toString();
            // if the new word exist, do dfs and calculate its longest path
            if (wordsSet.contains(newWord)) {
                int currentLength = 1 + dfs(wordsSet, wordToLongestPath, newWord); // O(L)?
                maxLength = Math.max(maxLength, currentLength);
            }
            sb.insert(i, currentWord.charAt(i));
        }
        wordToLongestPath.put(currentWord, maxLength);
        return maxLength;
    }
}
