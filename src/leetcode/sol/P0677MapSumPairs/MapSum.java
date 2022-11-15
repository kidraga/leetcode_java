package leetcode.sol.P0677MapSumPairs;

import leetcode.sol.util.TrieMap;

import java.util.List;

public class MapSum {

    TrieMap<Integer> map = new TrieMap<>();

    public void insert(String key, int val) {
        map.put(key, val);
    }

    public int sum(String prefix) {
        List<String> keys = map.keysWithPrefix(prefix);
        int res = 0;
        for (String key : keys) {
            res += map.get(key);
        }
        return res;
    }
}
