package leetcode.sol.P0931MinimumFallingPathSum;

/**
 * Time: O(n*2)
 * Space: O(n*2)
 */
class Solution {
    public int minFallingPathSum(int[][] matrix) {
        /**
         * dp(i,j) = min(dp(i-1,j-1), dp(i-1,j), dp(i-1, j+1)) + matrix[i,j] with boundary check
         * dp(0,j) is base cases. dp(0,j) = matrix(0,j)
         *
         * min(dp(n,j)) is answer
         */

        int n = matrix.length;
        if (n == 1) return matrix[0][0];

        int[][] dp = new int[n][n];
        // base
        for (int i = 0; i < n; i++) {
            dp[0][i] = matrix[0][i];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int upperLeft = j - 1 < 0 ? Integer.MAX_VALUE : dp[i - 1][j - 1];
                int upper = dp[i - 1][j];
                int upperRight = j + 1 == n ? Integer.MAX_VALUE : dp[i - 1][j + 1];
                dp[i][j] = Math.min(Math.min(upperLeft, upper), upperRight) + matrix[i][j];
            }
        }

        int result = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            result = Math.min(result, dp[n - 1][i]);
        }
        return result;

    }
}
