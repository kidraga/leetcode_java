package leetcode.sol.P1804ImplementTrieII;

import java.net.Proxy;

/**
 * 精简版
 */
class Trie2 {

    class TrieNode {
        int count = 0; // 每个node里存这个key被插入了多少次
        TrieNode[] children = new TrieNode[R];
    }

    private static final int R = 256;
    private TrieNode root = null;

    public void insert(String word) {
        root = put(root, word, 0);
    }

    // 以node为根, 插入key[i:]
    private TrieNode put(TrieNode node, String key, int i) {
        if (node == null) {
            node = new TrieNode();
        }
        if (i == key.length()) {
            node.count++;
            return node;
        }

        char c = key.charAt(i);
        node.children[c] = put(node.children[c], key, i + 1);
        return node;
    }

    public void erase(String word) {
        if (!containsKey(word)) {
            return;
        }
        root = remove(root, word, 0);
    }

    // 以node为根, 删除key[i:]
    private TrieNode remove(TrieNode node, String key, int i) {
        if (node == null || i > key.length()) {
            return null;
        }

        if (i == key.length()) {
            // 找到了key所指向的node
            node.count--;
        } else {
            // 还没找到, 继续往下一层找
            char c = key.charAt(i);
            node.children[c] = remove(node.children[c], key, i + 1);
        }

        // 后序位置
        // 需要考虑是否要把当前node删掉
        // node删掉需要满足一些条件:
        // 假如node的count为0了, 并且没有任意一个children了, 就可以删了
        // 单纯的count为0不能删, 因为key所在的path上的其他节点都有可能count为0
        // 例如"app"的count为0了, 但是"apple"可能count还不为0. 假如删除第二个p的node就不合理了
        if (node.count == 0) {
            for (TrieNode child : node.children) {
                if (child != null) return node;
            }
            return null;
        }
        // 如果count!=0, 那node就不能删
        return node;
        // 这一段可以写的更简洁一些, 懒得改了
    }

    private boolean containsKey(String word) {
        TrieNode node = getNode(root, word);
        return node != null;
    }

    private TrieNode getNode(TrieNode node, String key) {
        TrieNode p = node;
        for (int i = 0; i < key.length(); i++) {
            if (p == null) {
                return null;
            }
            char c = key.charAt(i);
            p = p.children[c];
        }
        return p;
    }

    public int countWordsEqualTo(String word) {
        TrieNode node = getNode(root, word);
        return node == null ? 0 : node.count;
    }

    public int countWordsStartingWith(String prefix) {
        // 找到prefix对应的node
        TrieNode node = getNode(root, prefix);
        // 以这个node为root, dfs求出所有子树的和
        return traverse(node);
    }

    private int traverse(TrieNode node) {
        if (node == null) return 0;
        int res = node.count;
        for (TrieNode child : node.children) {
            res += traverse(child);
        }
        return res;
    }
}
