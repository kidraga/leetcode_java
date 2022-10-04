package leetcode.sol.P0740DeleteAndEarn;

import java.util.HashMap;
import java.util.Map;

public class Solution2 {
    public int deleteAndEarn(int[] nums) {
        Map<Integer, Integer> points = new HashMap<>();
        int maxNumber = 0;

        for (int num : nums) {
            points.put(num, points.getOrDefault(num, 0) + num);
            maxNumber = Math.max(maxNumber, num);
        }

        int[] maxPoints = new int[maxNumber + 1];
        // maxPoints[0] = 0; no need to write this line
        maxPoints[1] = points.getOrDefault(1, 0);

        for (int num = 2; num < maxPoints.length; num++) {
            int gain = points.getOrDefault(num, 0);
            maxPoints[num] = Math.max(maxPoints[num - 1], maxPoints[num - 2] + gain);
        }
        return maxPoints[maxNumber];
    }
}
