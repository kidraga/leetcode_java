package leetcode.sol.P0078Subsets;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> selected = new ArrayList<>();
        backtrack(result, selected, nums, 0);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> selected, int[] nums, int startIndex) {
        result.add(new ArrayList<Integer>(selected)); // cannot add selected directly. Need to make copy
        if (selected.size() == nums.length) return; // we have used up all the numbers

        for (int i = startIndex; i < nums.length; i++) {
            if (selected.contains(nums[i])) continue;

            selected.add(nums[i]);
            backtrack(result, selected, nums, i + 1);
            selected.remove(selected.size() - 1);
        }
    }
}
