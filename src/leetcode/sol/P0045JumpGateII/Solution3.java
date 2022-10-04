package leetcode.sol.P0045JumpGateII;

public class Solution3 {
    /**
     * DP solution
     * Time: O(n)
     */
    public int jump(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];

        // base case: if we are at last position, zero step is needed.
        // Since int[] by default initialized all value as zero, we don't need this step
        // dp[n - 1]  = 0;

        // Since dp[n-1] is already resolved, we can start from dp[n-2]
        for (int i = n - 2; i >= 0; i--) {
            dp[i] = n; // initialized the worst case to be n step.
            for (int j = 1; j <= nums[i]; j++) {
                // j is the current steps we will try in this loop,
                // increment from 1 to nums[i]
                // we are at i position, with all positions behind i solved.

                // Note there's no point if we jump out of the array.
                // i.e. if (i + j) >= n. In that case dp[i + j] is out of scope.
                // we only need to calculate at max i + j = n - 1, which is dp[n-1] = 0.
                // if j can let us jump to n - 1 position, it means at current position we need 1 step to finish
                // i.e. dp[i] = 1 + dp[i + j] = 1 + dp[n-1] = 1.
                // there's no point of keep increase j and calculate more, even if j can be larger.
                if (i + j < n) {
                    dp[i] = Math.min(dp[i], 1 + dp[i + j]);
                }
            }
        }
        return dp[0];
    }
}
