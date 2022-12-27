package leetcode.sol.P0077Combinations;

import java.util.ArrayList;
import java.util.List;

class Solution {
    /**
     *
     * Time: O(k*C^k_n), C^k_n is the combination of k out of n = n! / (n-k)!k!
     * Space: O(C^k_n)
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> selected = new ArrayList<>();

        backtrack(n, k, result, selected, 1);
        return result;
    }

    private void backtrack(
            int n,
            int k,
            List<List<Integer>> result,
            List<Integer> selected,
            int start
    ) {
        // base
        if (selected.size() == k) {
            result.add(new ArrayList<>(selected));
            return;
        }

        for (int i = start; i <= n; i++) {


            selected.add(i);
            backtrack(n, k, result, selected, i + 1);
            selected.remove(selected.size() - 1);
        }
    }
}
