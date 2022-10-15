package leetcode.sol.P0710RandomPickWithBlacklist;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Space: O(n). Memory Limit Exceeded.
 * This question is hard because it wants you to solve with O(1) space,
 * or at least O(blacklist size).
 */
public class Solution {

    int nClean;
    List<Integer> arr = new ArrayList<>();
    Random rand = new Random();
    public Solution(int n, int[] blacklist) {
        Set<Integer> blacklistSet = new HashSet<>();
        for (int num : blacklist) {
            blacklistSet.add(num);
        }

        for (int i = 0; i < n; i++) {
            if (!blacklistSet.contains(i)) {
                arr.add(i);
            }
        }
        nClean = arr.size();
    }

    public int pick() {
        return arr.get(rand.nextInt(nClean));
    }
}
