package leetcode.sol.P0211DesignAddAndSearchWordsDataStructure;

import leetcode.sol.util.TrieSet;

class WordDictionary {

    TrieSet set = new TrieSet();

    public void addWord(String word) {
        set.add(word);
    }

    public boolean search(String word) {
        return set.hasKeyWithPattern(word);
    }
}
