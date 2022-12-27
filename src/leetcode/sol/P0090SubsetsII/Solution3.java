package leetcode.sol.P0090SubsetsII;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution3 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> results = new ArrayList<>();
        results.add(new ArrayList<Integer>());

        int subsetSize = 0;

        for (int i = 0; i < nums.length; i++) {
           int startIndex = (i >= 1 && nums[i] == nums[i - 1]) ? subsetSize : 0;
           // subsetSize refers to the size of the subset in the previous step. This value also indicates the starting index of the subsets generated in this step.
           subsetSize = results.size();
           for (int j = startIndex; j < subsetSize; j++) {
               List<Integer> currentSubset = new ArrayList<>(results.get(j));
               currentSubset.add(nums[i]);
               results.add(currentSubset);
           }
        }
        return results;
    }
}
