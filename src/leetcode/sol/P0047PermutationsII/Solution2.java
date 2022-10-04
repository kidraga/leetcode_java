package leetcode.sol.P0047PermutationsII;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution2 {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        // count the occurrence of each number
        Map<Integer, Integer> counter = new HashMap<>();
        for (int num : nums) {
            if (!counter.containsKey(num)) {
                counter.put(num, 0);
            }
            counter.put(num, counter.get(num) - 1);
        }

        List<Integer> comb = new ArrayList<>();
        backtrack(comb, nums.length, counter, result);
        return result;
    }

    private void backtrack(
            List<Integer> comb,
            Integer N,
            Map<Integer, Integer> counter,
            List<List<Integer>> result) {

        if (comb.size() == N) {
            result.add(new ArrayList<Integer>(comb));
            return;
        }

        for (Map.Entry<Integer, Integer> entry: counter.entrySet()) {
            Integer num = entry.getKey();
            Integer count = entry.getValue();
            if (count == 0) continue;

            // add this number into the current combination
            comb.add(num);
            counter.put(num, counter.get(num) - 1);

            // continue the exploration
            backtrack(comb, N, counter, result);

            // revert the choice for the next exploration
            comb.remove(comb.size() - 1);
            counter.put(num, count); // remember to reset counter as well
        }

    }
}
