package leetcode.sol.P0140WordBreakII;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {

    private List<String> sentences;

    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> words = new HashSet<>(wordDict);
        sentences = new ArrayList<>();

        backtrack(words, s, 0, "");
        return sentences;
    }

    private void backtrack(Set<String> words, String s, int i, String sentence) {
        if (i == s.length()) {
            sentences.add(sentence);
            return;
        }

        for (int j = i; j < s.length(); j++) {
            String word = s.substring(i, j + 1);
            if (words.contains(word)) {
                if (sentence.length() == 0) {
                    backtrack(words, s, j + 1, sentence + word);
                } else {
                    backtrack(words, s, j + 1, sentence + " " + word);
                }
            }
        }
    }
}
