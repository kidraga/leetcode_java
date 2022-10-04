package leetcode.sol.P0046Permutations;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        backtrack(path, nums, result);
        return result;
    }

    private void backtrack(List<Integer> path, int[] nums, List<List<Integer>> result) {

        if (path.size() == nums.length) {
            result.add(new ArrayList<Integer>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (path.contains(num)) continue;

            path.add(num);
            backtrack(path, nums, result);
            path.remove(path.size() - 1);
        }
    }
}


