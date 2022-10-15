package leetcode.sol.P0710RandomPickWithBlacklist;

import java.util.HashMap;
import java.util.Random;
import java.util.Map;

/**
 * https://leetcode.com/problems/random-pick-with-blacklist/solutions/144624/java-o-b-o-1-hashmap/
 */
public class Solution2 {
    int M;
    Random r;
    Map<Integer, Integer> map;

    public Solution2(int N, int[] blacklist) {
        map = new HashMap<>();
        for (int b : blacklist) {
            map.put(b, -1);
        }
        M = N - map.size();

        for (int b : blacklist) {
            if (b < M) {
                while (map.containsKey(N - 1)) {
                    N--;
                }
                map.put(b, N - 1);
                N--;
            }
        }
        r = new Random();
    }

    public int pick() {
        int p = r.nextInt(M);
        if (map.containsKey(p)) {
            return map.get(p);
        }
        return p;
    }
}
