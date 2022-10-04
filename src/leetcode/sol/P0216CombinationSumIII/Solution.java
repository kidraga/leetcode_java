package leetcode.sol.P0216CombinationSumIII;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> selected = new ArrayList<>();

        backtrack(result, selected, k, n, 1);
        return result;
    }

    private void backtrack(
            List<List<Integer>> result,
            List<Integer> selected,
            int k,
            int target,
            int start
    ) {
        // base
        if (selected.size() == k) {
            if (target == 0) {
                result.add(new ArrayList<>(selected));
            }
            return;
        }

        for (int i = start; i <= 9; i++) {
            if (selected.size() < k && i <= target) {
                selected.add(i);
                backtrack(result, selected, k, target - i, i + 1);
                selected.remove(selected.size() - 1);
            } else {
                break;
            }
        }
    }
}
