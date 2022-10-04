package leetcode.sol.P0473MatchsticksToSquare;

import java.util.Arrays;
import java.util.List;

/**
 * TLE, but correct
 */
public class Solution {
    // public List<Integer> nums;
    public int[] matchsticks;
    public int[] sums = new int[4];
    public int sideLength;

    public boolean makesquare(int[] matchsticks) {
        // check empty
        if (matchsticks == null || matchsticks.length == 0) return false;

        // Calculate perimeter
        int perimeter = 0;
        for (int matchstick : matchsticks) {
            perimeter += matchstick;
        }

        // check if it's possible to create square at all
        if (perimeter % 4 != 0) return false;

        // sort matches
        Arrays.sort(matchsticks);
        // save length of side
        this.sideLength = perimeter / 4;
        this.matchsticks = matchsticks;

        return backtrack(0);
    }

    private boolean backtrack(int index) {
        if (index == matchsticks.length) {
            return sums[0] == sums[1]
                && sums[1] == sums[2]
                && sums[2] == sums[3];
        }

        // get current matchstick
        int matchstick = this.matchsticks[index];

        for (int i = 0; i < 4; i++) {
            if (this.sums[i] + matchstick <= this.sideLength) {
                this.sums[i] += matchstick;
                if (backtrack(index + 1)) return true;
                this.sums[i] -= matchstick;
            }
        }
        return false;
    }

}
