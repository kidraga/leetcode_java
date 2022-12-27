package leetcode.sol.P1335MinimumDifficultyOfAJobSchedule;

import java.util.Arrays;

class Solution {
    private int n;
    private int d;
    private int[] jobDifficulty;
    private int[] hardestJobRemaining = new int[n];
    private int[][] memo;
    public int minDifficulty(int[] jobDifficulty, int d) {
        n = jobDifficulty.length;
        // it's required that we do at least 1 job per day
        // if we cannot schedule at least one job per day,
        // it is impossible to create a schedule
        if (n < d) return -1;

        // precompute the hardest job from ith day to last day
        // this will be used on computing dp for the last day
        hardestJobRemaining = new int[n];
        int hardestJob = 0;
        for (int i = n - 1; i >= 0; i--) {
            hardestJob = Math.max(hardestJob, jobDifficulty[i]);
            hardestJobRemaining[i] = hardestJob;
        }

        // Initialize memo array with value of -1.
        memo = new int[n][d + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }

        this.d = d;
        this.jobDifficulty = jobDifficulty;
        return dp(0, 1);
    }

    private int dp(int i, int day) {
        // base
        // last day, we need to finish all remaining jobs
        if (day == d) {
            return hardestJobRemaining[i];
        }

        if (memo[i][day] == -1) {
            int best = Integer.MAX_VALUE;
            int hardest = 0;
            // iterate through the options and choose the best
            for (int j = i; j < n - (d - day); j++) {
                hardest = Math.max(hardest, jobDifficulty[j]);
                // recurrence relation (dp function)
                best = Math.min(best, hardest + dp(j + 1, day + 1));
            }
            memo[i][day] = best;
        }
        return memo[i][day];
    }
}
