package leetcode.sol.P0745PrefixAndSuffixSearch;


/**
 * Time: O(NK^2 + QK), N: number of words, K: max length of a word, Q: number of queries.
 * Space: O(NK^2).
 */

class TrieNode {
    TrieNode[] children;
    int index;
    public TrieNode() {
        children = new TrieNode[28];
        index = 0;
    }
}

class WordFilter2 {

    TrieNode trie;

    public WordFilter2(String[] words) {
        trie = new TrieNode();
        for (int wordIndex = 0; wordIndex < words.length; wordIndex++) {
            String word = words[wordIndex];
            for (int i = 0; i < word.length(); i++) {
                TrieNode cur = trie;
                cur.index = wordIndex;
                String newWord = word.substring(i) + "#" + word;
                // word="apple", index = 3
                // newWord = [apple#apple, pple#apple, ple#apple, le#apple, e#apple]. all nodes on path have trie.index=3
                // another word = "appxle", index 5
                // newWord = [appxle#appxle, ppxle#appxle, pxle#appxle, xle#appxle, le#appxle, e#appxle]. all nodes on path have trie.index=5
                for (char ch : newWord.toCharArray()) {
                    int k = (ch != '#') ? ch - 'a' : 27; // #当做第27个字母
                    if (cur.children[k]  == null) {
                        cur.children[k] = new TrieNode();
                    }
                    cur = cur.children[k];
                    cur.index = wordIndex;
                }
            }
        }

    }

    public int f(String pref, String suff) {
        TrieNode cur = trie;
        for (char letter : (suff + "#" + pref).toCharArray()) {
            // pref = ap, suff = le
            // query = le#ap
            // 最后一次更新这个path是appxle, 添加le#appxle时, 更新了最大的index是5. le#ap这个node的index也被更新成了5
            int k = (letter != '#') ? letter - 'a' : 27;
            if (cur.children[k] == null) return -1;
            cur = cur.children[k];
        }
        return cur.index;
    }
}
