package leetcode.sol.P1905CountSubIslands;

class Solution {
    private int m;
    private int n;
    private boolean isOneSub;
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        m = grid1.length;
        n = grid1[0].length;

        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid2[i][j] == 1) {
                    isOneSub = true;
                    isSubIsland(grid1, grid2, i, j);
                    if (isOneSub) ans++;
                }
            }
        }
        return ans;
    }

    private void isSubIsland(int[][] grid1, int[][] grid2, int i, int j) {
        if (i < 0 || i >= m || j < 0 || j >= n) return;

        if (grid2[i][j] == 0) return;
        if (grid2[i][j] == 1 && grid1[i][j] == 0) {
            // not sub island
            isOneSub = false;
        }
        grid2[i][j] = 0;
        isSubIsland(grid1, grid2, i + 1, j);
        isSubIsland(grid1, grid2, i - 1, j);
        isSubIsland(grid1, grid2, i, j + 1);
        isSubIsland(grid1, grid2, i, j - 1);
    }
}
