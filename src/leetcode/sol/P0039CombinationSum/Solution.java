package leetcode.sol.P0039CombinationSum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> selected = new ArrayList<>();

        Arrays.sort(candidates);
        backtrack(result, selected, candidates, target, 0);
        return result;
    }

    private void backtrack(
            List<List<Integer>> result,
            List<Integer> selected,
            int[] candidates,
            int target,
            int start
    ) {

        // base
        if (target == 0) { // sum of selected is target
            result.add(new ArrayList<>(selected));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] <= target) {
                selected.add(candidates[i]);
                backtrack(result, selected, candidates, target - candidates[i], i);
                selected.remove(selected.size() - 1);
            } else {
                break;
            }
        }
    }
}
