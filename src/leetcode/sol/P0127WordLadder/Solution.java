package leetcode.sol.P0127WordLadder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * Time: O(M^2 * N)
 * Space: O(M*N)
 * M: length of a word
 * N: number of words in wordList
 */
class Solution {
    private Set<String> words;
    public int ladderLength (String beginWord, String endWord, List<String> wordList) {
        // queue for BFS
        Queue<String> queue = new LinkedList<>();
        // set of words for quick check
        words = new HashSet<>(wordList); // Space: O(M*N)
        words.remove(beginWord);
        // put first word to the queue
        queue.add(beginWord);
        // keep track of how many steps we have used
        int level = 0;

        // start the BFS
        while (!queue.isEmpty()) { // O(M^2 * N)
            int size = queue.size();
            // we have entered a new level, increase level number
            level++;
            // loop through all nodes in this level
            for (int i = 0; i < size; i++) { // in worst case the size is N, so time O(N), space O(M*N)
                String currentWord = queue.poll();
                // if we found the word, return the level.
                if (currentWord.equals(endWord)) return level;

                // if not found the word, put all next level node to the queue
                // calculate all neighbor words
                List<String> neighbors = getNeighborWords(currentWord); // O(M^2)
                for (String neighbor: neighbors) {
                    queue.add(neighbor);
                }
            }
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
                if (words.contains(neighbor)) {
                    words.remove(neighbor); // if we used a word before, we don't need to use it again
                    neighbors.add(neighbor);
                }
            }
            chars[i] = temp; // restore the word
        }
        return neighbors;
    }
}
