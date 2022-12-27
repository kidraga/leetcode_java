package leetcode.sol.P0090SubsetsII;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution2 {
    /**
     * Wrong answer
     * Failed test case: [4, 4, 4, 1, 4]
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> selected = new ArrayList<>();


        backtrack(result, selected, nums, 0);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> selected,
            int[] nums, int startIndex) {
        result.add(new ArrayList<Integer>(selected)); // 不能result.add(selected)
        if (selected.size() == nums.length) return;

        Set<Integer> seen = new HashSet<>();
        for (int i = startIndex; i < nums.length; i++) {

            // skip the element if seen
            if (seen.contains(nums[i])) continue;

            seen.add(nums[i]);
            selected.add(nums[i]);
            backtrack(result, selected, nums, i + 1);

            // restore
            selected.remove(selected.size() - 1);
        }
    }
}
