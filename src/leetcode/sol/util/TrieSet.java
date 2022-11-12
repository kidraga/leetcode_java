package leetcode.sol.util;

import java.util.List;

public class TrieSet<V> {
    // 底层用一个TrieMap, 键就是TrieSet，值仅仅起到占位作用
    // 值的类型可以随便设置，这里参考Java标准库设置成Object
    private final TrieMap<Object> map = new TrieMap<>();

    /** 增 **/

    // 在集合中添加元素key
    public void add(String key) {
        map.put(key, new Object());
    }

    /** 删 **/

    // 从集合中删除元素key
    public void remove(String key) {
        map.remove(key);
    }

    /** 查 **/

    // 判断元素key是否存在集合中
    public boolean contains(String key) {
        return map.containsKey(key);
    }

    // 在集合中寻找query的最短前缀
    public String shortestPrefixOf(String query) {
        return map.shortestPrefixOf(query);
    }

    // 在集合中寻找query的最长前缀
    public String longestPrefixOf(String query) {
        return map.longestPrefixOf(query);
    }

    // 在集合中搜索前缀为prefix的所有元素
    public List<String> keysWithPrefix(String prefix) {
        return map.keysWithPrefix(prefix);
    }

    // 判断集合中是否存在前缀为prefix的元素
    public boolean hasKeyWithPrefix(String prefix) {
        return map.hasKeyWithPrefix(prefix);
    }

    // 通配符"."匹配任意字符，返回集合中匹配pattern的所有元素
    public List<String> keysWithPattern(String pattern) {
        return map.keysWithPattern(pattern);
    }

    // 通配符"."匹配任意字符，判断集合中是否存在匹配pattern的元素
    public boolean hasKeyWithPattern(String pattern) {
        return map.hasKeyWithPattern(pattern);
    }

    // 返回集合中元素的个数
    public int size() {
        return map.size();
    }
}
