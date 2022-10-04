package leetcode.sol.P0276PaintFence;

/**
 * Time: O(n)
 * Space: O(n), can be optimized to O(1)
 */
public class Solution {
    public int numWays(int n , int k) {
        /* dp(i), at ith fence, the number of ways to paint this fence
           dp(n) is the answer
           at each fence we have 2 options: choose a different color, or choose the same color as before
           if we choose a different color, then we have k-1 colors to choose
           so we have (k-1) * dp(i-1) options in total
           The other option we can do is to choose a different color, so we only have 1 option
           BUT, when choosing the same color, we are not having 1 * dp(i-1) options!!!
           The dp(i-1) part is wrong, it will include all options that dp(i-1) and dp(i-2) were having same color
           we should have 1 * (at i-1, number of options that we choose a color that is different from i-2)
           we already know this when we calculate the first option:
           number of options if we choose diff color is: dp_diff(i) = (k-1)*dp(i-1)
           so number of options if we chose diff color at i-1 is: dp_diff(i) = (k-1)*dp(i-2).
           That is: dp(i) = dp_diff(i) + dp_same(i)
           dp(i) = (k-1)*dp(i-1) + 1*(k-1)*dp(i-2)
           We know dp(1) = k.
           We know dp(2) = k*k.
         */

        // if there's no post, there's no way to paint them
        if (n == 0) return 0;

        // if there is only one post, there are k ways to paint
        if (n == 1) return k;

        // if there are 2 posts, there are k*k ways
        if (n == 2) return k*k;

        int dp[] = new int[n+1];
        dp[1] = k;
        dp[2] = k*k;
        for (int i = 3; i <= n; i++) {
            dp[i] = (k - 1) * (dp[i - 1] + dp[i - 2]);
        }
        return dp[n];
    }
}
