package leetcode.sol.P1254NumberOfClosedIslands;

class Solution {
    private int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private int m;
    private int n;
    public int closedIsland(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        int count = 0;

        // 贴边的land都不可能是closed
        // 全部改成1
        for (int i = 0; i < m; i++) {
            // 左右两边
            if (grid[i][0] == 0) flip(grid, i, 0, 1);
            if (grid[i][n - 1] == 0) flip(grid, i, n - 1, 1);
        }
        for (int j = 0; j < n; j++) {
            // 左右两边
            if (grid[0][j] == 0) flip(grid, 0, j, 1);
            if (grid[m - 1][j] == 0) flip(grid, m - 1, j, 1);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    count++;
                    flip(grid, i, j, 1);
                }
            }
        }
        return count;
    }

    private void flip(int[][] grid, int i, int j, int flipTo) {
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == flipTo) {
            return;
        }

        grid[i][j] = flipTo;
        for (int[] d :dirs) {
            int nextI = i + d[0];
            int nextJ = j + d[1];
            flip(grid, nextI, nextJ, flipTo);
        }
    }
}
