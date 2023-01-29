package leetcode.sol.util;

import java.util.LinkedList;
import java.util.List;

public class TrieMap<V> { // key是String, 或者说char, 所以泛型不会是<K, V>
    // ASCII码个数
    private static final int R = 256;
    // 当前存在Map中的键值对个数
    private int size = 0;

    private static class TrieNode<V> {
        V val = null;
        TrieNode<V>[] children = new TrieNode[R];
        // 这里有uncheck warning, 因为我们declare的事TrieNode<V>[], 就是宣称我们要存的类型是V
        // 但我们实例化的是TrieNode[], 是一个generic的TrieNode, 里面不一定是V
        // 我们需要手动保证所有可以往TrieNode里写入和取出的方法都必须操作的是V
    }

    // Trie树的根节点
    private TrieNode<V> root = null;

    /** 增/改 **/

    // 在map中添加或修改键值对
    // 这个key可能在树中完全不存在
    // 可能存在一部分prefix
    // 也可能已经存在但是值不相同
    // 我们需要把key的整个path都创建出来，沿路放上空的TrieNode
    // 然后在最后一个node把值存进去
    public void put(String key, V val) {
        if (!containsKey(key)) {
            // 新增键值对
            size++;
        }
        // 需要一个额外的辅助函数，并接受其返回值
        root = put(root, key, val, 0);
        // 这里会把root改写吗？不会的
    };

    // 定义：向以node为根的trie树中插入key[i..]，返回插入完成后的根节点
    private TrieNode<V> put(TrieNode<V> node, String key, V val, int i) {
        if (node == null) {
            // 如果树枝不存在，新建
            // 这一步是每一层都要做的，但不是每个新node里都要存值
            // 只有最后一层(i==key.length)的时候才往node里存值
            node = new TrieNode<>();
        }
        if (i == key.length()) {
            // key的路径已经插入完成了
            node.val = val;
            return node;
        }
        char c = key.charAt(i);
        // 递归插入子节点，并接收返回值
        node.children[c] = put(node.children[c], key, val, i + 1);
        return node;
    }

    /** 删 **/

    // 删除key以及对应的值
    public void remove(String key) {
        if (!containsKey(key)) {
            return;
        }
        // 递归修改数据结构要接受函数的返回值
        root = remove(root, key, 0);
        size--;
    };

    // 定义：在以node为根的trie树中删除key[i..]，返回删除后的根节点
    private TrieNode<V> remove(TrieNode<V> node, String key, int i) {
        if (node == null) {
            return null;
        }
        if (i == key.length()) {
            // 找到了key对应的TrieNode，删除val
            node.val = null;
        } else {
            char c = key.charAt(i);
            // 递归去子树进行删除
            node.children[c] = remove(node.children[c], key, i+1);
        }
        // 后序位置，递归路径上的节点可能需要被清理
        if (node.val != null) {
            // 如果这个TrieNode存储着val，不需要被清理
            return node;
        }
        // 检查这个TrieNode是否还有其他后缀
        for (int c = 0; c < R; c++) {
            if (node.children[c] != null) {
                // 只要存在一个子节点（后缀树枝），就不需要被清理
                return node;
            }
        }
        // 能到这里，说明key的路线上的子树也删了，同时也没有其他子树了。那么这个节点也可以删了
        return null;
    }

    /** 查 **/

    // 搜索key对应的值，不存在则返回null
    // get("the") -> 4
    // get("tha") -> null
    public V get(String key) {
        // 从root开始搜索key
        TrieNode<V> x = getNode(root, key);
        if (x == null || x.val == null) {
            // x为空或x的val字段为空都说明key没有对应的值
            return null;
        }
        return x.val;
    };

    // 从节点node开始搜索key，如果存在返回对应节点，否则返回null
    private TrieNode<V> getNode(TrieNode<V> node, String key) {
        TrieNode<V> p = node;
        // 从节点node开始搜索key
        for (int i = 0; i < key.length(); i++) {
            if (p == null) {
                // 无法向下搜索
                return null;
            }
            // 向下搜索
            char c = key.charAt(i);
            p = p.children[c];
        }
        return p;
    }

    // 判断key是否存在在map中
    // containsKey("tea") -> false
    // containsKey("team") -> true
    public boolean containsKey(String key) {
        return get(key) != null;
    };

    /** 常用API **/

    // 在map的所有key中搜索query的最短前缀
    // shortestPrefixOf("themxyz") -> "the"
    public String shortestPrefixOf(String query) {
        TrieNode<V> p = root;
        // 从节点node开始搜索key for (int i = 0; i < query.length(); i++) {
        for (int i = 0; i < query.length(); i++) {
            if (p == null) {
                // 无法向下搜索
                return "";
            }
            if (p.val != null) {
                // 找到一个键是query的前缀
                return query.substring(0, i);
            }
            // 向下搜索
            char c = query.charAt(i);
            p = p.children[c];

            // 注意，p是在上一个循环定义的，所以当for loop进行到最后一次的时候
            // 就是i指向query的最后一个字符，然后c和p都更新了
            // 此时我们退出了循环，然而p这个node里面的内容我们是没有check过的
            // 所以，退出循环之后我们需要额外再check一次
        }

        if (p != null && p.val != null) {
            // 说明query本身就是一个key
            return query;
        }
        return "";
    };

