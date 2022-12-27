package leetcode.sol.P0047PermutationsII;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        final List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        Arrays.fill(used, false);
        Arrays.sort(nums);
        backtrack(path, nums, used, result);
        return result;
    }

    private void backtrack(List<Integer> path, int[] nums, boolean[] used, List<List<Integer>> result) {
        if (path.size() == nums.length) {
            result.add(new ArrayList<Integer>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            if (i > 0 && nums[i] == nums[i-1] && !used[i-1]) continue;
            used[i] = true;
            path.add(nums[i]);
            backtrack(path, nums, used, result);
            used[i] = false;
            path.remove(path.size() - 1);
        }
    }
}
