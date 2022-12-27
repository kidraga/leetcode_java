package leetcode.sol.P0126WordLadderII;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Solution {

    private Map<String, List<String>> adjList = new HashMap<>();
    private List<List<String>> result = new ArrayList<>();
    private List<String> currPath = new ArrayList<>();
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        // copy the words into the set for efficient deletion in BFS
        Set<String> copiedWordList = new HashSet<>(wordList);
        // build the adjList graph using BFS
        bfs(beginWord, endWord, copiedWordList);

        currPath.add(beginWord);

        backtrack(beginWord, endWord);
        return result;
    }

    /**
     * build the adjList graph
     */
    private void bfs(String beginWord, String endWord, Set<String> wordSet) {
        Deque<String> queue = new LinkedList<>();
        queue.add(beginWord);

        if (wordSet.contains(beginWord)) {
            wordSet.remove(beginWord);
        }

        // keep record of the words already put in the bfs queue
        // otherwise we will traval between two neighbors since getNeighbors will return all neighbors
        // e.g. bot -> cot, then cot -> bot
        Set<String> wordsAlreadyEnqueued = new HashSet<>();
        wordsAlreadyEnqueued.add(beginWord);

        boolean foundShortestPath = false;
        while (!queue.isEmpty() && !foundShortestPath) {

            // save node in a layer
            List<String> visited = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                if (word.equals(endWord)) {
                    foundShortestPath = true;
                }
                List<String> neighbors = getNeighbors(word, wordSet);
                for (String neighbor : neighbors) {
                    visited.add(neighbor);

                    // update adjList map
                    if (!adjList.containsKey(word)) {
                        adjList.put(word, new ArrayList<String>());
                    }
                    adjList.get(word).add(neighbor);

                    // avoid add word back to queue again
                    if (!wordsAlreadyEnqueued.contains(neighbor)) {
                        queue.add(neighbor);
                        wordsAlreadyEnqueued.add(neighbor);
                    }
                }
            }

            for (int i = 0; i < visited.size(); i++) {
                if (wordSet.contains(visited.get(i))) {
                    wordSet.remove(visited.get(i));
                }
            }
        }
    }

    private void backtrack(String source, String destination) {
        // base
        // store the path if we reached the end of the path
        if (source.equals(destination)) {
            List<String> temp = new ArrayList<>(currPath);
            result.add(temp);
        }

        // if no neighbor, return
        if (!adjList.containsKey(source)) {
            return;
        }

        for (int i = 0; i < adjList.get(source).size(); i++) {
            String selectedNeighbor = adjList.get(source).get(i);
            currPath.add(selectedNeighbor);
            backtrack(selectedNeighbor, destination);
            currPath.remove(currPath.size() - 1);
        }
    }

    private List<String> getNeighbors(String word, Set<String> wordSet) {
        List<String> neighbors = new ArrayList<>();
        char charList[] = word.toCharArray();
        for (int i = 0; i < word.length(); i++) {
            char oldChar = charList[i];

            for (char c = 'a'; c <= 'z'; c++) {
                charList[i] = c;
                if (c == oldChar || !wordSet.contains(String.valueOf(charList))) {
                    continue;
                }
                neighbors.add(String.valueOf(charList));
            }
            charList[i] = oldChar;
        }
        return neighbors;
    }
}
