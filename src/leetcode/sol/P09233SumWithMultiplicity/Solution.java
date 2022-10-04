package leetcode.sol.P09233SumWithMultiplicity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Wrong answer
 * 没有考虑一种case，就是重复的element相加也可以等于target
 * 例如：[3,3,3], 9
 * [2,2,4], 8
 */
public class Solution {
    public int threeSumMulti(int[] arr, int target) {
        Arrays.sort(arr);
        Map<Integer, Integer> counter = new HashMap<>();
        for (int num : arr) {
            counter.put(num, counter.getOrDefault(num, 0) + 1);
        }

        List<Integer> dedupedAndSortedList = new ArrayList<>();
        Set<Integer> seen = new HashSet<>();
        for (int num: arr) {
            if (!seen.contains(num)) {
                dedupedAndSortedList.add(num);
            }
            seen.add(num);
        }

        int ans = 0;
        for (int i = 0; i < dedupedAndSortedList.size() - 2; i++) {
            int newTarget = target - dedupedAndSortedList.get(i);
            int left = i + 1;
            int right = dedupedAndSortedList.size() - 1;
            while (left < right) {
                int leftNum = dedupedAndSortedList.get(left);
                int rightNum = dedupedAndSortedList.get(right);
                if (leftNum + rightNum == newTarget) {
                    ans += counter.get(dedupedAndSortedList.get(i)) * counter.get(leftNum) * counter.get(rightNum);
                    left++;
                    right--;
                } else if (leftNum + rightNum > newTarget) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return ans;
    }
}
