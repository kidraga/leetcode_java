package leetcode.sol.P2279MaximumBagsWithFullCapacityOfRocks;
import java.util.Arrays;

class Solution {
    public int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
        int bagsNumber = capacity.length;
        int[] difference = new int[bagsNumber];
        for (int i = 0; i < bagsNumber; i++) {
            difference[i] = capacity[i] - rocks[i];
        }

        Arrays.sort(difference);
        for (int i = 0; i < bagsNumber; i++) {
            if (difference[i] <= additionalRocks && difference[i] != 0) {
                additionalRocks -= difference[i];
                difference[i] = 0;
            }
        }

        int result = 0;
        for (int diff : difference) {
            if (diff == 0) {
                result += 1;
            }
        }
        return result;
    }
}
