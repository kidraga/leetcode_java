package leetcode.sol.P1631PathWithMinimumEffort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

class State {
    // 矩阵中的一个位置
    int x;
    int y;
    int effortFromStart;
    public State(int x, int y, int effortFromStart) {
        this.x = x;
        this.y = y;
        this.effortFromStart = effortFromStart;
    }

}

class Solution {

    int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int minimumEffortPath(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        // 定义：从(0, 0)到(i, j)的最小体力消耗是effortTo[i][j]
        int[][] effortTo = new int[m][n];
        // dp table初始化为正无穷
        for (int i = 0; i < m; i++) {
            Arrays.fill(effortTo[i], Integer.MAX_VALUE);
        }
        // 起点到起点的最小消耗就是0
        effortTo[0][0] = 0;

        Queue<State> pq = new PriorityQueue<>((a, b) -> {
            return a.effortFromStart - b.effortFromStart;
        });

        // 从起点(0, 0)开始进行BFS
        pq.offer(new State(0, 0, 0));

        while (!pq.isEmpty()) {
            State curState = pq.poll();
            int curX = curState.x;
            int curY = curState.y;
            int curEffortFromStart = curState.effortFromStart;

            // 到达终点提前结束
            if (curX == m - 1 && curY == n - 1) {
                return curEffortFromStart;
            }

            // 到达当前node的effort比已知路线的effort还要大，没有再走下去的必要了
            if (curEffortFromStart > effortTo[curX][curY]) {
                continue;
            }

            // 将(curX, curY)相邻坐标装入队列
            for (int[] neighbor : adj(heights, curX, curY)) {
                int nextX = neighbor[0];
                int nextY = neighbor[1];
                // 计算(curX, curY)到(nextX, nextY)的effort
                int effortToNextNode = Math.max(
                        effortTo[curX][curY],
                        Math.abs(heights[curX][curY] - heights[nextX][nextY])
                );
                // 更新dp table
                if (effortTo[nextX][nextY] > effortToNextNode) {
                    effortTo[nextX][nextY] = effortToNextNode;
                    pq.offer(new State(nextX, nextY, effortToNextNode));
                }
            }
        }
        // 算法一定会提前结束，不会到达这里
        // 保险的话可以写return dp[m-1][n-1]
        return -1;
    }

    // 返回坐标(x, y)的上下左右相邻坐标
    private List<int[]> adj(int[][] matrix, int x, int y) {
        int m = matrix.length;
        int n = matrix[0].length;
        // 存储相邻节点
        List<int[]> neighbors = new ArrayList<>();
        for (int[] dir : dirs) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            if (nx >= m || nx < 0 || ny >= n || ny < 0) {
                continue;
            }
            neighbors.add(new int[]{nx, ny});
        }
        return neighbors;
    }
}
