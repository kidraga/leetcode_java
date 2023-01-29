package leetcode.sol.P0211DesignAddAndSearchWordsDataStructure;

class WordDictionary2 {

    class TrieNode {
        boolean valid; // 如果有word的话这个值就是true
        TrieNode[] children = new TrieNode[R];
    }
    private static int R = 26;
    private TrieNode root = null;

    public void addWord(String word) {
        root = put(root, word, 0);
        return;
    }

    private TrieNode put(TrieNode node, String key, int i) {
        if (node == null) {
            node = new TrieNode();
        }

        if (i == key.length()) {
            node.valid = true;
            return node;
        }
        int c = key.charAt(i) - 'a';
        node.children[c] = put(node.children[c], key, i + 1);
        return node;
    }

    public boolean search(String word) {
        return hasKeyWithPattern(root, word, 0);
    }

    private boolean hasKeyWithPattern(TrieNode node, String pattern, int i) {
        if (node == null) return false;

        if (i == pattern.length()) {
            // 已经到pattern的最后了, 只要看node里是否为true即可
            return node.valid;
        }
        // 还没有走到头, 那么有两种情况: 下一个字符是通配符, 或者普通字符
        char c = pattern.charAt(i);
        // 如果是通配符, 则要检查所有的children
        if (c == '.') {
            for (int j = 0; j < R; j++) {
                if (hasKeyWithPattern(node.children[j], pattern, i + 1)) {
                    return true;
                }
            }
            return false;
        } else {
            return hasKeyWithPattern(node.children[c - 'a'], pattern, i + 1);
        }
    }

}
