package leetcode.sol.P1219PathWithMaximumGold;

class Solution {

    private int[][] moves = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    /**
     * https://leetcode.com/problems/path-with-maximum-gold/discuss/398388/C%2B%2BJavaPython-DFS-Backtracking-Clean-code-O(3k)
     * Time: O(3^k), k is number of cells
     */
    public int getMaximumGold(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int maxGold = 0;
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                maxGold = Math.max(maxGold, backtrack(grid, m, n, row, col));
            }
        }
        return maxGold;
    }

    private int backtrack(
            int[][] grid,
            int m,
            int n,
            int row,
            int col
    ) {
        // base
        if (row < 0 || row == m || col < 0 || col == n
                || grid[row][col] == 0) {
            return 0;
        }

        // will be used to restore the value
        int origin = grid[row][col];
        grid[row][col] = 0;
        int maxGold = 0;
        for (int[] move : moves) {
            maxGold = Math.max(maxGold, backtrack(grid, m, n, row + move[0], col + move[1]));
        }
        grid[row][col] = origin;
        return origin + maxGold;
    }
}
