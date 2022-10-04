package leetcode.sol.P0244ShortestWordDistanceII;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordDistance {
    private Map<String, List<Integer>> wordToIndex = new HashMap<>();

    public WordDistance(String[] wordsDict) {
        for (int i = 0; i < wordsDict.length; i++) {
            String word = wordsDict[i];
            if (!wordToIndex.containsKey(word)) {
                wordToIndex.put(word, new ArrayList<>());
            }
            wordToIndex.get(word).add(i);
        }
    }

    public int shortest(String word1, String word2) {
        List<Integer> word1Indices = wordToIndex.get(word1);
        List<Integer> word2Indices = wordToIndex.get(word2);

        int result = Integer.MAX_VALUE;
        for (int word1Index : word1Indices) {
            for (int word2Index : word2Indices) {
                result = Math.min(result, Math.abs(word1Index - word2Index));
            }
        }
        return result;
    }
}
