package leetcode.sol.P0745PrefixAndSuffixSearch;

import java.util.HashSet;
import java.util.Set;

/**
 * The question description is a little misleading. It says it want a "dictionary",
 * which makes you think of key-value pair (like python dict). But it really wants is a list.
 * You put words into the list, and index increases incrementally.
 * Then you get a word with a matching prefix and a surfix.
 * If there are multiple words that match, return the one with largest index.
 *
 */

class TrieNode {
    TrieNode[] children;
    Set<Integer> indices; // store the index of a key
    public TrieNode() {
        children = new TrieNode[26];
        indices = new HashSet<>();
    }
}

/**
 * TLE soltuion.
 * Time: O(NK + Q(N + K)), N is number of words, K is max length of a word, Q is number of query
 * Space: O(NK)
 */
class WordFilter {

    TrieNode prefixTrie;
    TrieNode suffixTrie;

    public WordFilter(String[] words) {
        prefixTrie = new TrieNode();
        suffixTrie = new TrieNode();
        int index = 0;
        for (String word : words) {
            char[] ca = word.toCharArray();

            TrieNode cur = prefixTrie;
            cur.indices.add(index); // store index at root as well
            for (char letter : ca) {
                // store index along all nodes in the path
                if (cur.children[letter - 'a'] == null) {
                    cur.children[letter - 'a'] = new TrieNode();
                }
                cur = cur.children[letter - 'a'];
                cur.indices.add(index);
            }

            cur = suffixTrie;
            cur.indices.add(index);
            for (int j = ca.length - 1; j >= 0; j--) {
                char letter = ca[j];
                if (cur.children[letter - 'a'] == null) {
                    cur.children[letter - 'a'] = new TrieNode();
                }
                cur = cur.children[letter - 'a'];
                cur.indices.add(index);
            }
            index++;
        }
    }

    public int f(String pref, String suff) {
        TrieNode cur1 = prefixTrie;
        TrieNode cur2 = suffixTrie;

        // get all indices with pref prefix
        for (char letter : pref.toCharArray()) {
            if (cur1.children[letter - 'a'] == null) return -1;
            cur1 = cur1.children[letter - 'a'];
        }

        // get all indices with suff as suffix
        char[] ca = suff.toCharArray();
        for (int j = ca.length - 1; j >= 0; j--) {
            char letter = ca[j];
            if (cur2.children[letter - 'a'] == null) return -1;
            cur2 = cur2.children[letter - 'a'];
        }

        // check indices in both sets
        // return the max index.
        int ans = -1;
        for (int index : cur1.indices) {
            if (index > ans && cur2.indices.contains(index)) {
                ans = index;
            }
        }
        return ans;

    }

}
