package leetcode.sol.P0090SubsetsII;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    /**
     * Follow up of P78
     * 和47类似，sort来跳过duplicate eliment
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> selected = new ArrayList<>();

        // sort element. 后面会loop并且跳过相同的element
        // 所以需要相同的element排在一起
        Arrays.sort(nums);

        backtrack(result, selected, nums, 0);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> selected,
            int[] nums, int startIndex) {
        result.add(new ArrayList<Integer>(selected)); // 不能result.add(selected)
        if (selected.size() == nums.length) return;

        for (int i = startIndex; i < nums.length; i++) {
            // skip this element if:
            // we have seen this element before, or
            // it's the same element as previous one, and previous one is visited
            if (i != startIndex && nums[i] == nums[i-1]) {
                continue;
            }

            selected.add(nums[i]);
            backtrack(result, selected, nums, i + 1);

            // restore
            selected.remove(selected.size() - 1);
        }
    }
}
