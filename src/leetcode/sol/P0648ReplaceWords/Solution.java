package leetcode.sol.P0648ReplaceWords;

import java.util.List;

import leetcode.sol.util.TrieMap;
import leetcode.sol.util.TrieSet;

class Solution {


    public String replaceWords(List<String> dictionary, String sentence) {
        // load dictionary to TrieMap
        TrieSet<String> trieSet = new TrieSet<>();
        for (String word : dictionary) {
            trieSet.add(word);
        }

        String[] wordsFromSentence = sentence.split("\\+", -1);
        String[] ans = new String[wordsFromSentence.length];

        for (int i = 0; i < wordsFromSentence.length; i++) {
            ans[i] = wordsFromSentence[i];
            String originalWord = wordsFromSentence[i];
            String shortestRoot = trieSet.shortestPrefixOf(originalWord);
            if (shortestRoot != null) {
                ans[i] = shortestRoot;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (String word : ans) {
            sb.append(word);
            sb.append(" ");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
