package leetcode.sol.P1091ShortestPathInBinaryMatrix;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Overriding input can cause some problem
 * This is the method that we don't need to override
 * Instead, we have to maintain a "seen" set.
 * You want to ask interviewer that whether we could override values from input
 */
class Solution2 {
    private static final int[][] directions = 
        new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
    public int shortestPathBinaryMatrix(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) return -1;
        // check start and end must be 0
        if (grid[0][0] != 0 || grid[grid.length - 1][grid[0].length - 1] != 0) {
            return -1;
        }
        Deque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0, 0, 1}); // {row, col, distance}
        boolean[][] visited = new boolean[grid.length][grid[0].length]; // used as visited set
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int row = cell[0];
            int col = cell[1];
            int distance = cell[2];
            // check if it's target
            if (row == grid.length - 1 && col == grid[0].length - 1) {
                return distance;
            }
            for (int[] neighbour : getNeighbours(row, col, grid)) {
                int neighbourRow = neighbour[0];
                int neighbourCol = neighbour[1];
                if (visited[neighbourRow][neighbourCol]) {
                    continue;
                }
                visited[neighbourRow][neighbourCol] = true;
                queue.add(new int[]{neighbourRow, neighbourCol, distance + 1});
            }
        }
        return -1;

    }

    private List<int[]> getNeighbours(int row, int col, int[][] grid) {
        List<int[]> neighbours = new ArrayList<>();
        for (int i = 0; i < directions.length; i++) {
            int newRow = row + directions[i][0];
            int newCol = col + directions[i][1];
            if (newRow < 0 || newRow >= grid.length || newCol < 0 || newCol >= grid[0].length
                    || grid[newRow][newCol] != 0) {
                continue;
            }
            neighbours.add(new int[]{newRow, newCol});
        }
        return neighbours;
    }


}
