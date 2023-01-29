package leetcode.sol.P0648ReplaceWords;

import java.net.Proxy;
import java.util.List;

import leetcode.sol.util.TrieNode;

class Solution2 {

    class TrieNode {
        boolean isKey;
        TrieNode[] children = new TrieNode[26];
    }

    class TrieSet {

        private TrieNode root = new TrieNode();

        void put(String word) {
            root = put(root, word, 0);
        }

        private TrieNode put(TrieNode node, String key, int i) {
            if (node == null) {
                node = new TrieNode();
            }

            if (i == key.length()) {
                node.isKey = true;
                return node;
            }
            char c = key.charAt(i);
            node.children[c - 'a'] = put(node.children[c - 'a'], key, i + 1);
            return node;
        }

        String shortestPrefixOf(String key) {
            TrieNode p = root;
            for (int i = 0; i < key.length(); i++) {
                if (p == null) {
                    return "";
                }
                if (p.isKey) {
                    return key.substring(0, i);
                }
                char c = key.charAt(i);
                p = p.children[c - 'a'];
            }

            if (p.isKey) {
                return key;
            }
            return "";
        }

    }

    public String replaceWords(List<String> dictionary, String sentence) {
        // load dictionary to trie
        TrieSet trie = new TrieSet();
        for (String word : dictionary) {
            trie.put(word);
        }

        String[] wordsFromSentence = sentence.split("\\s+");
        String[] ans = new String[wordsFromSentence.length];

        for (int i = 0; i < wordsFromSentence.length; i++) {
            ans[i] = wordsFromSentence[i];
            String originalWord = wordsFromSentence[i];
            String shortestRoot = trie.shortestPrefixOf(originalWord);
            if (!shortestRoot.isEmpty()) ans[i] = shortestRoot;
        }

        StringBuilder sb = new StringBuilder();
        for (String word : ans) {
            sb.append(word);
            sb.append(" ");
        }
        sb.deleteCharAt(sb.length() - 1); // remove the last space
        return sb.toString();
    }
}
