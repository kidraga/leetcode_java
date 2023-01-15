package leetcode.sol.P1020NumberOfEnclaves;

class Solution {
    private int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private int ans = 0;
    private int m;
    private int n;
    public int numEnclaves(int[][] grid) {
        m = grid.length;
        n = grid[0].length;

        // 左右两边的1变成0
        for (int i = 0; i < m; i++) {
            if (grid[i][0] == 1) flip(grid, i, 0, 0, 0);
            if (grid[i][n - 1] == 1) flip(grid, i, n - 1, 0, 0);
        }
        // 上下两边的1变成0
        for (int j = 0; j < n; j++) {
            if (grid[0][j] == 1) flip(grid, 0, j, 0, 0);
            if (grid[m - 1][j] == 1) flip(grid, m - 1, j, 0, 0);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    flip(grid, i, j, 0, 1);
                }
            }
        }
        return ans;
    }

    private void flip(int[][] grid, int i, int j, int flipTo, int increment) {
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == flipTo) return;

        grid[i][j] = flipTo;
        ans += increment;
        for (int[] d : dirs) {
            int nextI = i + d[0];
            int nextJ = j + d[1];
            flip(grid, nextI, nextJ, flipTo, increment);
        }
    }
}
