package leetcode.sol.P0304RangeSumQuery2DImmutable;

class NumMatrix {
    private int[][] prefixSum;

    public NumMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        if (m == 0 || n == 0) return;

        prefixSum = new int[m+1][n+1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // calculate prefix sum of [0, 0, i, j]
                prefixSum[i][j] = prefixSum[i-1][j]
                        + prefixSum[i][j-1]
                        + matrix[i-1][j-1]
                        - prefixSum[i-1][j-1];
            }
        }

    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return prefixSum[row2+1][col2+1]
                - prefixSum[row1][col2+1]
                - prefixSum[row2+1][col1]
                + prefixSum[row1][col1];
    }
}
