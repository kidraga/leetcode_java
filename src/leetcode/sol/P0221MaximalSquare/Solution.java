package leetcode.sol.P0221MaximalSquare;

/**
 * Wrong solution
 */
class Solution {
    private char[][] matrix;
    private int M;
    private int N;
    private int[][] memo;
    public int maximalSquare(char[][] matrix) {
        this.matrix = matrix;
        this.M = matrix.length;
        this.N = matrix[0].length;
        if (this.M == 0 || this.N == 0) {
            return 0;
        }
        memo = new int[this.M][this.N];

        // dp(i, j) = max square from position (i, j) to its right and bottom
        // if matrix(i, j) = 0, dp(i, j) = max(dp(i+1, j), dp(i, j+1))
        // if matrix(i, j) = 1, there are two cases:
        // if all num on i row are 1 and all num on j col are 1, then increase max square. (wrong)
        return dp(0, 0);
    }

    private int dp(int i, int j) {
        // base
        return 0;
    }
}
