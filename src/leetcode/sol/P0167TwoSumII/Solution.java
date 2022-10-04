package leetcode.sol.P0167TwoSumII;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int[] twoSum(int[] numbers, int target) {
        Map<Integer, Integer> complementToIndex = new HashMap<>();

        int[] result = new int[2];
        for (int i = 0; i < numbers.length; i++) {
            int num = numbers[i];
            if (complementToIndex.containsKey(num)) {
                int index1 = complementToIndex.get(num) + 1;
                int index2 = i + 1;
                result[0] = index1;
                result[1] = index2;
                return result;
            }

            int complement = target - num;
            complementToIndex.put(complement, i);
        }
        return result;
    }
}