    // 在map的所有key中搜索query的最长前缀
    // longestPrefixOf("themxyz") -> "them"
    public String longestPrefixOf(String query) {
        TrieNode<V> p = root;
        // 记录前缀的最大长度
        int maxLen = 0;

        // 从节点node开始搜索key
        for (int i = 0; i < query.length(); i++) {
            if (p == null) {
                // 无法向下搜索
                return "";
            }
            if (p.val != null) {
                // 找到一个key是query的前缀，更新最大长度
                maxLen = i;
            }
            // 向下搜索
            char c = query.charAt(i);
            p = p.children[c];

            // 同样的，退出循环后需要额外check一下p
        }

        if (p != null && p.val != null) {
            // 说明query本身就是一个key
            return query;
        }
        return query.substring(0, maxLen);
    };

    // 搜索所有前缀为prefix的key
    // keysWithPrefix("th") -> ["that", "the", "them"]
    public List<String> keysWithPrefix(String prefix) {
        List<String> res = new LinkedList<>();
        // 找到匹配prefix在Trie树中的哪个节点
        TrieNode<V> x = getNode(root, prefix);
        if (x == null) {
            return res;
        }
        // DFS遍历以x为根的树，并把找到的所有key存到res里
        traverse(x, new StringBuilder(prefix), res);
        return res;
    };

    // 遍历以node节点为根的树，找到所有键
    private void traverse(TrieNode<V> node, StringBuilder path, List<String> res) {
        if (node == null) {
            // 到达Trie树底部叶子节点
            return;
        }

        if (node.val != null) {
            // 找到一个key，放入结果里
            res.add(path.toString());
        }

        // backtracking遍历
        for (char c = 0; c < R; c++) { // R就是所有ASCII码的个数，这里是256.
            // 做选择
            path.append(c);
            traverse(node.children[c], path, res);
            // 撤销选择
            path.deleteCharAt(path.length() - 1);
        }
    }

    // 判断是否存在前缀为prefix的key
    // hasKeyWithPrefix("tha") -> true
    // hasKeyWithPrefix("apple") -> false
    public boolean hasKeyWithPrefix(String prefix) {
        // 只要能找到prefix对应的节点，就是存在前缀
        return getNode(root, prefix) != null;
    };

    // 通配符"."匹配任意字符，搜索所有匹配的key
    // keysWithPattern("t.a.") -> ["team", "that"]
    public List<String> keysWithPattern(String pattern) {
        List<String> res = new LinkedList<>();
        traverse(root, new StringBuilder(), pattern, 0, res);
        return res;
    };

    // 遍历函数，尝试在"以node为根的trie树中"匹配pattern[i..]
    private void traverse(TrieNode<V> node, StringBuilder path, String pattern, int i, List<String> res) {
        if (node == null) {
            // 树枝不存在，匹配失败
            return;
        }
        if (i == pattern.length()) {
            // pattern匹配完成
            if (node.val != null) {
                // 如果这个节点存储着val，则找到一个匹配的键
                res.add(path.toString());
            }
            return;
        }
        char c = pattern.charAt(i);
        if (c == '.') {
            // pattern[i]是通配符，可以变化成任意字符
            // 多叉树backtracking遍历
            for (char j = 0; j < R; j++) {
                path.append(j);
                traverse(node.children[j], path, pattern, i + 1, res);
                path.deleteCharAt(path.length() - 1);
            }
        } else {
            // pattern[i]是普通字符
            path.append(c);
            traverse(node.children[c], path, pattern, i + 1, res);
            path.deleteCharAt(path.length() - 1);
        }
    }

    // 通配符"."匹配任意字符，判断是否存在匹配的key
    // hasKeyWithPattern(".ip") -> true
    // hasKeyWithPattern(".i") -> false
    public boolean hasKeyWithPattern(String pattern) {
        // 从root节点开始匹配pattern[0...]
        return hasKeyWithPattern(root, pattern, 0);
    };

    // 函数定义：从node节点开始匹配pattern[i..]，返回是否能成功匹配
    private boolean hasKeyWithPattern(TrieNode<V> node, String pattern, int i) {
        if (node == null) {
            // 树枝不存在，即匹配失败
            return false;
        }
        if (i == pattern.length()) {
            // 模式串走到尽头了，看看匹配到的是否是一个键
            return node.val != null;
        }
        char c = pattern.charAt(i);
        // 不是通配符
        if (c != '.') {
            // 从node.children[c]节点开始匹配剩下的串pattern[i+1..]
            return hasKeyWithPattern(node.children[c], pattern, i + 1);
        }
        // 遇到通配符
        for (int j = 0; j < R; j++) {
            // pattern[i]可以变化成任意字符，尝试所有可能，只要有一个成功就可以返回true
            if (hasKeyWithPattern(node.children[j], pattern, i+1)) {
                return true;
            }
        }
        // 完全没有匹配
        return false;
    }

    // 返回map中键值对的数量
    public int size() {
        return size;
    };

}
