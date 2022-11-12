package leetcode.sol.P1804ImplementTrieII;

import leetcode.sol.util.TrieMap;

public class Trie {

    private TrieMap<Integer> map = new TrieMap<>();

    // 插入word并记录次数
    public void insert(String word) {
        if (!map.containsKey(word)) {
            map.put(word, 1);
        } else {
            map.put(word, map.get(word) + 1);
        }
    }

    // 查询word插入次数
    public int countWordsEqualTo(String word) {
        if (!map.containsKey(word)) {
            return 0;
        }
        return map.get(word);
    }

    // 累加前缀为prefix的键的出入次数总和
    public int countWordsStartingWith(String prefix) {
        int res = 0;
        for (String key : map.keysWithPrefix(prefix)) {
            res += map.get(key);
        }
        return res;
    }

    // word的插入次数减一
    public void erase(String word) {
        int freq = map.get(word);
        if (freq == 1) {
            map.remove(word);
        } else {
            map.put(word, freq - 1);
        }
    }
}
