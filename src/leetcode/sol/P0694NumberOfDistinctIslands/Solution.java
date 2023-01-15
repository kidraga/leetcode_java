package leetcode.sol.P0694NumberOfDistinctIslands;

import java.util.HashSet;
import java.util.Set;

/**
 * 思路: 需要分辨相同形状的岛,就是需要分辨unique.
 * 简单的分辨unique的办法就是某种形式的serialization.
 * 同时,遍历的顺序是一定的,所以相同形状的岛都会按固定的顺序遍历所包含的cell
 * 那么按遍历的顺序来serialize一个岛,相同形状的岛就会有相同的serialization.
 */
class Solution {
    private int m;
    private int n;
    public int numDistinctIslands(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        Set<String> islands = new HashSet<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    StringBuilder sb = new StringBuilder();
                    dfs(grid, i, j, i, j, sb);
                    islands.add(sb.toString());
                }
            }
        }
        return islands.size();
    }

    private void dfs(int[][] grid, int startI, int startJ, int i, int j, StringBuilder sb) {
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == 0) return;

        // 注意这里要用相对坐标
        sb.append(i - startI);
        sb.append(j - startJ);

        grid[i][j] = 0;

        dfs(grid, startI, startJ, i + 1, j, sb);
        dfs(grid, startI, startJ, i - 1, j, sb);
        dfs(grid, startI, startJ, i, j + 1, sb);
        dfs(grid, startI, startJ, i, j - 1, sb);
    }
}
