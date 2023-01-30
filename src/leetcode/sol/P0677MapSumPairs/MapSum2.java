package leetcode.sol.P0677MapSumPairs;


/**
 * https://labuladong.github.io/algo/di-yi-zhan-da78c/shou-ba-sh-daeca/qian-zhui--46e56/
 * 就是把之前几道题的套路的重新运用
 * 前面的写出来了这道题很容易
 */
class MapSum2 {

    class TrieNode {
        int val;
        TrieNode[] children = new TrieNode[26];
    }

    TrieNode root = null;

    public void insert(String key, int val) {
        root = put(root, key, val, 0);
    }

    private TrieNode put(TrieNode node, String key, int val, int i) {
        if (node == null) {
            node = new TrieNode();
        }

        if (i == key.length()) {
            node.val = val;
            return node;
        }

        char c = key.charAt(i);
        node.children[c - 'a'] = put(node.children[c - 'a'], key, val, i + 1);
        return node;
    }

    public int sum(String prefix) {
        TrieNode node = get(prefix);
        if (node == null) return 0;
        return traverseSum(node);
    }

    private TrieNode get(String prefix) {
        TrieNode p = root;
        for (int i = 0; i < prefix.length(); i++) {
            if (p == null) {
                return null;
            }
            char c = prefix.charAt(i);
            p = p.children[c - 'a'];
        }
        return p;
    }

    private int traverseSum(TrieNode node) {
        if (node == null) return 0;
        int sum = node.val;
        for (TrieNode child : node.children) {
            sum += traverseSum(child);
        }
        return sum;
    }
}
