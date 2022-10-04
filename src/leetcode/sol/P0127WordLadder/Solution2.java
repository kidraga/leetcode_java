package leetcode.sol.P0127WordLadder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Solution2 {
    private Set<String> words;
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        words = new HashSet<>(wordList); // Space: O(M*N)
        if (!words.contains(endWord)) return 0;

        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        beginSet.add(beginWord);
        endSet.add(endWord);

        int level = 1;
        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            // keep begin set to be the smaller set, so we can check less
            if (beginSet.size() > endSet.size()) {
                Set<String> temp = beginSet;
                beginSet = endSet;
                endSet = temp;
            }

            Set<String> newBeginSet = new HashSet<>();
            for (String word : beginSet) {
                List<String> neighbors = getNeighborWords(word);
                for (String neighbor : neighbors) {
                    if (endSet.contains(neighbor)) return level + 1;
                    if (words.contains(neighbor)) {
                        newBeginSet.add(neighbor);
                        words.remove(neighbor);
                    }
                }
            }
            beginSet = newBeginSet;
            level++;
        }
        return 0;

    }

    // Time O(26*M^2) = O(M^2)
    private List<String> getNeighborWords(String word) {
        char[] chars = word.toCharArray();
        List<String> neighbors = new ArrayList<>();

        // change each letter in the word to all possible 26 letters,
        for (int i = 0; i < chars.length; i++) { // Time: O(M) M is word length
            char temp = chars[i]; // save the letter, will use it to restore the word
            for (char c = 'a'; c <= 'z'; c++) { // Time: O(26)
                chars[i] = c;
                String neighbor = new String(chars); // Time: O(M)
                neighbors.add(neighbor);
            }
            chars[i] = temp; // restore the word
        }
        return neighbors;
    }
}
