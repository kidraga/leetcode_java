package leetcode.sol.P0827MakingALargeIsland;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 思路: 对每个岛编号,从碰见的第一个岛开始, dfs遍历岛的所有cell,并把所有cell的值都变成岛的index
 * 同时,求出岛的面积,存到int[] area里, index就是岛的编号
 * 完成后,重新遍历grid里所有为0的cell, 看看四个邻居的面积是多大,可以用它们的index来去重,
 * 4个邻居的面积和+1就是当前cell变成1后能连成的最大面积. 用一个数不停的更新这个值求出整个grid的最大就行.
 */
class Solution {

    int[] dr = new int[]{-1, 0, 1, 0};
    int[] dc = new int[]{0, -1, 0, 1};
    int[][] grid;
    int N;

    public int largestIsland(int[][] grid) {
        this.grid = grid;
        N = grid.length;

        // 每碰到一个岛,就给这个岛一个编号index
        // 遍历的同时求出面积
        // 因为原grid里已经用了0和1, 我们的编号就从2开始
        int index = 2;
        int[] area = new int[N*N + 2];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (grid[r][c] == 1) {
                    area[index] = dfs(r, c, index);
                    index++;
                }
            }
        }

        // 遍历所有的0
        // 找出4个邻居的面积,用邻居岛的index去重
        // 不停的更新最大面积sum(4个邻居岛面积)+1
        int ans = 0;
        for (int x : area) ans = Math.max(ans, x); // 最差的情况就是最大值是单个岛的最大面积. 为什么不是最大岛+1?因为有可能grid里没有0
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (grid[r][c] == 0) {
                    Set<Integer> seen = new HashSet<>();
                    for (Integer move : neighbors(r, c)) {
                        if (grid[move / N][move % N] > 1) {
                            seen.add(grid[move / N][move % N]);
                        }
                    }
                    int bns = 1;
                    for (int i : seen) bns += area[i];
                    ans = Math.max(ans, bns);
                }
            }
        }
        return ans;
    }

    private int dfs(int r, int c, int index) {
        int ans = 1;
        grid[r][c] = index;
        for (Integer move : neighbors(r, c)) {
            if (grid[move / N][move % N] == 1) {
                ans += dfs(move / N, move % N, index);
            }
        }
        return ans;
    }

    private List<Integer> neighbors(int r, int c) {
        List<Integer> ans = new ArrayList<>();
        for (int k = 0; k < 4; k++) {
            int nr = r + dr[k];
            int nc = c + dc[k];
            if (0 <= nr && nr < N && 0 <= nc && nc < N) {
                ans.add(nr * N +nc);
            }
        }
        return ans;
    }
}
