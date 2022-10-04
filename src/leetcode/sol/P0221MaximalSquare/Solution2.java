package leetcode.sol.P0221MaximalSquare;

public class Solution2 {
    public int maximalSquare(char[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] dp = new int[rows + 1][cols + 1];
        // dp(i,j) is defined as:
        // the side length of the maximum square whose bottom right corner is the cell with index (i, j)
        // in the original matrix.

        int maxsqlen = 0;
        // for convenience, we add an extra all zero column and row
        // outside of the actual dp table, to simplify the transition
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                if (matrix[i-1][j-1] == '1') {
                    dp[i][j] = Math.min(Math.min(dp[i][j-1], dp[i-1][j]), dp[i-1][j-1]) + 1;
                    maxsqlen = Math.max(maxsqlen, dp[i][j]);
                }
            }
        }
        return maxsqlen * maxsqlen;
    }
}
