package leetcode.sol.P0208ImplementTrie;

import leetcode.sol.util.TrieSet;

public class Trie {
    // 直接封装TrieSet
    TrieSet set = new TrieSet();

    public void insert(String word) {
        set.add(word);
    }

    public boolean search(String word) {
        return set.contains(word);

    }

    public boolean startsWith(String prefix) {
        return set.hasKeyWithPrefix(prefix);
    }
}
